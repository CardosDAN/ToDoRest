<script>
    import axios from 'axios';
    import { Link } from 'svelte-routing';
    import {
        Card,
        Col,
        Row,
        Container,
        Badge,
        ButtonDropdown,
        DropdownItem,
        DropdownMenu,
        DropdownToggle,
    } from "sveltestrap";
    let task = [];
    let progress = 0;

    async function deleteTask(id) {
        const response = await fetch(`http://localhost:8080/api/tasks/delete/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            console.log('Task deleted');
            location.reload();
        } else {
            console.error('Error deleting task:', response.statusText);
        }
    }

    async function index() {
        const response = await axios.get('http://localhost:8080/api/tasks');
        task = response.data;
    }

    index();
</script>
<main>
    <Container class="mt-5 mb-3">
        <Row>
            {#each task as task}
                <Col md="4">
                    <Card class="c p-3 mb-2">
                        <div class="d-flex justify-content-between">
                            <Badge color="primary">Design</Badge>
                            <ButtonDropdown>
                                <DropdownToggle color="light">
                                    <i class="fa fa-ellipsis-v"></i>
                                </DropdownToggle>
                                <DropdownMenu>
                                    <DropdownItem>
                                        <Link to="/task/{task.id}">View</Link>
                                    </DropdownItem>
                                    <DropdownItem divider/>
                                    <DropdownItem>
                                        <Link to="/task/edit/{task.id}">Edit</Link>
                                    </DropdownItem>
                                    <DropdownItem divider/>
                                    <DropdownItem>
                                        <button on:click={() => deleteTask(task.id)}>Delete</button>
                                    </DropdownItem>
                                </DropdownMenu>
                            </ButtonDropdown>
                        </div>
                        <div class="mt-5">
                            <!--                            <div hidden="hidden">{fetchDueDateFromDatabase(task.id)}</div>-->
                            <h3 class="heading">{task.title}</h3>
                            <h3 class="heading">{task.category.id}</h3>
                            <div class="mt-5">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style={`width: ${progress}%`} aria-valuenow={progress} aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <div class="mt-3"><span class="text1">32 Applied <span
                                        class="text2">of 50 capacity</span></span></div>
                            </div>
                        </div>
                    </Card>
                </Col>
            {/each}
        </Row>
    </Container>
</main>

<style>
    body {
        background-color: #eee
    }

    .c {
        border: none;
        border-radius: 10px
    }

    .c-details span {
        font-weight: 300;
        font-size: 13px
    }

    .badge span {
        background-color: #fffbec;
        width: 60px;
        height: 25px;
        padding-bottom: 3px;
        border-radius: 5px;
        display: flex;
        color: #fed85d;
        justify-content: center;
        align-items: center
    }

    .progress {
        height: 10px;
        border-radius: 10px
    }

    .progress div {
        background-color: red
    }

    .text1 {
        font-size: 14px;
        font-weight: 600
    }

    .text2 {
        color: #a5aec0
    }
</style>
<script>
    // import axios from 'axios';
    // import {Link} from 'svelte-routing';
    // import {
    //     Card,
    //     Col,
    //     Row,
    //     Container,
    //     Badge,
    //     ButtonDropdown,
    //     DropdownItem,
    //     DropdownMenu,
    //     DropdownToggle,
    // } from "sveltestrap";

    import { onMount } from 'svelte'
    import { get, post } from '../../utils'

    let tasks = []
    let isAuthenticated = false

    const loadTasks = async () => {
        tasks = await get('tasks')
    }

    onMount(() => {
        isAuthenticated = !!localStorage.getItem('accessToken')
        loadTasks()
    })

    const login = async (event) => {
        event.preventDefault()
        const email = event.target.email.value
        const password = event.target.password.value
        const response = await post('login', { email, password })
        localStorage.setItem('accessToken', response.token)
        isAuthenticated = true
        await loadTasks()
        event.target.reset()
    }

    const logout = () => {
        localStorage.removeItem('accessToken')
        isAuthenticated = false
    }

    // let task = [];
    // let progress = 0;

    // async function deleteTask(id) {
    //     const response = await fetch(`http://localhost:8080/api/tasks/delete/${id}`, {
    //         method: 'DELETE'
    //     });
    //
    //     if (response.ok) {
    //         console.log('Task deleted');
    //         location.reload();
    //     } else {
    //         console.error('Error deleting task:', response.statusText);
    //     }
    // }

    // async function index() {
    //     let token;
    //     if (localStorage.getItem('token')) {
    //         token = localStorage.getItem('token');
    //     } else {
    //         token = sessionStorage.getItem('token');
    //     }
    //
    //     axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    //     const response = await axios.get('http://localhost:8080/api/tasks');
    //     task = response.data;
    // }

    // index();
</script>
<!--<main>-->
<!--    <Container class="mt-5 mb-3">-->
<!--        <Row>-->
<!--            {#each task as task}-->
<!--                <Col md="4">-->
<!--                    <Card class="c p-3 mb-2">-->
<!--                        <div class="d-flex justify-content-between">-->
<!--                            <Badge color="primary">Design</Badge>-->
<!--                            <ButtonDropdown>-->
<!--                                <DropdownToggle color="light">-->
<!--                                    <i class="fa fa-ellipsis-v"></i>-->
<!--                                </DropdownToggle>-->
<!--                                <DropdownMenu>-->
<!--                                    <DropdownItem>-->
<!--                                        <Link to="/task/{task.id}">View</Link>-->
<!--                                    </DropdownItem>-->
<!--                                    <DropdownItem divider/>-->
<!--                                    <DropdownItem>-->
<!--                                        <Link to="/task/edit/{task.id}">Edit</Link>-->
<!--                                    </DropdownItem>-->
<!--                                    <DropdownItem divider/>-->
<!--&lt;!&ndash;                                    <DropdownItem>&ndash;&gt;-->
<!--&lt;!&ndash;                                        <button on:click={() => deleteTask(task.id)}>Delete</button>&ndash;&gt;-->
<!--&lt;!&ndash;                                    </DropdownItem>&ndash;&gt;-->
<!--                                </DropdownMenu>-->
<!--                            </ButtonDropdown>-->
<!--                        </div>-->
<!--                        <div class="mt-5">-->
<!--                            <h3 class="heading">{task.title}</h3>-->
<!--                            <h3 class="heading">{task.category.id}</h3>-->
<!--                            <div class="mt-5">-->
<!--&lt;!&ndash;                                <div class="progress">&ndash;&gt;-->
<!--&lt;!&ndash;                                    <div class="progress-bar" role="progressbar" style={`width: ${progress}%`}&ndash;&gt;-->
<!--&lt;!&ndash;                                         aria-valuenow={progress} aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                                </div>&ndash;&gt;-->
<!--                                <div class="mt-3"><span class="text1">32 Applied <span-->
<!--                                        class="text2">of 50 capacity</span></span></div>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </Card>-->
<!--                </Col>-->
<!--            {/each}-->
<!--        </Row>-->
<!--    </Container>-->
<!--</main>-->


<main>
    {#if isAuthenticated}
        <button on:click={logout}>Logout</button>
        <ul>
            {#each tasks as task}
                <li>{task.description}</li>
            {/each}
        </ul>
    {:else}
        <form on:submit={login}>
            <input name="username" type="text" placeholder="Enter username" />
            <input name="password" type="password" placeholder="Enter password" />
            <button type="submit">Login</button>
        </form>
    {/if}
</main>

<!--<style>-->
<!--    body {-->
<!--        background-color: #eee-->
<!--    }-->

<!--    .c {-->
<!--        border: none;-->
<!--        border-radius: 10px-->
<!--    }-->

<!--    .c-details span {-->
<!--        font-weight: 300;-->
<!--        font-size: 13px-->
<!--    }-->

<!--    .badge span {-->
<!--        background-color: #fffbec;-->
<!--        width: 60px;-->
<!--        height: 25px;-->
<!--        padding-bottom: 3px;-->
<!--        border-radius: 5px;-->
<!--        display: flex;-->
<!--        color: #fed85d;-->
<!--        justify-content: center;-->
<!--        align-items: center-->
<!--    }-->

<!--    .progress {-->
<!--        height: 10px;-->
<!--        border-radius: 10px-->
<!--    }-->

<!--    .progress div {-->
<!--        background-color: red-->
<!--    }-->

<!--    .text1 {-->
<!--        font-size: 14px;-->
<!--        font-weight: 600-->
<!--    }-->

<!--    .text2 {-->
<!--        color: #a5aec0-->
<!--    }-->
<!--</style>-->

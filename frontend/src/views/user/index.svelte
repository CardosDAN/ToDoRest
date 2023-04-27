<script>
    import axios from "axios";
    import {Link} from "svelte-routing";
    import NavBar from "../components/NavBar.svelte";
    import {
        Col,
        Row,
        Container,
        Button,
        Table,
    } from "sveltestrap";

    let users = [];

    async function getUsers() {
        const response = await axios.get("http://localhost:8080/api/users");
        users = response.data;
    }

    async function deleteUser(id) {
        const response = await fetch(`http://localhost:8080/api/users/${id}`, {
            method: "DELETE",
        });
        if (response.ok) {
            // users = users.filter((user) => user.id !== id);
            console.log("User deleted");
            location.reload();
        } else {
            console.log("Error deleting user");
        }
    }

    getUsers();
</script>

<NavBar />
<main>
    <Container class="mt-5 mb-3">
        <Row>
            <Col>
                <h1>Users</h1>
            </Col>
            <Col class="text-right">
                <Link to="/user/create">
                    <Button color="primary">New User</Button>
                </Link>
            </Col>
        </Row>
        <Row>
            <Col>
                <Table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {#each users as user}
                        <tr>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.email}</td>
                            <td>
                                <Link to={`/user/edit/${user.id}`}>
                                    <Button color="primary">Edit</Button>
                                </Link>
                                <Button
                                    color="danger"
                                    on:click={() => deleteUser(user.id)}
                                >
                                    Delete
                                </Button>
                            </td>
                        </tr>
                        {/each}
                    </tbody>
                </Table>
            </Col>
        </Row>
    </Container>
</main>
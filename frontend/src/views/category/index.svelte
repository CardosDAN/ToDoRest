<script>
    import axios from "axios";
    import {Link} from 'svelte-routing';
    import NavBar from "../components/NavBar.svelte";
    import {
        Col,
        Row,
        Container,
        Button,
        Table,
    } from "sveltestrap";

    let categories = [];

    async function getCategories() {
        const response = await axios.get("http://localhost:8080/api/categories");
        categories = response.data;
    }

    async function deleteCategory(id) {
        const response = await fetch(`http://localhost:8080/api/categories/delete/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            console.log('Category deleted');
            location.reload();
        } else {
            console.error('Error deleting category:', response.statusText);
        }

    }

    getCategories();
</script>

<NavBar/>
<main>
    <Container class="mt-5 mb-3">
        <Row>
            <Col>
                <h1>Categories</h1>
            </Col>
            <Col class="text-right">
                <Link to="/category/create">
                    <Button color="primary">Create</Button>
                </Link>
            </Col>
        </Row>
        <Row>
            <Col>
                <Table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {#each categories as category}
                        <tr>
                            <td>{category.name}</td>
                            <td>
                                <Link to="/category/edit/{category.id}">
                                    <Button color="primary">Edit</Button>
                                </Link>
                                <Button on:click={() => deleteCategory(category.id)} color="danger">Delete</Button>
                            </td>
                        </tr>
                    {/each}
                    </tbody>
                </Table>
            </Col>
        </Row>
    </Container>
</main>
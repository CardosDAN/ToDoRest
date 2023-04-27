<script>
    import Navbar from "../components/Navbar.svelte";
    import {Container} from "sveltestrap";
    import {onMount} from "svelte";

    let category = {
        name: "",
    }

    const url = new URL(window.location.href);
    const id = url.pathname.split('/').pop();

    onMount(async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/categories/${id}`);
            if (response.ok) {
                category = await response.json();
            } else {
                console.log('Error fetching category: ', response.statusText);
            }
        } catch (error) {
            console.log('Error fetching category: ', error);
        }
    })


    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch(`http://localhost:8080/api/categories/edit/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(category),
        });

        console.log(category, response.statusText);

        if (response.ok) {
            console.log('Category updated');
        } else {
            console.log('Error updating category', response.statusText);
        }
    }

</script>

<Navbar/>

<Container>
    <h1>Edit Category</h1>
    <form on:submit={handleSubmit}>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" bind:value={category.name} class="form-control" id="name" placeholder="Enter name">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</Container>

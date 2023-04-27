<script>
    import Navbar from "../components/Navbar.svelte";
    import { Container } from "sveltestrap";

    let category = {
        name: '',
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch('http://localhost:8080/api/categories', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(category),
        });
        console.log('Request body', JSON.stringify(category));

        if (response.ok) {
            category.name = '';
        } else {
            console.log('Error adding category', response.statusText);
        }
    }

    export {
        category,
        handleSubmit,
    }

</script>

<Navbar />
<Container>
    <h1>Add Category</h1>
    <form on:submit={handleSubmit}>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" bind:value={category.name} class="form-control" id="name" placeholder="Enter name" />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</Container>

<script>
    import Navbar from "../components/Navbar.svelte";
    import {Container} from "sveltestrap";
    import {onMount} from "svelte";
    import user from "./index.svelte";

    let category = {
        name: "",
    }

    const url = new URL(window.location.href);
    const id = url.pathname.split('/').pop();

    onMount(async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/users/${id}`);
            if (response.ok) {
                category = await response.json();
            } else {
                console.log('Error fetching user: ', response.statusText);
            }
        } catch (error) {
            console.log('Error fetching user: ', error);
        }
    })

    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch(`http://localhost:8080/api/users/edit/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(category),
        });

        console.log(user, response.statusText);

        if (response.ok) {
            console.log('User updated');
        } else {
            console.log('Error updating user: ', response.statusText);
        }

    }
</script>

<Navbar />

<Container>
    <h1>Edit User</h1>
    <form on:submit={handleSubmit}>
        <div class="form-group">
            <label for="name">Username</label>
            <input type="text" bind:value={category.username} class="form-control" id="name" placeholder="Enter name" />
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" bind:value={category.email} class="form-control" id="email" placeholder="Enter email" />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="text" bind:value={category.password} class="form-control" id="password" placeholder="Enter password" />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</Container>
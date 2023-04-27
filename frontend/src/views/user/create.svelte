<script>
    import Navbar from "../components/Navbar.svelte";
    import {Container} from "sveltestrap";

    let user = {
        username: "",
        email: " ",
        password: " ",
        password_confirmation: " ",
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch("http://localhost:8080/api/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });
        console.log('Request body:', JSON.stringify(user));

        if (response.ok) {
            const data = await response.json();
            console.log(data);
        } else {
            console.log("Error adding user", response.statusText);
        }
    };
    export {
        user,
        handleSubmit,
    }
</script>

<Navbar />
<Container>
    <h1>Add user</h1>
    <form on:submit={handleSubmit}>
        <div class="form-group">
            <label for="name">Name</label>
            <input bind:value={user.username} type="text" class="form-control" id="name" placeholder="Enter name" />
        </div>
        <div class="form-group">
            <label for="email">Email address</label>
            <input bind:value={user.email} type="email" class="form-control" id="email" placeholder="Enter email" />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input bind:value={user.password} type="password" class="form-control" id="password" placeholder="Password" />
        </div>
        <button type="submit" class="btn btn-primary">Add Task</button>
    </form>
</Container>

<script>
    import {Container} from "sveltestrap";
    import {onMount} from 'svelte';
    import NavBar from "../components/NavBar.svelte";

    let task = {
        title: "",
        description: "",
        duedate: "",
        category: {
            name: ""
        }
    };

    const url = new URL(window.location.href);
    const id = url.pathname.split('/').pop();

    onMount(async () => {
        try {
            const taskResponse = await fetch(`http://localhost:8080/api/tasks/${id}`);
            task = await taskResponse.json();
        } catch (e) {
            console.error('Error fetching task:', e);
        }
    });
</script>

<NavBar/>
<Container>
    <h1>{task.title}</h1>
    <p>{task.description}</p>
    <p>Due: {task.duedate}</p>
    <p>Category: {task.category.name}</p>
</Container>


<script>
   import {Container} from "sveltestrap";
   import {onMount} from 'svelte';
   import NavBar from "../components/NavBar.svelte";

    let task = {
        title: "",
        description: "",
        dueDate: "",
    };

    const url = new URL(window.location.href);
    const id = url.pathname.split('/').pop();

    onMount(async () => {
        try {
            const taskResponse = await fetch(`http://localhost:8080/api/tasks/${id}`);
            const json = await taskResponse.json();
            // convert date format
            const dueDate = json.dueDate.slice(0, 10); // extract date part in 'yyyy-MM-dd' format
            task = {...json, dueDate}; // spread json and add 'dueDate' property in required format
        } catch (e) {
            console.error('Error fetching task:', e);
        }
    });
</script>

<NavBar/>
<Container>
    <h1>{task.title}</h1>
    <p>{task.description}</p>
    <p>Created: {task.created}</p>
    <p>Due: {task.dueDate}</p>

    <p>Completed: {task.completed}</p>


    <p>Category: {task.category}</p>
</Container>


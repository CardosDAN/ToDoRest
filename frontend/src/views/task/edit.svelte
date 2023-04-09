<script>
    import Navbar from "../components/Navbar.svelte";
    import {Container} from "sveltestrap";
    import {onMount} from "svelte";

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

    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch(`http://localhost:8080/api/tasks/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(task),
        });

        if (response.ok) {
            console.log("Task updated successfully!");
        } else {
            console.error("Error updating task:", response.statusText);
        }
    };
</script>

<Navbar />
<Container>
    <h1>Edit Task</h1>
    <form on:submit={handleSubmit}>
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" bind:value={task.title}>
        </div>
        <div class="mb-3">
            <label for="dueDate" class="form-label">Due Date</label>
            <input type="date" class="form-control" id="dueDate" bind:value={task.dueDate}>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" rows="3" bind:value={task.description}></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update Task</button>
    </form>
</Container>

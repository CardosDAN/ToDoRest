<script>
    import Navbar from "../components/Navbar.svelte";
    import { Container } from "sveltestrap";
    import { onMount } from 'svelte';

    let task = {
        title: '',
        description: '',
        duedate: '',
        priority: '',
        category: '',
    };

    let categories = [];

    const handleSubmit = async (event) => {
        event.preventDefault();

        const selectedCategory = categories.find(c => c.id === parseInt(task.category));
        task.category = {
            id: selectedCategory.id,
            name: selectedCategory.name,
        };

        const response = await fetch('http://localhost:8080/api/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(task),
        });
        console.log('Request body:', JSON.stringify(task));

        if (response.ok) {
            console.log('Task added successfully!');
            // clear the form
            task = {
                title: '',
                description: '',
                duedate: '',
                priority: '',
                category: '',
            };
        } else {
            console.error('Error adding task:', response.statusText);
        }
    };

    onMount(async () => {
        const response = await fetch('http://localhost:8080/api/categories');
        if (response.ok) {
            categories = await response.json();
        } else {
            console.error('Error fetching categories:', response.statusText);
        }
    });

    export {
        Navbar,
        Container,
        handleSubmit,
        task,
        categories
    };
</script>
<Navbar />
<Container>
    <h1>Add Task</h1>
    <form on:submit={handleSubmit}>
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" bind:value={task.title}>
        </div>
        <div class="mb-3">
            <label for="dueDate" class="form-label">Due Date</label>
            <input type="datetime-local" class="form-control" id="dueDate" bind:value={task.duedate}>
        </div>
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <select class="form-control" id="category" bind:value={task.category}>
                {#each categories as category}
                    <option value={category.id}>{category.name}</option>
                {/each}
            </select>
        </div>
        <div class="mb-3">
            <label for="priority" class="form-label">Priority</label>
            <select class="form-control" id="priority" bind:value={task.priority}>
                <option value="1">Low</option>
                <option value="5">Medium</option>
                <option value="10">High</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" rows="3" bind:value={task.description}></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Task</button>
    </form>
</Container>

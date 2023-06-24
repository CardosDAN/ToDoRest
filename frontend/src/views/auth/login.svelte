<script>
    import { onMount } from 'svelte';

    import config from '../../config';

    let username = '';
    let password = '';

    async function handleSubmit() {
        try {
            const response = await fetch(`${config.baseURL}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                // Autentificare cu succes, gestionează răspunsul aici
                console.log('Success:', response);
            } else {
                // Eroare de autentificare, gestionează răspunsul aici
                console.log('Error:', response);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
</script>

<form on:submit={handleSubmit}>
    <label>
        Username:
        <input type="text" bind:value={username} />
    </label>

    <label>
        Password:
        <input type="password" bind:value={password} />
    </label>

    <button type="submit">Login</button>
</form>
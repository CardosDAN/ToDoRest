<script>
    let email = '';
    let password = '';
    let username = '';

    async function handleLogin() {
        const response = await fetch('http://localhost:8080/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password , username})
        });
        console.log("response", JSON.stringify(response));

        const data = await response.json();
        console.log(data); // this will log the response from the server

    }
</script>

<form on:submit|preventDefault={handleLogin}>
    <label>
        Email:
        <input type="email" bind:value={email} />
    </label>
    <label>
        Password:
        <input type="password" bind:value={password} />
    </label>
    <label>
        Username:
        <input type="text" bind:value={username} />
    </label>
    <button type="submit">Signing</button>
</form>

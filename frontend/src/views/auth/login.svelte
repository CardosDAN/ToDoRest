<!--<script>-->
<!--    import axios from "axios";-->
<!--    import {push} from "svelte-spa-router";-->

<!--    let email = '';-->
<!--    let password = '';-->


<!--    $: submit = async () => {-->
<!--        const response = await axios.post('http://localhost:8080/auth/login', {-->
<!--            email,-->
<!--            password-->
<!--        }, {-->
<!--            withCredentials: true});-->
<!--        if (response.status === 200) {-->
<!--           axios.defaults.headers.common['Authorization'] = response.data.token;-->
<!--           console.log(axios.defaults.headers.common['Authorization']);-->

<!--           await push('/task');-->
<!--        }-->
<!--        console.log(response);-->
<!--    }-->

<!--</script>-->

<!--<form on:submit|preventDefault={submit()}>-->
<!--    <label>-->
<!--        Email:-->
<!--        <input type="email" bind:value={email}/>-->
<!--    </label>-->
<!--    <label>-->
<!--        Password:-->
<!--        <input type="password" bind:value={password}/>-->
<!--    </label>-->
<!--    <button type="submit">Login</button>-->
<!--</form>-->


<main>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Login</h1>
        {#if errorMessage}
            <div class="alert alert-danger" role="alert">
                {errorMessage}
            </div>
        {/if}
        <form on:submit|preventDefault={login}>
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" bind:value={email}>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" bind:value={password}>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</main>

<script>
    import {post} from '../../utils'
    import { navigate } from 'svelte-routing'

    let email = ''
    let password = ''
    let errorMessage = ''

    const login = async () => {
        const response = await post('auth/login', {email, password})
        console.log(response);
        if (response.status === 200) {
            localStorage.setItem('accessToken', response.accessToken)
            window.location.href = '/task'
            navigate('/task')
        } else if (response.status === 400) {
            errorMessage = 'Invalid email or password'
        }
    }


</script>


<style>
    .container {
        max-width: 400px;
    }
</style>

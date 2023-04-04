<script>
    import { Router, Route } from 'svelte-routing'
    import NavBar from './views/components/Navbar.svelte'
    import CreateTask from './views/task/create.svelte'
</script>

<Router>
    <Route component={NavBar} />
    <Route path="/task/create" component={CreateTask} />
</Router>
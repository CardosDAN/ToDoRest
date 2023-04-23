<script>
    import { Router, Route } from 'svelte-routing'
    import NavBar from './views/components/Navbar.svelte'
    import CreateTask from './views/task/create.svelte'
    import IndexTask from './views/task/index.svelte'
    import TaskEdit from './views/task/edit.svelte'
    import ShowTask from './views/task/show.svelte'

    import IndexCategoory from './views/category/index.svelte'
    import CreateCategory from './views/category/create.svelte'
    import EditCategory from './views/category/edit.svelte'
</script>

<Router>
    <Route path="/">
        <NavBar />
        <IndexTask />
    </Route>
    <Route path="/task/:id" component={ShowTask} />
    <Route path="/task/edit/:id" component={TaskEdit} />
    <Route path="/task/create" component={CreateTask} />

    <Route path="/category" component={IndexCategoory} />
    <Route path="/category/create" component={CreateCategory} />
    <Route path="/category/edit/:id" component={EditCategory} />

</Router>

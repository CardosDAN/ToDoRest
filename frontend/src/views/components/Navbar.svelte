<script>
    import {
        Collapse,
        Navbar,
        NavbarToggler,
        NavbarBrand,
        Nav,
        NavItem,
        NavLink,
        Dropdown,
        DropdownToggle,
        DropdownMenu,
        DropdownItem
    } from 'sveltestrap';

    let isOpen = false;

    function handleUpdate(event) {
        isOpen = event.detail.isOpen;
    }
    import {Link} from 'svelte-routing';
</script>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <title></title>
</head>

<main>
    <Navbar color="light" light expand="md">
        <NavbarBrand href="/">Save Time</NavbarBrand>
        <NavbarToggler on:click={() => (isOpen = !isOpen)}/>
        <Collapse {isOpen} navbar expand="md" on:update={handleUpdate}>
            <Nav class="ms-auto" navbar>
                <NavItem>
                    <NavLink href="#components/">Components</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink>
                        <Link to="/task/create">Add Task</Link>
                    </NavLink>
                </NavItem>
                <Dropdown nav inNavbar>
                    <DropdownToggle nav caret>Options</DropdownToggle>
                    <DropdownMenu end>
                        <DropdownItem>
                            <Link to="/category">Category</Link>
                        </DropdownItem>
                        <DropdownItem>
                            <Link to="/user">Users</Link>
                        </DropdownItem>
                        <DropdownItem divider/>
                        <DropdownItem>Reset</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </Nav>
        </Collapse>
    </Navbar>
</main>

<style>
    main {
        text-align: center;
        padding: 1em;
        max-width: 240px;
        margin: 0 auto;
    }

    h1 {
        color: #ff3e00;
        text-transform: uppercase;
        font-size: 4em;
        font-weight: 100;
    }

    @media (min-width: 640px) {
        main {
            max-width: none;
        }
    }
</style>
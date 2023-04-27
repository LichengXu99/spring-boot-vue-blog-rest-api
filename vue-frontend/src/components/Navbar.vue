<template>
    <header class="header">
        <!-- Blog logo -->

        <!-- Blog logo -->
        <!-- Navigation Bar -->
        <nav class="nav">
            <ul class="navigation-main">
                <li>
                    <router-link to="/">Home</router-link>
                </li>
                <li>
                    <div class="dropdown">
                        <button class="dropbtn">Category ⏷</button>
                        <div class="dropdown-content">
                            <a href="#"
                               v-for="category in categories"
                               :key="category.id">
                                {{ category.name }}
                            </a>
                        </div>
                    </div>
                </li>
                <li>
                    <router-link to="/about">About</router-link>
                </li>
                <li>
                    <router-link to="/contact">Contact</router-link>
                </li>

            </ul>
        </nav>
        <!-- Navigation Bar -->

    </header>
</template>

<script>
export default {
    name: 'ShareHeader',
    data() {
        return {
            categories: [],
        }
    },
    created() {
        this.fetchCategories();
    },
    methods: {
        fetchCategories() {
            this.$store.dispatch("getCategories")
                .then(response => {
                    this.categories = response;
                })
                .catch(error => {
                    console.log(error);
                })
        }
    }
}
</script>

<style>
.header {
    background-color: #145474;
    display: flex;
    align-items: center;
    position: fixed;
    width: 100%; /* Limit width of header  */
    transform: translate(0, 0);
    z-index: 999;
}

.nav {
    width: 100%;
}

.navigation-main {
    margin: 0;
    padding: 0;
    list-style: none;
    width: 100%;
    text-align: center;
}

.navigation-main ul {
    min-width: 150px;
    margin: 0;
    padding: 0;
    list-style: none;
    position: absolute;
    top: -999em;
    left: -999em;
}

.navigation-main li {
    display: inline-block;
    position: relative;
    text-align: left;
}

.navigation-main li.focus > ul,
.navigation-main li:hover > ul {
    top: auto;
    left: auto;
}

.navigation-main li li {
    display: block;
}

.navigation-main li li.focus > ul,
.navigation-main li li:hover > ul {
    left: 100%;
    top: 0;
}

.navigation-main a {
    color: #fff;
    text-decoration: none;
    display: block;
    white-space: nowrap;
    padding: 10px 15px;
}

.navigation-main a:hover,
.navigation-main li:hover > a,
.navigation-main .focus > a,
.navigation-main .current-menu-item > a,
.navigation-main .current-menu-ancestor > a {
    color: #ffcc03;
}

.navigation-main .menu-item-has-children > a::before {
    content: "";
    position: absolute;
    right: 0;
    top: 50%;
    transform: translateY(-50%);
    border: 4px solid transparent;
    border-top-color: currentColor;
}

.navigation-main ul .menu-item-has-children > a::before {
    right: 2px;
    border-top-color: transparent;
    border-left-color: currentColor;
}

.navigation-main ul {
    background-color: #145474;
}

/* Style The Dropdown Button */
.dropbtn {
    background-color: #145474;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
    position: relative;
    display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
    background-color: #f1f1f1
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
    display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropbtn:hover .dropbtn {
    color: #ffcc03;
}
</style>
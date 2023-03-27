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
  components: {},
}
</script>

<style>
.header {
  background-color: #145474;
  display: flex;
  align-items: center;
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
.navigation-main li :li:hover > ul {
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
</style>
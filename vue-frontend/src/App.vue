<template>
  <div id="app">
    <Navbar />
    <div id="content" class="container">
        <main>
          <router-view />
        </main>
    </div>
    <Footer />
  </div>
</template>

<script>
import Navbar from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  }
}
</script>

<style>
#content {
  min-height: 1000px;
}
</style>

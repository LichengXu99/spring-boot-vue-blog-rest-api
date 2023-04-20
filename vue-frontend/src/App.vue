<template>
    <div id="app">
        <Navbar/>
        <main>
            <div class="container">
                <div class="row clearfix">
                    <div class="col-lg-8 col-md-12 left-box">
                        <router-view/>
                    </div>
                    <div class="col-lg-4 col-md-12 right-box">
                        <SideBox/>
                    </div>
                </div>
            </div>
        </main>
        <transition name="fade">
            <ScrollToTop />
        </transition>
    </div>
    <Footer/>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import Footer from "@/components/Footer.vue";
import SideBox from "@/components/SideBox.vue";
import ScrollToTop from "@/components/ScrollToTop.vue";

export default {
    name: 'App',
    components: {
        ScrollToTop,
        SideBox,
        Navbar,
        Footer
    }
}
</script>

<style>
.container {
    min-height: 1000px;
}

</style>

import {createRouter, createWebHashHistory} from "vue-router";
import Home from "@/views/Home.vue";
import About from "@/views/About.vue";
import Contact from "@/views/Contact.vue";
import PageNotFound from "@/views/PageNotFound.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";

const routes = [
    { path: '/', component: Home },
    { path: '/about', name: 'About', component: About},
    { path: '/contact', name: 'Contact', component: Contact},
    { path: '/login', name: 'Login', component: Login },
    { path: '/register', name: 'Register', component: Register },
    { path: '/:pathMatch(.*)*', component: PageNotFound},
]

const router = createRouter({
    history: createWebHashHistory(),
    scrollBehavior: () => ({ y: 0 }),
    routes:routes
})

export default router
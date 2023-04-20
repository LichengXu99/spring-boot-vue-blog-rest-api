import { createRouter, createWebHashHistory } from "vue-router";
import Home from "@/views/blog/home/index.vue";
import About from "@/views/blog/about/index.vue";
import Contact from "@/views/blog/contact/index.vue";
import PageNotFound from "@/views/blog/error/PageNotFound.vue";
import Login from "@/views/admin/auth/index.vue";
import Register from "@/views/admin/register/index.vue";
import Post from "@/views/blog/post/index.vue";

const routes = [
    { path: '/', component: Home },
    { path: '/about', name: 'About', component: About },
    { path: '/contact', name: 'Contact', component: Contact },
    { path: '/login', name: 'Login', component: Login },
    { path: '/register', name: 'Register', component: Register },
    { path: '/post/:id', name: 'Post', component: Post },
    { path: '/:pathMatch(.*)*', component: PageNotFound },
]

const router = createRouter({
    history: createWebHashHistory(),
    scrollBehavior: () => ({ y: 0 }),
    routes:routes
})

export default router
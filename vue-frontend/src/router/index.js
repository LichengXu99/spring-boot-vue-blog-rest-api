import {createRouter, createWebHashHistory} from "vue-router";
import Home from "@/views/blog/home/index.vue";
import About from "@/views/blog/about/index.vue";
import Contact from "@/views/blog/contact/index.vue";
import PageNotFound from "@/views/blog/error/PageNotFound.vue";
import Login from "@/views/admin/auth/index.vue";
import Register from "@/views/admin/register/index.vue";
import Post from "@/views/blog/post/index.vue";
import Editor from "@/views/admin/editor/index.vue";

const routes = [
    {path: '/', component: Home},
    {path: '/about', name: 'About', component: About},
    {path: '/contact', name: 'Contact', component: Contact},
    {path: '/login', name: 'Login', component: Login},
    {path: '/register', name: 'Register', component: Register},
    {path: '/post/:id', name: 'Post', component: Post},
    {path: '/editor/:id', name: 'Editor', component: Editor, meta: {requiresAuth: true}},
    {path: '/:pathMatch(.*)*', component: PageNotFound},
]


const router = createRouter({
    history: createWebHashHistory(),
    scrollBehavior: function (to, _from, savedPosition) {
        if (savedPosition) {
            return savedPosition;
        }
        if (to.hash) {
            return {el: to.hash, behavior: 'smooth'};
        } else {
            setTimeout(() => {
                window.scrollTo(0, 0);
            }, 500);
        }
    },
    routes: routes
})

// 全域前置守衛
router.beforeEach((to, from, next) => {
    const isAuthenticated = localStorage.getItem('token');

    // 檢查 'token' 是否存在
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!isAuthenticated) {
            // 若 'token' 不存在，重新導向到登入頁面或首頁
            next({ name: 'Login' }); // 或 next({ path: '/' });
        } else {
            // 若 'token' 存在，繼續導航
            next();
        }
    } else {
        // 不需要驗證權限的路由，繼續導航
        next();
    }
});

export default router;
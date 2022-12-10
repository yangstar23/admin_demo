import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'none',
        component: () => import('../views/none.vue')
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('../views/Home.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('../views/about.vue')
    }
]

const router = createRouter({
    history: createWebHistory("/"),
    routes: routes,
})

/**
 * 输出对象
 */
export default router;
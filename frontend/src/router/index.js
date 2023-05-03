import Home from '../views/Home.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Test from "@/views/Test";

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/test',
        component: Test
    }
]

const routerHistory = createWebHistory()
const router = createRouter({
    history: routerHistory,
    routes,
})
export default router

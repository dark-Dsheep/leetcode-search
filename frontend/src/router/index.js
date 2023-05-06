import Home from '../views/Home.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Test from "@/views/Test";
import AddCodeInfo from "@/views/AddCodeInfo";

const routes = [
    {
        path: '/',
        component: Home,
        meta: {
            title: '力扣题解搜索'
        }
    },
    {
        path: '/test',
        component: Test
    },
    {
        path: '/addCodeInfo',
        component: AddCodeInfo,
        meta: {
            title: '录入力扣题解'
        }
    }
]

const routerHistory = createWebHistory()
const router = createRouter({
    history: routerHistory,
    routes,
})

router.beforeEach(async (to) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
})
export default router

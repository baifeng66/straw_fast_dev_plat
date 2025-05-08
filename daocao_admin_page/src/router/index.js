import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'

// 配置路由规则
let routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
  },
  // 重定向到登录页面
  {
    path: '',
    redirect: '/login'
  }
]

// 路由器
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // 路由规则
  routes: routes
})

export default router

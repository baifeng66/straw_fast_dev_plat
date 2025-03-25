import { ElMessage } from 'element-plus'
import router from '@/router/index.js'
import axios from 'axios'
// 封装 axios 请求
const request = axios.create({
    baseURL: 'http://localhost:8066',
    // 配置请求接口跨域时是否需要凭证
    withCredentials: false,
    timeout: 3000
})

// pinia 中获取 token
let token = ''

// 配置请求头
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

// 配置请求拦截器
request.interceptors.request.use(config => {
    // 在请求头添加token, 判断是否需要发送token
    if (token) {
        config.headers['Daocao-Authorization'] = token
    }
    return config
}, error => {//发生异常
    console.log('发生异常');
    return Promise.reject(error)
})

// 配置响应拦截器
request.interceptors.response.use(response => {
    let { code, msg } = response.data
    // 判断响应码
    if (code == 200) {
        return response
    } else if (code == 401) {
        ElMessage.error('没有操作权限')
    } else if (code == 500) {
        ElMessage.error('服务端异常')
    } else if (code == 403) {
        ElMessage.error('登录过期')
        // 需要重新登录，清除 pinia 里面的数据，存储到 sessionStorage 里面
        window.sessionStorage.clear()
        router.push('/login')
    }
    return Promise.reject(msg)
}, error => {
    console.log('发生异常');
    window.sessionStorage.clear()
    router.push('/login')
    return Promise.reject(error)
})

// 导出
export default request
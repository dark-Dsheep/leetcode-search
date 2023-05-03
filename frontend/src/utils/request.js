import axios from 'axios';


const request = axios.create({
    baseURL: 'http://localhost:8080/api',
    withCredentials: true,
    timeout: 6000
})


// 响应拦截器
request.interceptors.response.use((response) => {
    const res = response.data
    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 20000 && res.code !== 40001 &&res.code !== 0) {
        console.log('错误code: ' + res.code)
        console.log('错误响应:' + res.message)
        // notification.error({
        //   message: '禁止的',
        //   description: res.message
        // })
        // message.error(res.message)
        return Promise.reject(new Error(res.message || 'Error'))
    } else {
        return res
    }
})


export default request

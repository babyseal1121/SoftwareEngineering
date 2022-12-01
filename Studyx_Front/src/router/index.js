import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//分别是用户开始界面和登录界面
import Login from '@/pages/Login/Login'

//测试界面
import Test from '@/pages/Test'


//有children的都是分成模块的
export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      redirect: 'login'
    },
    //测试界面，输入exerciseno,返回对应的exercise数据
    {
      path: '/test',
      name: 'Test',
      component: Test
    },
      //login
    {
      path: '/logn',
      name: 'login',
      component: Login
    },
  ]
})

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//分别是用户开始界面和登录界面
import Login from '@/pages/Login/Login'

//总页面
import General from '@/pages/General'

//欢迎页面
import Index from '@/pages/Index'
//成绩管理
import GradeSet from '@/pages/Grade/GradeSet'
//个人信息
import MyInfo from '@/pages/MyInfo/MyInfo'
//测试界面
import Test from '@/pages/Test'


//有children的都是分成模块的
export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      redirect: 'index'
    },
    //测试界面，输入exerciseno,返回对应的exercise数据

      //login
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {//需要统一格式的页面放到这里
      path: '/',
      name: 'General',
      component: General,
      redirect: '/index',
      children: [{
        path: '/myinfo',
        component: MyInfo,
        meta: {
          name: '个人信息展示'
        }
      },
        {
          path: '/gradeset',
          component: GradeSet,
          meta: {
            name: '成绩权重设置'
          }
        },
        {
          path: '/index',
          component: Index,
          meta: {
            name: '欢迎页面'
          },
        },
        {
          path: '/test',
          name: 'Test',
          component: Test
        },
      ]
    }
  ]
})

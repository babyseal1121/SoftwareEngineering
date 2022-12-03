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
import GradeDisplay from '@/pages/Grade/GradeDisplay'
//个人信息
import MyInfo from '@/pages/MyInfo/MyInfo'
//测试界面
import Test from '@/pages/Test'

//用户信息列表
import UserList from '@/pages/User/UserList'

const routes = [
  // 登录
  {
    path: '/login',
    component: Login,
    hide: true    // 不是菜单
  },
  // 布局
  {
    path: '/',
    component: General,
    redirect: '/index',     //重定向
    meta: { title: '后台首页' },
    children: [
      {
        path: '/index',
        component: Index,
      }
    ]
  },
]

// 公共路由添加
const router = new Router({
  mode: 'history',
  routes })

//动态路由
const dynamicRoutes = [
  // 成绩管理
  {
    path: '/order',
    component: General,
    redirect: '/order/order-list',
    meta: { title: '订单管理', roles: '', icon: 'el-icon-document' },
    children: [
      {
        path: '/order/order-list',
        meta: { title: '订单列表', roles: '' },
        component: GradeDisplay
      },
      {
        path: '/order/order-edit',
        meta: { title: '订单编辑', roles: 'super' },
        component:GradeSet
      },
    ]
  },

  // 账号管理
  /*{
    path: '/account',
    component: Layout,
    redirect: '/account/account-list',
    meta: { title: '账号管理', roles: '', icon: 'el-icon-user' },
    children: [
      {
        path: '/account/account-list',
        meta: { title: '账号列表', roles: 'super' },
        component: () => import('@/views/account/AccountList')
      },
      {
        path: '/account/account-add',
        meta: { title: '账号添加', roles: 'super' },
        component: () => import('@/views/account/AccountAdd')
      },
      {
        path: '/account/modify-pwd',
        meta: { title: '修改密码', roles: '' },
        component: () => import('@/views/account/ModifyPassword')
      },
      {
        path: '/account/personal',
        meta: { title: '个人中心', roles: '' },
        component: () => import('@/views/account/Personal')
      },
    ]
  },*/

  // 给所有空白页配置路由

]

export default router    // 默认导出


// 计算路由
export function calcRoutes() {
  //let rule = local.get('role')    // 从本地获取当前登录用户权限
  /*// local.get('role') 等同于 localStorage.getItem('role')
  if (!rule) {                // 如果没有获取到权限 则不计算路由渲染菜单
    return
  }
  let allRoutes = []    //计算合并后的路由
  let menu = []         // 存入本地的数组
  // 计算路由
  if (rule === 'super') {
    // 超级管理员
    router.addRoutes(dynamicRoutes)           // 可访问路由
    allRoutes = [...routes, ...dynamicRoutes]  // 计算后合并的路由
    // 计算菜单
    menu = allRoutes.filter(v => !v.hide)        // 需要显示出来的菜单
  } else {
    // 普通管理员可以访问的菜单和路由
    let arr = dynamicRoutes.filter(v => {         // 过滤一级路由
      if (v.meta.roles.indexOf('super') === -1) {    // 如果找到了super 那么代表管理员才能访问 普通用户无法访问
        if (v.meta.roles != 'super') {    // 如果没有super 证明普通用户可以访问
          if (v.children) {v.children = v.children.filter(c=>c.meta.roles !='super')}}  //过滤二级路由    若有子级则判断子级是否有super 有则不可访问该路由 反正可访问
        return true
      }else{
        return false
      }
    })
    router.addRoutes(arr)                    // 可访问路由
    allRoutes = [...routes, ...arr]        // 计算后合并的路由
    // 计算菜单
    menu = allRoutes.filter(v => !v.hide)    // 需要显示出来的菜单
  }
  // 存入本地
  local.set('menu', menu)*/
}
calcRoutes()    // 每次刷新页面重新计算路由

//有children的都是分成模块的
/*export default new Router({
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
          path: '/成绩权重设置',
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
        {
          path: '/用户信息列表',
          name: 'UserList',
          component: UserList
        },
      ]
    }
  ]
})*/


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
import AddUser from '@/pages/User/AddUser'

//实验项目
import ExperimentProjectManagement from '@/pages/ExperimentProject/ExperimentProjectManagement.vue'
import ExperimentDetail from '@/pages/ExperimentProject/ExperimentDetail.vue'
import CreateExperimentProject from '@/pages/ExperimentProject/CreateExperimentProject.vue'

//实验报告
import SubmitReport from '@/pages/ExperimentReport/SubmitReport.vue'
import FinishReport from '@/pages/ExperimentReport/FinishReport.vue'
import CorrectReport from '@/pages/ExperimentReport/CorrectReport.vue'
import CorrectReportDetail from '@/pages/ExperimentReport/CorrectReportDetail.vue'
import MyReport from '@/pages/ExperimentReport/MyReport.vue'
import ReportDetail from '@/pages/ExperimentReport/ReportDetail.vue'

//快速测试
import ProblemManagement from '@/pages/Exercise/ProblemManagement.vue'
import ExperimentProblem from '@/pages/Exercise/ExperimentProblem.vue'
import CreateProblem from '@/pages/Exercise/CreateProblem.vue'
import PublishExercise from '@/pages/Exercise/PublishExercise.vue'
import ExperimentExercise from '@/pages/Exercise/ExperimentExercise.vue'
import MyExercise from '@/pages/Exercise/MyExercise.vue'
import ExerciseDetail from '@/pages/Exercise/ExerciseDetail.vue'
import ComeExercise from '@/pages/Exercise/ComeExercise.vue'

//班级信息管理
import ClassManagement from '@/pages/ExperimentClass/ClassManagement.vue'
import CreateClass from '@/pages/ExperimentClass/CreateClass.vue'
import ClassExperiment from '@/pages/ExperimentClass/ClassExperiment.vue'
import ClassStudent from '@/pages/ExperimentClass/ClassStudent.vue'
import MyClass from '@/pages/ExperimentClass/MyClass.vue'

//文件
import ShowStudyFile from '@/pages/StudyFile/ShowStudyFile.vue'
import ExperimentFile from '@/pages/StudyFile/ExperimentFile.vue'
import ManageFile from '@/pages/StudyFile/ManageFile.vue'
import ManageExperimentFile from '@/pages/StudyFile/ManageExperimentFile.vue'

//公告板
import BoardDiaplay from '@/pages/BulletinBoard/BoardDiaplay.vue'
import BoardDiaplayview from '@/pages/BulletinBoard/BoardDiaplayview.vue'
import CourseInformationManagement from '@/pages/BulletinBoard/CourseInformationManagement.vue'
import CreatLearningFeedback from '@/pages/BulletinBoard/CreatLearningFeedback.vue'
import LearningFeedback from '@/pages/BulletinBoard/LearningFeedback.vue'
import LearningFeedbackview from '@/pages/BulletinBoard/LearningFeedbackview.vue'

//笔记管理
import CreatNewNotes from '@/pages/Notes/CreatNewNotes.vue'
import MyNotes from '@/pages/Notes/MyNotes.vue'



//导入外部数据
//import navData from "@/utils/navData";
/*const routes = [
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
      },
      {
        path: '/myinfo',
        component: MyInfo,
      },
      {
        path: '/index',
        component: Index,
      },
      {
        path: '/test',
        component: Test,
      },
      {
        path: '/gradeset',
        component: GradeSet,
      },
      {
        path: '/gradedisplay',
        component: GradeDisplay,
      },
      {
        path: '/userlist',
        component: UserList,
      },
      {
        path: '/experimentprojectmanagement',
        component: ExperimentProjectManagement
      },
      {
        path: '/experimentdetail',
        component: ExperimentDetail
      },
      {
        path: '/createexperimentproject',
        component: CreateExperimentProject
      },
      {
        path: '/submitreport',
        component: SubmitReport
      },
      {
        path: '/finishreport',
        component: FinishReport
      },
      {
        path: '/correctreport',
        component: CorrectReport
      },
      {
        path: '/correctreportdetail',
        component: CorrectReportDetail
      },
      {
        path: '/myreport',
        component: MyReport
      },
      {
        path: '/reportdetail',
        component: ReportDetail
      },
      {
        path: '/problemmanagement',
        component: ProblemManagement
      },
      {
        path: '/experimentproblem',
        component: ExperimentProblem
      },
      {
        path: '/createproblem',
        component: CreateProblem
      },
      {
        path: '/publishexercise',
        component: PublishExercise
      },
      {
        path: '/experimentexercise',
        component: ExperimentExercise
      },
      {
        path: '/myexercise',
        component: MyExercise
      },
      {
        path: '/exercisedetail',
        component: ExerciseDetail
      },
      {
        path: '/comeexercise',
        component: ComeExercise
      },
      {
        path: '/classmanagement',
        component: ClassManagement
      },
      {
        path: '/createclass',
        component: CreateClass
      },
      {
        path: '/classexperiment',
        component: ClassExperiment
      },
      {
        path: '/classstudent',
        component: ClassStudent
      },
      {
        path: '/myclass',
        component: MyClass
      }

    ]
  },

]*/

// 公共路由添加
/*const router = new Router({
  mode: 'history',
  routes })*/




//export default router    // 默认导出

export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    redirect: 'login'
  },
      //公共路由
    //登录界面
    {
      path: '/login',
      name: 'Login',
      component: Login,
      hidden: true,
      meta: { permission: [1, 2, 3] },
    },
    //管理员中心界面
    {
      path: '/admin',
      component: General,
      redirect: '/admin/adminuserlist',     //重定向
      children: [
          {
        path: '/userlist',
        component: UserList,
      },
        {
          path: '/index',
          component: Index,
        },
        {
          path: '/adduser',
          component: AddUser,
        },
        {
          path: '/myinfo',
          component: MyInfo,
        },
        {
          path: '/index',
          component: Index,
        },
        {
          path: '/test',
          component: Test,
        },
        {
          path: '/gradeset',
          component: GradeSet,
        },
        {
          path: '/gradedisplay',
          component: GradeDisplay,
        },
        /*{
          path: '/userlist',
          component: UserList,
        },*/
        {
          path: '/experimentprojectmanagement',
          component: ExperimentProjectManagement
        },
        {
          path: '/experimentdetail',
          component: ExperimentDetail
        },
        {
          path: '/createexperimentproject',
          component: CreateExperimentProject
        },
        {
          path: '/submitreport',
          component: SubmitReport
        },
        {
          path: '/finishreport',
          component: FinishReport
        },
        {
          path: '/correctreport',
          component: CorrectReport
        },
        {
          path: '/correctreportdetail',
          component: CorrectReportDetail
        },
        {
          path: '/myreport',
          component: MyReport
        },
        {
          path: '/reportdetail',
          component: ReportDetail
        },
        {
          path: '/problemmanagement',
          component: ProblemManagement
        },
        {
          path: '/experimentproblem',
          component: ExperimentProblem
        },
        {
          path: '/createproblem',
          component: CreateProblem
        },
        {
          path: '/publishexercise',
          component: PublishExercise
        },
        {
          path: '/experimentexercise',
          component: ExperimentExercise
        },
        {
          path: '/myexercise',
          component: MyExercise
        },
        {
          path: '/exercisedetail',
          component: ExerciseDetail
        },
        {
          path: '/comeexercise',
          component: ComeExercise
        },
        {
          path: '/classmanagement',
          component: ClassManagement
        },
        {
          path: '/createclass',
          component: CreateClass
        },
        {
          path: '/classexperiment',
          component: ClassExperiment
        },
        {
          path: '/classstudent',
          component: ClassStudent
        },
        {
          path: '/myclass',
          component: MyClass
        },
        {
          path: '/showstudyfile',
          component: ShowStudyFile
        },
        {
          path: '/experimentfile',
          component: ExperimentFile
        },
        {
          path: '/managefile',
          component: ManageFile
        },
        {
          path: '/manageexperimentfile',
          component: ManageExperimentFile
        },
        {
          path: '/boarddiaplay',
          component: BoardDiaplay
        },
        {
          path: '/boarddiaplayview',
          component: BoardDiaplayview
        },
        {
          path: '/courseinformationmanagement',
          component: CourseInformationManagement
        },
        {
          path: '/creatlearningfeedback',
          component: CreatLearningFeedback
        },
        {
          path: '/learningfeedback',
          component: LearningFeedback
        },
        {
          path: '/learningfeedbackview',
          component: LearningFeedbackview
        },
        {
          path: '/mynotes',
          component: MyNotes
        },
        {
          path: '/creatnewnotes',
          component: CreatNewNotes
        },

      ],
      props: true
    },

  ]
})
/*router.beforeEach((to, from, next)=>{
  next();
  axios.get('/post/adminmenu').then(res=>{
    store.commit('setMenuData',res.data.menu_data)
  });
})*/





//引入mockjs
import Mock from 'mockjs'
//使用mockjs模拟数据
Mock.mock('http://localhost:8443/api/test123456', {
    "res":0,
    "data":
        {
            "datatime": "@datetime",//随机生成日期时间
            "weekday|1-7": 7,//随机生成1-7的数字
            "name": "@cname",//随机生成中文名字
        }
});
Mock.mock('http://localhost:8443/api/adminmenu','get',function(){
    const menu_data = [
        {
            name: '用户管理',
            icon: 'el-icon-location',
            path: '/user',
            child: [
                {
                    name: '用户信息列表',
                    icon: 'el-icon-user',
                    path: '/userlist',
                    component:"UserList",
                },
                {
                    name: '添加新用户',
                    icon: 'el-icon-user',
                    path: '/adduser',
                    component:"AddUser",
                },
            ]
        },
    ]

    return{
        menu_data
    }
});
//责任教师
Mock.mock('http://localhost:8443/api/instructormenu','get',function(){
    const menu_data = [
        {
            name: '成绩管理',
            icon: 'el-icon-setting',
            path: '/gradeset',
            component:"GradeSet",
            child: [
                {
                    name: '成绩权重设置',
                    icon: 'el-icon-s-help',
                    path: '/gradeset',
                    component:"GradeSet",
                },
                {
                    name: '查看成绩',
                    icon: 'el-icon-help',
                    path: '/gradedisplay',
                    component: "GradeDisplay",
                }
            ]
        },
        {
            name: '实验项目',
            icon: 'el-icon-s-management',
            path: '/experiment',
            child: [
                {
                    name: '实验项目管理',
                    icon: 'el-icon-document',
                    path: '/experimentprojectmanagement',
                    component:"ExperimentProjectManagement",

                },
                {
                    name: '提交实验报告',
                    icon: 'el-icon-document-add',
                    path: '/submitreport',
                    component:"SubmitReport",

                },
                {
                    name: '批改实验报告',
                    icon: 'el-icon-document-checked',
                    path: '/correctreport',
                    component:"CorrectReport",

                },
                {
                    name: '我的实验报告',
                    icon: 'el-icon-folder-opened',
                    path: '/myreport',
                    component:"MyReport",

                },
            ]
        },
        {
            name: '快速测验',
            icon: 'el-icon-tickets',
            path: '/exercise',
            child: [
                {
                    name: '题目管理',
                    icon: 'el-icon-paperclip',
                    path: '/problemmanagement',
                    component:"ProblemManagement",
                },
                {
                    name: '发布测验',
                    icon: 'el-icon-plus',
                    path: '/publishexercise',
                    component:"PublishExercise",
                },
                {
                    name: '我的测验',
                    icon: 'el-icon-edit',
                    path: '/myexercise',
                    component:"MyExercise",
                },
            ]
        },
        {
            name: '实验班级',
            icon: 'el-icon-s-order',
            path: '/experimentClass',
            child: [
                {
                    name: '我的班级',
                    icon: 'el-icon-s-flag',
                    path: '/myclass',
                    component:"MyClass",
                },
                {
                    name: '实验管理',
                    icon: 'el-icon-folder',
                    path: '/classexperiment',
                    component:"ClassExperiment",
                },
                {
                    name: '人员管理',
                    icon: 'el-icon-s-custom',
                    path: '/classstudent',
                    component:"ClassStudent",
                },
                {
                    name: '班级管理',
                    icon: 'el-icon-collection-tag',
                    path: '/classmanagement',
                    component:"ClassManagement",
                },
            ]
        },
        {
            name: '学习资料',
            icon: 'el-icon-notebook-2',
            path: '/file',
            child: [
                {
                    name: '资料列表',
                    icon: 'el-icon-files',
                    path: '/showstudyfile',
                    component: "ShowStudyFile"
                },
                {
                    name: '资料管理',
                    icon: 'el-icon-folder-checked',
                    path: '/managefile',
                    component: "ManageFile"
                },
            ]
        },
    ]

    return{
        menu_data
    }
});
//教师
Mock.mock('http://localhost:8443/api/teachermenu','get',function(){
    const menu_data = [
        {
            name: '成绩管理',
            icon: 'el-icon-setting',
            path: '/myinfo',
            component:"MyInfo",
            child: [
                {
                    name: '查看成绩',
                    icon: 'el-icon-help',
                    path: '/gradedisplay',
                    component: "GradeDisplay",
                }
            ]
        },
        {
            name: '实验项目',
            icon: 'el-icon-s-management',
            path: '/experiment',
            child: [
                {
                    name: '实验项目管理',
                    icon: 'el-icon-document',
                    path: '/experimentprojectmanagement',
                    component:"ExperimentProjectManagement",

                },
                {
                    name: '提交实验报告',
                    icon: 'el-icon-document-add',
                    path: '/submitreport',
                    component:"SubmitReport",

                },
                {
                    name: '批改实验报告',
                    icon: 'el-icon-document-checked',
                    path: '/correctreport',
                    component:"CorrectReport",

                },
                {
                    name: '我的实验报告',
                    icon: 'el-icon-folder-opened',
                    path: '/myreport',
                    component:"MyReport",

                },
            ]
        },
        {
            name: '快速测验',
            icon: 'el-icon-tickets',
            path: '/exercise',
            child: [
                {
                    name: '题目管理',
                    icon: 'el-icon-paperclip',
                    path: '/problemmanagement',
                    component:"ProblemManagement",
                },
                {
                    name: '发布测验',
                    icon: 'el-icon-plus',
                    path: '/publishexercise',
                    component:"PublishExercise",
                },
                {
                    name: '我的测验',
                    icon: 'el-icon-edit',
                    path: '/myexercise',
                    component:"MyExercise",
                },
            ]
        },
        {
            name: '实验班级',
            icon: 'el-icon-s-order',
            path: '/experimentClass',
            child: [
                {
                    name: '我的班级',
                    icon: 'el-icon-s-flag',
                    path: '/myclass',
                    component:"MyClass",
                },
                {
                    name: '实验管理',
                    icon: 'el-icon-folder',
                    path: '/classexperiment',
                    component:"ClassExperiment",
                },
            ]
        },
        {
            name: '学习资料',
            icon: 'el-icon-notebook-2',
            path: '/file',
            child: [
                {
                    name: '资料列表',
                    icon: 'el-icon-files',
                    path: '/showstudyfile',
                    component: "ShowStudyFile"
                },
                {
                    name: '资料管理',
                    icon: 'el-icon-folder-checked',
                    path: '/managefile',
                    component: "ManageFile"
                },
            ]
        },
    ]

    return{
        menu_data
    }
});
Mock.mock('http://localhost:8443/api/tutormenu','get',function(){
    const menu_data = [
        {
            name: '成绩管理',
            icon: 'el-icon-setting',
            path: '/myinfo',
            component:"MyInfo",
            child: [
                {
                    name: '查看成绩',
                    icon: 'el-icon-help',
                    path: '/gradedisplay',
                    component: "GradeDisplay",
                }
            ]
        },
        {
            name: '实验项目',
            icon: 'el-icon-s-management',
            path: '/experiment',
            child: [
                {
                    name: '提交实验报告',
                    icon: 'el-icon-document-add',
                    path: '/submitreport',
                    component:"SubmitReport",

                },
                {
                    name: '我的实验报告',
                    icon: 'el-icon-folder-opened',
                    path: '/myreport',
                    component:"MyReport",

                },
            ]
        },
        {
            name: '快速测验',
            icon: 'el-icon-tickets',
            path: '/exercise',
            child: [
                {
                    name: '我的测验',
                    icon: 'el-icon-edit',
                    path: '/myexercise',
                    component:"MyExercise",
                },
            ]
        },
        {
            name: '实验班级',
            icon: 'el-icon-s-order',
            path: '/experimentClass',
            child: [
                {
                    name: '我的班级',
                    icon: 'el-icon-s-flag',
                    path: '/myclass',
                    component:"MyClass",
                },
            ]
        },
        {
            name: '学习资料',
            icon: 'el-icon-notebook-2',
            path: '/file',
            child: [
                {
                    name: '资料列表',
                    icon: 'el-icon-files',
                    path: '/showstudyfile',
                    component: "ShowStudyFile"
                },
            ]
        },
    ]

    return{
        menu_data
    }
});
Mock.mock('http://localhost:8443/api/studentmenu','get',function(){
    const menu_data = [
        {
            name: '成绩管理',
            icon: 'el-icon-setting',
            path: '/myinfo',
            component:"MyInfo",
            child: [
                {
                    name: '查看成绩',
                    icon: 'el-icon-help',
                    path: '/gradedisplay',
                    component: "GradeDisplay",
                }
            ]
        },
        {
            name: '实验项目',
            icon: 'el-icon-s-management',
            path: '/experiment',
            child: [
                {
                    name: '提交实验报告',
                    icon: 'el-icon-document-add',
                    path: '/submitreport',
                    component:"SubmitReport",

                },
                {
                    name: '批改实验报告',
                    icon: 'el-icon-document-checked',
                    path: '/correctreport',
                    component:"CorrectReport",

                },
                {
                    name: '我的实验报告',
                    icon: 'el-icon-folder-opened',
                    path: '/myreport',
                    component:"MyReport",

                },
            ]
        },
        {
            name: '快速测验',
            icon: 'el-icon-tickets',
            path: '/exercise',
            child: [
                {
                    name: '我的测验',
                    icon: 'el-icon-edit',
                    path: '/myexercise',
                    component:"MyExercise",
                },
            ]
        },
        {
            name: '实验班级',
            icon: 'el-icon-s-order',
            path: '/experimentClass',
            child: [
                {
                    name: '我的班级',
                    icon: 'el-icon-s-flag',
                    path: '/myclass',
                    component:"MyClass",
                },
            ]
        },
        {
            name: '学习资料',
            icon: 'el-icon-notebook-2',
            path: '/file',
            child: [
                {
                    name: '资料列表',
                    icon: 'el-icon-files',
                    path: '/showstudyfile',
                    component: "ShowStudyFile"
                },
            ]
        },
    ]

    return{
        menu_data
    }
});
//完整版侧边栏导航
Mock.mock('http://localhost:8443/api/fullsidebar','get',function(){
    const menu_data = [
        {
            name: '成绩管理',
            icon: 'el-icon-setting',
            path: '/myinfo',
            component:"MyInfo",
            child: [
                {
                    name: '成绩权重设置',
                    icon: 'el-icon-s-help',
                    path: '/gradeset',
                    component:"GradeSet",
                },
                {
                    name: '查看成绩',
                    icon: 'el-icon-help',
                    path: '/myinfo',
                    component:"MyInfo",
                }
            ]
        },
        {
            name: '用户管理',
            icon: 'el-icon-location',
            path: '/user',
            child: [
                {
                    name: '用户信息列表',
                    icon: 'el-icon-user',
                    path: '/userlist',
                    component:"UserList",
                },
            ]
        },
        {
            name: '实验项目',
            icon: 'el-icon-s-management',
            path: '/experiment',
            child: [
                {
                    name: '实验项目管理',
                    icon: 'el-icon-document',
                    path: '/experimentprojectmanagement',
                    component:"ExperimentProjectManagement",

                },
                {
                    name: '提交实验报告',
                    icon: 'el-icon-document-add',
                    path: '/submitreport',
                    component:"SubmitReport",

                },
                {
                    name: '批改实验报告',
                    icon: 'el-icon-document-checked',
                    path: '/correctreport',
                    component:"CorrectReport",

                },
                {
                    name: '我的实验报告',
                    icon: 'el-icon-folder-opened',
                    path: '/myreport',
                    component:"MyReport",

                },
            ]
        },
        {
            name: '快速测验',
            icon: 'el-icon-tickets',
            path: '/exercise',
            child: [
                {
                    name: '题目管理',
                    icon: 'el-icon-paperclip',
                    path: '/problemmanagement',
                    component:"ProblemManagement",
                },
                {
                    name: '发布测验',
                    icon: 'el-icon-plus',
                    path: '/publishexercise',
                    component:"PublishExercise",
                },
                {
                    name: '我的测验',
                    icon: 'el-icon-edit',
                    path: '/myexercise',
                    component:"MyExercise",
                },
            ]
        },
        {
            name: '实验班级',
            icon: 'el-icon-s-order',
            path: '/experimentClass',
            child: [
                {
                    name: '我的班级',
                    icon: 'el-icon-s-flag',
                    path: '/myclass',
                    component:"MyClass",
                },
                {
                    name: '实验管理',
                    icon: 'el-icon-folder',
                    path: '/classexperiment',
                    component:"ClassExperiment",
                },
                {
                    name: '人员管理',
                    icon: 'el-icon-s-custom',
                    path: '/classstudent',
                    component:"ClassStudent",
                },
                {
                    name: '班级管理',
                    icon: 'el-icon-collection-tag',
                    path: '/classmanagement',
                    component:"ClassManagement",
                },
            ]
        },
        {
            name: '学习资料',
            icon: 'el-icon-notebook-2',
            path: '/file',
            child: [
                {
                    name: '资料列表',
                    icon: 'el-icon-files',
                    path: '/showstudyfile',
                    component: "ShowStudyFile"
                },
                {
                    name: '资料管理',
                    icon: 'el-icon-folder-checked',
                    path: '/managefile',
                    component: "ManageFile"
                },
            ]
        },
    ]

    return{
        menu_data
    }
});


export default[
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
                path: '/gradeset',
                component:"UserList",
                
            },
            {
                name: '提交实验报告',
                icon: 'el-icon-document-add',
                path: '/gradeset',
                component:"UserList",
                
            },
            {
                name: '批改实验报告',
                icon: 'el-icon-document-checked',
                path: '/gradeset',
                component:"UserList",
                
            },
            {
                name: '我的实验报告',
                icon: 'el-icon-folder-opened',
                path: '/gradeset',
                component:"UserList",
                
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
                path: '/userlist',
                component:"UserList",
            },
            {
                name: '发布测验',
                icon: 'el-icon-plus',
                path: '/userlist',
                component:"UserList",
            },
            {
                name: '我的测验',
                icon: 'el-icon-edit',
                path: '/userlist',
                component:"UserList",
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
                path: '/userlist',
                component:"UserList",
            },
            {
                name: '实验管理',
                icon: 'el-icon-folder',
                path: '/userlist',
                component:"UserList",
            },
            {
                name: '人员管理',
                icon: 'el-icon-s-custom',
                path: '/userlist',
                component:"UserList",
            },
            {
                name: '班级管理',
                icon: 'el-icon-collection-tag',
                path: '/userlist',
                component:"UserList",
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
                path: '/userlist',
                component:"UserList",
            },
            {
                name: '资料管理',
                icon: 'el-icon-folder-checked',
                path: '/userlist',
                component:"UserList",
            },
        ]
    },
]

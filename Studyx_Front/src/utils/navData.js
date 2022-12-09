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

]

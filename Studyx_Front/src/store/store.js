import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const state ={
    username: '明世隐',
    userState:0,
    menu_data:[],
    isLoadRoute:false,
    editableTabsValue: '1',
    editableTabs: [
        {
            title: '首页',
            name: '首页',
            content: '首页'
        },

    ]
}
const mutations ={
    setUser (state, name) {
        state.username = name
    },
    setUserState (state, data) {
        state.userState +=data
    },
    addEditableTabs(state,tab){
        //根据tab.name 查询 editableTabs 是否已经存在
        let idx = state.editableTabs.findIndex(item=>item.name===tab.name)
        if(idx===-1){//不存在则可以添加
            state.editableTabs.push({
                title: tab.name,
                name: tab.name
            })
        }
        state.editableTabsValue = tab.name
    },

}


const getters = {
    getUserState (state) {
        let data;
        if(state.userState==0){
            data='无效'
        }else{
            data = state.userState+'级'
        }
        return data;
    }
}
export default new Vuex.Store({
    state,
    mutations,
    getters,
})



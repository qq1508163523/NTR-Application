import {reqSearchResultByKeyword} from "@/api";


const state = {
    searchResult: {
        dataList:[]
    },
    searchQuery:{
        keyword:"",
        from: 1,
        sortBy: "releaseDate",
        orderBy: "DESC"
    }
};

const mutations = {
    GET_SEARCH_RESULT(state,searchResult){
        state.searchResult = searchResult;
    }
};

const actions = {
    async getSearchResult({commit,state}){
        let result = await reqSearchResultByKeyword(state.searchQuery);
        if(result.code === 200){
            commit("GET_SEARCH_RESULT",result.data)
        }
    }
};

const getters = {};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
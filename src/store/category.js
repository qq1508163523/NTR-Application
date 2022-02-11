import {reqIndexTable,reqCategorySearchResult} from "@/api";


const state = {
    indexTable: {},
    categoryResult: {
        dataList:[]
    },
    categoryQuery:{
        genre: null,
        year: null,
        month: null,
        status: null,
        sortBy: "releaseDate",
        order: "DESC",
        from: 1
    }
};

const mutations = {
    GET_INDEX_TABLE(state,indexTable){
        indexTable.yearList.unshift("全部");
        indexTable.monthList.unshift("全部");
        indexTable.statusList.unshift("全部");
        indexTable.genreList.unshift({genreName: "全部"});
        state.indexTable = indexTable;
    },
    GET_CATEGORY_RESULT_LIST(state,categoryResult){
        state.categoryResult = categoryResult;
    }
};

const actions = {
    async getIndexTable({commit}){
        let result = await reqIndexTable();
        if(result.code === 200){
            commit("GET_INDEX_TABLE",result.data)
        }
    },
    async getCategoryResult({commit,state}){
        let result = await reqCategorySearchResult(state.categoryQuery);
        if(result.code === 200){
            commit("GET_CATEGORY_RESULT_LIST",result.data)
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
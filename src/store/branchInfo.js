import {reqBranchByBranchId,
        reqRelateByBranchId,
        reqSimilarRecommendByBranchId,
        reqEpisodesByBranchId,
    reqEpisodeByBranchIdAndEpisodeNumber,
    reqCollectionByBranchId,
    reqInsertCollection,
    reqDeleteCollection,
    reqCommentByBranchId} from "@/api";

const state = {
    branch: {},
    relateList: [],
    similarRecommendList: [],
    episodeList: [],
    episode: [],
    commentRoot: {
        dataList: []
    },
    isCollected: false
};

const mutations = {
    GET_BRANCH(state,branch){
        state.branch = branch;
    },
    GET_RELATE_LIST(state,relateList){
        state.relateList = relateList;
    },
    GET_SIMILAR_RECOMMEND_LIST(state,similarRecommendList){
        state.similarRecommendList = similarRecommendList;
    },
    GET_EPISODE_LIST(state,episodeList){
        state.episodeList = episodeList.sort((v1,v2)=>{
            let t1 = parseInt(v1.episodeNumber);
            let t2 = parseInt(v2.episodeNumber);
            if(isNaN(t1) && isNaN(t2)){
                return (v1<v2?-1:(v1>v2?1:0));
            }else if(isNaN(t1)){
                return 1;
            }else if(isNaN(t2)){
                return -1;
            }else{
                return t1 - t2;
            }
        });
    },
    GET_EPISODE(state,episode){
        state.episode = episode;
    },
    GET_COLLECTION(state,collection){
        state.isCollected = collection !== null;
    },
    INSERT_COLLECTION(state){
        state.isCollected = true;
    },
    DELETE_COLLECTION(state){
        state.isCollected = false;
    },
    GET_COMMENT(state,commentRoot){
        state.commentRoot = commentRoot;
    }
};

const actions = {
    async getBranch({commit},branchId){
        let result = await reqBranchByBranchId(branchId);
        if(result.code === 200){
            commit("GET_BRANCH",result.data)
        }
    },
    async getRelateList({commit},branchId){
        let result = await reqRelateByBranchId(branchId);
        if(result.code === 200){
            commit("GET_RELATE_LIST",result.data)
        }
    },
    async getSimilarRecommendList({commit},branchId){
        let result = await reqSimilarRecommendByBranchId(branchId);
        if(result.code === 200){
            commit("GET_SIMILAR_RECOMMEND_LIST",result.data)
        }
    },
    async getEpisodeList({commit},branchId){
        let result = await reqEpisodesByBranchId(branchId);
        if(result.code === 200){
            commit("GET_EPISODE_LIST",result.data)
        }
    },
    async getEpisode({commit},{branchId,episodeNumber,callback}){
        let result = await reqEpisodeByBranchIdAndEpisodeNumber(branchId,episodeNumber);
        if(result.code === 200){
            commit("GET_EPISODE",result.data);
            if(callback !== undefined){
                callback(result.data.videoUrl);
            }
        }
    },
    async getComment({commit},{branchId,pageNum}){
        let result = await reqCommentByBranchId(branchId,pageNum);
        if(result.code === 200){
            commit("GET_COMMENT",result.data)
        }
    },
    async getCollection({commit},branchId){
        let result = await reqCollectionByBranchId(branchId);
        if(result.code === 200){
            commit("GET_COLLECTION",result.data);
        }
    },
    async insertCollection({commit},branchId){
        let result = await reqInsertCollection(branchId);
        if(result.code === 200){
            commit("INSERT_COLLECTION");
            return "OK";
        }else{
            return Promise.reject(new Error(result.message));
        }
    },
    async deleteCollection({commit},branchId){
        let result = await reqDeleteCollection(branchId);
        if(result.code === 200){
            commit("DELETE_COLLECTION");
            return "OK";
        }else{
            return Promise.reject(new Error(result.message));
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
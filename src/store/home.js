import {reqCarousel, reqDailyRecommend, reqWeekSchedule,reqRecentUpdate} from "@/api";

const state = {
    carouselList: [],
    dailyRecommendList: [],
    weekScheduleList: [],
    recentUpdateList: []
};

const mutations = {
    GET_CAROUSEL_LIST(state, carouselList){
        state.carouselList = carouselList;
    },
    GET_DAILY_RECOMMEND_LIST(state,dailyRecommendList){
        state.dailyRecommendList = dailyRecommendList;
    },
    GET_WEEK_SCHEDULE(state,weekScheduleList){
        state.weekScheduleList = weekScheduleList;
    },
    GET_RECENT_UPDATE(state,recentUpdateList){
        state.recentUpdateList = recentUpdateList;
    }
};

const actions = {
    async getCarouselList({commit}){
        let result = await reqCarousel();
        if(result.code === 200){
            commit("GET_CAROUSEL_LIST",result.data)
        }
    },
    async getDailyRecommendList({commit}){
        let result = await reqDailyRecommend();
        if(result.code === 200){
            commit("GET_DAILY_RECOMMEND_LIST",result.data)
        }
    },
    async getWeekSchedule({commit}){
        let result = await reqWeekSchedule();
        if(result.code === 200){
            commit("GET_WEEK_SCHEDULE",result.data)
        }
    },
    async getRecentUpdate({commit}){
        let result = await reqRecentUpdate();
        if(result.code === 200){
            commit("GET_RECENT_UPDATE",result.data)
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
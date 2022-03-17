import {login,reqAccountInfo,reqUpdateNickname,reqModifyPassword,reqUpdateProfileImage} from "@/api";

const state = {
    account: {},
    token: sessionStorage.getItem("token")
};

const mutations = {
    GET_TOKEN(state,token){
        state.token = token;
    },
    GET_ACCOUNT(state,account){
        state.account = account
    },
    UPDATE_NICKNAME(state,nickname){
        state.account.nickname = nickname;
    },
    CLEAR_STATE(state){
        sessionStorage.removeItem("token");
        state.token = null;
        state.account = {};
    },
    UPDATE_PROFILE_IMAGE(state,profilePicUrl){
        state.account.profilePicUrl = profilePicUrl;
    }
};

const actions = {
    async userLogin({commit},loginForm){
        const formData = new FormData();
        formData.append("username",loginForm.username);
        formData.append("password",loginForm.password);
        let result = await login(formData);
        if(result.code === 200){
            commit('GET_TOKEN',result.data.token);
            sessionStorage.setItem("token",result.data.token);
            return result.message;
        }else{
            return Promise.reject(new Error(result.message));
        }
    },
    async getAccount({commit,state}) {
        if(state.token === null) return;
        let result = await reqAccountInfo();
        if (result.code === 200) {
            commit('GET_ACCOUNT', result.data);
        }
    },
    async updateNickname({commit},nickname){
        let result = await reqUpdateNickname(nickname);
        if (result.code === 200) {
            commit('UPDATE_NICKNAME', nickname);
            return result.message;
        }else{
            return Promise.reject(new Error(result.message));
        }
    },
    async modifyPassword({commit},formData){
        let result = await reqModifyPassword(formData);
        if(result.code === 200){
            commit('CLEAR_STATE');
            return result.message;
        }else{
            return Promise.reject(new Error(result.message));
        }
    },
    async updateProfileImage({commit},formData){
        let result = await reqUpdateProfileImage(formData);
        if(result.code === 200){
            commit('UPDATE_PROFILE_IMAGE',result.data.profilePicUrl);
            return result.message;
        }else{
            return Promise.reject(new Error(result.message));
        }
    },
    logout({commit}){
        commit('CLEAR_STATE');
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
// import vuex
import Vuex from "vuex";
// import vue
import Vue from "vue";

// add to plugin
Vue.use(Vuex);

// import module
import home from "@/store/home";
import branchInfo from "@/store/branchInfo";
import category from "@/store/category";
import search from "@/store/search";
import account from "@/store/account";

// export store
export default new Vuex.Store({
   modules: {
       home,
       branchInfo,
       category,
       search,
       account
   }
});
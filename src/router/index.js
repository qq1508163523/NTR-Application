import VueRouter from "vue-router";

const router = new VueRouter({
   routes:[
       {
           name: "home",
           path: "/",
           component:  () => import("@/pages/Home") // speed up loading
       },
       {
           name: "detail",
           path: "/detail/:branchId",
           component: () => import("@/pages/Detail"),
           props($route){
               return {
                   branchId: $route.params.branchId
               }
           }
       },
       {
           name: "search",
           path: "/search",
           component: () => import("@/pages/Search"),
           props($route){
               return {
                   keyword: $route.query.keyword,
               }
           }
       },
       {
           name: "category",
           path: "/category",
           component: () => import("@/pages/Category")
       },
       {
           name: "play",
           path: "/play/:branchId",
           component: () => import("@/pages/Play"),
           props($route){
               return {
                   branchId: $route.params.branchId,
                   episodeNumber: $route.query.episodeNumber
               }
           }
       },
       {
           name: "register",
           path: "/register",
           component: () => import("@/pages/Register")
       },
       {
           name: "login",
           path: "/login",
           component: () => import("@/pages/Login")
       },
       {
           name: "space",
           path: "/space",
           component: () => import("@/pages/Space"),
           children: [
               {
                   path: "info",
                   component: () => import("@/pages/Space/Info")
               },
               {
                   path: "security",
                   component: () => import("@/pages/Space/Security")
               },
               {
                   path: "collection",
                   component: () => import("@/pages/Space/Collection")
               },
               {
                   path: "comment",
                   component: () => import("@/pages/Space/MyComment")
               },
               {
                   path: "profilePic",
                   component: () => import("@/pages/Space/Picture")
               }
           ],
           beforeEnter: (to,from,next) => {
               if(sessionStorage.getItem("token") !== null){
                   next();
               }else{
                   window.location.href = "/#/login";
               }
           }
       },
       {
           name: "404",
           path: "/404",
           component: () => import("@/pages/NotFound")
       },
       {
           path: "*",
           redirect: "/404"
       }
   ]
});

router.afterEach((to,from)=>{
    let top = document.documentElement.scrollTop || document.body.scrollTop;
    const timerTop = setInterval(()=>{
        document.body.scrollTop = document.documentElement.scrollTop = top -= 50;
        if(top <= 0){
            clearInterval(timerTop);
        }
    },10);
});

export default router;
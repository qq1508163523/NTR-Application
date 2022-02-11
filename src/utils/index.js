import dateFormat from "dateformat";
import Vue from "vue";
import {Loading} from "element-ui";


Vue.filter("dateFormatter",function (date,str='yyyy-mm-dd') {
    return dateFormat(date,str);
});

Vue.filter("mapEpisodeNumber",function (episodeNumber){
    if(isNaN(parseInt(episodeNumber))){
        return episodeNumber;
    }else{
        return "第"+episodeNumber+"集";
    }
});

Vue.filter("mapCategory",function (item,appendVal){
    return item !== '全部'?(item+appendVal):item;
});

Vue.filter("mapRole",function (roleVal){
    if(roleVal === 'USER') return '普通用户';
    else if(roleVal === 'GOD') return '神';
    else return '管理员';
});

Vue.prototype.$backTop = () =>{
    let top = document.documentElement.scrollTop || document.body.scrollTop;
    const timerTop = setInterval(()=>{
        document.body.scrollTop = document.documentElement.scrollTop = top -= 50;
        if(top <= 0){
            clearInterval(timerTop);
        }
    },10);
}

Vue.prototype.$processing = (target,text) => {
    return Loading.service({
        target,
        lock: true,
        text,
        spinner: 'el-icon-loading'
    });
}
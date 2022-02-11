import axios from "axios";
import nprogress from "nprogress";
import store from "@/store";
import "nprogress/nprogress.css";

let requests = axios.create({
    baseURL: "/api/v1",
    timeout: 10000,
});

requests.interceptors.request.use((config) => {
    nprogress.start();
    if(store.state.account.token){ // set token to headers
        config.headers.token = store.state.account.token;
    }
    return config;
});

requests.interceptors.response.use(
    (res) => {
        nprogress.done();
        return res.data;
    },
    (err) => {
        nprogress.done();
        alert(err);
    }
);

export default requests;
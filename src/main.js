import Vue from 'vue';
import App from './App.vue';
// import router
import router from "@/router";

//import store
import store from "@/store";

//import utils
import "@/utils";

//import element-ui component
import {
  Container,
  Header,
  Footer,
  Main,
  Row,
  Col,
  Input,
  Carousel,
  CarouselItem,
  Icon,
  Tabs,
  TabPane,
  Breadcrumb,
  BreadcrumbItem,
  Button,
  Pagination,
  Empty,
  Form,
  FormItem,
  Steps,
  Step,
  Result,
  Message,
  Menu,
  MenuItem,
  Popover,
  Divider,
  Dialog,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Link,
  Checkbox
}
from "element-ui";
//element-ui style
import 'element-ui/lib/theme-chalk/index.css';

import VueRouter from "vue-router";

Vue.config.productionTip = false;

//register ui components
Vue.component(Container.name,Container);
Vue.component(Header.name,Header);
Vue.component(Footer.name,Footer);
Vue.component(Main.name,Main);
Vue.component(Row.name,Row);
Vue.component(Col.name,Col);
Vue.component(Input.name,Input);
Vue.component(Carousel.name,Carousel);
Vue.component(CarouselItem.name,CarouselItem);
Vue.component(Icon.name,Icon);
Vue.component(Tabs.name,Tabs);
Vue.component(TabPane.name,TabPane);
Vue.component(Breadcrumb.name,Breadcrumb);
Vue.component(BreadcrumbItem.name,BreadcrumbItem);
Vue.component(Button.name,Button);
Vue.component(Pagination.name,Pagination);
Vue.component(Empty.name,Empty);
Vue.component(Form.name,Form);
Vue.component(FormItem.name,FormItem);
Vue.component(Steps.name,Steps);
Vue.component(Step.name,Step);
Vue.component(Result.name,Result);
Vue.component(Menu.name,Menu);
Vue.component(MenuItem.name,MenuItem);
Vue.component(Popover.name,Popover);
Vue.component(Divider.name,Divider);
Vue.component(Dialog.name,Dialog);
Vue.component(Dropdown.name,Dropdown);
Vue.component(DropdownMenu.name,DropdownMenu);
Vue.component(DropdownItem.name,DropdownItem);
Vue.component(Link.name,Link);
Vue.component(Checkbox.name,Checkbox);



Vue.use(VueRouter);

new Vue({
  render: h => h(App),
  router,
  store,
  beforeCreate() {
    Vue.prototype.$bus = this;
    Vue.prototype.$message = Message;
  }
}).$mount('#app');

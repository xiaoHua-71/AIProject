import { createApp } from "vue";
import App from "./App.vue";
import { createPinia } from 'pinia'
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import router from "./router";
import "@/access";

const pinia = createPinia();

createApp(App).use(ArcoVue).use(router).use(pinia).mount("#app");

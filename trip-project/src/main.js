// /**
//  * main.js
//  *
//  * Bootstraps Vuetify and other plugins then mounts the App`
//  */

// // Plugins
// import { registerPlugins } from '@/plugins'

// // Components
// import App from './App.vue'

// // Composables
// import { createApp } from 'vue'

// const app = createApp(App)

// registerPlugins(app)

// app.mount('#app')

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

const vuetify = createVuetify({
  components,
  directives,
});
const app = createApp(App);

//로컬스토리지와 연동
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(pinia);
app.use(router);
app.use(vuetify);
app.mount("#app");

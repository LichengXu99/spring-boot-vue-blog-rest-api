import {createApp} from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'

import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

/** Md-editor */
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
import zhTW from "@kangc/v-md-editor/es/lang/zh-TW";
// Preview
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// Highlightjs
import hljs from 'highlight.js';

VueMarkdownEditor
    .use(vuepressTheme, { Prism })
    .lang
    .use('zh-tw', zhTW);

VMdPreview
    .use(vuepressTheme, { Hljs: hljs });

/** Md-editor */

const app = createApp(App)
app.use(router)
app.use(store)
app.use(VueMarkdownEditor)
app.use(VMdPreview)
app.mount('#app')

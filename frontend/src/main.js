import {createApp} from 'vue'
import router from './router'
import App from './App.vue'

import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
// import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

// 引入你所使用的主题 此处以 github 主题为例
// import githubTheme from '@kangc/v-md-editor/lib/theme/github';
// import '@kangc/v-md-editor/lib/theme/style/github.css';

import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
import createHighlightLinesPlugin from '@kangc/v-md-editor/lib/plugins/highlight-lines/index';
import '@kangc/v-md-editor/lib/plugins/highlight-lines/highlight-lines.css';

// highlightjs
// import hljs from 'highlight.js';

import Prism from 'prismjs';

// 选择使用主题
// VMdPreview.use(githubTheme, {Hljs: hljs});
VMdPreview.use(vuepressTheme, {
    Prism
})
// markdown支持显示代码行数
VMdPreview.use(createLineNumbertPlugin)
// markdown支持代码快速复制
VMdPreview.use(createCopyCodePlugin);
// markdown支持emoji
VMdPreview.use(createEmojiPlugin);
VMdPreview.use(createHighlightLinesPlugin)


const app = createApp(App)
app.use(router)
app.use(VMdPreview)
app.mount('#app')

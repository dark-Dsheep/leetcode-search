<template>
  <div id="logo"><a href="https://leetcode.cn/"><img alt="lc" src="../assets/img/lc_logo.png"> </a></div>
  <div v-if="engines" id="site-main">
    <div id="search-bar" >
      <div id="container" >
        <div id="headline-content">
<!--          <form id="search-form" ref="search_form" :action=engines[search_engine][0] target="_blank">-->
<!--            <input id="search-keyword" ref="search_input" v-model="keyword" :name=engines[search_engine][1]-->
<!--                   :placeholder=engines[search_engine][2]-->
<!--                   autocomplete=off autofocus class="float-left" type=search-->
<!--                   @blur="blur()" @focus="focus()" @input="get_hot_keyword()" @keydown.down="down()"-->
<!--                   @keydown.prevent.up="up()">-->
<!--            <input id="search-form-submit" class="float-right" type="submit" value="搜索">-->
<!--          </form>-->
          <form id="search-form" ref="search_form" @submit.prevent="queryCodeInfo">
            <input id="search-keyword" ref="search_input" v-model="codeInfoQueryVO.keyword"
                   placeholder="输入力扣题目名称或者题号"
                   autocomplete=off autofocus class="float-left" type="search"
                   @blur="blur()" @focus="focus()"
                   @keyup.enter="queryCodeInfo()"
            >
            <input id="search-form-submit" class="float-right" type="submit" value="搜索">
          </form>
<!--          <div id="search-hot" :style="search_hot_display">-->
<!--            <ul>-->
<!--              <li v-for="(key,index) in keywords" v-bind:key="key" :class="{selected:key_selected === index}"-->
<!--                  @click="go_submit(key)">-->
<!--                <span :class="'search_index' + index">{{ index + 1 }}</span>{{ key }}-->
<!--              </li>-->
<!--            </ul>-->
<!--          </div>-->
        </div>
        <!-- 代码显示 -->
        <v-md-preview v-if="codeInfoMd !== ''" :text="codeInfoMd">
        </v-md-preview>
      </div>
<!--      style="height:300px;overflow-y:auto"-->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import storage from "@/utils/storage";
import fetchJsonp from 'fetch-jsonp';
import { QueryCodeInfo } from '@/api/CodeInfoApi'

export default {
  name: "Home",
  data() {
    return {
      search_engine: "",
      engines: "",
      engine_names: "",
      keyword: "",
      keywords: [],
      key_selected: -1,
      search_hot_display: "display: none",
      codeInfoQueryVO: {
        questionNo: "",
        questionName: "",
        keyword: "1"
      },
      codeInfoMd: ""
    }
  },
  created() {
    this.codeInfoQueryVO.keyword = ''
    // 将引擎列表设置为本地存储
    if (storage.get("engines")) {
      this.engines = storage.get("engines")
      this.engine_names = storage.get("engines")["list"]
      if (storage.get("search_engine")) {
        this.set_search_engine(storage.get("search_engine"))
      } else {
        this.set_search_engine(this.engine_names[0])
      }
    } else {
      let engines;
      engines = () => axios.get("./json/search_engine.json")
      engines().then(res => {
        storage.set("engines", res.data)
        this.engines = res.data
        this.engine_names = storage.get("engines")["list"]
        this.set_search_engine(this.engine_names[0])
      })
    }
  },
  methods: {
    set_search_engine(engine) {
      storage.set("search_engine", engine)
      this.search_engine = engine
    },
    get_search_engine() {
      return storage.get("search_engine")
    },
    get_hot_keyword() {
      if (this.keyword === "") {
        this.keywords = []
        return;
      }
      // https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su?wd=golang&cb=xxxxx
      if (this.keyword !== '' && this.keyword !== '0') {
        clearTimeout(this.timer) // 清除定时器
        this.timer = setTimeout(() => {
          let api = 'https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su?wd=' + this.keyword;
          fetchJsonp(api, {
            jsonpCallback: 'cb'
          })
              .then((response) => {
                return response.json()
              }).then((data) => {
            this.keywords = data.s // 用到this一定要注意this指向
          }).catch((error) => {
            console.log(error)
          })
        }, 50)
      } else {
        this.keywords = []
      }
    },
    go_submit(val) {
      this.keyword = val
      this.$refs.search_input.value = val
      this.$refs.search_form.submit()
    },
    getData(url) {
      fetchJsonp(url, {
        jsonpCallback: 'callback'
      })
          .then((response) => {
            console.log(response);
            console.log(response.json());
            return response.json();
          }).then((json) => {
        this.users = json;	// 在此处进行接收数据之后的操作
      }).catch((error) => {
        console.log(error);	// 此处是数据请求失败后的处理
      })
    },
    down() {
      this.key_selected = (this.key_selected + 1) % this.keywords.length
      this.keyword = this.keywords[this.key_selected]
      this.$refs.search_input.value = this.keywords[this.key_selected]
    },
    up() {
      this.key_selected = (this.key_selected - 1 + this.keywords.length) % this.keywords.length
      this.keyword = this.keywords[this.key_selected]
      this.$refs.search_input.value = this.keywords[this.key_selected]
    },
    blur() {
      clearTimeout(this.timer);  //清除延迟执行
      this.timer = setTimeout(() => {   //设置延迟执行
        this.search_hot_display = "display: none"
        this.key_selected = -1
      }, 100)
    },
    focus() {
      this.search_hot_display = "display: block"
      this.key_selected = -1
      this.get_hot_keyword()
    },
    queryCodeInfo() {
      // console.log('表单提交')
      QueryCodeInfo(this.codeInfoQueryVO)
          .then(response => {
            this.codeInfoMd = response.data
            console.log(this.codeInfoMd)
          })
          .catch(err => {
            console.log(err)
          })
    }
  }
}
;
</script>
<style lang="less">
@import "../style/search";
</style>

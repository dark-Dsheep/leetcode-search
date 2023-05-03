<template>
  <div id="content" ref="content" >
    <div id="top-menu-list">
      <ul id="top-menu-ul">
        <li :class="{selected:this.$route.path==='/'}">
          <router-link to="/">首页</router-link>
        </li>
      </ul>
    </div>
    <router-view ></router-view>
    <!--    <Background @background="set_background"></Background>-->
    <div id="message"></div>
  </div>
</template>
<script>
import packageJson from '../package.json'
// import storage from "@/utils/storage";
// import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      version: packageJson.version,
      is_on: false,
      menu_selected: "bookmark",
      background: "",
    }
  },
  methods: {
    set_background(data) {
      this.background = data;
    },
  },
  watch: {
    background() {
      try {
        this.$refs.content.style.background = this.background
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    this.$refs.content.style.background = this.background
  }
};
</script>

<style lang='less'>
@import "style/main";
</style>

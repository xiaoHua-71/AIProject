<template>
  <div class="home">
    {{aaa}}
  </div>
</template>

<script setup lang="ts">
import { defineComponent } from "vue";
import HelloWorld from "@/components/HelloWorld.vue"; // @ is an alias to /src

const aaa = "欢迎来到简医答题平台";
</script>

<style scoped>
	#home{
		color: aqua;
	}
</style>

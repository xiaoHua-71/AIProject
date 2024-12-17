<template>
		<a-row class="GlobalHeader" align="center" :wrap="false">
			<a-col flex="auto">
				<a-menu mode="horizontal" :selected-keys="selectedKeys" @menu-item-click="doMenuClick">
						<a-menu-item key="0" :style="{ padding: 0, marginRight: '38px' }" disabled>
							<div class="titleBar">
								<img class="logo" src="../assets/logo.png"/>
								<div class="title">智能问答</div>
								
							</div>
						</a-menu-item>
						<a-menu-item v-for="item in visibleRoutes" :key="item.path">{{item.name}}</a-menu-item>
					</a-menu>
			</a-col>
			
			<a-col flex="100px">
				<div>
					<a-button type="primary" href="/user/login">登录</a-button>
					
				</div>
			</a-col>
		</a-row>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";

const router = useRouter();
//当前栏选中菜单项
const selectedKeys = ref(["/"]);
//路由跳转时，自动更新选中的菜单项
router.afterEach((to,from,failure) =>{
	selectedKeys.value = [to.path];
});
//点击菜单跳转到对应的页面
const doMenuClick = (key:string) =>{
	router.push({
		path: key,
	});
};
//展示在菜单栏的路由数组
const visibleRoutes = routes.filter((item) => {
	if(item.meta?.hideInMenu){
		return false;
	}
	return true;
});

</script>

<!-- 	.类选择器 #id选择器 -->
<style>
	#GlobalHeader{}
	
	.titleBar{
		display: flex;
		/* 垂直居中 */
		align-items: center;
	}
	.title{
		color: black;
		margin-left: 16px;
		font-weight: bold;
		
	}
	.logo{
		height: 48px;
	
	}
</style>
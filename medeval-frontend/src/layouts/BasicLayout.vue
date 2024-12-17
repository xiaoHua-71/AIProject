<template>
	<div id="bascLayout">
		<a-layout style="height: 100vh;">
				<a-layout-header class="header">
					<GlobalHeader />
				</a-layout-header>
				<a-layout-content class="content">
					<router-view />
				</a-layout-content>
				<a-layout-footer class="footer">
					<a href="https://github.com/xiaoHua-71" 
					target="_blank" style="text-decoration: none;color: black;">
						智能问答 By 小花
					</a>
				</a-layout-footer>
			</a-layout>
	</div>
	
</template>


<script setup lang="ts">
	import GlobalHeader from "../components/GlobalHeader.vue"
</script>

<!-- 	.类选择器 #id选择器 -->
<style>
	
	#bascLayout .content{
		/* 渐变色 */
		background: linear-gradient(to right,#fefefe , #fff);
		margin-bottom: 28px;
		padding: 20px;
		
	}
	
	#bascLayout .header{
		/* 底部外边距 */
		margin-bottom: 16px;
		box-shadow: #eee 1px 1px 5px;
		
	}

	.footer{
		background: #e3e3e3;
		/* 增加边距 */
		padding: 16px;
		text-align: center;

	}
</style>
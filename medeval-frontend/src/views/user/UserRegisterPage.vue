<template>
  <div id="UserRegisterPage">
    <h2 style="margin-bottom: 16px">用户注册</h2>
    <a-form
      style="max-width: 320px; margin: 0 auto"
      label-align="left"
      auto-label-width
      :model="form"
      @submit="handleSubmit"
    >
      <a-form-item field="userAccount" label="账号">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item field="userPassword" tooltip="密码不少于 8 位" label="密码">
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
			<a-form-item field="checkPassword" tooltip="密码不少于 8 位" label="密码">
			  <a-input-password
			    v-model="form.checkPassword"
			    placeholder="请确认密码"
			  />
			</a-form-item>
				<a-form-item>
				<div style="display: flex; width: 100%; justify-content: space-between; align-items: center;">
					<a-button type="primary" html-type="submit" style="width: 120px">
					  注册
					</a-button>
					<a-link href="/user/login" style="text-decoration: none; ">有账号点击登录</a-link>
				</div>
				</a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
	
import { reactive } from "vue";
import API from "@/api";
import { userRegisterUsingPost } from "@/api/userController";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";

const router = useRouter();
/**
 * 表单信息
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as API.UserRegisterRequest);

/**
 * 提交
 */
const handleSubmit = async () => {
  const res = await userRegisterUsingPost(form);
  if (res.data.code === 0) {
    message.success("注册成功");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("注册失败，" + res.data.message);
  }
};
</script>

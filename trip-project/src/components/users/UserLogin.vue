<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/store/auth";
const authStore = useAuthStore();

const router = useRouter();

const loginForm = ref({
  userId: authStore.rememberedId,
  userPassword: "",
});

const login = async () => {
  try {
    await authStore.login(loginForm.value);
    router.push("/");
  } catch (error) {
    console.log(loginForm.value);
    console.log("로그인 실패 에러:", error);
    alert("아이디 또는 비밀번호가 일치하지 않습니다.");
  }
};
</script>

<template>
  <div><h3>로그인</h3></div>
  <div>
    <div>
      <form @submit.prevent="login">
        <p>
          <label for="signin-id"></label>
          <input type="text" v-model="loginForm.userId" placeholder="아이디" />
        </p>
        <p>
          <label for="singin-password"></label>
          <input
            type="password"
            v-model="loginForm.userPassword"
            placeholder="비밀번호"
            autocomplete="off"
          />
        </p>
        <p>
          <input
            type="checkbox"
            id="remember-id"
            v-model="authStore.isRemember"
          />
          <label for="remember-id">아이디 기억하기</label>
        </p>
        <p>
          <button>로그인</button>
        </p>
      </form>
    </div>
    <div></div>
  </div>
</template>

<style scoped></style>

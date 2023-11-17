<script setup>
import { ref } from "vue";
import { useAuthStore } from "@/store/auth";
const authStore = useAuthStore();

const userInfo = ref({
  userId: "",
  password: "",
  nickname: "",
  email: "",
  userType: "",
});

const join = async () => {
  try {
  } catch (error) {
    console.log("회원가입 실패 에러:", error);
    alert("아이디가 이미 존재합니다.");
  }
};
</script>

<template>
  <div><h3>회원가입</h3></div>
  <form>
    <p>
      <label></label>
      <input type="text" v-model="userInfo.userId" placeholder="아이디" />
    </p>
    <p>
      <label for="singin-password"></label>
      <input
        type="password"
        v-model="userInfo.password"
        placeholder="비밀번호"
      />
    </p>
    <p>
      <label for="nickname"></label>
      <input type="text" v-model="userInfo.nickname" placeholder="닉네임" />
    </p>
    <p>
      <label for="nickname"></label>
      <input type="email" v-model="userInfo.email" placeholder="이메일" />
    </p>
    <v-select
      label="당신의 타입은?"
      density="compact"
      :items="['MZ', 'X']"
      :item-props="['1980~2010년생', '1965~1979년생']"
      v-model="userInfo.userType"
    ></v-select>
    <p>
      <label for="agreement"></label>
      <input type="checkbox" /> <a href="">약관 동의</a>
    </p>
    <p>
      <input type="button" value="가입하기" />
    </p>
  </form>
</template>

<style scoped></style>

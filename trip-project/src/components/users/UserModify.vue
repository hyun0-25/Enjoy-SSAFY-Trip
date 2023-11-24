<script setup>
import { ref } from "vue";
import { useAuthStore } from "@/store/auth";
import { useDate } from "vuetify";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const authStore = useAuthStore();

// 유저 정보
const userInfo = ref({
  userId: authStore.user.userId,
  userPassword: "",
  againPassword: "",

  userNickname: authStore.user.userNickname,
  role: authStore.user.role,
  email: authStore.user.email,
  userType: authStore.user.userType,
  birthday: "2000-02-22",
  // birthday: authStore.user.birthday,
});

//달력 관련
const date = ref(
  new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
    .toISOString()
    .slice(0, 10)
);
const menu = ref(false);

const saveDate = () => {
  birthday.value = date.value;
  dialog.value = false;
};
const cancleMenu = () => {
  menu.value = false;
};

const dialog = ref(false);

//정보 수정
const modify = async () => {
  console.log(userInfo.value.userId);
  try {
    await authStore.modify(userInfo.value);
    alert("재로그인 해주세요.");
    router.push("/");
  } catch (error) {
    console.log(userInfo.value);
    console.log("로그인 실패 에러:", error);
    alert("정보 수정 실패");
  }
};
// 취소 버튼
</script>

<template>
  <!-- 사용자 정보 수정 -->
  <!-- 사진, 닉네임, 비밀번호, 이메일  -->
  <v-card title="정보 수정">
    <form @submit.prevent="modify">
      <!-- 아이디 -->
      <v-text-field v-model="userInfo.userId" label="아이디" disabled="">
      </v-text-field>
      <!-- 비밀번호 -->
      <v-text-field
        type="password"
        v-model="userInfo.userPassword"
        label="비밀번호"
        autocomplete="off"
        id="singin-password"
      >
      </v-text-field>
      <!-- 비밀번호 확인 -->
      <v-text-field
        type="password"
        v-model="userInfo.againPassword"
        label="비밀번호 확인"
        autocomplete="off"
        id="repeat-password"
      >
      </v-text-field>
      <!-- 닉네임 -->
      <v-text-field
        v-model="userInfo.userNickname"
        label="닉네임"
        id="nickname"
      >
      </v-text-field>
      <!-- 이메일 -->
      <v-text-field v-model="userInfo.email" label="이메일" id="email">
      </v-text-field>
      <!-- 생일 -->
      <!-- <div class="text-center"> -->
      <!-- <v-text-field
          v-model="userInfo.birthday"
          label="생일"
          @click="dialog = true"
        > -->
      <!-- {{ userInfo.birthday }} -->
      <!-- </v-text-field> -->

      <!-- <v-dialog v-model="dialog" width="auto">
          <v-date-picker></v-date-picker>
          <v-btn @click="saveDate">확인</v-btn>
        </v-dialog>
      </div> -->

      <!-- 프로필 사진 -->
      <!-- <P>
        <v-file-input label="프로필 사진 File input"></v-file-input>
      </P> -->
      <!-- button -->

      <v-btn @click="modify"> 수정하기 </v-btn>
    </form>
  </v-card>
</template>

<style scoped>
#modify-button {
  align-items: end;
}
</style>

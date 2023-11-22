<script setup>
import { ref, defineEmits } from "vue";
import { useAuthStore } from "@/store/auth";
import { useDate } from "vuetify";

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
const adapter = useDate();

function allowedDates(val) {
  return parseInt(adapter.toISO(val).split("-")[2], 10) % 2 === 0;
}

const dialog = ref(false);

//정보 수정
const modify = async () => {
  try {
    if (userInfo.userPassword == userInfo.againPassword) {
      await authStore.login(userInfo.value);
      alert("재로그인 해주세요.");
    }
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
      <v-text-field v-model="userInfo.email" label="이메일" id="eamil">
      </v-text-field>
      <!-- 생일 -->
      <v-dialog v-model="dialog" width="600">
        <template v-slot:activator="{ on, attrs }">
          <a v-on:click="() => (dialog = !dialog)" v-bind="attrs"
            >생일 수정하기</a
          >
        </template>
        <template v-slot:default="{ isActive }">
          <v-card title="날짜">
            <v-card-text>
              <v-container>
                <v-row justify="space-around">
                  <v-date-picker
                    v-model="date"
                    :allowed-dates="allowedDates"
                    min="1950-06-15"
                    max="2018-03-20"
                  ></v-date-picker>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn text="확인" @click="isActive.value = false"></v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>

      <!-- 프로필 사진 -->
      <P>
        <v-file-input label="프로필 사진 File input"></v-file-input>
      </P>
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

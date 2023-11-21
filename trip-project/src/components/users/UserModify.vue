<script setup>
import { ref } from "vue";
import { useAuthStore } from "@/store/auth";
import { useDate } from "vuetify";

const date = ref(new Date("2018-03-02"));
const adapter = useDate();

function allowedDates(val) {
  return parseInt(adapter.toISO(val).split("-")[2], 10) % 2 === 0;
}
const authStore = useAuthStore();

const userInfo = ref({
  userId: authStore.user.userId,
  userPassword: "",
  againPassword: "",

  userNickname: authStore.user.userNickname,
  role: authStore.user.role,
  email: authStore.user.email,
  userType: authStore.user.userType,
  birthday: authStore.user.birthday,
});
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
const dialog = ref(false);
</script>

<template>
  <!-- 사용자 정보 수정 -->
  <!-- 사진, 닉네임, 비밀번호, 이메일  -->
  <div><h3>정보 수정</h3></div>
  <form @submit.prevent="modify">
    <!-- 아이디 -->
    <p>
      <label for="singin-id">아이디 :</label>
      <input type="text" v-model="userInfo.userId" id="singin-id" readonly />
    </p>
    <!-- 비밀번호 -->
    <p>
      <label for="singin-password"></label>
      <input
        type="password"
        v-model="userInfo.userPassword"
        placeholder="비밀번호"
        autocomplete="off"
        id="singin-password"
      />
      <!-- 비밀번호 확인 -->
    </p>
    <p>
      <label for="repeat-password"></label>
      <input
        type="password"
        v-model="userInfo.againPassword"
        placeholder="비밀번호 확인"
        autocomplete="off"
        id="repeat-password"
      />
    </p>
    <!-- 닉네임 -->
    <p>
      <label for="nickname"></label>
      <input type="text" v-model="userInfo.userNickname" placeholder="닉네임" />
    </p>
    <!-- 이메일 -->
    <p>
      <label for="eamil"></label>
      <input type="email" v-model="userInfo.email" placeholder="이메일" />
    </p>
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
    <template> </template>
    <!-- button -->
    <button @click="modify">수정하기</button>
  </form>
</template>

<style scoped></style>

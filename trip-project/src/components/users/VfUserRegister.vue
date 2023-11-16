<script setup>
import { ref } from "vue";
import UserLogin from "./UserLogin.vue";
import UserRegister from "./UserRegister.vue";
const tab = ref("login");
</script>

<template>
  <v-card>
    <v-tabs v-model="tab" bg-color="primary">
      <v-tab value="login">로그인</v-tab>
      <v-tab value="join">회원가입</v-tab>
    </v-tabs>

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="login">
          <UserLogin></UserLogin>
        </v-window-item>

        <v-window-item value="join">
          <UserRegister></UserRegister>
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>

<style scoped></style>

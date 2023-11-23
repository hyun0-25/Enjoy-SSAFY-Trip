<script setup>
import { useAuthStore } from "@/store/auth";
import AdminPage from "@/components/users/AdminPage.vue";
import UserMyPage from "@/components/users/UserMyPage.vue";
const authStore = useAuthStore();
</script>

<template>
  <div></div>
  <router-view></router-view>
  <!-- <template v-if="authStore.user.role === 관리자">
    <AdminPage></AdminPage>
  </template>
  <template v-else>
    <UserMyPage></UserMyPage>
  </template> -->
</template>

<style scoped></style>

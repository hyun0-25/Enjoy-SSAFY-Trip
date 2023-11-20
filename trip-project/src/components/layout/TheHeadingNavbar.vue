<script setup>
import { ref, computed } from "vue";
import { storeToRefs } from "pinia";
import VfUserRegister from "../users/VfUserRegister.vue";
import { useAuthStore } from "@/store/auth";

const authStore = useAuthStore();
const isLogin = computed(() => !!authStore.token); //!!의 경우 해당 정보가 truthy하다면 true를 반환
const userId = computed(() => authStore.user.userId);
const logout = () => {
  if (!confirm("로그아웃 하시겠습니까?")) return;
  authStore.logout();
};

// 다이얼로그의 열림/닫힘 상태를 나타내는 데이터 속성
const dialog = ref(false);

const types = ref([{ title: "MZ" }, { title: "X" }]);
</script>

<template>
  <nav>
    <div class="flex-container">
      <!-- nav list -->
      <!-- 메인 로고 -->
      <div>
        <router-link :to="{ name: 'main' }">
          <img src="@/assets/ssafy_logo.png" alt="..." />
        </router-link>
      </div>
      <div>
        <ul>
          <li>공지사항</li>
          <li>
            <div class="text-center">
              <v-menu open-on-hover>
                <template v-slot:activator="{ props }">
                  <a v-bind="props"> 핫플레이스 </a>
                </template>
                <v-list>
                  <v-list-item v-for="(item, index) in types" :key="index">
                    <v-list-item-title
                      ><a href="">{{ item.title }}</a></v-list-item-title
                    >
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </li>
          <li>
            <div class="text-center">
              <v-menu open-on-hover>
                <template v-slot:activator="{ props }">
                  <a v-bind="props"> 커뮤니티 </a>
                </template>
                <v-list>
                  <v-list-item v-for="(item, index) in types" :key="index">
                    <v-list-item-title
                      ><a href="">{{ item.title }}</a></v-list-item-title
                    >
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </li>
          <li>
            <div class="text-center">
              <v-menu open-on-hover>
                <template v-slot:activator="{ props }">
                  <a v-bind="props"> 여행코스 </a>
                </template>

                <v-list>
                  <v-list-item v-for="(item, index) in types" :key="index">
                    <v-list-item-title
                      ><a href="">{{ item.title }}</a></v-list-item-title
                    >
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </li>
          <!-- 로그인하지 않은 경우 -->
          <template v-if="!isLogin">
            <li>
              <v-dialog v-model="dialog" width="600">
                <template v-slot:activator="{ on, attrs }">
                  <a v-on:click="() => (dialog = !dialog)" v-bind="attrs"
                    >로그인</a
                  >
                </template>
                <VfUserRegister v-if="dialog"></VfUserRegister>
              </v-dialog>
            </li>
          </template>
          <!-- 로그인한 경우 -->
          <template v-else>
            <li>
              <RouterLink :to="{ name: 'user-mypage' }">마이페이지</RouterLink>
            </li>
            <li>{{ userId }}({{ authStore.user.userType }})님 로그인</li>

            <li><button @click="logout">로그아웃</button></li>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.flex-container {
  /* display: flex; */
  text-align: center;
  widows: 100%;
}
ul {
  list-style: none;
}
li {
  display: inline-block;
  margin: 20px;
}
a {
  text-align: center;
  text-decoration: none;
  color: inherit;
}
</style>

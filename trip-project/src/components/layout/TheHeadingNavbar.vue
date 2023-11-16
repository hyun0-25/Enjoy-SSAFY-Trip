<script setup>
import { useMenuStore } from "@/store/menu";
import { storeToRefs } from "pinia";
import VfUserRegister from "../users/VfUserRegister.vue";

const menuStore = useMenuStore();
const { menuList } = storeToRefs(menuStore);

// const logout = () => {
//   console.log("로그아웃 요청");
//   changeMenuState();
// };
const OpenLogin = () => {
  window.open("/go-user-login", "_blank", "window=480 height=720");
};
</script>

<template>
  <nav>
    <div>
      <!-- 메인 로고 -->
      <!-- nav list -->
      <div>
        <ul>
          <li>
            <a href="#" role="button">핫플레이스</a>
            <ul>
              <li><a href="">MZ</a></li>
              <li><a href="">X</a></li>
            </ul>
          </li>
          <li>
            <a href="" role="button">커뮤니티</a>
            <ul>
              <li><a href="">MZ</a></li>
              <li><a href="">X</a></li>
            </ul>
          </li>
          <li>
            <a href="" role="button">여행코스</a>
            <ul>
              <li><a href="">MZ</a></li>
              <li><a href="">X</a></li>
            </ul>
          </li>
          <li><a href="">마이페이지</a></li>
          <li>
            <v-dialog width="600">
              <VfUserRegister>로그인</VfUserRegister>
            </v-dialog>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>

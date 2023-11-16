<script setup>
import { ref } from "vue";
import { useMenuStore } from "@/store/menu";
import { storeToRefs } from "pinia";
import VfUserRegister from "../users/VfUserRegister.vue";

const menuStore = useMenuStore();
const { menuList } = storeToRefs(menuStore);

// 다이얼로그의 열림/닫힘 상태를 나타내는 데이터 속성
const dialog = ref(false);
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
            <v-dialog v-model="dialog" width="600">
              <template v-slot:activator="{ on, attrs }">
                <v-btn v-on="on" v-bind="attrs" @click="dialog = !dialog"
                  >로그인</v-btn
                >
              </template>
              <VfUserRegister v-if="dialog"></VfUserRegister>
            </v-dialog>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>

<script setup>
import { ref } from "vue";
import { useAuthStore } from "@/store/auth";
import UserModify from "./UserModify.vue";
import { useRouter, useRoute } from "vue-router";
const authStore = useAuthStore();
const router = useRouter();

const linksA = [
  ["mdi-account-multiple-minus", "전체 사용자 목록", ""],
  ["mdi-send", "게시판", "/board/notice"],
];
const goDetail = (url) => {
  router.push(url);
};

// 다이얼로그의 열림/닫힘 상태를 나타내는 데이터 속성
const dialog = ref(false);
const dialog2 = ref(false);
</script>

<template>
  <div class="bg-grey-lighten-3">
    <v-container id="container">
      <v-row id="warp">
        <!-- 사이드 start-->
        <v-col cols="2" id="side-style">
          <!-- 프로필 -->
          <v-sheet color="grey-lighten-4" class="pa-4" rounded="lg">
            <v-row id="profile">
              <v-col :cols="3" :style="{ padding: '0' }">
                <v-avatar class="mb-4" color="grey-darken-1" size="64">
                  <p>사진</p>
                </v-avatar>
              </v-col>
              <v-col :style="{ padding: '0' }">
                <v-list style="background-color: transparent">
                  <v-list-item>
                    <!-- 이용자 정보 -->
                    <v-list-item-content>
                      <v-list-item-title
                        >{{ authStore.user.userNickname }} ({{
                          authStore.user.userId
                        }})</v-list-item-title
                      >
                      <v-list-item-subtitle>{{
                        authStore.user.email
                      }}</v-list-item-subtitle>
                    </v-list-item-content>
                    <!-- 수정 버튼 -->
                    <v-list-item-action class="right">
                      <v-spacer></v-spacer>
                      <v-dialog icon v-model="dialog" width="600">
                        <template v-slot:activator="{ on, attrs }">
                          <a
                            v-on:click="() => (dialog = !dialog)"
                            v-bind="attrs"
                            ><v-icon class="custom-icon">mdi-pencil</v-icon></a
                          >
                        </template>
                        <UserModify v-if="dialog"></UserModify>
                      </v-dialog>
                    </v-list-item-action>
                  </v-list-item>
                </v-list>
              </v-col>
            </v-row>
            <div>가입일 : {{ authStore.user.registerDate }}</div>
          </v-sheet>

          <v-divider></v-divider>

          <!-- end 프로필 -->
          <!-- 사이드 리스트 start-->

          <v-sheet rounded="lg">
            <v-list rounded="lg">
              <v-list-item
                v-for="[icon, text, url] in linksA"
                :key="icon"
                :prepend-icon="icon"
                :title="text"
                link
                @click="goDetail(url)"
                ><v-divider></v-divider>
              </v-list-item>
            </v-list>
          </v-sheet>
          <!-- 사이드 리스트 end-->
        </v-col>
        <!-- 사이드 end-->
        <!-- 본 페이지 -->
        <v-col>
          <v-sheet min-height="70vh" rounded="lg">
            <!-- 본 -->
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped>
div {
  margin: 0;
}
#warp {
  margin-left: 20px;
  margin-right: 20px;
}
#container {
  margin: 0 auto;
}
#intro-text {
  font-size: 50px;
}
#side-style {
  min-width: 300px;
}
#profile {
  padding: 0;
}
.custom-icon {
  font-size: 20;
}
.v-dialog::v-sheet {
  background-color: rgba(255, 255, 255, 1); /* 흰색 배경을 투명하게 설정 */
}
</style>

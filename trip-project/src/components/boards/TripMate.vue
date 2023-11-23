<script setup>
import { ref, computed } from "vue";
import { useAuthStore } from "@/store/auth";
import { useMyLocationStore } from "@/store/mylocation";

const authStore = useAuthStore();
const mylocationStore = useMyLocationStore();

//2.반응형 데이터 연결하기
const mylist = computed(() => mylocationStore.mylist);
const params = ref({
  userId: authStore.user.userId, //로그인 유저정보
});
//목록 조회
mylocationStore.getMyList(params.value);

const moveToTrip = (n) => {
  router.push({ path: `/map/detail/${n}` });
};

const tab = ref(null);
const search = ref("");
const tabType = ref(["전체", "모집중", "모집완료"]);
const sorting = ref(["최신순", "출발일순", "좋아요순"]);
const boardInfo = ref([
  {
    type: "1",
    title: "1",
    content: "1",
  },
  {
    type: "12",
    title: "12",
    content: "12",
  },
  {
    type: "13",
    title: "13",
    content: "13",
  },
  {
    type: "14",
    title: "14",
    content: "14",
  },
]);
</script>

<template>
  <div class="bg-grey-lighten-3">
    <v-container>
      <v-row>
        <v-col cols="12" md="2">
          <v-sheet rounded="lg" min-height="268">
            <!-- 왼쪽 사이드 -->
            <a
              href="https://www.facebook.com/sweeteuro/posts/1013008952232448/?locale=ko_KR"
            >
              <v-img cover src="@/assets/with.jpg"
            /></a>
          </v-sheet>
        </v-col>
        <!-- 본문 -->
        <v-col cols="12" md="8">
          <v-sheet min-height="70vh" rounded="lg">
            <v-card>
              <!-- 탭 -->
              <v-tabs
                v-model="tab"
                color="deep-purple-accent-4"
                align-tabs="head"
              >
                <v-tab
                  v-for="(item, index) in tabType"
                  :key="index"
                  :value="index"
                  >{{ item }}</v-tab
                >
              </v-tabs>

              <!-- 검색바 -->

              <v-container>
                <v-card
                  class="pa-4"
                  flat
                  img="https://cdn.vuetifyjs.com/images/toolbar/map.jpg"
                >
                  <v-toolbar dense floating>
                    <v-text-field
                      hide-details
                      prepend-icon="mdi-magnify"
                      single-line
                      v-model="search"
                      placeholder="관심 여행일정을 검색해보세요!"
                    ></v-text-field>
                    <v-btn>검색</v-btn>
                  </v-toolbar>
                </v-card>
              </v-container>
              <!-- 정렬 키워드-->
              <v-list>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      v-for="(item, index) in sorting"
                      :key="index"
                    >
                      <v-btn> {{ item }}</v-btn>
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
              <!-- 탭에 해당하는 것들 -->
              <v-window v-model="tab">
                <v-window-item
                  :value="index"
                  v-for="(item, index) in tabType"
                  :key="index"
                >
                  <!-- 여행 리스트 -->
                  <v-list rounded="lg">
                    <v-list-item
                      v-for="item in mylist"
                      :key="item.courseId"
                      link
                      :title="`${item.courseName}`"
                      @click="moveToTrip(item.courseId)"
                    >
                      <h5>
                        여행일 : {{ item.startDate + " ~ " + item.endDate }}
                      </h5>
                    </v-list-item>

                    <v-divider class="my-2"></v-divider>
                  </v-list>
                  <v-container fluid>
                    <!-- 게시글 리스트 시작 -->
                    <v-row align="center" justify="center">
                      <v-col v-for="(info, i) in mylist" :key="i" cols="auto">
                        <v-card
                          class="mx-auto"
                          max-width="344"
                          :info="outlined"
                        >
                          <v-card-item>
                            <div>
                              <div class="text-overline mb-1">
                                전체/모집/마감
                              </div>
                              <div class="text-h6 mb-1">{{ info.title }}</div>
                              <div class="text-caption">{{ info.content }}</div>
                            </div>
                          </v-card-item>

                          <v-card-actions>
                            <v-btn> 참가하기 </v-btn>

                            <!-- 잡다한거 -->
                            <v-btn value="recent">
                              <v-icon>mdi-history</v-icon>

                              <span>Recent</span>
                            </v-btn>

                            <v-btn value="favorites">
                              <v-icon>mdi-heart</v-icon>

                              <span>Favorites</span>
                            </v-btn>

                            <v-btn value="nearby">
                              <v-icon>mdi-map-marker</v-icon>

                              <span>Nearby</span>
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-col>
                    </v-row>
                    <!-- 게시글 리스트 끝 -->
                  </v-container>
                </v-window-item>
              </v-window>
              <!-- tap end  -->
            </v-card>
          </v-sheet>
        </v-col>

        <v-col cols="12" md="2">
          <v-sheet rounded="lg" min-height="268">
            <!-- 오른쪽 사이드 -->
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped>
v-row {
  /* margin: 0 auto; */
  padding: 0;
}
</style>

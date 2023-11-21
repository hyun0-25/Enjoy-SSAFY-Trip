<script setup>
import { ref } from "vue";

const tab = ref(null);
const search = ref("");
</script>

<template>
  <div class="bg-grey-lighten-3">
    <v-container>
      <v-row>
        <v-col cols="12" md="2">
          <v-sheet rounded="lg" min-height="268">
            <!-- 왼쪽 사이드 -->
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
                align-tabs="center"
              >
                <v-tab :value="1">전체</v-tab>
                <v-tab :value="2">모집중</v-tab>
                <v-tab :value="3">모집완료</v-tab>
              </v-tabs>
              <!-- 검색바 -->
              <v-icon>fas fa-search</v-icon>
              <v-text-field clearable label="Label" variant="outlined"
                ><input type="text"
              /></v-text-field>
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
                  </v-toolbar>
                </v-card>
              </v-container>

              <!-- 탭에 해당하는 것들 -->
              <v-window v-model="tab">
                <v-window-item v-for="n in 3" :key="n" :value="n">
                  <v-container fluid>
                    <v-row>
                      <v-col v-for="i in 6" :key="i" cols="12" md="4">
                        <v-img
                          :src="`https://picsum.photos/500/300?image=${
                            i * n * 5 + 10
                          }`"
                          :lazy-src="`https://picsum.photos/10/6?image=${
                            i * n * 5 + 10
                          }`"
                          aspect-ratio="1"
                        ></v-img>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-window-item>
              </v-window>
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

<style scoped></style>

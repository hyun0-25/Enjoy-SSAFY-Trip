<script setup>
import { ref, computed } from "vue";
//1.store 객체 얻어오기
import { useBoardStore } from "../../store/board";
const boardStore = useBoardStore();

//2.반응형 데이터 연결하기
const articles = computed(() => boardStore.articles);

const params = ref({
  key: "", //조건 검색 시 컬럼명
  word: "", //해당 컬럼에 일치하는 데이터
});

//목록 조회
boardStore.getArticles(params.value, "contest");
// 좋아요
const good = ref(false);
const like = (boardId) => {
  good.value = !good.value;
  if (good) boardStore.likeArticel("contest", boardId);
  else boardStore.deleteLikeArticel("contest", boardId);
};
</script>

<template>
  <v-card class="mx-auto" max-width="800px">
    <v-card-title> 콘테스트 게시판 </v-card-title>
    <RouterLink :to="{ name: 'contest-write' }"
      ><v-btn class="button">글쓰기</v-btn></RouterLink
    >
    <v-container fluid>
      <v-row dense>
        <v-col v-for="card in articles" :key="card.title" :cols="4">
          <v-card>
            <v-img
              :src="card.src"
              class="align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
              cover
            >
              <v-card-title
                class="text-white"
                v-text="card.title"
              ></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                size="small"
                color="surface-variant"
                variant="text"
                icon="mdi-heart"
              ></v-btn>
              <v-btn
                size="small"
                color="surface-variant"
                variant="text"
                icon="mdi-bookmark"
              ></v-btn>
              <v-btn
                size="small"
                color="surface-variant"
                variant="text"
                icon="mdi-thumb-up"
                @click="like(card.boardId)"
              ></v-btn>
              {{ card.boardId }}
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<style scoped>
.button {
  text-decoration: none;
  color: black;
}
</style>

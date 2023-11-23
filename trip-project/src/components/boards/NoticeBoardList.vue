<template>
  <v-card>
    <v-card-title>
      공지사항
      <v-spacer></v-spacer>
      <!-- <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field> -->
      <SearchBar
        @search-event="getSearchArticles"
        :options="[
          { value: 'title', text: '제목' },
          { value: 'subject', text: '내용' },
        ]"
      ></SearchBar>
    </v-card-title>

    <v-data-table
      v-model:page="page"
      :headers="headers"
      :items="articles"
      item-key="boardId"
      :items-per-page="itemsPerPage"
      :loading="loading"
      loading-text="Loading... Please wait"
    >
      <!-- <template v-slot:item="{ item }">
        <tr @click="handleRowClick(item)"></tr>
        <div>
          {{ item.boardId }}
        </div>
      </template> -->

      <template v-slot:bottom>
        <div class="text-center pt-2">
          <v-pagination
            v-model="page"
            :length="pageCount"
            :total-visible="totalVisible"
          ></v-pagination>
        </div>
      </template>
    </v-data-table>
    <RouterLink
      :to="{ name: 'notice-write' }"
      v-if="authStore.user.role == 'admin'"
      ><v-btn class="button">글쓰기</v-btn></RouterLink
    >
  </v-card>
</template>
<script setup>
// import PageNavigation from "../../components/navigation/PageNavigation.vue";
import SearchBar from "../../components/searchbar/SearchBar.vue";
import { RouterLink, useRouter } from "vue-router";
import { ref, computed } from "vue";
//1.store 객체 얻어오기
import { useAuthStore } from "@/store/auth";
import { useBoardStore } from "../../store/board";
const boardStore = useBoardStore();
const authStore = useAuthStore();
const loading = ref(true);
const router = useRouter();

//2.반응형 데이터 연결하기
const articles = computed(() => boardStore.articles);

const params = ref({
  key: "", //조건 검색 시 컬럼명
  word: "", //해당 컬럼에 일치하는 데이터
});

//목록 조회
boardStore.getArticles(params.value, "notice");
loading.value = false;

const getSearchArticles = (searchKeyword) => {
  console.log("BoardList의 조건 검색 메소드 호출:", searchKeyword);

  params.value.key = searchKeyword.key;
  params.value.word = searchKeyword.word;
  // params.value.pgno = 1;

  //목록 조회 필요
  boardStore.getArticles(params.value, "notice");
};

const page = ref(1);
const itemsPerPage = ref(10);
const totalVisible = ref(8);

const headers = ref([
  { title: "No", sortable: false, key: "boardId" },
  // { title: "작성자", sortable: false, key: "nickName" },
  { title: "제목", sortable: false, key: "title" },
  { title: "조회수", sortable: false, key: "hit" },
  { title: "등록일", sortable: false, key: "registerDate" },
  // { title: "좋아요수", sortable: false, key: "totalLike" },
]);

const pageCount = computed(() =>
  Math.ceil(articles.value.length / itemsPerPage.value)
);

// 리스트를 눌러 해당 요소의 상세 페이지 이동
const goDetail = () => {
  // router.push("/board/notice/"+headers.value.title);
};
</script>
<style scoped>
.button {
  text-decoration: none;
  color: black;
}
</style>

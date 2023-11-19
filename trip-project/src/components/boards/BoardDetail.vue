<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useBoardStore } from "@/store/board";

const boardStore = useBoardStore();
const article = computed(() => boardStore.article); //store 데이터를 반응형으로 가져오기

const router = useRouter();
const route = useRoute();

boardStore.getArticle(route.params.boardId);

const deleteArticle = async () => {
  try {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    await boardStore.deleteArticle(article.value.boardId);
    router.push("/board/notice");
    alert("삭제 완료");
  } catch (error) {
    console.log("삭제 에러:", error);
    alert("삭제 실패");
  }
};

const cancel = async () => {
  if (!confirm("취소하시겠습니까?")) return;
  router.push({ path: "/board/notice" });
};

const comments = ref([
  {
    commentId: 1,
    nickname: "User1",
    registerDate: "2023-01-01",
    content: "첫 번째 댓글입니다.",
  },
  {
    commentId: 2,
    nickname: "User2",
    registerDate: "2023-01-02",
    content: "두 번째 댓글입니다.",
  },
]);

const newComment = ref({
  commentId: 3,
  nickname: "User2",
  registerDate: "2023-01-02",
  content: "",
});

const addComment = () => {
  if (newComment.content !== "") {
    const comment = {
      nickname: newComment.nickname,
      registerDate: newComment.nickname,
      content: newComment.content,
    };
    comments.value.push(comment);
    console.log(comments);
    console.log("hi");
    // 추가 후 폼 초기화
    newComment.content = "";
  }
};
</script>

<template>
  <v-app>
    <v-main>
      <v-container>
        <v-row>
          <v-col cols="12" md="10">
            <v-card>
              <div style="width: 300px; margin-left: 100px; padding-top: 20px">
                글쓴이 : {{ article.nickname }}
              </div>
              <div style="width: 300px; margin-left: 100px; padding-top: 10px">
                제목 : {{ article.title }}
              </div>
              <div style="width: 300px; margin-left: 100px; padding-top: 10px">
                작성일 : {{ article.registerDate }}
              </div>
              <div style="width: 300px; margin-left: 100px; padding-top: 10px">
                조회수 : {{ article.hit }}
              </div>
              <div style="width: 300px; margin-left: 100px; padding-top: 10px">
                좋아요수 : {{ article.total_likes }}
              </div>
              <div style="width: 300px; margin-left: 100px; padding-top: 10px">
                내용
              </div>
              <v-textarea
                outlined
                rows="13"
                style="width: 730px; margin-left: 100px; padding-top: 10px"
                readonly
                >{{ article.content }}</v-textarea
              >
              <!-- textarea의 disabled 속성을 통해 원래는 수정을 할 수 없지만
                      수정 버튼을 누르면 수정할 수 있게끔 바뀜 -->
              <v-btn width="100px" style="margin-bottom: 30px" @click="cancel()"
                >뒤로가기</v-btn
              >
              <!-- 수정 화면 이동 -->
              <RouterLink
                :to="{
                  name: 'board-modify',
                  params: { boardId: article.boardId },
                }"
                ><v-btn
                  width="100px"
                  style="margin-left: 30px; margin-bottom: 20px"
                  >수정</v-btn
                ></RouterLink
              >
              <v-btn
                width="100px"
                style="margin-left: 30px; margin-bottom: 20px"
                @click="deleteArticle"
                >삭제</v-btn
              >
            </v-card>
          </v-col>
          <v-col cols="12" md="1" />
        </v-row>
      </v-container>
    </v-main>
    <v-container>
      <v-list two-line>
        <v-list-subheader>댓글</v-list-subheader>

        <div v-if="comments.length > 0">
          <v-list-item v-for="comment in comments" :key="comment.commentId">
            <v-list-item-title class="font-weight-bold">{{
              comment.nickname
            }}</v-list-item-title>
            <v-list-item-subtitle>{{
              comment.registerDate
            }}</v-list-item-subtitle>
            <v-list-item-subtitle>{{ comment.content }}</v-list-item-subtitle>
          </v-list-item>
        </div>

        <div v-else>댓글이 없습니다.</div>

        <v-divider></v-divider>

        <v-list-item>
          <v-text-field
            v-model="newComment.nickname"
            label="작성자"
            required
            readonly
          ></v-text-field>
          <v-text-field
            v-model="newComment.content"
            label="댓글 내용"
            required
          ></v-text-field>
          <v-btn @click="addComment" color="primary">댓글 추가</v-btn>
        </v-list-item>
      </v-list>
    </v-container>
  </v-app>
</template>

<style scoped></style>

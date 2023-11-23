<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useBoardStore } from "@/store/board";
import { useCommentStore } from "@/store/comment";
import { useAuthStore } from "@/store/auth";

const authStore = useAuthStore();
const boardStore = useBoardStore();
const article = computed(() => boardStore.article); //store 데이터를 반응형으로 가져오기

const router = useRouter();
const route = useRoute();
const imgview = ref("");
onMounted(async () => {
  await boardStore.getArticle(route.params.boardType, route.params.boardId);
  if (article.value.fileInfos[0] != null) {
    const file = article.value.fileInfos[0];
    imgview.value = `http://localhost/images/${file.saveFile}`;
  }
});

const deleteArticle = async () => {
  try {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    await boardStore.deleteArticle(
      article.value.boardType,
      article.value.boardId
    );
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

//로그인 유저정보
// const authStore = useAuthStore();
// const userId = computed(() => authStore.user.userId);
const userId = authStore.user.userId;
console.log(userId);

const commentStore = useCommentStore();
const comments = computed(() => commentStore.comments);

commentStore.getComments(route.params.boardId);
const newComment = ref({
  boardId: route.params.boardId,
  userId: userId,
  content: "",
});

const form = ref(null);
const addComment = async () => {
  const { valid } = await form.value.validate();
  // rules 만족시
  if (valid) {
    try {
      await commentStore.writeComment(route.params.boardId, newComment.value);
      commentStore.getComments(route.params.boardId);
      newComment.value.content = "";
    } catch (error) {
      //등록 시 에러 발생
      console.log("등록 에러 내용:", error);
    }
  }
};

const deleteComment = async (commentId) => {
  try {
    if (!confirm("댓글을 삭제하시겠습니까?")) return;
    await commentStore.deleteComment(article.value.boardId, commentId);
    commentStore.getComments(route.params.boardId);
  } catch (error) {
    console.log("삭제 에러:", error);
  }
};

const editedCommentIndex = ref(-1);
const editedComment = ref({});

// 메서드
const modifyComment = (index) => {
  // 수정할 댓글의 아이디 저장
  editedCommentIndex.value = index;
  editedComment.value = comments.value[index];
};

const saveEdit = async () => {
  // 수정한 댓글 내용으로 업데이트
  // rules 만족시
  if (editedComment.value.content.length > 0) {
    editedCommentIndex.value = -1;
    try {
      await commentStore.modifyComment(editedComment.value);
    } catch (error) {
      console.log("수정 에러 내용:", error);
    }
  }
};

const cancelEdit = () => {
  editedCommentIndex.value = -1;
  commentStore.getComments(route.params.boardId);
};

//파일 업로드
// 상태 변수 정의
const preview = ref("");
// const file = ref(null);

console.log(article.value.fileInfos);
// 메서드 정의
const previewFile = () => {
  const fileData = (data) => {
    preview.value = data;
  };
  // console.log(file.value[0].name);

  if (article.value.fileInfos) {
    const reader = new FileReader();
    // reader.readAsDataURL(file.value[0]);
    reader.readAsDataURL(article.value.fileInfos);
    reader.addEventListener(
      "load",
      function () {
        fileData(reader.result);
      },
      false
    );
  }
};

const validateCheck = () => {
  // 추가 버튼 클릭 시 유효성 검사 등 추가 로직 구현
  console.log("파일 추가 버튼이 클릭되었습니다.");
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
                글쓴이 : {{ article.nickName }}
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
                좋아요수 : {{ article.totalLike }}
              </div>
              <v-textarea
                outlined
                rows="13"
                style="width: 730px; margin-left: 100px; padding-top: 10px"
                readonly
                label="내용"
                v-model="article.content"
              ></v-textarea>
              <v-col>
                <img :src="imgview" />
              </v-col>

              <v-btn width="100px" style="margin-bottom: 30px" @click="cancel()"
                >뒤로가기</v-btn
              >
              <!-- 수정 화면 이동 -->
              <!-- 로그인 유저정보랑 같은 경우만 수정,삭제 버튼 생김. role(관리자)이면 수정,삭제 생김-->
              <div
                v-if="
                  userId === article.userId || authStore.user.role == 'admin'
                "
              >
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
                  @click="deleteArticle()"
                  >삭제</v-btn
                >
              </div>
            </v-card>
          </v-col>
          <v-col cols="12" md="1" />
        </v-row>
      </v-container>
    </v-main>
    <!-- comment start -->
    <v-container v-if="article.boardType === 'hot'">
      <v-list two-line>
        <v-list-subheader>댓글</v-list-subheader>

        <div v-if="comments.length > 0">
          <v-list-item v-for="(comment, index) in comments" :key="index">
            <v-list-item-title class="font-weight-bold">{{
              comment.nickName
            }}</v-list-item-title>
            <v-list-item-subtitle>{{
              comment.registerDate
            }}</v-list-item-subtitle>
            <template v-if="index !== editedCommentIndex">
              <v-list-item-subtitle>{{ comment.content }}</v-list-item-subtitle>
              <!-- 권한 설정 -->
              <div
                v-if="
                  comment.userId === userId || authStore.user.role == 'admin'
                "
              >
                <v-btn style="margin-bottom: 10px" @click="modifyComment(index)"
                  >수정</v-btn
                >
                <v-btn
                  style="margin-bottom: 10px"
                  @click="deleteComment(comment.commentId)"
                  >삭제</v-btn
                >
              </div>
            </template>
            <template v-else>
              <v-text-field
                v-model="editedComment.content"
                label="댓글 내용"
                required
                :rules="[(v) => !!v || '댓글을 입력해주세요.']"
              ></v-text-field>
              <v-btn
                @click="saveEdit"
                color="primary"
                v-if="editedCommentIndex !== -1"
                >저장</v-btn
              >
              <v-btn
                @click="cancelEdit"
                color="primary"
                v-if="editedCommentIndex !== -1"
                >취소</v-btn
              >
            </template>
            <!-- <v-list-item-subtitle>{{ comment.content }}</v-list-item-subtitle> -->
          </v-list-item>
        </div>

        <div v-else>댓글이 없습니다.</div>

        <v-divider></v-divider>

        <v-form ref="form" @submit.prevent="addComment">
          <v-list-item>
            <v-text-field
              v-model="article.nickName"
              label="작성자"
              required
              readonly
            ></v-text-field>
            <v-text-field
              v-model="newComment.content"
              label="댓글 내용"
              required
              :rules="[(v) => !!v || '댓글을 입력해주세요.']"
            ></v-text-field>
            <v-btn type="submit" color="primary">댓글 추가</v-btn>
          </v-list-item>
        </v-form>
      </v-list>
    </v-container>
  </v-app>
</template>

<style scoped></style>

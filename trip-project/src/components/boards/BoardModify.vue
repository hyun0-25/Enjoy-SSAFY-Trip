<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref } from "vue";
import { useBoardStore } from "../../store/board";

const boardStore = useBoardStore();

const router = useRouter();
const route = useRoute();

const modifyForm = ref({});

//수정 정보 초기데이터 세팅
const setModifyInfo = async () => {
  await boardStore.getArticle(route.params.boardId);
  modifyForm.value.boardId = boardStore.article.boardId;
  modifyForm.value.subject = boardStore.article.subject;
  modifyForm.value.content = boardStore.article.content;
};
setModifyInfo();
const form = ref(null);
const modify = async () => {
  const { valid } = await form.value.validate();
  // rules 만족시
  if (valid) {
    try {
      if (!confirm("이대로 수정하시겠습니까?")) return;
      await boardStore.modifyArticle(modifyForm.value);
      router.push(`/board/detail/${modifyForm.value.boardId}`);
      alert("수정 성공");
    } catch (error) {
      console.log("수정 에러 내용:", error);
      alert("수정 실패");
    }
  }
};

const cancel = async () => {
  if (!confirm("취소하시겠습니까?")) return;
  router.push({ path: `/board/detail/${modifyForm.value.boardId}` });
};
</script>

<template>
  <v-app>
    <v-main>
      <v-container>
        <v-row>
          <v-col cols="12" md="10">
            <v-card>
              <v-form ref="form" @submit.prevent="modify">
                <v-text-field
                  v-model="modifyForm.title"
                  dense
                  outlined
                  label="제목"
                  style="width: 500px"
                  :rules="[(v) => !!v || '제목은 필수입니다.']"
                ></v-text-field>
                <v-textarea
                  v-model="modifyForm.content"
                  label="내용"
                  outlined
                  rows="13"
                  style="width: 730px"
                  :rules="[(v) => !!v || '내용은 필수입니다.']"
                ></v-textarea>
                <v-btn
                  width="100px"
                  style="margin-bottom: 30px"
                  @click="cancel()"
                  >취소</v-btn
                >
                <v-btn width="100px" style="margin-bottom: 30px" type="submit"
                  >수정</v-btn
                >
              </v-form>
            </v-card>
          </v-col>
          <v-col cols="12" md="1" />
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<style scoped></style>

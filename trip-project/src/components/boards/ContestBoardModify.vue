<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref } from "vue";
import { useBoardStore } from "../../store/board";

const boardStore = useBoardStore();

const router = useRouter();
const route = useRoute();

const modifyForm = ref({});
const file = ref([]);
const imgview = ref("");
const setModifyInfo = async () => {
  //1. store의 article 데이터 세팅
  await boardStore.getModifyArticle(
    route.params.boardType,
    route.params.boardId
  );
  //2. article 데이터를 통해 modifyForm 세팅
  modifyForm.value = { ...boardStore.modifyarticle };

  if (modifyForm.value.fileInfos[0] != null) {
    const fileinfo = modifyForm.value.fileInfos[0];
    imgview.value = `http://localhost/images/${fileinfo.originalFile}`;
  }
  preview.value = imgview.value;
  const response = await fetch(imgview.value);
  const imageBlob = await response.blob();

  // Create a File object from the Blob
  const fileName = imgview.value.substring(imgview.value.lastIndexOf("/") + 1);
  file.value[0] = new File([imageBlob], fileName, { type: imageBlob.type });
};

setModifyInfo();

const form = ref(null);

const modify = async () => {
  const { valid } = await form.value.validate();
  // rules 만족시
  if (valid) {
    try {
      if (!confirm("이대로 수정하시겠습니까?")) return;
      var formData = new FormData();
      for (let i = 0; i < file.value.length; i++) {
        formData.append("files", file.value[i]);
      }
      if (file.value.length == 0) {
        formData.append("files", file.value);
      }
      formData.append("title", modifyForm.value.title);
      formData.append("content", modifyForm.value.content);
      formData.append("boardId", modifyForm.value.boardId);
      await boardStore.modifyArticle(modifyForm.value, formData);
      router.push(`/board/contest/detail/${modifyForm.value.boardId}`);
      alert("수정 성공");
    } catch (error) {
      console.log("수정 에러 내용:", error);
      alert("수정 실패");
    }
  }
};

const cancel = async () => {
  if (!confirm("취소하시겠습니까?")) return;
  router.push({ path: `/board/contest/detail/${modifyForm.value.boardId}` });
};

//파일 업로드
// 상태 변수 정의
const preview = ref("");

// 메서드 정의
const previewFile = () => {
  const fileData = (data) => {
    preview.value = data;
  };
  console.log(file.value[0]);

  if (file.value[0]) {
    const reader = new FileReader();
    reader.readAsDataURL(file.value[0]);
    reader.addEventListener(
      "load",
      function () {
        fileData(reader.result);
      },
      false
    );
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
              <v-form ref="form" @submit.prevent="modify">
                <v-text-field
                  v-model="modifyForm.title"
                  dense
                  outlined
                  label="제목"
                  style="width: 500px"
                  :rules="[(v) => !!v || '제목은 필수입니다.']"
                ></v-text-field>

                <v-col>
                  <img :src="preview" />
                  <v-file-input v-model="file" @change="previewFile" />
                </v-col>
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

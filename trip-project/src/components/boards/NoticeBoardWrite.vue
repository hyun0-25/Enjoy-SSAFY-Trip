<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useBoardStore } from "../../store/board";
import { useAuthStore } from "@/store/auth";
const boardStore = useBoardStore();
const authStore = useAuthStore();

const router = useRouter();
const writeForm = ref({
  userId: authStore.user.userId,
  title: "",
  content: "",
  boardType: "notice",
});

const formData = new FormData();
console.log(formData);
const form = ref(null);
const file = ref([]);
// const files = ref([]);
//등록 요청 보내고 등록 성공 시 목록 페이지 이동
const write = async () => {
  const { valid } = await form.value.validate();
  // rules 만족시
  if (valid) {
    try {
      if (!confirm("이대로 등록하시겠습니까?")) return;

      var formData = new FormData();
      for (let i = 0; i < file.value.length; i++) {
        formData.append("files", file.value[i]);
      }
      if (file.value.length == 0) {
        formData.append("files", file.value);
      }
      formData.append("title", writeForm.value.title);
      formData.append("content", writeForm.value.content);
      // for (let key of formData.keys()) {
      //   console.log(key, ":", formData.get(key));
      // }
      await boardStore.writeArticle(modifyForm, formData);
      router.push({ path: "/board/notice" });
      alert("등록 성공");
    } catch (error) {
      //등록 시 에러 발생
      console.log("등록 에러 내용:", error);
      alert("등록 실패");
    }
  }
};

const cancel = async () => {
  if (!confirm("취소하시겠습니까?")) return;
  router.push({ path: "/board/notice" });
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
    // reader.readAsDataURL(file.value[0]);
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
              <v-form ref="form" @submit.prevent="write">
                <v-text-field
                  v-model="writeForm.title"
                  dense
                  outlined
                  label="제목"
                  style="width: 500px"
                  :rules="[(v) => !!v || '제목은 필수입니다.']"
                ></v-text-field>
                <v-textarea
                  v-model="writeForm.content"
                  label="내용"
                  outlined
                  rows="13"
                  style="width: 730px"
                  :rules="[(v) => !!v || '내용은 필수입니다.']"
                ></v-textarea>
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
                  >제출</v-btn
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

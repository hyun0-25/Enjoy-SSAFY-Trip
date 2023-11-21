import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useBoardStore = defineStore("mylocation", () => {
  /* =======여행 경로 목록 Start========== */
  const articles = ref([]); //목록 데이터
  const getArticles = async (params, boardType) => {
    const { data } = await axios.get(`/api/board/${boardType}`, {
      params: params,
    });
    console.log("getArticles(params) 응답데이터:", data);
    articles.value = data.articles;
  };
  /* =======여행 경로 목록 End========== */

  /* =======여행 경로 등록 Start========== */
  const addMyLocation = async (writeForm) => {
    console.log("writeArticle() 요청 데이터:", writeForm);
    return await axios.post(`/api/board`, writeForm);
  };
  /* =======여행 경로 등록 End========== */

  // 수정시 모두 삭제후 다시 추가
  /* =======여행 경로 삭제 Start========== */
  const deleteAllMyLocation = async (courseId) => {
    console.log(`deleteAllMyLocation(${courseId}) 요청 보냄`);
    await axios.delete(`/api/mylocation/${boardType}/${boardId}`);
  };
  /* =======여행 경로 삭제 End========== */

  return {
    articles,
    getArticles,
    article,
    getArticle,
    writeArticle,
    modifyArticle,
    deleteArticle,
    getModifyArticle,
    modifyarticle,
  };
});

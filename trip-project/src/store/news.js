import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useNeswStore = defineStore("attraction", () => {
  /* ====== 뉴스 크롤링 ========== */
  const news = ref([]); //목록 데이터
  const listNews = async () => {
    const { data } = await axios.get(`/api/news`);
    console.log(`listNews() 요청 응답: `, data);
    news.value = data;
  };
  /* =======시도 목록 End========== */

  return {
    news,
    listNews,
  };
});

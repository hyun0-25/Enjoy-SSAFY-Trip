import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useMyLocationStore = defineStore("mylocation", () => {
  // /* =======여행 경로 목록 Start========== */
  // const articles = ref([]); //목록 데이터
  // const getMyLocations = async (params, boardType) => {
  //   const { data } = await axios.get(`/api/board/${boardType}`, {
  //     params: params,
  //   });
  //   console.log("getMyLocations(params) 응답데이터:", data);
  //   articles.value = data.articles;
  // };
  // /* =======여행 경로 목록 End========== */

  /* =======여행 경로 등록 Start========== */
  const addMyLocations = async (listForm) => {
    console.log("addMyLocations() 요청 데이터:", listForm);
    return await axios.post(`/api/map/mylocation`, listForm);
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
    addMyLocations,
    deleteAllMyLocation,
  };
});

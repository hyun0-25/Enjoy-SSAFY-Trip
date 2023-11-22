import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useAttractionStore = defineStore("attraction", () => {
  /* =======시도 목록 Start========== */
  const sidos = ref([]); //목록 데이터
  const listSido = async () => {
    const { data } = await axios.get(`/api/map/sido`);
    console.log(`listSido() 요청 응답: `, data);
    sidos.value = data;
  };
  /* =======시도 목록 End========== */

  /* =======구군 목록 Start========== */
  const guguns = ref([]); //목록 데이터
  const listGugun = async (param) => {
    const { data } = await axios.get(`/api/map/gugun`, { params: param });
    console.log(`listGugun(${param}) 요청 응답: `, data);
    guguns.value = data;
  };

  /* =======여행지 목록 Start========== */
  const attractions = ref([]); //목록 데이터

  const getAttractions = async (params) => {
    const { data } = await axios.get(`/api/map/attraction`, {
      params: params,
    });
    console.log("getAttractions(params) 응답데이터:", data);
    attractions.value = data;
  };
  /* =======여행지 목록 End========== */

  /* =======여행지 상세 Start========== */
  const attraction = ref({});

  const getAttraction = async (attractionNo) => {
    const { data } = await axios.get(`/api/map/${attractionNo}`);
    console.log(`getAttraction(${attractionNo}) 요청 응답: `, data);
    attraction.value = data;
  };
  /* =======여행지 상세 End========== */

  return {
    attractions,
    getAttractions,
    attraction,
    getAttraction,
    sidos,
    listSido,
    guguns,
    listGugun,
  };
});

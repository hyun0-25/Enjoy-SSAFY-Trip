import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useMyLocationStore = defineStore("mylocation", () => {
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

  /* =======내 여행 목록 Start========== */
  const mylist = ref([]); //목록 데이터

  const getMyList = async (params) => {
    const { data } = await axios.get(`/api/map/mylist`, {
      params: params,
    });
    console.log("getMyList(params) 응답데이터:", data);
    mylist.value = data;
  };
  /* =======내 여행 목록 End========== */

  /* =======여행 경로 목록 Start========== */
  const courseList = ref([]); //목록 데이터

  const getCourseList = async (courseId) => {
    const { data } = await axios.get(`/api/map/mylocation//${courseId}`);
    console.log("getCourseList(courseId) 응답데이터:", data);
    courseList.value = data;
  };
  /* =======여행 경로 목록 End========== */

  /* =======여행지 상세 Start========== */
  const attraction = ref({});

  const getAttraction = async (contentId) => {
    const { data } = await axios.get(`/api/map/${contentId}`);
    console.log(`getAttraction(${contentId}) 요청 응답: `, data);
    attraction.value = data;
  };
  /* =======여행지 상세 End========== */

  /* =======게시글 삭제 Start========== */
  const deleteCourse = async (courseId) => {
    console.log(`deleteCourse(${courseId}) 요청 보냄`);
    await axios.delete(`/api/map/mylocation/${courseId}`);
  };
  /* =======게시글 삭제 End========== */

  return {
    addMyLocations,
    deleteAllMyLocation,
    mylist,
    getMyList,
    courseList,
    getCourseList,
    attraction,
    getAttraction,
    deleteCourse,
  };
});

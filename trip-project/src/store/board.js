import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useBoardStore = defineStore("board", () => {
  /* =======게시글 목록 Start========== */
  const articles = ref([]); //목록 데이터
  /*
  Response
{
    config:"",
    data:{
        articles:[{}],
        totalPageCount:12,
        currentPage:10,
    },
    status:200
}
  */
  const getArticles = async (params, boardType) => {
    const { data } = await axios.get(`/api/board/${boardType}`, {
      params: params,
    });
    console.log("getArticles(params) 응답데이터:", data);
    articles.value = data.articles;
  };
  /* =======게시글 목록 End========== */

  /* =======게시글 상세 Start========== */

  const article = ref({});

  const getArticle = async (boardType, boardId) => {
    const { data } = await axios.get(`/api/board/${boardType}/${boardId}`);
    console.log(`getArticle(${boardType}, ${boardId}) 요청 응답: `, data);
    article.value = data;
  };
  /* =======게시글 상세 End========== */

  /* =======게시글 등록 Start========== */
  const writeArticle = async (formData) => {
    console.log("writeArticle() 요청 데이터:", formData);
    var type;
    for (let key of formData.keys()) {
      if (key == "boardType") {
        type = formData.get(key);
      }
    }
    return await axios.post(`/api/board/${type}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  };
  /* =======게시글 등록 End========== */

  /* =======게시글 수정 정보 Start========== */
  const modifyarticle = ref({});

  const getModifyArticle = async (boardType, boardId) => {
    const { data } = await axios.get(
      `/api/board/modify/${boardType}/${boardId}`
    );
    console.log(`getModifyArticle(${boardType}, ${boardId}) 요청 응답: `, data);
    modifyarticle.value = data;
  };
  /* =======게시글 정보 End========== */

  /* =======게시글 수정 Start========== */
  const modifyArticle = async (modifyForm, formData) => {
    console.log(modifyForm);
    console.log("modifyArticle() 요청 데이터:", modifyForm);
    return await axios.put(
      `/api/board/${modifyForm.boardType}/${modifyForm.boardId}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
  };
  /* =======게시글 수정 End========== */

  /* =======게시글 삭제 Start========== */
  const deleteArticle = async (boardType, boardId) => {
    console.log(`deleteArticle(${boardType}, ${boardId}) 요청 보냄`);
    await axios.delete(`/api/board/${boardType}/${boardId}`);
  };
  /* =======게시글 삭제 End========== */

  /* =======좋아요 여부 Start========== */
  const islike = ref(0);
  const islikeArticle = async (boardType, boardLike) => {
    console.log(boardLike);
    const url = `/api/board/${boardType}/${boardLike.boardId}/like`;
    console.log(url);
    const { data } = await axios.get(url, { params: boardLike });
    console.log(
      `likeArticle(${boardType}, ${boardLike.boardId}) 요청 응답 - ${data}`
    );
    islike.value = data;
  };
  /* =======좋아요 여부 End========== */

  /* =======좋아요 등록 Start========== */
  const likeArticle = async (boardType, boardLike) => {
    console.log(`likeArticel(${boardType}, ${boardLike.boardId}) 요청 보냄`);
    await axios.post(
      `/api/board/${boardType}/${boardLike.boardId}/like`,
      boardLike
    );
  };
  /* =======좋아요 등록 End========== */

  /* =======좋아요 삭제 Start========== */
  const deleteLikeArticle = async (boardType, boardLike) => {
    console.log(
      `deleteLikeArticle(${boardType}, ${boardLike.boardId}) 요청 보냄`
    );
    const url = `/api/board/${boardType}/${boardLike.boardId}/like`;
    console.log(url);
    console.log(boardLike);
    await axios.delete(url, { params: boardLike });
  };
  /* =======좋아요 삭제 End========== */

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
    likeArticle,
    deleteLikeArticle,
    islikeArticle,
    islike,
  };
});

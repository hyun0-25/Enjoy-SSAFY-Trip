import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useCommentStore = defineStore("comment", () => {
  /* =======댓글 목록 Start========== */
  const comments = ref([]); //목록 데이터

  const getComments = async (boardId) => {
    const { data } = await axios.get(`/api/comment/${boardId}`);
    console.log("getComments(params) 응답데이터:", data);
    comments.value = data;
  };
  /* =======댓글 목록 End========== */

  /* =======게시글 등록 Start========== */
  const writeComment = async (boardId, newComment) => {
    console.log("writeComment() 요청 데이터:", newComment);
    return await axios.post(`/api/comment/${boardId}`, newComment);
  };
  /* =======게시글 등록 End========== */

  //   /* =======게시글 수정 정보 Start========== */
  //   const modifyarticle = ref({});

  //   const getModifyArticle = async (boardType, boardId) => {
  //     const { data } = await axios.get(
  //       `/api/board/modify/${boardType}/${boardId}`
  //     );
  //     console.log(`getModifyArticle(${boardType}, ${boardId}) 요청 응답: `, data);
  //     modifyarticle.value = data;
  //   };
  //   /* =======게시글 정보 End========== */

  /* =======게시글 수정 Start========== */
  const modifyComment = async (modifyForm) => {
    console.log("modifyArticle() 요청 데이터:", modifyComment);
    return await axios.put(
      `/api/comment/${modifyForm.boardId}/${modifyForm.commentId}`,
      modifyForm
    );
  };
  /* =======게시글 수정 End========== */

  /* =======댓글 삭제 Start========== */
  const deleteComment = async (boardId, commentId) => {
    console.log(`deleteComment(${boardId}, ${commentId}) 요청 보냄`);
    await axios.delete(`/api/comment/${boardId}/${commentId}`);
  };
  /* =======댓글 삭제 End========== */

  return {
    // articles,
    // getArticles,
    // article,
    // getArticle,
    writeComment,
    modifyComment,
    deleteComment,
    // getModifyArticle,
    getComments,
    comments,
  };
});

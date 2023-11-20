import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export const useAuthStore = defineStore(
  "auth",
  () => {
    //====================로그인 관련===================
    //login User 정보
    const user = ref({
      userId: "",
      userNickname: "",
      role: "",
      userType: "",
    });
    const token = ref(""); //jwt 토큰 정보
    const isRemember = ref(false);
    const rememberedId = ref("");

    const login = async (loginForm) => {
      const { data } = await axios.post(`/api/user/login`, loginForm);
      console.log("로그인 요청 후 응답 데이터:", data);

      token.value = data.token; //토큰 정보 저장

      const decoded = jwtDecode(data.token); //토큰에서 유저정보 추출하여 유저정보 저장
      console.log("디코딩된 토큰 정보 : ", decoded);
      user.value.userId = decoded.userId;
      user.value.userNickname = decoded.userNickname;
      user.value.role = decoded.role;
      user.value.userType = decoded.userType;

      //아이디 기억
      isRemember.value
        ? (rememberedId.value = user.value.userId)
        : (rememberedId.value = "");
    };
    const logout = () => {
      clearUser();
    };

    const clearUser = () => {
      user.value.userId = "";
      user.value.userNickname = "";
      user.value.role = "";
      user.value.userType = "";
      token.value = "";
    };
    //===================회원가입 관련================
    const registUser = async (joinForm) => {
      const { data } = await axios.post(`/api/user/sign-up`, joinForm);
      console.log("회원가입 요청 후 응답 데이터:", data);
      clearUser();
    };

    // ============== 아이디 중복 검사 ================
    const isCheck = async (userId) => {
      const response = await axios.get(`/api/checkUserId/${userId}`);
      console.log("isCheck 아이디 중복 검사 : ", userId, response.data);
      return response.data;
    };

    //-------------------------------------------------

    return {
      user,
      token,
      isRemember,
      rememberedId,
      login,
      logout,
      clearUser,
      registUser,
      isCheck,
    };
  },
  //새로고침시 데이터 유지를 위한 설정(localStorage에 저장해서 불러오는 방식)
  {
    persist: true,
  }
  //sessionStorage 사용하고 싶은 경우
  // {
  //   persist: {
  //     storage: sessionStorage,
  //   },
  // }
);

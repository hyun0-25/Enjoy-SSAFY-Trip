<script setup>
// import { ref } from "vue";
// import { storeToRefs } from "pinia";
// import { useRouter } from "vue-router";
// import { useMemberStore } from "@/store/member";

// const router = useRouter();

// const memberStore = useMemberStore();
// const { isLogin } = storeToRefs(memberStore);
// const { userLogin, getUserInfo } = memberStore;
// const { changeMenuState } = useMemberStore();

// const loginUser = ref({
//   userId: "",
//   userPwd: "",
// });

// const login = async () => {
//   console.log("login successful !!!");
//   await userLogin(loginUser.value);
//   let token = sessionStorage.getItem("accessToken");
//   console.log("111. ", token);
//   console.log("isLogin", isLogin);
//   if (isLogin) {
//     console.log("로그인 성공");
//     getUserInfo(token);
//     changeMenuState();
//   }
//   router.push("/");
// };
</script>

<template>
  <div>
    <div>
      <ul class="">
        <li><a href="#0">로그인</a></li>
        <li><a href="#0">회원가입</a></li>
      </ul>
      <div>
        <form>
          <p>
            <label for="signin-id">아이디</label>
            <input type="signin-id" placeholder="아이디" />
          </p>
          <p>
            <label for="singin-password"></label>
            <input type="password" placeholder="비밀번호" />
          </p>
          <p>
            <input type="checkbox" id="remember-me" checked />
            <label for="remember-me">아이디 기억하기</label>
          </p>
          <p>
            <input type="submit" value="로그인" />
          </p>
        </form>
      </div>
      <div></div>
    </div>
  </div>
</template>

<style scoped></style>

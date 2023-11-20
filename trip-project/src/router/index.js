import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
import TheUserView from "../views/TheUserView.vue";
import TheKakaoMap from "../views/TheKakaoMap.vue";
import { useAuthStore } from "@/store/auth";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/board",
      name: "board",
      component: () => import("@/views/TheBoardView.vue"),
      children: [
        {
          path: "notice",
          name: "notice-list",
          component: () => import("@/components/boards/NoticeBoardList.vue"),
        },
        {
          path: "free",
          name: "free-list",
          component: () => import("@/components/boards/FreeBoardList.vue"),
        },
        {
          path: "hot",
          name: "hot-list",
          component: () => import("@/components/boards/HotBoardList.vue"),
        },
        {
          path: "contest",
          name: "contest-list",
          component: () => import("@/components/boards/ContestBoardList.vue"),
        },
        {
          path: "write",
          name: "board-write",
          component: () => import("@/components/boards/BoardWrite.vue"),
        },
        {
          path: "detail/:boardId",
          name: "board-detail",
          component: () => import("@/components/boards/BoardDetail.vue"),
        },
        //BoardModify
        {
          path: "modify/:boardId",
          name: "board-modify",
          component: () => import("@/components/boards/BoardModify.vue"),
          // meta: { requiresAuth: true },
          //관리자 권한 또한 필요한 경우
          // meta: { requiresAuth: true, requiresAdmin: true },
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      component: TheUserView,
      children: [
        {
          path: "/login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "/join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "/mypage",
          name: "user-mypage",
          component: () => import("@/components/users/UserMyPage.vue"),
        },
      ],
    },
    {
      path: "/map",
      name: "map",
      component: TheKakaoMap,
    },
  ],
});

// -----토큰----
//로그인, 또는 관리자 권한이 필요한 경우 제어 ( next()의 경우, 원래 이동하려 했던 라우트로 이동 )
router.beforeEach((to, from, next) => {
  //to:가려는 곳, from : 현재 화면
  const authStore = useAuthStore();
  //로그인 필요
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (!authStore.token) {
      //토큰이 없는 경우, 로그인 필요.
      alert("로그인 권한 필요");
      next({ path: "/login" }); // 인증되지 않은 경우, 로그인 페이지로 리다이렉트
    } else {
      //관리자 권한 필요
      if (to.matched.some((record) => record.meta.requiresAdmin)) {
        // 관리자가 아닌 경우 홈으로
        if (authStore.user.role !== "admin") {
          alert("관리자 권한 필요");
          next({ path: "/" });
        } else next(); // 관리자인 경우, 해당 경로로 이동
      } else next(); // 인증된 경우 해당 경로로 이동
    }
  } else next(); // 인증이 필요하지 않은 경우 해당 경로로 이동
});

export default router;

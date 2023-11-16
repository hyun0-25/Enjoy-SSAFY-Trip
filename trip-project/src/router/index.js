import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
import TheUserView from "../views/TheUserView.vue";

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
          path: "/gologin",
          name: "go-user-login",
          component: () => import("@/components/users/GoUserLogin.vue"),
        },
      ],
    },
  ],
});

export default router;

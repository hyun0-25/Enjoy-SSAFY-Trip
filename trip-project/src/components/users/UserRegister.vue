<script setup>
import { ref, watch } from "vue";
import { useAuthStore } from "@/store/auth";
import { useRouter } from "vue-router";
const authStore = useAuthStore();
const router = useRouter();

const dialog = ref(false);

const userInfo = ref({
  userId: "",
  userPassword: "",
  nickname: "",
  email: "",
  userType: "",
});

// 아이디 중복검사
// const isChecking = ref(0);
// const checkUserId = async () => {
//   const result = await authStore.isCheck(userInfo.value.userId);
//   isChecking.value = result.value;
//   console.log(
//     "중복검사 결과 : ",
//     userInfo.value.userId,
//     " , ",
//     isChecking.value
//   );
// };
// watch(() => userInfo.value.userId, checkUserId);
//---

const userTypes = ref(["MZ", "X"]);

const join = async () => {
  try {
    await authStore.registUser(userInfo.value);
    alert("회원가입되셨습니다.");
    router.push("/");
  } catch (error) {
    console.log("회원가입 실패 에러:", error);
    console.log("input users : ", userInfo);
    alert("회원가입이 불가합니다.");
  }
};
</script>

<template>
  <div><h3>회원가입</h3></div>
  <form @submit.prevent="join">
    <!-- 아이디 -->
    <p>
      <label for="singin-id"></label>
      <input
        type="text"
        v-model="userInfo.userId"
        placeholder="아이디"
        id="singin-id"
      />
      <!-- @input="checkUserId" -->
      <span v-if="userInfo.userId.length > 16"
        >아이디: 16글자까지 가능합니다.</span
      >
      <!-- <span v-if="isCheck === 1">이미 존재하는 아이디입니다.</span> -->
    </p>
    <!-- 비밀번호 -->
    <p>
      <label for="singin-password"></label>
      <input
        type="password"
        v-model="userInfo.userPassword"
        placeholder="비밀번호"
        autocomplete="off"
        id="singin-password"
      />
      <span
        v-if="
          userInfo.userPassword.length > 16 || userInfo.userPassword.length < 8
        "
      >
        비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.
      </span>
    </p>
    <!-- 닉네임 -->
    <p>
      <label for="nickname"></label>
      <input type="text" v-model="userInfo.nickname" placeholder="닉네임" />
    </p>
    <!-- 이메일 -->
    <p>
      <label for="eamil"></label>
      <input type="email" v-model="userInfo.email" placeholder="이메일" />
    </p>
    <!-- MZ/X 선택 -->
    <v-select
      label="당신의 타입은?"
      density="compact"
      :items="['MZ', 'X']"
      :item-text="['1980~2010년생', '1965~1979년생']"
      v-model="userInfo.userType"
    ></v-select>
    <!-- 약관동의 -->
    <p>
      <label for="agreement"></label>
      <input type="checkbox" />
      <v-dialog v-model="dialog" width="600">
        <template v-slot:activator="{ on, attrs }">
          <a v-on:click="() => (dialog = !dialog)" v-bind="attrs">약관 동의</a>
        </template>
        <template v-slot:default="{ isActive }">
          <v-card title="약관">
            <v-card-text>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem
              ipsum dolor sit amet consectetur adipisicing elit. Ipsam eaque
              delectus reiciendis ratione perspiciatis nam deserunt est,
              accusantium asperiores, nisi explicabo, natus rem atque. Quia
              dolores iste dolore. Commodi, excepturi. Lorem ipsum dolor, sit
              amet consectetur adipisicing elit. Obcaecati accusamus repudiandae
              ratione dignissimos soluta, molestiae exercitationem autem fugit
              perspiciatis, voluptatem quae atque aliquid sapiente libero
              perferendis error, voluptatum velit. Culpa amet commodi,
              blanditiis voluptates eveniet provident nobis iste consectetur
              fugiat vitae consequatur fugit. Ea eius laboriosam quis optio
              cupiditate eum animi tenetur harum, consectetur quibusdam vel,
              nemo nisi est, velit culpa perferendis deserunt dolores officia
              dolore temporibus porro! Modi enim voluptatibus voluptate nesciunt
              praesentium molestias itaque aspernatur beatae neque labore,
              alias, vel fugiat architecto laudantium aperiam magnam fuga a ex
              facere ad repellat cupiditate? Ab, totam quod corrupti doloremque,
              assumenda iusto molestiae nesciunt consequatur nostrum neque porro
              nulla dolorum sint explicabo optio tempore! Consectetur corporis
              esse aliquam quos perferendis, nihil voluptatibus illo dolorum
              reprehenderit nemo commodi saepe recusandae, mollitia iste vitae
              distinctio aperiam, architecto enim! Delectus quisquam, expedita
              sequi dolore ipsa consectetur eaque, maxime obcaecati eligendi
              iusto laudantium nobis deserunt ratione. Fuga explicabo est nemo
              consequuntur harum provident quisquam quis sed nulla, recusandae
              beatae quibusdam culpa error ad nihil expedita ipsam repellat
              maxime cum in vitae, deserunt excepturi neque. Reiciendis itaque,
              debitis minus voluptatibus dolor unde facilis excepturi
              repellendus earum nam in ducimus, pariatur assumenda amet iste ea
              nostrum ut.
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn text="확인" @click="isActive.value = false"></v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </p>
    <p>
      <button>가입하기</button>
    </p>
  </form>
</template>

<style scoped>
span {
  color: red;
}
</style>

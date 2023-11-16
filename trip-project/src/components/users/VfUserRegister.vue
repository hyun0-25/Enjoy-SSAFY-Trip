<script setup></script>

<template>
  <v-card>
    <v-tabs v-model="tab" bg-color="primary">
      <v-tab value="login">로그인</v-tab>
      <v-tab value="join">회원가입</v-tab>
    </v-tabs>

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="login">
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
        </v-window-item>

        <v-window-item value="join"
          ><form>
            <p>
              <label for="signin-id">아이디</label>
              <input type="text" placeholder="아이디" />
            </p>
            <p>
              <label for="singin-password"></label>
              <input type="password" placeholder="비밀번호" />
            </p>
            <p>
              <label for="nickname"></label>
              <input type="text" placeholder="닉네임" />
            </p>
            <p>
              <label for="nickname"></label>
              <input type="email" placeholder="이메일" />
            </p>
            <p>
              <label for="remember-me"></label>
              <input type="checkbox" /> 약관 동의
            </p>
            <p>
              <input type="submit" value="가입하기" />
            </p>
          </form>
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>

<style scoped></style>

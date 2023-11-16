<script setup></script>

<template>
  <div>회원가입</div>
  <form>
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
</template>

<style scoped></style>

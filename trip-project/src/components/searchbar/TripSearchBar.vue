<script setup>
import { ref } from "vue";
const props = defineProps(["options"]);

const searchInfo = ref({
  word: "",
});
</script>

<template>
  <div>
    <form @submit.prevent="$emit('searchEvent', searchInfo)">
      <v-text-field
        type="text"
        v-model="searchInfo.word"
        @input="$emit('searchEvent', searchInfo)"
        append-icon="mdi-magnify"
        single-line
        hide-details
        placeholder="여행지명 검색"
      />
      <!-- <button>검색</button> -->
    </form>
  </div>
</template>

<style scoped></style>

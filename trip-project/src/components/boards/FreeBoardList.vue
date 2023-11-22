<script setup>
import { ref } from "vue";
import draggable from "vuedraggable";

const data = ref([
  {
    id: 1,
    content: "test1",
  },
  {
    id: 2,
    content: "test2",
  },
  {
    id: 3,
    content: "teat3",
  },
]);
const data2 = ref([
  {
    id: 1,
    content: "testa",
  },
  {
    id: 2,
    content: "testb",
  },
  {
    id: 3,
    content: "teatc",
  },
]);
</script>

<template>
  <div class="container">
    <draggable v-model="data" item-key="id" group="items">
      <template #item="{ element, index }">
        <div class="drag-item">
          {{ index }}
          {{ element.content }}
        </div>
      </template>
    </draggable>
  </div>
  <div>
    <draggable v-model="data2" item-key="id" group="items">
      <template #item="{ element }">
        <div class="drag-item">
          {{ element.content }}
        </div>
      </template>
    </draggable>
  </div>
</template>

<style scoped>
.container {
  display: flex;
}

.drag-item {
  border: 1px solid #ccc;
  margin: 8px;
  padding: 8px;
  cursor: grab;
}
</style>

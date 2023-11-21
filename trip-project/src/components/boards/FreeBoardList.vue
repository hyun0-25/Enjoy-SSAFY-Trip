<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col cols="12" md="8">
          <!-- Map -->
          <v-card class="map-container">
            <v-card-text>
              <!-- Your map component goes here -->
              <p>Map goes here...</p>
            </v-card-text>
          </v-card>

          <!-- List overlay -->
          <v-overlay
            :value="isListOpen"
            @input="toggleList"
            class="list-overlay"
            style="z-index: 10"
          >
            <v-card class="list-card">
              <v-list dense>
                <v-list-item-group v-model="selectedItem">
                  <v-list-item @click="toggleList">
                    <v-list-item-content>
                      <v-list-item-title>Toggle List</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-action>
                      <v-icon>{{
                        isListOpen ? "mdi-chevron-left" : "mdi-chevron-right"
                      }}</v-icon>
                    </v-list-item-action>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-card>
          </v-overlay>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const isListOpen = ref(true);
    const selectedItem = ref(null);

    const toggleList = () => {
      isListOpen.value = !isListOpen.value;
    };

    return {
      isListOpen,
      selectedItem,
      toggleList,
    };
  },
};
</script>

<style scoped>
.map-container {
  height: 300px; /* Set the height of the map container */
  position: relative;
}

.list-overlay {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.list-card {
  width: 200px; /* Set the width of the list card */
}
</style>

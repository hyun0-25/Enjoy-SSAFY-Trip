<script setup>
import { ref } from "vue";

const cards = ["Today", "Yesterday"];
const links = [
  ["mdi-inbox-arrow-down", "Inbox"],
  ["mdi-send", "Send"],
  ["mdi-delete", "Trash"],
  ["mdi-alert-octagon", "Spam"],
];

const drawer = ref(null);
</script>

<template>
  <v-app id="inspire">
    <v-main>
      <v-navigation-drawer v-model="drawer">
        <v-sheet color="grey-lighten-4" class="pa-4">
          <v-avatar class="mb-4" color="grey-darken-1" size="64"></v-avatar>

          <div>john@google.com</div>
        </v-sheet>

        <v-divider></v-divider>

        <v-list>
          <v-list-item
            v-for="[icon, text] in links"
            :key="icon"
            :prepend-icon="icon"
            :title="text"
            link
          ></v-list-item>
        </v-list>
      </v-navigation-drawer>
      <v-container class="py-8 px-6" fluid>
        <v-row>
          <v-table>
            <thead>
              <tr>
                <th class="text-left">Name</th>
                <th class="text-left">Calories</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in desserts" :key="item.name">
                <td>{{ item.name }}</td>
                <td>{{ item.calories }}</td>
              </tr>
            </tbody>
          </v-table>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<style scoped></style>

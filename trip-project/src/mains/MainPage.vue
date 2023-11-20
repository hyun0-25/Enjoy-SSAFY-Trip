<script setup></script>

<template>
  <div id="wrap">
    <!-- MZ -->

    <v-row>
      <v-col cols="12" sm="6">
        <v-card class="mx-auto" height="auto" max-width="1000">
          <v-card-text
            class="font-weight-medium mt-12 text-center text-subtitle-1"
          >
            <!-- MZ main -->
            <h2>MZ</h2>
            <v-container>
              <v-card height="400">
                <v-img
                  src="https://picsum.photos/seed/picsum/1000/600"
                  class="bg-grey-lighten-2"
                ></v-img
              ></v-card>
            </v-container>
            <!-- MZ Hot Place -->
            <h2>HOT PLACE</h2>
            <v-container>
              <!-- 시안1 -->
              <v-row>
                <v-col cols="12" md="4">
                  <v-skeleton-loader
                    class="mx-auto border"
                    max-width="300"
                    type="image, article"
                  ></v-skeleton-loader>
                </v-col>
                <v-col cols="12" md="4">
                  <v-skeleton-loader
                    class="mx-auto border"
                    max-width="300"
                    type="image, article"
                  ></v-skeleton-loader>
                </v-col>
                <v-col cols="12" md="4">
                  <v-skeleton-loader
                    class="mx-auto border"
                    max-width="300"
                    type="image, article"
                  ></v-skeleton-loader>
                </v-col>
              </v-row>
            </v-container>
            <!-- 시안2 -->
            <v-carousel>
              <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                cover
              ></v-carousel-item>

              <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/hotel.jpg"
                cover
              ></v-carousel-item>

              <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                cover
              ></v-carousel-item>
            </v-carousel>
            <!-- END MZ Hot Place -->
            <!-- MZ Contest -->
            <h2>MZ Contest</h2>
            <v-container>
              <v-row>
                <v-col v-for="n in 3" :key="n" cols="4">
                  <v-card>
                    <v-img
                      src="https://picsum.photos/350/165?random"
                      max-height="350"
                      class="bg-grey-lighten-2"
                    ></v-img>
                    <v-card-title class="text-h6">
                      콘테스트
                      <v-btn
                        class="ma-2"
                        variant="text"
                        icon="mdi-thumb-up"
                        color="blue-lighten-2"
                      ></v-btn
                    ></v-card-title>
                  </v-card>
                </v-col>
              </v-row>
            </v-container>
            <!-- end contest -->
            <!-- 오늘의 여행 소식 start-->
            <div>
              <h3>오늘의 여행 소식</h3>
              <v-table>
                <thead>
                  <tr>
                    <th class="text-left">뉴스 타이틀</th>
                    <th class="text-left">뉴스 미리보기</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- <tr v-for="item in desserts" :key="item.name">
                    <td>{{ item.name }}</td>
                    <td>{{ item.calories }}</td> -->
                  <tr>
                    <td>이런저런 뉴스</td>
                    <td>저런이런 뉴스</td>
                  </tr>
                  <tr>
                    <td>이런저런 뉴스</td>
                    <td>저런이런 뉴스</td>
                  </tr>
                  <!-- </tr> -->
                </tbody>
              </v-table>
            </div>
            <!-- 오늘의 여행 소식 end-->
          </v-card-text>
        </v-card>
      </v-col>
      <!-- ===================================================================== -->
      <!-- X main-->
      <v-col cols="12" sm="6">
        <v-card class="mx-auto" height="auto" max-width="1000">
          <v-card-text
            class="font-weight-medium mt-12 text-center text-subtitle-1"
          >
            <!-- X main -->
            <h2>X</h2>
            <v-container>
              <v-card height="400">
                <v-img
                  src="https://picsum.photos/seed/picsum/1000/600"
                  class="bg-grey-lighten-2"
                ></v-img
              ></v-card>
            </v-container>
            <!-- X Hot Place -->
            <h2>HOT PLACE</h2>
            <v-container>
              <v-row>
                <v-col cols="12" md="4">
                  <v-skeleton-loader
                    class="mx-auto border"
                    max-width="300"
                    type="image, article"
                  ></v-skeleton-loader>
                </v-col>
                <v-col cols="12" md="4">
                  <v-skeleton-loader
                    class="mx-auto border"
                    max-width="300"
                    type="image, article"
                  ></v-skeleton-loader>
                </v-col>
                <v-col cols="12" md="4">
                  <v-skeleton-loader
                    class="mx-auto border"
                    max-width="300"
                    type="image, article"
                  ></v-skeleton-loader>
                </v-col>
              </v-row>
            </v-container>
            <v-carousel>
              <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                cover
              ></v-carousel-item>

              <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/hotel.jpg"
                cover
              ></v-carousel-item>

              <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                cover
              ></v-carousel-item>
            </v-carousel>
            <!-- END MZ Hot Place -->
            <!-- MZ Contest -->
            <h2>MZ Contest</h2>
            <v-container>
              <v-row>
                <v-col v-for="n in 3" :key="n" cols="4">
                  <v-card>
                    <v-img
                      src="https://picsum.photos/350/165?random"
                      max-height="350"
                      class="bg-grey-lighten-2"
                    ></v-img>
                    <v-card-title class="text-h6">
                      콘테스트
                      <v-btn
                        class="ma-2"
                        variant="text"
                        icon="mdi-thumb-up"
                        color="blue-lighten-2"
                      ></v-btn
                    ></v-card-title>
                  </v-card>
                </v-col>
              </v-row>
            </v-container>
            <!-- end contest -->
            <!-- 오늘의 여행 소식 start-->
            <div>
              <h3>오늘의 여행 소식</h3>
              <v-table>
                <thead>
                  <tr>
                    <th class="text-left">뉴스 타이틀</th>
                    <th class="text-left">뉴스 미리보기</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- <tr v-for="item in desserts" :key="item.name">
                    <td>{{ item.name }}</td>
                    <td>{{ item.calories }}</td> -->
                  <tr>
                    <td>이런저런 뉴스</td>
                    <td>저런이런 뉴스</td>
                  </tr>
                  <tr>
                    <td>이런저런 뉴스</td>
                    <td>저런이런 뉴스</td>
                  </tr>
                  <!-- </tr> -->
                </tbody>
              </v-table>
            </div>
            <!-- 오늘의 여행 소식 end-->
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<style scoped>
#wrap {
  margin-left: 15%;
  margin-right: 15%;
}
</style>

<script setup>
import { ref, watch } from "vue";
//hot place 정보
const hotPlaceInfo = ref([
  {
    imgScr: "https://picsum.photos/seed/picsum/300/200",
    headline: "어딘가의 풍경",
    subTitle: "가보고 싶다!",
    text: "어디일까? 그곳은... Lorem ipsum dolor sit amet consectetur adipisicing elit.Eos natus perferendis impedit voluptates at temporibus suscipit,labore,iste inventore modi reiciendis earum et?Laboriosam fugit architecto commodi minima asperiores nam?Est eligendi repudiandae ipsam quia.Qui optio quos doloremque aliquid asperiores temporibus eligendi,vitae tempore placeat id delectus facere maxime animi,similique autem odit aliquam eveniet tenetur quaerat porro iure.Voluptate soluta ab,laborum porro nostrum expedita magnam optio corrupti in quas quis deleniti exercitationem voluptates dicta similique,delectus animi est dolore vitae atque!Iste perferendis odio dolores eaque nam!Fugit illo esse doloribus.Officiis nemo vel repudiandae,totam corrupti esse fugit,ut sunt consequuntur dolorem quo nihil quod inventore aperiam ad minima provident,consectetur blanditiis repellendus placeat perferendis facere?Dolorem veritatis dolor quam mollitia distinctio ipsa tenetur minus,error eum.Quaerat quidem beatae inventore fugiat voluptates id,quia necessitatibus,nisi excepturi reiciendis magnam,ab optio maxime assumenda culpa corrupti?Dicta nostrum reprehenderit sint facilis!Eos,impedit eligendi.Exercitationem facere voluptatum doloribus neque,voluptates atque velit sapiente at adipisci nemo reiciendis labore quibusdam eveniet perspiciatis consequuntur sint amet?Ipsum,esse?Ea eligendi qui aut temporibus,neque delectus autem sequi eveniet vero repellat amet ex officiis asperiores fugit placeat tempore quod quam ad porro deleniti molestias debitis atque optio!Earum,amet.Modi nobis eveniet,nostrum doloremque corrupti sapiente,repellat magnam adipisci perferendis consequuntur nulla repudiandae nihil praesentium!Deleniti,accusantium quidem culpa vero minus voluptas itaque reprehenderit?A nobis ratione ipsa sapiente.Neque,molestiae facere,nesciunt repudiandae doloremque,dolorum est vitae placeat corrupti eveniet deleniti similique rem aspernatur?Odio iure deserunt qui.Explicabo impedit nemo facere debitis quo nihil,earum voluptate voluptatibus.Harum,sequi illum.Dolor adipisci expedita impedit iure dolorem,facere quasi labore reprehenderit magni et!Possimus nesciunt aliquid omnis voluptatum quis nobis magnam,architecto temporibus,minus alias animi,aut exercitationem.",
  },

  {
    imgScr: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
    headline: "Top western road trips",
    subTitle: "1,000 miles of wonder",
    text: "I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.",
  },
  {
    imgScr: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
    headline: "Top western road trips",
    subTitle: "1,000 miles of wonder",
    text: "I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.",
  },
  {
    imgScr: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
    headline: "Top western road trips",
    subTitle: "1,000 miles of wonder",
    text: "I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.",
  },
  {
    imgScr: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
    headline: "Top western road trips",
    subTitle: "1,000 miles of wonder",
    text: "I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.",
  },
]);
const hpButtons = ref([]);
watch(
  hotPlaceInfo,
  () => {
    hpButtons.value = hotPlaceInfo.value.map(() => ({ show: false }));
  },

  ///////Hot Place 데이터 받아오면 지울 부분
  {
    immediate: true,
  }
  //////=============================
);
// hot place 열기,닫기 버튼---
const toggleButton = (index) => {
  console.log("toggleButton:", index);
  //다른 버튼은 닫기
  for (let i = 0; i < hpButtons.value.length; i++) {
    if (i !== index) hpButtons.value[i].show = false;
  }
  hpButtons.value[index].show = !hpButtons.value[index].show;
};
// hot place 확대,축소---

//---
</script>

<template>
  <div id="wrap">
    <!-- MZ -->

    <v-card class="mx-auto" height="auto">
      <v-card-text class="font-weight-medium mt-12 text-center text-subtitle-1">
        <!-- MZ main -->
        <h2>MZ</h2>
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
        <!-- MZ Hot Place -->
        <h2>HOT PLACE</h2>
        <div>
          <v-layout row>
            <!-- HOT PLACE 1 start-->
            <v-flex
              xs12
              sm6
              offset-sm3
              v-for="(item, index) in hotPlaceInfo"
              :key="index"
            >
              <v-card>
                <v-img
                  :src="item.imgScr"
                  height="200px"
                  class="hoverable-image"
                >
                </v-img>

                <v-card-title primary-title class="hoverable-image">
                  <div>
                    <div class="headline">{{ item.headline }}</div>
                    <span class="grey--text">{{ item.subTitle }}</span>
                  </div>
                </v-card-title>

                <v-card-actions class="hoverable-image">
                  <v-btn flat>Share</v-btn>
                  <v-btn flat color="purple">전체 보러가기</v-btn>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="toggleButton(index)">
                    <v-icon>{{
                      hpButtons[index].show
                        ? "mdi mdi-chevron-up"
                        : "mdi mdi-chevron-down"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-slide-y-transition class="hoverable-image">
                  <v-card-text v-show="hpButtons[index].show">
                    {{ item.text }}
                  </v-card-text>
                </v-slide-y-transition>
              </v-card>
            </v-flex>
            <!-- HOT PLACE 1 end-->
          </v-layout>
        </div>
        <v-container>
          <!-- hover 비율 바꾸기 -->
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
        <div>
          <v-container>
            <v-card height="400">
              <v-img
                src="https://picsum.photos/seed/picsum/1000/600"
                class="bg-grey-lighten-2"
              ></v-img
            ></v-card>
          </v-container>
        </div>
        <!-- END MZ Hot Place -->
        <!-- MZ Contest -->
        <div>
          <h2>MZ Contest</h2>
          <br />
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
        </div>
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
                    <td>{{ item.calories }}</td> </tr>-->
              <tr>
                <td>이런저런 뉴스</td>
                <td>저런이런 뉴스</td>
              </tr>
              <tr>
                <td>이런저런 뉴스</td>
                <td>저런이런 뉴스</td>
              </tr>
            </tbody>
          </v-table>
        </div>
        <!-- 오늘의 여행 소식 end-->
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped>
#wrap {
  margin-left: 15%;
  margin-right: 15%;
}
v-container {
  margin: 5px;
}
.image-col {
  flex: 1;
}

.hoverable-image {
  transition: width 0.3s;
  min-width: 100px;
}
.hoverable-image:hover {
  width: 500px; /* 넓게 하고 싶은 크기로 조절 */
}
</style>

<script setup>
import { ref, watch, computed } from "vue";
import { useBoardStore } from "@/store/board";
// import { useNeswStore } from "@/store/news";
// const newsStore = useNeswStore();
const boardStore = useBoardStore();

//-----------contest
const articles = computed(() => boardStore.articles);
const params = ref({
  key: "", //조건 검색 시 컬럼명
  word: "", //해당 컬럼에 일치하는 데이터
});

//목록 조회
boardStore.getArticles(params.value, "contest");
//-----------contest end
//---------- hot
const articlesHot = computed(() => boardStore.articles);

const paramsHot = ref({
  key: "", //조건 검색 시 컬럼명
  word: "", //해당 컬럼에 일치하는 데이터
});
boardStore.getArticles(paramsHot.value, "hot");

//---------- hot end

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
// 뉴스 크롤링
// const getNews = computed(() => newsStore.news);
// const neswParams = ref({
//   key: "",
//   word: "",
// });
// newsStore.listNews();
const newsDum = ref([
  {
    company: "뉴시스",
    title: "임시공휴일 해외여행 급증…국내 숙박비 11분기 만에 최대 감소",
    url: "https://www.newsis.com/view/?id=NISX20231123_0002533291&cID=10401&pID=10400",
  },
  {
    company: "조선일보",
    title: "[류동현의 예술여행] [5] 한국 최초의 ‘카페’, 정관헌",
    url: "https://www.chosun.com/opinion/specialist_column/2023/11/24/HZR46OHSWVDDBOIELDYUCYBE2M/?utm_source=naver&utm_medium=referral&utm_campaign=naver-news",
  },
  {
    company: "이데일리",
    title: "진시황 무덤을 발굴하지 않는 이유는…中 고대 수도 시안 [여행]",
    url: "https://www.edaily.co.kr/news/read?newsId=01190646635809000&mediaCodeNo=257&OutLnkChk=Y",
  },
  {
    company: "연합뉴스",
    title: "[K스토리] '나도 사진작가다' 연합뉴스 여행자학교 사진전",
    url: "https://www.yna.co.kr/view/MYH20231123006300528?input=1197m",
  },
  {
    company: "경남도민일보",
    title: "[세대공감]팔레스타인 서안지구 여행기",
    url: "https://www.idomin.com/news/articleView.html?idxno=839936",
  },
]);

//---
</script>

<template>
  <div id="wrap">
    <!-- MZ -->

    <v-card class="mx-auto" height="auto">
      <v-card-text class="font-weight-medium mt-12 text-center text-subtitle-1">
        <!-- MZ main -->
        <div>
          <v-carousel>
            <RouterLink :to="{ path: '/map/write' }">
              <v-carousel-item
                src="https://dimg.donga.com/wps/NEWS/IMAGE/2022/11/05/116328493.1.edit.jpg"
              ></v-carousel-item
            ></RouterLink>

            <RouterLink :to="{ path: '/map/write' }">
              <v-carousel-item
                src="https://cdn.lecturernews.com/news/photo/201809/7434_19046_3811.jpg"
                cover
              ></v-carousel-item>
            </RouterLink>

            <RouterLink :to="{ path: '/map/write' }">
              <v-carousel-item
                src="https://mblogthumb-phinf.pstatic.net/MjAxOTEyMTBfMjg4/MDAxNTc1OTU3MTQ1MTU1.pH-PUmgINJ29hgr1Qh8ZQr3jKdYR5IIzWDPkP_xLGpUg.iJnGDLYq3vduN4GzOMJKX-FQG1LCDSr3I3Juo0VoAJ8g.JPEG.kjchol123/%EC%A0%95%EB%8F%99%EC%A7%84.jpg?type=w800"
                cover
              ></v-carousel-item>
            </RouterLink>
          </v-carousel>
        </div>
        <!-- MZ Hot Place -->
        <h2>HOT PLACE</h2>
        <div>
          <v-layout row>
            <!-- HOT PLACE start-->
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
            <!-- HOT PLACE end-->
          </v-layout>
        </div>

        <!-- hover 비율 바꾸기 -->

        <!-- END Hot Place -->

        <!-- Contest -->
        <div>
          <h2>Contest</h2>
          <br />
          <v-container>
            <v-row>
              <v-col v-for="card in articles" :key="card.title" :cols="4">
                <v-card>
                  <!-- 콘테스트 사진 -->
                  <v-img
                    :src="card.src"
                    class="align-end"
                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                    height="200px"
                    cover
                  ></v-img>
                  <!-- class="bg-grey-lighten-2" -->
                  <!-- 콘테스트 게시글 제목 -->
                  <v-card-title class="text-h6">
                    {{ card.title }}
                    <!-- 종아요 버튼 -->
                    <RouterLink :to="{ name: 'contest-list' }">
                      <v-btn
                        class="ma-2"
                        variant="text"
                        icon="mdi-thumb-up"
                        color="blue-lighten-2"
                      ></v-btn></RouterLink
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
                <th class="text-left">뉴스 발행처</th>
                <th class="text-left">뉴스 타이틀</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in newsDum" :key="index">
                <td>{{ item.company }}</td>
                <a :href="item.url">
                  <td>{{ item.title }}</td>
                </a>
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
div {
  boarder: 20px;
}
.hoverable-image {
  transition: width 0.3s;
  min-width: 100px;
}
.hoverable-image:hover {
  width: 500px; /* 넓게 하고 싶은 크기로 조절 */
}
a {
  text-align: justify;
  text-decoration: none;
  color: black;
}
</style>

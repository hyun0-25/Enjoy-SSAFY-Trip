<script setup>
import { onMounted, ref, computed, watch } from "vue";
import draggable from "vuedraggable";
import { useRouter, useRoute } from "vue-router";
//1.store 객체 얻어오기
import { useAttractionStore } from "@/store/attraction";
import { useMyLocationStore } from "@/store/mylocation";
const router = useRouter();
const route = useRoute();
const mylocationStore = useMyLocationStore();

const { courseId } = route.params;
const tripview = ref([]);
const attractions = ref([]);
const attraction = computed(() => mylocationStore.attraction);

//2.반응형 데이터 연결하기
const courseList = computed(() => mylocationStore.courseList);
const tripDateFormat = ref("");
onMounted(async () => {
  await mylocationStore.getCourseList(courseId);
  const dataS = new Date(courseList.value[0].startDate);
  const dataE = new Date(courseList.value[0].endDate);
  const datesize =
    (dataE.getTime() - dataS.getTime()) / (1000 * 60 * 60 * 24) + 1;
  for (let i = 0; i < datesize; i++) {
    tripview.value.push(new Array());
    const filtertrip = courseList.value.filter((x) => x.courseOrder == i);
    for (let j = 0; j < filtertrip.length; j++) {
      await getoneattraction(filtertrip, j);
      tripview.value[i].push(attraction.value);
      attractions.value.push(attraction.value);
    }
  }
  trip_title.value = courseList.value[0].courseName;
  tripDateFormat.value =
    courseList.value[0].startDate + " ~ " + courseList.value[0].endDate;
});

const getoneattraction = async (filtertrip, j) => {
  await mylocationStore.getAttraction(filtertrip[j].contentId);
};

//////////////////////////////////////////////////////////////////////
//지도
const container = ref(null); //<div id="map"> 엘리먼트 객체
// const map = ref(null); //kakaoMap 객체
var map;

const message = ref("");

//최초에 javascript 파일 가져올 때만 실행될 메소드
const loadScript = () => {
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${
    import.meta.env.VITE_KAKAO_MAP_API_KEY
  }&autoload=false`;
  script.onload = () => kakao.maps.load(loadMap);
  document.head.appendChild(script);
};
const markers = ref([]);
const positions = ref([]);
//지도 불러오는 메소드
const loadMap = () => {
  //1.지도 출력
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container.value, options);

  positions.value = [];
  attractions.value.forEach((station) => {
    let obj = {};
    obj.latlng = new kakao.maps.LatLng(station.latitude, station.longitude);
    obj.title = station.title;

    positions.value.push(obj);
  });
  markers.value = [];
  positions.value.forEach((position) => {
    console.log(position);
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });
    markers.value.push(marker);
    marker.setMap(map);
  });
  if (positions.value.length > 0) {
    map.panTo(positions.value[0].latlng);
  }
};

const selectAttraction = ref({});
watch(
  () => selectAttraction.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(
      selectAttraction.value.latitude,
      selectAttraction.value.longitude
    );

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) loadMap();
  else loadScript();
});

const markposition = (item) => {
  selectAttraction.value = item;
};
const dialog = ref(false);
const attractionInfo = ref({});
const ondialog = (info) => {
  attractionInfo.value = info;
  dialog.value = true;
};
const offdialog = () => {
  dialog.value = false;
};

const trip_title = ref("");

const isListOpen = ref(false);
</script>

<template>
  <div style="display: flex">
    <!-- <div style=""> -->
    <!-- Content area -->

    <!-- </div> -->

    <div style="width: 100%">
      <div ref="container" id="map" style="height: 100%">
        <v-expand-x-transition style="width: 45%; position: relative">
          <v-card v-show="!isListOpen" class="slide">
            <v-card-text>
              <!-- Your content goes here -->
              <v-text-field
                v-model="trip_title"
                label="여행명"
                variant="outlined"
                readonly
              ></v-text-field>
              <p>여행 일정</p>
              <h5>{{ tripDateFormat }}</h5>
              <v-btn style="margin-bottom: 5px">여행 수정</v-btn>
              <v-list
                style="overflow-x: auto; white-space: nowrap"
                width="100%"
              >
                <v-list-item
                  v-for="(list, idx) in tripview"
                  height="500"
                  width="300"
                  style="display: inline-block"
                >
                  <v-list-item-title> {{ idx + 1 }}일차 </v-list-item-title>
                  <v-list style="overflow-y: scroll" height="500">
                    <draggable
                      v-model="tripview[idx]"
                      item-key="id"
                      group="items"
                    >
                      <template #item="{ index, element }">
                        <v-card>
                          <div>
                            <v-avatar
                              class="ma-3"
                              size="100"
                              rounded="0"
                              @dblclick="ondialog(element)"
                              @click="markposition(element)"
                            >
                              <v-img :src="element.firstImage"></v-img>
                            </v-avatar>
                            <div>
                              <!-- {{ idx }}
                            {{ index }} -->
                              <v-card-title
                                class="text-h6"
                                @dblclick="ondialog(element)"
                                @click="markposition(element)"
                              >
                                {{ element.title }}
                              </v-card-title>

                              <v-card-subtitle>{{
                                element.address
                              }}</v-card-subtitle>
                            </div>
                          </div>
                        </v-card>
                      </template>
                    </draggable>
                    <v-dialog v-model="dialog" width="50vw">
                      <v-card>
                        <v-avatar class="ma-3" size="150" rounded="0">
                          <v-img :src="attractionInfo.firstImage"></v-img>
                        </v-avatar>
                        <v-card-title class="text-h6">
                          {{ attractionInfo.title }}
                        </v-card-title>
                        <v-card-text>{{ attractionInfo.overview }}</v-card-text>
                        <v-card-actions>
                          <v-btn color="primary" block @click="offdialog()"
                            >X</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-list>
                </v-list-item>
              </v-list>
            </v-card-text>
          </v-card>
        </v-expand-x-transition>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide-btn {
  position: absolute;
  top: 45%;
  right: 0;
  transform: translateX(100%);
  z-index: 5;
  background-color: rgba(241, 239, 239, 0.781);
  border-radius: 0px 20px 20px 0px;
}
.slide {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 3;
  border-radius: 20px;
  margin: 10px;
  height: 100%;
}
</style>

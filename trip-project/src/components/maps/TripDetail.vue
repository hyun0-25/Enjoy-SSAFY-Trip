<script setup>
import { onMounted, ref, computed, watch } from "vue";
import draggable from "vuedraggable";
import { useRouter, useRoute } from "vue-router";
//1.store 객체 얻어오기
import { useMyLocationStore } from "@/store/mylocation";
const router = useRouter();
const route = useRoute();
const mylocationStore = useMyLocationStore();

const { courseId } = route.params;
const tripview = ref([]);
const attraction = computed(() => mylocationStore.attraction);
const trip_title = ref("");
const isListOpen = ref(false);

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
    }
  }
  trip_title.value = courseList.value[0].courseName;
  tripDateFormat.value =
    courseList.value[0].startDate + " ~ " + courseList.value[0].endDate;

  if (window.kakao && window.kakao.maps) loadMap();
  else loadScript();
});

const getoneattraction = async (filtertrip, j) => {
  await mylocationStore.getAttraction(filtertrip[j].contentId);
};

///////////////////////////////////////
//지도
const container = ref(null); //<div id="map"> 엘리먼트 객체
// const map = ref(null); //kakaoMap 객체
var map;

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
const linePath = ref([]);
//지도 불러오는 메소드
const loadMap = async () => {
  //1.지도 출력
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 6,
  };
  map = new kakao.maps.Map(container.value, options);
  const strokeColors = [
    "#00D1FF",
    "#82D333",
    "#FFAE00",
    "#D5A2FF",
    "#FF0078",
    "#007EFF",
    "#987E00",
  ];
  positions.value = [];
  markers.value = [];

  for (let i = 0; i < tripview.value.length; i++) {
    linePath.value = [];
    var count = 1;
    for (let j = 0; j < tripview.value[i].length; j++) {
      const station = tripview.value[i][j];
      console.log(station);
      let position = {};
      position.latlng = new kakao.maps.LatLng(
        station.latitude,
        station.longitude
      );
      position.title = station.title;
      positions.value.push(position);

      const marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: position.latlng, // 마커를 표시할 위치
        clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      });

      linePath.value.push(position.latlng);

      markers.value.push(marker);

      var iwContent =
        '<div style="padding:5px;">' +
        count++ +
        ". " +
        position.title +
        "</div>";

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        position: position.latlng,
        content: iwContent,
      });

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(map, marker);
    }

    var polyline = new kakao.maps.Polyline({
      path: linePath.value, // 선을 구성하는 좌표배열 입니다
      strokeWeight: 5, // 선의 두께 입니다
      strokeColor: strokeColors[i], // 선의 색깔입니다
      strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
      strokeStyle: "solid", // 선의 스타일입니다
    });
    polyline.setMap(map);
  }

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

const deleteCourse = async () => {
  try {
    if (!confirm("여행 정보를 삭제하시겠습니까?")) return;
    await mylocationStore.deleteCourse(courseId);
    router.push("/user/mypage");
    alert("삭제 완료");
  } catch (error) {
    console.log("삭제 에러:", error);
    alert("삭제 실패");
  }
};
</script>

<template>
  <div style="display: flex">
    <!-- <div style=""> -->
    <!-- Content area -->

    <!-- </div> -->

    <div style="width: 100%; display: flex">
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
            <RouterLink
              :to="{
                name: 'trip-modify',
                params: { courseId: courseId },
              }"
              ><v-btn style="margin-bottom: 5px">여행 수정</v-btn></RouterLink
            >
            <v-btn style="margin-bottom: 5px" @click="deleteCourse()"
              >여행 삭제</v-btn
            >
            <v-list style="overflow-x: auto; white-space: nowrap" width="100%">
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
      <div
        ref="container"
        id="map"
        style="height: 100%; width: 55%; position: relative"
      ></div>
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
  /* border-radius: 20px; */
  /* margin: 10px; */
  height: 100%;
}
</style>

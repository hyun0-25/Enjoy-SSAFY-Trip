<script setup>
import { onMounted, ref, computed, watch } from "vue";
import draggable from "vuedraggable";
import { useRouter, useRoute } from "vue-router";
import VSelect from "@/components/select/VSelect.vue";
import TripSearchBar from "@/components/searchbar/TripSearchBar.vue";
//1.store 객체 얻어오기
import { useAttractionStore } from "@/store/attraction";
import { useMyLocationStore } from "@/store/mylocation";
const router = useRouter();
const route = useRoute();
const attractionStore = useAttractionStore();

//2.반응형 데이터 연결하기
const attractions = computed(() => attractionStore.attractions);

const params = ref({
  sido: -1, //시도
  gugun: -1, //구군
  word: "", //검색데이터
});

//목록 조회
attractionStore.getAttractions(params.value);

const sidoList = ref([]);
const sidos = computed(() => attractionStore.sidos);

const getSidoList = () => {
  let options = [];
  options.push({ text: "시도선택", value: "" });
  sidos.value.forEach((sido) => {
    options.push({ text: sido.sidoName, value: sido.sidoCode });
  });
  sidoList.value = options;
};

const init = async () => {
  //목록 조회
  await attractionStore.listSido();
  getSidoList();
};
init();

const gugunList = ref([{ text: "구군선택", value: "" }]);

const guguns = computed(() => attractionStore.guguns);

const onChangeSido = async (val) => {
  await attractionStore.listGugun({ sido: val });
  getGugunList();
  params.value.sido = val;
  if (params.value.gugun == 0 && params.value.word == "") {
    alert("시도 전체 검색시 여행지명을 입력해주세요!");
    params.value.gugun = -1;
    attractionStore.getAttractions(params.value);
    params.value.gugun = 0;
    return;
  }
  attractionStore.getAttractions(params.value);
};

const getGugunList = () => {
  let options = [];
  options.push({ text: "구군선택", value: "" });
  options.push({ text: "전체", value: 0 });
  guguns.value.forEach((gugun) => {
    options.push({ text: gugun.gugunName, value: gugun.gugunCode });
  });
  gugunList.value = options;
};

const onChangeGugun = (val) => {
  params.value.gugun = val;
  console.log(params.value);
  // getChargingStations();
  if (val == 0 && params.value.word == "") {
    alert("시도 전체 검색시 여행지명을 입력해주세요!");
    params.value.gugun = -1;
    console.log("hi");
    attractionStore.getAttractions(params.value);
    params.value.gugun = 0;
    return;
  }
  attractionStore.getAttractions(params.value);
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

  // //2. 마커 찍기
  // const marker = new kakao.maps.Marker({
  //   position: map.value.getCenter(),
  // });
  // marker.setMap(map.value);

  // //3. 지도 click 이벤트 핸들링
  // kakao.maps.event.addListener(map.value, "click", (mouseEvent) => {
  //   // 클릭한 위도, 경도 정보를 가져옵니다
  //   const latlng = mouseEvent.latLng;
  //   // 마커 위치를 클릭한 위치로 옮깁니다
  //   marker.setPosition(latlng);

  //   message.value = `<h3>클릭한 위치의 위도는 ${latlng.getLat()}이고, 경도는 ${latlng.getLng()} 입니다</h3>`;
  // });
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

watch(
  () => attractions.value,
  () => {
    positions.value = [];
    attractions.value.forEach((station) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(station.latitude, station.longitude);
      obj.title = station.title;

      positions.value.push(obj);
    });
    loadMarkers();
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) loadMap();
  else loadScript();
});
const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  markers.value = [];
  positions.value.forEach((position) => {
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

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  // const bounds = positions.value.reduce(
  //   (bounds, position) => bounds.extend(position.latlng),
  //   new kakao.maps.LatLngBounds()
  // );

  // map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};

const markposition = (item) => {
  selectAttraction.value = item;
};
const dialog = ref(false);
const attractionInfo = ref({});
const sample_myloc = ref([]);
const ondialog = (info) => {
  attractionInfo.value = info;
  dialog.value = true;
};
const offdialog = () => {
  dialog.value = false;
};

const addMyLocation = (info) => {
  //기존 리스트에 안담겨있음
  if (!sample_myloc.value.includes(info)) {
    sample_myloc.value.push(info);
  }
};
const deleteMyLocation = (item) => {
  sample_myloc.value = sample_myloc.value.filter((e) => e !== item);
};
watch(sample_myloc, () => {
  console.log(sample_myloc.value);
});

const caldialog = ref(false);
const startDate = ref(null);
const endDate = ref(null);
const PickstartDate = ref(null);
const PickendDate = ref(null);
const MaxDate = ref(null);

watch(PickstartDate, () => {
  var sdate = new Date(PickstartDate.value);
  // console.log(sdate);
  var mdate = new Date(sdate.setDate(sdate.getDate() + 6));
  MaxDate.value = mdate;
  // console.log(MaxDate.value);
  if (PickstartDate.value != null) {
    startDateFormat.value = PickstartDate.value.toISOString().slice(0, 10);
  }
});
watch(PickendDate, () => {
  if (PickendDate.value != null) {
    endDateFormat.value = PickendDate.value.toISOString().slice(0, 10);
  }
});
const cancel = () => {
  caldialog.value = false;
  // 취소 버튼을 눌렀을 때의 로직 추가
};

const startDateFormat = ref("");
const endDateFormat = ref("");
const tripDateFormat = ref("일정을 선택해주세요");

const tripview = ref([]);

//최대 사이즈
const size = ref(0);
//진짜 사이즈
const listsize = ref(0);
const confirm = () => {
  if (
    size.value == 0 &&
    PickstartDate.value != null &&
    PickendDate.value != null
  ) {
    size.value = listsize.value =
      (PickendDate.value.getTime() - PickstartDate.value.getTime()) /
        (1000 * 60 * 60 * 24) +
      1;
    for (var i = 0; i < size.value; i++) {
      tripview.value.push(new Array());
    }
  } else if (PickstartDate.value != null && PickendDate.value != null) {
    var addsize =
      (PickendDate.value.getTime() - PickstartDate.value.getTime()) /
        (1000 * 60 * 60 * 24) +
      1 -
      listsize.value;
    if (addsize > 0) {
      size.value = listsize.value =
        (PickendDate.value.getTime() - PickstartDate.value.getTime()) /
          (1000 * 60 * 60 * 24) +
        1;
      for (var i = 0; i < addsize; i++) {
        tripview.value.push(new Array());
      }
    } else {
      listsize.value =
        (PickendDate.value.getTime() - PickstartDate.value.getTime()) /
          (1000 * 60 * 60 * 24) +
        1;
    }
  }

  // 확인 버튼을 눌렀을 때의 로직 추가
  caldialog.value = false;
  startDate.value = PickstartDate.value;
  endDate.value = PickendDate.value;
  if (startDate.value != null && endDate.value != null) {
    startDateFormat.value = startDate.value.toISOString().slice(0, 10);
    endDateFormat.value = endDate.value.toISOString().slice(0, 10);
    tripDateFormat.value = startDateFormat.value + " ~ " + endDateFormat.value;
    //view에 보일 일정
    tripview.value = tripview.value.slice(0, listsize.value);
  }
  console.log(listsize.value);
};

const openModal = () => {
  caldialog.value = true;
};

const deletetrip = (idx, index) => {
  tripview.value[idx].splice(index, index + 1);
};

const trip_title = ref("");

const mylocationStore = useMyLocationStore();

const listForm = ref([]);

const addAllMyLocation = async () => {
  if (PickstartDate.value == null || PickendDate.value == null) {
    alert("일정을 선택해주세요!");
    return;
  }
  console.log(tripview.value);
  for (let i = 0; i < tripview.value.length; i++) {
    if (tripview.value[i].length == 0) {
      alert("하루에 최소 1개 일정을 추가해주세요!");
      return;
    }
  }
  if (trip_title.value == "" || tripview.value == []) {
    alert("여행명을 입력해주세요!");
    return;
  }
  //else
  for (var i = 0; i < tripview.value.length; i++) {
    for (var j = 0; j < tripview.value[i].length; j++) {
      var writeForm = {
        contentId: tripview.value[i][j].contentId,
        courseName: trip_title.value,
        userId: "ssafy",
        startDate: startDateFormat.value,
        endDate: endDateFormat.value,
        courseOrder: i,
        order: j,
      };
      listForm.value.push(writeForm);
    }
  }
  try {
    await mylocationStore.addMyLocations(listForm.value);
    router.push({ path: "/map" });
  } catch (error) {
    console.log("등록 에러 내용:", error);
    alert("등록 실패");
  }
};

const isListOpen = ref(false);

const toggleList = () => {
  if (PickstartDate.value != null && PickendDate.value != null) {
    isListOpen.value = !isListOpen.value;
  } else {
    alert("여행 일정을 선택해주세요!");
  }
};

const getSearchArticles = (searchKeyword) => {
  console.log("BoardList의 조건 검색 메소드 호출:", searchKeyword);

  params.value.word = searchKeyword.word;

  if (params.value.gugun == 0 && params.value.word == "") {
    params.value.gugun = -1;
    attractionStore.getAttractions(params.value);
    params.value.gugun = 0;
    return;
  }
  //목록 조회 필요
  attractionStore.getAttractions(params.value);
};
</script>

<template>
  <div style="display: flex">
    <div style="width: 20%" v-show="!isListOpen">
      <div>
        <TripSearchBar @search-event="getSearchArticles"></TripSearchBar>
        <div>
          <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
        </div>
        <div>
          <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
        </div>
      </div>
      <v-expand-x-transition>
        <v-card>
          <v-card class="mx-auto">
            <v-container>
              <v-list height="600">
                <v-list-item v-for="item in attractions">
                  <!-- <v-row dense> -->
                  <!-- <v-col v-for="attr in items" :key="attr.raw.contentId" cols="12"> -->
                  <v-card>
                    <!-- <div class="d-flex flex-no-wrap justify-space-between"> -->
                    <div>
                      <v-avatar
                        class="ma-3"
                        size="100"
                        rounded="0"
                        @dblclick="ondialog(item)"
                        @click="markposition(item)"
                      >
                        <v-img :src="item.firstImage"></v-img>
                      </v-avatar>
                      <div>
                        <v-card-title
                          class="text-h5"
                          @dblclick="ondialog(item)"
                          @click="markposition(item)"
                        >
                          {{ item.title }}
                        </v-card-title>

                        <v-card-subtitle>{{ item.address }}</v-card-subtitle>

                        <v-card-actions>
                          <v-btn
                            class="ms-2"
                            variant="outlined"
                            size="small"
                            @click="addMyLocation(item)"
                          >
                            +
                          </v-btn>
                        </v-card-actions>
                      </div>
                    </div>
                  </v-card>
                </v-list-item>
              </v-list>
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
                    <v-btn color="primary" block @click="offdialog()">X</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
        </v-card>
      </v-expand-x-transition>
    </div>

    <div style="width: 20%; position: relative">
      <v-card class="mx-auto">
        <v-container>
          <v-col>
            <v-text-field
              v-model="trip_title"
              label="여행명"
              variant="outlined"
              :rules="[(v) => !!v || '여행명을 입력해주세요.']"
            ></v-text-field>
          </v-col>
          <div>
            <h5>{{ tripDateFormat }}</h5>
            <v-btn @click="openModal" style="margin-bottom: 5px"
              >여행 일정 선택</v-btn
            >

            <v-dialog v-model="caldialog" max-width="800px">
              <v-card>
                <v-card-title class="headline">여행 일정 선택</v-card-title>
                <v-card-text>최대 7일까지 설정할 수 있습니다</v-card-text>
                <v-card-text>
                  <div style="display: flex; justify-content: space-around">
                    <div>
                      <v-row>
                        <v-col cols="12">
                          <v-text-field
                            v-model="startDateFormat"
                            label="시작 날짜"
                            prepend-icon="mdi-calendar"
                            readonly
                          ></v-text-field>
                          <v-date-picker
                            v-model="PickstartDate"
                            ref="startDatePicker"
                            show-adjacent-months
                          ></v-date-picker>
                        </v-col>
                      </v-row>
                    </div>
                    <div>
                      <v-row>
                        <v-col cols="12">
                          <v-text-field
                            v-model="endDateFormat"
                            label="종료 날짜"
                            prepend-icon="mdi-calendar"
                            readonly
                          ></v-text-field>
                          <v-date-picker
                            v-model="PickendDate"
                            ref="endDatePicker"
                            show-adjacent-months
                            :min="PickstartDate"
                            :max="MaxDate"
                          ></v-date-picker>
                        </v-col>
                      </v-row>
                    </div>
                  </div>
                </v-card-text>
                <v-card-actions>
                  <v-btn color="blue darken-1" text @click="cancel">취소</v-btn>
                  <v-btn color="blue darken-1" text @click="confirm"
                    >확인</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>

          <v-list height="500" style="overflow-y: auto">
            <draggable v-model="sample_myloc" item-key="id" group="items">
              <template #item="{ element }">
                <div class="drag-item">
                  <!-- {{ element.content }} -->
                  <v-card>
                    <div>
                      <v-avatar class="ma-3" size="100" rounded="0">
                        <v-img :src="element.firstImage"></v-img>
                      </v-avatar>
                      <div>
                        <v-card-title class="text-h6">
                          {{ element.title }}
                        </v-card-title>

                        <v-card-subtitle>{{ element.address }}</v-card-subtitle>

                        <v-card-actions>
                          <v-btn
                            class="ms-2"
                            variant="outlined"
                            size="small"
                            @click="deleteMyLocation(element)"
                          >
                            X
                          </v-btn>
                        </v-card-actions>
                      </div>
                    </div>
                  </v-card>
                </div>
              </template>
            </draggable>
          </v-list>
        </v-container>
      </v-card>
      <v-btn class="ms-2" variant="outlined" @click="addAllMyLocation()">
        여행생성
      </v-btn>
      <v-list-item @click="toggleList" class="slide-btn">
        <h6>일정</h6>
        <h6>생성</h6>

        <v-list-item-action>
          <v-icon>{{
            isListOpen ? "mdi-chevron-left" : "mdi-chevron-right"
          }}</v-icon>
        </v-list-item-action>
      </v-list-item>
      <!-- Content area -->
      <v-expand-x-transition style="width: 300%">
        <v-card v-show="isListOpen" class="slide">
          <v-card-text>
            <!-- Your content goes here -->
            <p>여행 일정</p>
            <v-list
              style="overflow-x: auto; white-space: nowrap"
              width="100%"
              height="600"
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
                          <v-avatar class="ma-3" size="100" rounded="0">
                            <v-img :src="element.firstImage"></v-img>
                          </v-avatar>
                          <div>
                            <!-- {{ idx }}
                            {{ index }} -->
                            <v-card-title class="text-h6">
                              {{ element.title }}
                            </v-card-title>

                            <v-card-subtitle>{{
                              element.address
                            }}</v-card-subtitle>

                            <v-card-actions>
                              <v-btn
                                class="ms-2"
                                variant="outlined"
                                size="small"
                                @click="deletetrip(idx, index)"
                              >
                                X
                              </v-btn>
                            </v-card-actions>
                          </div>
                        </div>
                      </v-card>
                    </template>
                  </draggable>
                </v-list>
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>
      </v-expand-x-transition>
    </div>

    <div style="width: 80%">
      <div ref="container" id="map" style="height: 85%"></div>
      <p><em>지도를 클릭해주세요!</em></p>
      <div v-html="message"></div>
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
  transform: translateX(100%);
  z-index: 3;
  border-radius: 20px;
  margin: 10px 0px 10px 10px;
}
</style>

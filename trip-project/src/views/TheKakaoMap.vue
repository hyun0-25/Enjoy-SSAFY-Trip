<script setup>
import { onMounted, ref, computed, watch } from "vue";
import draggable from "vuedraggable";
import { useRouter, useRoute } from "vue-router";
import VSelect from "@/components/select/VSelect.vue";
import TripSearchBar from "../components/searchbar/TripSearchBar.vue";
//1.store 객체 얻어오기
import { useAttractionStore } from "../store/attraction";
import { useMyLocationStore } from "../store/mylocation";
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

const sample = ref([
  {
    contentId: 126486,
    contentTypeId: 12,
    title: "도산공원",
    address: "서울특별시 강남구 도산대로45길 20",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52146325,
    longitude: 127.0338117,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/60/2678560_image3_1.jpg",
    overview:
      "서울시 강남구 신사동에 자리한 근린공원이다. 1970년 3월 10일 박정희 전 대통령은 도산 안창호 선생이 이 나라 자주와 독립을 위해 바친 위대한 애국정신과 민중의 교화를 위한 교육정신을 국민의 귀감으로 삼게 하고자 도산공원을 조성할 것을 지시한다. 이에 서울시는 1973년 강남구 신사동에 약 1만여평 공원을 조성하고, 망우리 공동묘지에서 선생의 유해를 이장하는 동시에 미국 로스앤젤레스에서 부인 이혜련 여사의 유해를 옮겨와 합장하였다. 그 후, 강남구 청담동에서 논현동에 이르는 노폭 50m 연장 3250m의 신설가로를 도산대로로 명명하였다. 공원 내에는 도산 선생 기념물로 동상 1기, 어록비 1기, 비문해설기 1기 등이 설치되어 있으며, 매년 3월 10일 이곳에서 홍사단 및 도산기념 사업회 주관으로 도산 선생 추모 기념 행사를 거행한다.",
  },
  {
    contentId: 126504,
    contentTypeId: 12,
    title: "봉은사(서울)",
    address: "서울특별시 강남구 봉은사로 531",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.51587822,
    longitude: 127.0577486,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/37/2652137_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/37/2652137_image3_1.jpg",
    overview:
      "봉은사는 신라 원성왕 10년(794년)에 연회국사가 세웠고, 견성사라고 했다. 1498년 성종의 계비 정현왕후가 성종의 능(선릉) 동편에 있던 이 절을 크게 중창하고 절이름을 봉은사로 바꿨다. 본래는 서남쪽으로 1km가량 떨어진 선릉에 있던 것을 조선 명종 때 보우가 옮겨 지었다. 추사 김정희가 쓴 현판이 걸린 판전에는 화엄경, 금강경 등 13가지의 불경 경판 3,479판이 보관되어 있다. 매년 음력 9월 9일에는 사부대중이 함께 경판을 머리에 이고 법성게를 독송하면서 법계도를 따라 행진하는 정대불사라는 의식이 행해진다.",
  },
  {
    contentId: 126525,
    contentTypeId: 12,
    title: "서울 선릉(성종과 정현왕후)과 정릉(중종) [유네스코 세계문화유산]",
    address: "서울특별시 강남구 선릉로100길 1",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.50742068,
    longitude: 127.0471803,
    firstImage: "",
    firstImage2: "",
    overview:
      "선릉에는 조선 제9대 성종과 그 계비 정현왕후 윤씨를 모신 선릉과 제11대 중종을 모신 정릉이 있다. 성종은 세조의 손자이며 덕종(추존)의 둘째 아들로 세조 3년(1457)에 탄생하여 13세(1469)때 즉위하고 재위 25년간 학문을 장려하고 제도를 정비하는 등 많은 치적을 남겼으며 38세(1494)로 승하하였다. 정현왕후는 우상 영원 부원군 윤호의 딸로서, 성종 11년 (1480)에 비로 책봉되고 중종 25년(1530)에 69세로 승하하였다. 중종은 성종의 둘째 아들로, 성종 19년(1488)에 탄생하여 1506년에 즉위하고, 재위 39년(1544) 57세로 승하하였다. 사적인 선정릉은 도시 가운데 있으면서도, 그리 잘 알려져 있지 않아 한적한 편이며, 조용히 산책을 즐길 수 있다. 또한, 능을 둘러싸고 있는 철망 울타리를 철거하여 숲이 있는 구간에는 고풍스러운 담장을 쌓아 돌담길을 만들었다.",
  },
  {
    contentId: 126823,
    contentTypeId: 12,
    title: "압구정 로데오거리",
    address: "서울특별시 강남구 압구정동",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52687661,
    longitude: 127.0388972,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/70/2024970_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/70/2024970_image3_1.jpg",
    overview:
      "압구정 로데오거리는 한국의 ‘유행1번지’란 말로 대표된다. 이곳은 90년대 초 패션의 중심가로 자리잡으면서 젊은이들의 해방구로, 기존질서나 가치로부터 탈피하려는 문화의 거리로 탄생되었다. 과거 압구정 로데오 거리는 비벌리힐스의 로데오거리를 표방하면서 부유층 자녀들이 외제차와 고급브랜드 옷을 입고 활보했던 곳으로 유명했지만 지금은 다양한 젊음의 문화를 대변하는 곳으로, 첨단 유행을 대표하는 곳으로 발전했다.로데오 거리에는 골목마다 명품 브랜드상점, 보세옷가게, 구두가게, 속옷가게, 액세서리가게, 명품 할인가게 등의 패션관련 매장이 들어서 있으며 피부과, 성형외과, 헤어숍들도 성업 중이다. 머리끝에서 발끝까지 패션에 관한 모든 것이 이곳에 모여있는 셈이다. 게다가 첨단 유행을 대변하는 곳인 만큼 모든 상점들이 독특한 인테리어로 손님의 눈길을 끌고 있다. 압구정 로데오 거리는 그 화려함을 가장 큰 장점으로 꼽을 수 있다. 현란하리만치 화려한 네온 불빛, 그 주위를 생동감 있게 거니는 활기찬 사람들의 모습이 눈에 띈다. 이 곳에 위치한 갤러리아 백화점 명품관은 압구정 로데오거리의 가장 대표적인 공간으로, 세계적으로 유명한 명품들을 한자리에서 비교 구입할 수 있다. 프라다, 발리, 조르지오 아르마니, 페라가모, 구찌, 베르사체 등 최고급 브랜드 매장이 자리하고 있다. 가격은 제품별로 천차만별이며, 세일기간에는 20~30% 할인된다. 반면 보세가게는 최신 유행을 쏟아놓는 곳으로 다른 곳에서는 볼 수 없는 독특한 디자인의 물건들이 많다. 좋은 품질을 기본으로 독특한 디자인에 승부를 거는 가게들이 많고, 젊은이들도 남들과는 다른 옷을 구입하기 위해 많이 찾는다. 로데오 거리에는 젊은이들에게 입소문으로 퍼진 맛집과 명소도 즐비하기때문에, 쇼핑 외에도 먹을거리,볼거리가 많다.",
  },
  {
    contentId: 127269,
    contentTypeId: 12,
    title: "청담공원",
    address: "서울특별시 강남구 영동대로131길 26",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52115807,
    longitude: 127.0526683,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/00/204200_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/00/204200_image3_1.jpg",
    overview:
      "1960년대 초까지만 해도 뚝섬 나루터에서 배를 타고 한강을 건너면 강남 땅에 닿는 곳이 지금의 청담동 일대였다. 오늘날 청담공원은 지극히 도시적인 청담동에 위치한 오염되지 않은 작고 아담한 공원이다. 낮은 산책로를 따라 올라가면 간단한 운동기구가 비치되어 있는 놀이터가 있는데 아침이면 운동하는 사람들로 제법 붐비는 곳이기도 하다. 빽빽이 들어선 나무들로 도시에서는 드물게 맑은 공기를 들이마실 수 있는 휴식공간이다. 공원의 면적은 59,347㎡이다.",
  },
  {
    contentId: 129921,
    contentTypeId: 14,
    title: "다도화랑",
    address: "서울특별시 강남구 논현로159길 24",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52298794,
    longitude: 127.0260901,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/80/1258080_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/80/1258080_image3_1.jpg",
    overview:
      "1992년 2월 개관한 다도화랑은 화랑 기획초대전, 초대작가전 등 회화,조각 등 여러 분야의 전시를 한다. 우리 조각의 선구자인 정관 김복진 선생의 자료를 미술사학회 팀과 발굴하여, 컴퓨터로 복원된 작품전시회 및 문집발간, 추모비 건립 등의 문화발전에 노력하여 왔다.",
  },
  {
    contentId: 129922,
    contentTypeId: 14,
    title: "갤러리미",
    address: "서울특별시 강남구 압구정로 461 네이처 포엠",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52531027,
    longitude: 127.0472465,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/58/683658_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/58/683658_image3_1.jpg",
    overview:
      "갤러리미 (GALLERY MEE)는 1977년 개관이래 기획전시화랑으로 운영되어 왔다. 원로, 중진, 중견 작가의 회화, 조각, 판화 전시회를 비롯하여 능력있는 작가를 발굴하여 지원해오고 있으며 작가의 국제적인 발판을 마련하기 위해 애쓰고 있다.",
  },
  {
    contentId: 129923,
    contentTypeId: 14,
    title: "예화랑",
    address: "서울특별시 강남구 가로수길 73 예화랑",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52293232,
    longitude: 127.0222322,
    firstImage: "",
    firstImage2: "",
    overview:
      "1978년 개관한 예화랑은 국내.외 기획전시 및 대규모 국제 미술전 참가를 비롯한 미술문화의 발전과 미술 국제화를 위한 노력으로 '대중과 함께 호흡하는 예술'을 지향하고 있다. 1982년 강남구 신사동으로 이전, 문화시설의 불모지였던 강남지역에 화랑을 이전 개관함으로써 그 후 다른 문화공간들이 생겨나게 된 계기를 마련하였다. 1983년에서 1985년에는 김태성 전 예화랑 대표이사가 화랑협회 회장 재임시 한국 미술계에서는 최초로 미술시장 유통구조 개선을 위한 세미나를 주관하여, 한국의 대표적인 작가, 화상, 미술평론가, 미술애호가들이 참여하는 진지한 토론회를 가짐으로써 한국미술계의 발전에 공헌하였으며, '86 아시안 게임'과 '88 서울올림픽' 기념 문화사업의 하나로 대규모 조각 전시회를 개최한 바 있다. 또한 스페인의 ARCO, 프랑스의 FIAC, 일본의 NICAF, 독일의 쾰른아트페어 등 세계적인 국제 미술제에도 활발히 참여하여 외국작가 전시회를 비롯한 국제 예술행사 또한 기획, 추진함으로써 한국미술 문화예술의 국제화에 기여하고 있다. 이런 움직임은 우리 예술이 세계인과 교감하고 내부로는 새로운 창작활동에 자극을 주는 원동력이 된다. 국가와 기업의 미술문화에 대한 관심과 지원을 촉진하는 예화랑은 공공 미술 프로젝트 사업과 Art Consulting 에도 많은 관심을 기울이고 있으며 25년에 이른 역사에 비추어 우리나라의 화단(畵壇)을 이끌어가는 정상급 화랑의 중심에 서서 한국미술발전에 크게 기여하고 있다.",
  },
  {
    contentId: 129924,
    contentTypeId: 14,
    title: "갤러리 인데코",
    address: "서울특별시 강남구 압구정로36길 8",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52794857,
    longitude: 127.0313117,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/81/2024981_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/81/2024981_image3_1.jpg",
    overview:
      "갤러리 인데코는 1988년 조덕현, 박순옥, 강성원 3인전 개관기념 초대전을 시작으로 1989년 고암 이응노 추모전을 비롯하여 방혜자, 김원숙 그리고 박항률, 이현, 김일영, 강명순, 이정웅, 장문걸 선생님 등을 모시고 크고 작은 그룹전 및 개인전을 기획 전시하였고, 98년 예술의 전당에서 ",
  },
  {
    contentId: 129927,
    contentTypeId: 14,
    title: "갤러리 서림",
    address: "서울특별시 강남구 학동로 523",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52013881,
    longitude: 127.0560653,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/32/1837332_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/32/1837332_image3_1.jpg",
    overview:
      "서림화랑은 1984년 개관하여 1985년 吳承雨展을 시작으로 그동안 개인전과 테마전을 포함한 그룹전을 백 수십여차례 가졌다. 1986년 동양화와 대형작품을 선호하던 당시 소품전을 개최하여 소품(1호 ∼10호)에 대한 관심을 갖게 했다. 또한 우리나라에서 처음으로 테마전(장미전, 초가전, 부채전, 설경전, 정물전, 어린이를 위한 그림전, 그리운 금강산전, 아름다운 강산, 海景展, 한글사랑, 그림잔치, 시가 있는 그림전)을 시도하여 그 주제를 통해 함께 생각해보는 장을 만들어 지금까지 많은 호응을 얻고 있다. 특히 1987년부터 ",
  },
  {
    contentId: 129930,
    contentTypeId: 14,
    title: "청화랑",
    address: "서울특별시 강남구 삼성로147길 4",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52273002,
    longitude: 127.0476549,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/89/684089_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/89/684089_image3_1.jpg",
    overview:
      "원로에서부터 중진, 역량 있는 신인작가들의 작품을 취급하고 있다. 월 1회 정기적인 전시회를 기획하며 판화미술제, 화랑미술제, 청담미술제, SIAF 등에 참여하고 있다. 기획 전시, 초대전 등 다양하고 이채로운 전시회 계획이 있다. * 주요 작가 : 김태호, 김선희, 강미선, 전영근, 안윤모, 이혜민, 이창분, 마틴버귤러( MARTIN BURGUILLO),조의현(조각),김근배(조각)",
  },
  {
    contentId: 129931,
    contentTypeId: 14,
    title: "영동 예맥화랑",
    address: "서울특별시 강남구 강남대로 546",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.51085038,
    longitude: 127.021902,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/63/1837263_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/63/1837263_image3_1.jpg",
    overview:
      "영동 예맥화랑(영동시장 4거리)은 1992년 9월 개관 이래 회화, 조각, 공예 분야의 정예작가, 중견 및 원로 작가들을 초대, 전시를 통한 지역사회의 문화 진흥에 기여해 왔으며, 미적 인간상 확립, 미적 환경상 조성, 미적 생활상 정립, 문화산업을 통한 국제 문화 교류의 장으로 신인발굴 및 소장자와의 교량적 역할을 목적으로 하고 있다. * 전시장 성격 : 기획전, 초대전, 대관전 혼합병행 * 전시경향 : 서양화, 동양화, 조각, 설치, 판화, 영상, 공예, 고미술 등 종합전시",
  },
  {
    contentId: 130198,
    contentTypeId: 14,
    title: "서울국가무형문화재전수회관",
    address: "서울특별시 강남구 봉은사로 406",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.51020087,
    longitude: 127.0446444,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/39/683839_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/39/683839_image3_1.jpg",
    overview:
      "서울국가무형문화재전수회관은 국가가 전통문화의 보존, 전승을 위해 건립한 건물이며 국가무형문화재 보유자를 입주시켜 전수교육, 일반인강습 등을 지원하고 있다.예능 종목은 8개 단체가 입주해 있으며 보유자를 중심으로 그 예능을 후세에 이어주기 위하여 전수교육과 일반인 강습을 연중 실시하고, 정기발표공연 및 국내,외 초청공연에도 참가한다. 기능 종목은 12개 공방이 입주해 있으며 국가무형문화재 기능보유자가 공예품의 제작과정과 완성된 작품을 일반에 공개하고, 공예기능의 전수교육도 실시하고 있다.주요 시설은 공연좌석 149석의 민속극장 '풍류'가 있으며, 2~3층의 상설전시장과 기획전시실이 있는 '전통공예관'이 있고, 국가무형문화재 보유자 및 전승공예가들의 작품을 판매하는 전통공예품 상설전시판매장, 세미나실, 예능연습실, 공예교육실기실 등이 있다. * 개관일 - 1997년 11월 29일",
  },
  {
    contentId: 130226,
    contentTypeId: 14,
    title: "포스코미술관",
    address: "서울특별시 강남구 테헤란로 440",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.50587658,
    longitude: 127.0560768,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/05/684205_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/05/684205_image3_1.jpg",
    overview:
      "1995년 9월 갤러리로 개관 1998년 2월 미술관으로 재등록하였다. 포스코미술관은 1년에 10번 정도의 전시를 기획하며, 주로 순수미술을 전시하며 지역작가 전 1회, 금속과조형전 3회, 신진작가전 1회, 원로작가전 1회 등의 전시 일정을 가지고 있다. * 개관년도1998.2.13",
  },
  {
    contentId: 130284,
    contentTypeId: 14,
    title: "코엑스 아쿠아리움",
    address: "서울특별시 강남구 영동대로 513",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.51180927,
    longitude: 127.0591319,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/86/2433886_image2_1.JPG",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/86/2433886_image2_1.JPG",
    overview:
      "코엑스 아쿠아리움은 전시 수조 183개, 사육수조 90개, 수량 3,500톤에 이르는 대형수족관이다. ‘환상적인 물의 여행’을 테마로 16개 테마존에서 650여 종 4만여 마리의 수중생물들을 만날 수 있으며, 특히 국내 최다(最多)종, 개체 수의 상어의 서식하고 있다. 20곳의 휴식공간과 상부개방형 수조가 있는 힐링플레이스 코엑스 아쿠아리움은 매년 100만 명 이상의 국내·외국인들이 찾는 서울 강남 도심 속 관광명소로 자리잡았다.",
  },
  {
    contentId: 130289,
    contentTypeId: 14,
    title: "LG아트센터",
    address: "서울특별시 강남구 논현로 508",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.50197669,
    longitude: 127.0373342,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/48/2024248_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/48/2024248_image3_1.jpg",
    overview:
      "2000년 3월 27일 개관한 LG아트센터는 공익법인 LG연암문화재단이 '문화예술의 창작과 교류를 통한 기업 이윤의 사회 환원'이라는 기치 아래 5년 여 동안 총 공사비 620억원을 들여 건립한 최첨단 다목적 공연장이다. 국내 처음으로 공연장 운영과 공연 프로그램 기획을 담당할 실무진이 설계 단계에서부터 참여하여 공연장 경영 비전을 건축에 반영했다. 또한 세계 수준의 공연장을 탄생시키기 위해 국내외의 전문가들과 협력, 전문적인 극장 설계가 이루어졌다. LG아트센터는 예술인들에게는 작품에 생명력을 주는 최상의 공간이 되고, 관객들에게는 최고의 감동과 서비스를 제공하는 문화 공간이 됨으로써, 공연예술의 창조성과 다양성을 증대시키는데 이바지하기 위해 노력하고 있다. LG아트센터는 서울 강남의 비즈니스 타운에 위치하고 있으며, 지하철 2호선과 바로 연결되어 편리한 교통환경을 보유하고 있다. 내부규모는 3개 층. 총 1,103석의 객석 공간, 폭 16.4m, 깊이 25m의 무대를 갖춘 중형 공연장으로 음악, 무용, 연극, 뮤지컬 등 모든 장르의 예술을 수용할 수 있는 첨단 시설을 보유하고 있다. 국내 최초로 건물의 하부와 측벽에 건축구조분리체를 삽입하는 첨단공법을 도입, 주변의 진동과 소음이 들어오는 것을 방지하였으며, 규모의 특성에 따라 잔향을 조절할 수 있는 가변형 어쿠스틱 배너, 이동형 콘서트 타워 등을 갖추어 모든 형태의 공연에서 최상의 음향 환경이 연출되도록 했다. 관객을 위한 시설로 1,100여대가 동시 주차 가능한 주차장, 물품보관소, 세 개 층에 걸쳐 마련된 식음료바, 그리고 장애인용 리프트가 있으며, 공연자를 위하여 지하 2층의 리허설 룸과 전용 휴게공간, 분장실, 메이크업, 워드롭 등의 편의시설이 갖추어져 있다.",
  },
  {
    contentId: 130304,
    contentTypeId: 14,
    title: "서울특별시교육청 강남도서관",
    address: "서울특별시 강남구 선릉로116길 45",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.51363384,
    longitude: 127.0466663,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/62/1923662_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/62/1923662_image3_1.jpg",
    overview:
      "1982년 3월 개관이래 각 분야의 정보 및 지식의 제공과 다양한 문화프로그램을 통해 지역주민의 지적수준 향상과 건전한 시민의 문화공간으로 그 역할을 담당해 오면서 14만여 권의 다양한 장서와 연속간행물, 비도서자료 및 인터넷 환경을 제공함으로서 강남구 정보문화센터로서의 역할을 담당하고 있다.",
  },
  {
    contentId: 130306,
    contentTypeId: 14,
    title: "유 아트 스페이스",
    address: "서울특별시 강남구 압구정로71길 10",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.52739334,
    longitude: 127.0458549,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/39/1258239_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/39/1258239_image3_1.jpg",
    overview:
      "1974년 인사동에서 갤러리 삼경으로 출발한 유아트스페이스는 작지만 깔끔한 느낌을 주는 미술품 상설 전시장이다. 동양화와 서양화를 한꺼번에 볼 수 있고, 중후한 멋의 조각품 들도 만날 수 있다. 수준 높은 작품감상과 구입을 원하는 이들이 한번쯤 들러 볼만한 곳이다.",
  },
  {
    contentId: 130373,
    contentTypeId: 14,
    title: "강남문화원",
    address: "서울특별시 강남구 테헤란로8길 36",
    sidoCode: 1,
    gugunCode: 1,
    latitude: 37.49649112,
    longitude: 127.0320237,
    firstImage:
      "http://tong.visitkorea.or.kr/cms/resource/87/1807787_image2_1.jpg",
    firstImage2:
      "http://tong.visitkorea.or.kr/cms/resource/87/1807787_image3_1.jpg",
    overview:
      "강남문화원은 개원 이래 30여 문화강좌를 개설하고 미술, 서예, 독서, 생활수기, 전통예술제를 비롯한 각종 문화행사를 통하여 구민이 한마음 되도록 하고 강남구를 문화의 고장으로 발전시켜 지역사회개발과 나라발전에 기여할 것을 목적으로 화합의 문화를 열어 가도록 노력하고 있다. 지역문화예술의 발전을 위해 역말도당제와 주민들의 문화욕구 충족을 위한 문화예술 강좌와 역사문화유적탐방을 하고 있다. 지역의 역사적 사료를 발굴하여 계승, 보존하기 위한 역사문화연구소를 운영하고, 문화 해설사를 양성하여 관내, 초,중학교에서 강남의 역사문화에 대한 인식을 고취시키고 있다. 또한, 역량 있는 작가를 발굴하기 위해 매년 강남미술대전, 강남서예문인화대전을 개최하고 있으며, 전통예술경연대회를 통하여 우리문화를 보급하는데 앞장서고 있다.미래의 버팀목이 될 청소년들의 문화적 가치를 함양시키기 위해 강남독서대전을 운영하고 있다.",
  },
]);

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
  console.log(sdate);
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
      PickendDate.value.getDate() - PickstartDate.value.getDate() + 1;
    for (var i = 0; i < size.value; i++) {
      tripview.value.push(new Array());
    }
  } else if (PickstartDate.value != null && PickendDate.value != null) {
    var addsize =
      PickendDate.value.getDate() -
      PickstartDate.value.getDate() +
      1 -
      listsize.value;
    if (addsize > 0) {
      size.value = listsize.value =
        PickendDate.value.getDate() - PickstartDate.value.getDate() + 1;
      for (var i = 0; i < addsize; i++) {
        tripview.value.push(new Array());
      }
    } else {
      listsize.value =
        PickendDate.value.getDate() - PickstartDate.value.getDate() + 1;
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

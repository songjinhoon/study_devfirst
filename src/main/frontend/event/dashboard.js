import DevMap from "../view/DevMap";
import $ from "jquery";
import axios from "axios";
import * as slick from "slick-carousel";

window.addEventListener('DOMContentLoaded', (event) => {
	const devMap = new DevMap({ target: 'devMapArea' });

	/* 기상청 레이더 합성 영상 */
	const url = 'http://apis.data.go.kr/1360000/RadarImgInfoService/getCmpImg';
	const serviceKey = '7ybMtm52Wvh5bOH8PhzGTk%2Bramhq1PsDZgL5B0dstTb%2FcqiNGoCYylggaTrr1iWyDjCj5K5ux6Gr1eYIwmol9g%3D%3D';
	const pageNo = 1;
	const numOfRows = 10;
	const dataType = 'JSON';
	const data = 'CMP_WRC';
	const time = '20210405';
	const queryParams = `?ServiceKey=${serviceKey}&pageNo=${pageNo}&numOfRows=${numOfRows}&dataType=${dataType}&data=${data}&time=${time}`;
	const requestUrl = url + queryParams;
	// 최신 데이터 부터 10개
	axios({
		method: 'get',
		url: requestUrl
	}).then(response => {
		console.log(response);
		const items = response.data.response.body.items.item[0];
		const item = items['rdr-img-file'].replace('[', '').replace(']', '');
		const itemArr = item.split(',').reverse();
		const radar1Dom = document.querySelector('.radar1');
		let count = 1;
		for(let i=0; i<itemArr.length; i+=12) {
			if(count === 11) {
				break;
			}else {
				count++;
				const dataDom = document.createElement("img");
				dataDom.setAttribute('src', itemArr[i]);
				radar1Dom.append(dataDom);
			}
		}
	}).catch(response => {
		console.log(response);
	});

	/* 슬라이드 반영 */
	$('.your-class').slick({
		slide: 'div',
		infinite : true, 	//무한 반복 옵션
		slidesToShow : 1,		// 한 화면에 보여질 컨텐츠 개수
		slidesToScroll : 1,		//스크롤 한번에 움직일 컨텐츠 개수
		speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
		arrows : true, 		// 옆으로 이동하는 화살표 표시 여부
		dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
		autoplay : true,			// 자동 스크롤 사용 여부
		autoplaySpeed : 2000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
		pauseOnHover : true,		// 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
		vertical : false,		// 세로 방향 슬라이드 옵션
		prevArrow : "<button type='button' style='background: gray' class='slick-prev'>Previous</button>",		// 이전 화살표 모양 설정
		nextArrow : "<button type='button' style='background: gray' class='slick-next'>Next</button>",		// 다음 화살표 모양 설정
		dotsClass : 'slick-dots', 	//아래 나오는 페이지네이션(점) css class 지정
		draggable : true, 	//드래그 가능 여부
	});


	$('.radar1').slick({
		slide: 'img',
		infinite : true, 	//무한 반복 옵션
		slidesToShow : 1,		// 한 화면에 보여질 컨텐츠 개수
		slidesToScroll : 1,		//스크롤 한번에 움직일 컨텐츠 개수
		speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
		arrows : true, 		// 옆으로 이동하는 화살표 표시 여부
		dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
		autoplay : true,			// 자동 스크롤 사용 여부
		autoplaySpeed : 2000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
		pauseOnHover : true,		// 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
		vertical : false,		// 세로 방향 슬라이드 옵션
		prevArrow : "<button type='button' style='background: gray' class='slick-prev'>Previous</button>",		// 이전 화살표 모양 설정
		nextArrow : "<button type='button' style='background: gray' class='slick-next'>Next</button>",		// 다음 화살표 모양 설정
		dotsClass : 'slick-dots', 	//아래 나오는 페이지네이션(점) css class 지정
		draggable : true, 	//드래그 가능 여부
	});
});
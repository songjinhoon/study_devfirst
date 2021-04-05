import DevMap from "../view/DevMap";
import $ from "jquery";
import axios from "axios";

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
	$.ajax({
		method: 'get',
		url: requestUrl,
		success: response => {
			const items = response.response.body.items.item[0];
			const item = items['rdr-img-file'].replace('[', '').replace(']', '');
			const itemArr = item.split(',');
			const radar1Dom = document.querySelector('.radar1');
			for(const data of itemArr) {
				const dataDom = document.createElement("img");
				dataDom.setAttribute('src', data);
				radar1Dom.append(dataDom);
			}
		},
		error: response => {
			console.log(response);
		}
	});
});
package com.devfirst.admin.epic.func;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionTest {
	
	@Test
	public void getDateTime() throws Exception {
	}
	
	@Test
	@DisplayName("LocalDateTime")
	public void test() throws Exception {
		LocalTime currentTime = LocalTime.now();   
		LocalTime targetTime = LocalTime.of(12,33,35,22); 
		LocalDate now = LocalDate.now();
		LocalDate targetDate = LocalDate.of(2019,11,12);
		LocalDateTime currentDateTime = LocalDateTime.now();    
		LocalDateTime targetDateTime = LocalDateTime.of(2019, 11, 12, 12, 32,22,3333);
		System.out.println(currentDateTime);
		System.out.println(currentDateTime.minusDays(30));
		System.out.println(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // LocalDateTime -> String
	}

	@Test
	@DisplayName("기상청 레이더 영상 - 자바스크립트")
	public void test1() throws Exception {
		/*const url = 'http://apis.data.go.kr/1360000/RadarImgInfoService/getCmpImg';
		const serviceKey = '7ybMtm52Wvh5bOH8PhzGTk%2Bramhq1PsDZgL5B0dstTb%2FcqiNGoCYylggaTrr1iWyDjCj5K5ux6Gr1eYIwmol9g%3D%3D';
		const pageNo = 1;
		const numOfRows = 10;
		const dataType = 'JSON';
		const data = 'CMP_WRC';
		const time = '20210405';
		const queryParams = `?ServiceKey=${serviceKey}&pageNo=${pageNo}&numOfRows=${numOfRows}&dataType=${dataType}&data=${data}&time=${time}`;
		const requestUrl = url + queryParams;
		axios({
			method: 'get',
			url: requestUrl
		}).then(response => {
			console.log(response);
			const items = response.data.response.body.items.item[0];
			const item = items['rdr-img-file'].replace('[', '').replace(']', '');
			const itemArr = item.split(',').reverse();
			const radar1Dom = document.querySelector('.radar2');
			let count = 1;
			for(let i=0; i<itemArr.length; i+=12) {
				if(count === 11) {
					break;
				}else {
					count++;
					const divDom = document.createElement('div');
					const imgDom = document.createElement('img');
					imgDom.setAttribute('src', itemArr[i]);
					divDom.append(imgDom);
					radar1Dom.append(divDom);
				}
			}
		}).catch(response => {
			console.log(response);
		});*/
	}
}

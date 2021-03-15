import Tile from 'ol/layer/Tile';
import XYZ from 'ol/source/XYZ';
import View from 'ol/View';
import Map from 'ol/Map';
import VectorSource from 'ol/source/Vector';
import VectorLayer from 'ol/layer/Vector';

const NAVER_STREET_TILE_URL = 'https://map.pstatic.net/nrb/styles/basic/1613538062/{z}/{x}/{y}.png?mt=bg.ol.ts.lko';
const NAVER_SATELLITE_TILE_URL = 'https://map.pstatic.net/nrb/styles/satellite/1613538062/{z}/{x}/{y}.png?mt=bg.ol.ts.lko';
const VWORLD_SATELLITE_TILE_URL = 'http://xdworld.vworld.kr:8080/2d/Satellite/service/{z}/{x}/{y}.jpeg';
const VWORLD_STREET_TILE_URL = 'http://xdworld.vworld.kr:8080/2d/Base/service/{z}/{x}/{y}.png';

export default class DevMap {
	constructor(props) {
		const { target } = props;
		this.map = this.intro(target);
		this.addVectorLayer();
	}
	
	intro(target) {
		const mapElement = document.getElementById(target);
        const baseMapUrl = "http://xdworld.vworld.kr:8080/2d/Base/service/{z}/{x}/{y}.png";
        const baseLayer = new Tile({
            id: "baseLayer",
            title: "VWorld Map",
            type: "base",
            source: new XYZ({url: baseMapUrl})
        });
        // 지도 뷰 옵션
        const viewOption = {
            projection: "EPSG:3857", // 지도 좌표계
            center: [14135163.862873793, 4518388.798904137], // 지도 초기 위치
            zoom: 10,
            maxZoom: 19,
            minZoom: 6
        };
        // 지도 옵션
        const olMapOptions = {
            target: mapElement,
            layers: [baseLayer],
            controls: [],
            view: new View(viewOption)
        };
        return new Map(olMapOptions);
	}
	
	addVectorLayer() {
		const vectorLayer = new VectorLayer({
    		id: 'vectorLayer',
    		source: new VectorSource({wrapX: false})
    	});
    	this.map.addLayer(vectorLayer);
	}
};
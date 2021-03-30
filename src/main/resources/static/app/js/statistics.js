import $ from 'jquery';
import axios from 'axios';
import StatisticsFirstView from "./view/statistics/statisticsFirstView";
import * as echarts from "echarts";

$(document).ready(() => {
    let chartDom = document.getElementById('statisticsFirstArea');
    chartDom.style.width = '600';
    chartDom.style.height = '400';
    let myChart = echarts.init(chartDom);
    let option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
                color: 'rgba(180, 180, 180, 0.2)'
            }
        }]
    };
    option && myChart.setOption(option);
});

/* Event */
$('#statisticsArea').on('click', () => {
    /*const statisticsFirstView = new StatisticsFirstView();
    statisticsFirstView.init();*/
});
import * as echarts from 'echarts';
import $ from "jquery";

export default class statisticsFirstView {

    init() {
        const chartDom = document.getElementById('statisticsFirstArea');
        const myChart = echarts.init(chartDom);
        const option = {
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
        const container = $('#statisticsArea');
        container.append(myChart);
    }
};
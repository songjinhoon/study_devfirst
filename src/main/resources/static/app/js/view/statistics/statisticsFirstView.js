import * as echarts from 'echarts';

export default class statisticsFirstView {

    init() {
        let chartDom = document.getElementById('statisticsFirstArea');
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
    }
};
import $ from 'jquery';
import axios from 'axios';
import StatisticsFirstView from "./view/statistics/statisticsFirstView";

$(document).ready(() => {

});

/* Event */
$('#statisticsControl button').on('click', () => {
    const statisticsFirstView = new StatisticsFirstView();
    statisticsFirstView.init();
});
import $ from 'jquery';

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

$('#postMove').on('click', e => {
    window.location.href = `${contextPath}/post/list`;
});

$('#statisticsMove').on('click', e => {
    window.location.href = `${contextPath}/statistics/index`;
});

$('#dashboardMove').on('click', e => {
   window.location.href = `${contextPath}/dashboard/index`;
});

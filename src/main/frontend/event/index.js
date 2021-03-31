import $ from 'jquery';

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

$('#postMove').on('click', e => {
    window.location.href = `${contextPath}/post/list`;
});
$('#statistics').on('click', e => {
    window.location.href = `${contextPath}/statistics/index`;
});
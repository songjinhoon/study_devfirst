import $ from 'jquery';
import axios from 'axios';

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

$('#postMove').on('click', e => {
    window.location.href = `${contextPath}/post/list`;
});
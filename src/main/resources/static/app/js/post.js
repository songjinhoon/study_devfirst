import $ from 'jquery';
import axios from 'axios';
import PostList from './view/post/PostList'

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

$(document).ready(function (){
    axios.post(`${contextPath}/post/list`, { page: 0 }).then(response => {
        console.log('아진심이건아니잖아333');
        $('#postListTable').html(response.data);
    });
});



$('#postListTable').on('click', e => {
   console.log('ggyo');
});

// 페이징처리
$('.pageDiv a').on('click', e => {
    console.log('222222');
    const { currentTarget } = e;
    const pageNo = $(currentTarget).data('page');

    axios.post(`${contextPath}/post/list`, { page: pageNo}).then(response => {
        $('#postListTable').html(response.data);
    });
});
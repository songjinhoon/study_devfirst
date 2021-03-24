import $ from 'jquery';
import axios from 'axios';

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

$(document).ready(function (){
/*    axios.post(`${contextPath}/post/list`, { page: 0 }).then(response => {
        $('#postListTable').html(response.data);
    });

    $('#testThead').on('click', e => {
       console.log('testThead Click..');
    });*/

    // 페이징처리 -> 이렇게 안됨.
    /*$('#postListTable .pageDiv a').on('click', e => {
        console.log('222222');
        const { currentTarget } = e;
        const pageNo = $(currentTarget).data('page');

        axios.post(`${contextPath}/post/list`, { page: pageNo}).then(response => {
            $('#postListTable').html(response.data);
        });
    });*/
});

// 페이징
$('.pageDiv a').on('click', e => {
    const { currentTarget } = e;
    const pageNo = $(currentTarget).data('page');
    $('.searchForm').find('input[name="page"]').val(pageNo);
    $('.searchForm').submit();
});
// 조회
$('.searchForm button.search').on('click', () => {
   alert('조회버튼 클릭!') ;
});
// 게시글 작성
$('.searchForm button.write').on('click', () => {
   alert('작성버튼 클릭!');
});
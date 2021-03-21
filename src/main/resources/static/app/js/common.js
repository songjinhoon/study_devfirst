import $ from 'jquery';
import axios from 'axios';

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

// 페이징처리
$('.pageDiv a').on('click', e => {
    const { currentTarget } = e;
    const pageNo = $(currentTarget).data('page');

    axios.post(`${contextPath}/post/list`, { page: pageNo }).then(response => {
        $('.container').html(response.data);
    });
});
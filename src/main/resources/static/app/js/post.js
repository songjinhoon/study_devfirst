import $ from 'jquery';
import axios from 'axios';

const contextPath = document.getElementById('contextPathHolder').dataset.contextPath;

$(document).ready(function (){
});

// 페이징
$('.pageDiv a').on('click', e => {
    const { currentTarget } = e;
    const pageNo = $(currentTarget).data('page');
    //$('.searchForm').find('input[name="page"]').val(pageNo);
    $('.searchForm').find('input').each((index, item) => {
        if($(item).attr('name') === 'page') {
            $(item).val(pageNo);
            return true; // continue
        }
        $(item).removeAttr('value');
    });
    $('.searchForm').submit();
});
// 작성
$('.searchForm button.write').on('click', e => {
    e.preventDefault(); // or type="button"
    window.location.href = `${location.protocol}//${location.hostname}:${location.port}${contextPath}/post/write`;

});
// 조회
$('.searchForm button.search').on('click', () => {
    $('.searchForm').find('input[name="page"]').val(1);
    $('.searchForm').submit();
});
// 저장
$('.writeForm button.write').on('click', () => {
    // 파일 처리는 추후에
    const formData = new FormData();
    formData.append('title', $('#writeFormTitle').val());
    formData.append('content', $('#writeFormContent').val());
    axios({
        method: 'post',
        url: `${contextPath}/post/api/save`,
        data: formData,
        headers: {'Content-Type': 'multipart/form-data'},
    }).then(response => {
        console.log(response.data);
    }).catch(response => {
        console.log(response);
    });
});
// 이미지 첨부
$('#writeFormImage').on('change', e => {
    alert('뿅');
    const { currentTarget } = e;
    const result = $(currentTarget).result;
    const dataUrl = $(currentTarget).files[0];
    const imageFile = new FileReader();
    imageFile.onload = () => {
        const imageElement = document.createElement('image');
        imageElement.setAttribute('src', result);
        $('#writeFormContent').appendChild(imageElement);
    };
    imageFile.readAsDataURL(dataUrl);
});
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
// 저장 -> 사실 비동기로 처리 안해도되지만, 비동기로 처리해보겠음. -> 비동기로 처리를 하더라도, form.submit을 쓴다면 name이 필요하지만 지금같은 경우는 name이 노필요, 그렇지만 추후를 위해 써놈
$('.writeForm button.write').on('click', () => {
    const formData = new FormData();
    formData.append('userId', '1');
    formData.append('title', $('#writeFormTitle').val());
    formData.append('content', $('#writeFormContent').val());
    if($('#writeFormFile')[0].files[0] == 'undefined') {
        formData.append('file', $('#writeFormFile')[0].files[0]);
    }
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
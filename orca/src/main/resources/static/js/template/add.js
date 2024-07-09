//textarea 라이브러리
$(document).ready(function() {
  $('#summernote').summernote({
      placeholder: '결재 양식 내용을 입력해주세요.',
      height: 500,
      minHeight: null, // 최소
      maxHeight: null, // 최대
      focus: true
  });
});
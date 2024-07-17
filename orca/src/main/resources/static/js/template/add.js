$(document).ready(function() {
    // 카테고리 가져오기 함수 호출
    fetchCategories();

    // textarea 라이브러리

    // textarea 라이브러리 초기화
    $('#summernote').summernote({
        placeholder: '결재 양식 내용을 입력해주세요.',
        minHeight: 300, // 최소
           minWidth: 200, // 최소
           maxWidth: 1000, // 최대
           focus: true
       });
});

// 카테고리 가져오기
function fetchCategories() {
    $.ajax({
        url: '/orca/document/categorie/list',
        method: 'GET',
        success: function(categories) {
            const categorySelect = document.querySelector('#category');
            categorySelect.innerHTML = '<option value="">--선택--</option>';

            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.categoryNo;
                option.text = category.categoryName;
                categorySelect.appendChild(option);
            });
        },
        error: function(error) {
            console.error('error:', error);
            console.log(error);
        }
    });
}
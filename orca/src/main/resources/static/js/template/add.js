//textarea 라이브러리
$(document).ready(function() {
   fetchCategories();

  $('#summernote').summernote({
      placeholder: '결재 양식 내용을 입력해주세요.',
      height: 500,
      minHeight: null, // 최소
      maxHeight: null, // 최대
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
                if (category.categoryNo == ${template.categoryNo}) {
                    option.selected = true;
                }
                categorySelect.appendChild(option);
            });
        },
        error: function(error) {
            console.error('error:', error);
            console.log(error);
        }
    });
}
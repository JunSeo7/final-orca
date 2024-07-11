$(document).ready(function() {
    // URL에서 templateNo 파라미터 추출
    const urlParams = new URLSearchParams(window.location.search);
    const templateNo = urlParams.get('templateNo');
    console.log("수정 들어옴: " + templateNo);

    fetchCategories(templateNo);

    // Summernote 초기화
    $('#summernote').summernote({
        placeholder: '결재 양식 내용을 입력해주세요.',
        height: 500,
        minHeight: null, // 최소 높이
        maxHeight: null, // 최대 높이
        focus: true
    });
    // 폼 제출 이벤트 처리
        $('#editForm').on('submit', function(event) {
            event.preventDefault(); // 폼의 기본 제출 동작을 막음
            updateTemplate();
        });
    });


// 카테고리 가져오기
function fetchCategories(templateNo) {
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

            // 카테고리를 가져온 후 템플릿 데이터를 가져와서 선택된 카테고리를 설정
            getTemplateData(templateNo);
        },
        error: function(error) {
            console.error('Error:', error);
            console.log(error);
        }
    });
}

// 양식 데이터 가져오기
function getTemplateData(templateNo) {
    if (templateNo) {
        $.ajax({
            url: '/orca/template/getTemplateData',
            type: 'GET',
            data: { templateNo: templateNo },
            success: function(data) {
                $('#category').val(data.categoryNo);
                $('#name').val(data.title);
                $('#summernote').summernote('code', data.content);
            },
            error: function(e) {
                alert('Failed to load template data');
            }
        });
    }
}

//양식 업데이트
function updateTemplate() {
    const templateData = {
        templateNo: $('#templateNo').val(),
        categoryNo: $('#category').val(),
        title: $('#name').val(),
        content: $('#summernote').summernote('code')
    };

    $.ajax({
        url: '/orca/template/edit',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(templateData),
        success: function(response) {
            alert('양식이 수정되었습니다');
            window.location.href = '/orca/template/list';
        },
        error: function(error) {
            alert('양식 수정에 실패하셨습니다.');
            console.error('Error:', error);
        }
    });
}
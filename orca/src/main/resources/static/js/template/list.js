// 검색
function searchTemplate() {

    let searchType = $('#searchType').val();
    let searchText = $('#searchText').val();

    let searchResults = $('.template-box');
    let noTemplate = $('.no-template');

    $.ajax({
        url: '/orca/template/search',
        method: 'GET',
        dataType:'html',
        success: function(response) {
        console.log('response:', response);
            searchResults.empty(); // 기존 결과 초기화
            noTemplate.empty();

            searchResults.html(response);
            searchTemplateData(searchType, searchText);

            // 검색 텍스트 초기화
            $('#searchText').val('');
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}


function searchTemplateData(searchType, searchText) {

    console.log(searchType);
    console.log(searchText);

    $.ajax({
        url: '/orca/template/search/data',
        method: 'GET',
        data: {
            searchType: searchType,
            searchText: searchText
        },
        success: function(data) {
        console.log('data:', data);
            displayResults(data);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}


function displayResults(data) {

    let searchResults = $('.template-box');
    let noTemplate = $('.no-template');
    let searchText = $('#searchText');

    searchText.innerText ="";

    searchResults.empty(); // 기존 결과 초기화
    noTemplate.empty();

    if (data && data.length > 0) {
        data.forEach(function(template) {
            // 기존 템플릿 구조를 그대로 사용하면서 title만 업데이트
            let resultItem = `
                <div class="template" data-template-no="${template.templateNo}">
                    <span class="template-category">카테고리 : ${template.categoryName}</span>
                    <br>
                    <span class="template-title">양식명 : ${template.title} [${template.templateNo}]</span>
                    <br>
                    <span class="template-enroll">생성날짜 : ${template.enrollDate}</span>
                    <hr>
                    <a class="template-btn" onclick="openModal()">
                        <img class="edit_img" src="/img/document/edit.png" alt="수정 아이콘">
                    </a>
                    <a class="template-btn delete-btn" data-template-no="${template.templateNo}">
                        <img class="delete_img" src="/img/document/delete.png" alt="삭제 아이콘">
                    </a>
                </div>`;
            searchResults.append(resultItem);
        });
    } else {
        let templateDiv =`
                 <div class="no-template">
                    키워드에 일치하는 양식이 없습니다.
                 <div>`;
                searchResults.append(templateDiv);
    }
}


//상세보기

 const detailDivs = document.querySelectorAll(".template");

 detailDivs.forEach(function(detailDiv) {
     detailDiv.addEventListener("click", function(event) {

         const templateNo = detailDiv.getAttribute('data-template-no');
         console.log('Template No:', templateNo);
         location.href = '/orca/template/detail?templateNo=' + templateNo;
     });
 });

//삭제
 const deleteBtns = document.querySelectorAll(".delete-btn");

  deleteBtns.forEach(function(deleteBtn) {
         deleteBtn.addEventListener("click", function(event) {
             event.stopPropagation();  // 부모 요소 이벤트 전파막기!
             const templateNo = deleteBtn.getAttribute('data-template-no');
             console.log('Template No:', templateNo);

             $.ajax({
                 url: '/orca/template/delete?templateNo=' + templateNo,
                 method: 'POST',
                 success: function(data) {
                     console.log('Template delete:', data);
                     location.reload();
                 },
                 error: function(e) {
                     console.error('Error:', e);
             }
         });
     });
 });
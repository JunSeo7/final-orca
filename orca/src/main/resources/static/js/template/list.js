// 검색
function searchTemplate() {
    let searchType = $('#searchType').val();
    let searchText = $('#searchText').val();

    console.log(searchType);
    console.log(searchText);

    $.ajax({
        url: '/orca/template/search',
        method: 'GET',
        data: {
            searchType: searchType,
            searchText: searchText
        },
        success: function(data) {
        console.error('data:', data);
            displayResults(data);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}

function displayResults(data) {
    let searchResults = $('#searchResults');
    searchResults.empty(); // 기존 결과 초기화

    if (Array.isArray(data) && data.length > 0) {
        data.forEach(function(template) {
            let templateDiv = `
                <div class="template-lines-box template-div" data-template-no="${template.templateNo}">
                    <div class="template-lines">
                        <span class="template-title">카테고리 : ${template.categoryName}</span><br>
                        <span class="template-title">양식명 : ${template.title}</span><br>
                        <span class="template-enroll">생성날짜 : ${template.enrollDate}</span>
                        <hr>
                        <a class="template-lines-btn" onclick="openModal()">
                            <img class="edit_img" src="/img/document/edit.png" alt="수정 아이콘">
                        </a>
                        <a class="template-lines-btn delete-btn" data-template-no="${template.templateNo}">
                            <img class="delete_img" src="/img/document/delete.png" alt="삭제 아이콘">
                        </a>
                    </div>
                </div>`;
            searchResults.append(templateDiv);
        });
    } else {
        let templateDiv =`
                 <div class="template-lines-box template-div">
                    <p>키워드에 일치하는 양식이 없습니다.</p>
                 <div>`;
                searchResults.append(templateDiv);
    }
}


//상세보기

 const detailDivs = document.querySelectorAll(".template-div");

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
                     console.log('Template deleted:', data);
                     location.reload();
                 },
                 error: function(e) {
                     console.error('Error:', e);
             }
         });
     });
 });
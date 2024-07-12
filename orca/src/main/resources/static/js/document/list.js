document.addEventListener("DOMContentLoaded", function() {

    // 검색 버튼 클릭 시 searchDocuments 함수를 호출합니다.
    const searchBtn = document.querySelector("#searchButton");

    searchBtn.addEventListener("click", function() {
        searchDocuments();
    });

    // 초기 페이지 로드 시 .document 요소들에 대해 이벤트 리스너 설정
    detailDocument();
});

// 검색 버튼 클릭 시 호출되는 함수
function searchDocuments() {
    const searchType = document.querySelector("#searchType").value;
    const searchText = document.querySelector("#searchText").value;
    let status = getParameterByName('status');


    if (status === null || status === '') {
        status = ''; // 빈 문자열을 설정하여 null을 방지
    }

    console.log("Search Type:", searchType);
    console.log("Search Text:", searchText);
    console.log("Status:", status);

    $.ajax({
        url: '/orca/document/search',
        type: 'GET',
        data: {
            searchType: searchType,
            searchText: searchText,
        },
        success: function(data) {
            displayDocuments(data);
            // AJAX 호출 후 .document 요소들에 대해 이벤트 리스너 재설정
            setDocumentClickListeners();
        },
        error: function() {
            alert('검색 중 오류가 발생했습니다.');
        }
    });
}



// URL의 쿼리 스트링에서 상태 값을 추출하는 함수
function getParameterByName(name) {
    const url = window.location.href;
    const nameRegex = name.replace(/[\[\]]/g, '\\$&');
    const regex = new RegExp('[?&]' + nameRegex + '(=([^&#]*)|&|#|$)');
    const results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}


function displayDocuments(data) {
    const documentListDiv = document.querySelector("#documentList");
    documentListDiv.innerHTML = ''; // 내용을 비웁니다.

    data.forEach(function(doc) {
        const docDiv = document.createElement("div");
        docDiv.className = "document";
        docDiv.dataset.docNo = doc.docNo;

        const creditDateP = document.createElement("p");
        creditDateP.textContent = doc.creditDate;
        docDiv.appendChild(creditDateP);

        const statusBoxDiv = document.createElement("div");
        statusBoxDiv.className = "status_box";

        const statusDetailsDiv = document.createElement("div");
        statusDetailsDiv.className = "status_details";

        const profileImg = document.createElement("img");
        profileImg.src = "/img/header/profile.png";
        profileImg.alt = "Profile Picture";
        profileImg.className = "profile-pic-small";
        statusDetailsDiv.appendChild(profileImg);

        const approvalTitleSpan = document.createElement("span");
        approvalTitleSpan.className = "approval_title";
        approvalTitleSpan.textContent = doc.docNo;
        statusDetailsDiv.appendChild(approvalTitleSpan);

        const approvalTitleSpan2 = document.createElement("span");
        approvalTitleSpan2.className = "approval_title";
        approvalTitleSpan2.textContent = doc.title;
        statusDetailsDiv.appendChild(approvalTitleSpan2);

        const approvalTitleSpan3 = document.createElement("span");
        approvalTitleSpan3.className = "approval_title";
        approvalTitleSpan3.textContent = `[${doc.categoryName}] ${doc.templateTitle}`;
        statusDetailsDiv.appendChild(approvalTitleSpan3);

        const approvalTitleSpan4 = document.createElement("span");
        approvalTitleSpan4.className = "approval_title";
        approvalTitleSpan4.textContent = `긴급여부: ${doc.urgent}`;
        statusDetailsDiv.appendChild(approvalTitleSpan4);

        const statusStepsDiv = document.createElement("div");
        statusStepsDiv.className = "status_steps";

        const statusStepDiv = document.createElement("div");
        statusStepDiv.className = "status_step";

        const statusNameP = document.createElement("p");
        statusNameP.textContent = doc.statusName;
        statusStepDiv.appendChild(statusNameP);

        const deptNameP = document.createElement("p");
        deptNameP.textContent = doc.deptName;
        statusStepDiv.appendChild(deptNameP);

        const writerNameP = document.createElement("p");
        writerNameP.textContent = `${doc.writerName}[${doc.positionName}]`;
        statusStepDiv.appendChild(writerNameP);

        const creditDateP2 = document.createElement("p");
        creditDateP2.textContent = doc.creditDate;
        statusStepDiv.appendChild(creditDateP2);

        statusStepsDiv.appendChild(statusStepDiv);

        doc.approverVoList.forEach(function(approver) {
            const approverStepDiv = document.createElement("div");
            approverStepDiv.className = "status_step";

            const seqP = document.createElement("p");
            seqP.textContent = approver.seq;
            approverStepDiv.appendChild(seqP);

            const apprStageNameP = document.createElement("p");
            apprStageNameP.textContent = approver.apprStageName;
            approverStepDiv.appendChild(apprStageNameP);

            const approverDeptNameP = document.createElement("p");
            approverDeptNameP.textContent = approver.deptName;
            approverStepDiv.appendChild(approverDeptNameP);

            const approverNameP = document.createElement("p");
            approverNameP.textContent = `${approver.approverName}[${approver.positionName}]`;
            approverStepDiv.appendChild(approverNameP);

            const approvalDateP = document.createElement("p");
            approvalDateP.textContent = approver.approvalDate;
            approverStepDiv.appendChild(approvalDateP);

            statusStepsDiv.appendChild(approverStepDiv);
        });

        statusDetailsDiv.appendChild(statusStepsDiv);
        statusBoxDiv.appendChild(statusDetailsDiv);
        docDiv.appendChild(statusBoxDiv);
        documentListDiv.appendChild(docDiv);
    });

    // 새로 생성된 문서들에 대한 클릭 이벤트 리스너 설정
    detailDocument();
}


function setDocumentClickListeners(){
console.log("t성공ㅎㅎ")
};


// 상세보기
function detailDocument() {
    const docElements = document.querySelectorAll(".document");

    docElements.forEach(element => {
        element.addEventListener("click", function() {
            const docNo = this.getAttribute("data-doc-no");
            window.location.href = `/orca/document/detail?docNo=${docNo}`;
        });
    });
}
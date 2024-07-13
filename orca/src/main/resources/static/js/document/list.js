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
            status: status // 추가된 부분
        },
        success: function(data) {
            displayDocuments(data);
            // AJAX 호출 후 .document 요소들에 대해 이벤트 리스너 재설정
            detailDocument();
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

    if (data && data.length > 0) {
    data.forEach(function(doc) {
        const docDiv = document.createElement("div");
        docDiv.className = "document";
        docDiv.dataset.docNo = doc.docNo;

        const creditDateP = document.createElement("p");
        creditDateP.textContent = doc.creditDate;
        docDiv.appendChild(creditDateP);

        const statusBoxDiv = document.createElement("div");
        statusBoxDiv.className = `status_box status_box_${doc.status}`;
        statusBoxDiv.dataset.docNo = doc.docNo;

        const statusDetailsDiv = document.createElement("div");
        statusDetailsDiv.className = "status_details";

        const documentInfoDiv = document.createElement("div");
        documentInfoDiv.className = "document_info";

        const documentInfoInnerDiv = document.createElement("div");
        documentInfoInnerDiv.className = "document_info_inner";

        const profileImg = document.createElement("img");
        profileImg.src = "/img/header/profile.png";
        profileImg.alt = "Profile Picture";
        profileImg.className = "profile-pic-small";
        documentInfoInnerDiv.appendChild(profileImg);

        const docInfoTextDiv = document.createElement("div");

        const docTitleDiv = document.createElement("div");
        docTitleDiv.className = "docTitle";
        docTitleDiv.textContent = `${doc.title} [${doc.docNo}]`;
        docInfoTextDiv.appendChild(docTitleDiv);

        const docTemplateDiv = document.createElement("div");
        docTemplateDiv.className = "docTemplate";
        docTemplateDiv.textContent = `[${doc.categoryName}] ${doc.templateTitle}`;
        docInfoTextDiv.appendChild(docTemplateDiv);

        documentInfoInnerDiv.appendChild(docInfoTextDiv);
        documentInfoDiv.appendChild(documentInfoInnerDiv);

        if (doc.urgent === 'Y') {
            const urgentDiv = document.createElement("div");
            urgentDiv.className = "urgent_yn";
            urgentDiv.textContent = '🔴';
            documentInfoDiv.appendChild(urgentDiv);
        }

        statusDetailsDiv.appendChild(documentInfoDiv);

        const statusStepsDiv = document.createElement("div");
        statusStepsDiv.className = "status_steps";

        const writerStepDiv = document.createElement("div");
        writerStepDiv.className = `status_step writer_${doc.status}`;

        const statusNameDiv = document.createElement("div");
        statusNameDiv.className = "statusName";
        statusNameDiv.textContent = `${doc.statusName}[${doc.status}]`;
        writerStepDiv.appendChild(statusNameDiv);

        const writerNameDiv = document.createElement("div");
        writerNameDiv.className = "writerName";
        writerNameDiv.textContent = `${doc.writerName}[${doc.positionName}]`;
        writerStepDiv.appendChild(writerNameDiv);

        const deptNameDiv = document.createElement("div");
        deptNameDiv.textContent = doc.deptName;
        writerStepDiv.appendChild(deptNameDiv);

        const creditDateDiv = document.createElement("div");
        creditDateDiv.textContent = doc.creditDate;
        writerStepDiv.appendChild(creditDateDiv);

        statusStepsDiv.appendChild(writerStepDiv);

        doc.approverVoList.forEach(function(approver) {
            const approverStepDiv = document.createElement("div");
            approverStepDiv.className = `status_step appr_${approver.approvalStage}`;

            const stageNameDiv = document.createElement("div");
            stageNameDiv.className = "stageName";
            stageNameDiv.textContent = `[${approver.seq}][${approver.apprStageName}]${approver.approvalStage}`;
            approverStepDiv.appendChild(stageNameDiv);

            const approverNameDiv = document.createElement("div");
            approverNameDiv.className = "approverName";
            approverNameDiv.textContent = `${approver.approverName}[${approver.positionName}]`;
            approverStepDiv.appendChild(approverNameDiv);

            const approverDeptNameDiv = document.createElement("div");
            approverDeptNameDiv.textContent = `[${approver.approverClassificationNo}]${approver.deptName}`;
            approverStepDiv.appendChild(approverDeptNameDiv);

            const approverDateDiv = document.createElement("div");
            approverDateDiv.textContent = approver.approvalDate;
            approverStepDiv.appendChild(approverDateDiv);

            statusStepsDiv.appendChild(approverStepDiv);
        });

        statusDetailsDiv.appendChild(statusStepsDiv);
        statusBoxDiv.appendChild(statusDetailsDiv);
        docDiv.appendChild(statusBoxDiv);
        documentListDiv.appendChild(docDiv);
    });

    } else {
        const noDocumentDiv = document.createElement("div");
        noDocumentDiv.className = "no-document";
        noDocumentDiv.textContent = "키워드에 일치하는 결재문서가 없습니다.";
        documentListDiv.appendChild(noDocumentDiv);
    }

    // 새로 생성된 문서들에 대한 클릭 이벤트 리스너 설정
    detailDocument();
}

// 상세보기
function detailDocument() {
    const docElements = document.querySelectorAll(".status_box");

    docElements.forEach(element => {
        element.addEventListener("click", function() {
            const docNo = this.getAttribute("data-doc-no");
            window.location.href = `/orca/document/detail?docNo=${docNo}`;
        });
    });
}

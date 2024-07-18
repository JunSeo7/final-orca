document.addEventListener("DOMContentLoaded", function() {
    // 결재선 표시 함수
    function displayApprovalProcess(approverVoList) {
        const approvalProcessContainer = document.querySelector('.approval-process');
        approvalProcessContainer.innerHTML = '';

        approverVoList.forEach(approver => {
            const approverDiv = document.createElement('div');
            approverDiv.className = 'approver';
            approverDiv.innerHTML = `
                <div class="approver-info">
                    <span class="approver-name">${approver.approverName}</span>
                    <span class="approver-position">${approver.positionName}</span>
                    <span class="approver-dept">${approver.deptName}</span>
                </div>
                <div class="approver-stage">
                    <span class="stage-name">${approver.apprStageName}</span>
                </div>
            `;
            approvalProcessContainer.appendChild(approverDiv);
        });
    }

    // 결재 수정 - 문서 데이터 가져오기
    function editDocument(docNo) {
        $.ajax({
            url: `/orca/document/getDocumentData`,
            type: 'GET',
            data: { docNo: docNo },
            success: function(data) {
                console.log("가져옴");

                // 선택된 카테고리와 템플릿 이름을 직접 셀렉트 박스에 추가
                const categorySelect = document.querySelector('#categoryNo');
                categorySelect.innerHTML = '';
                const categoryOption = document.createElement('option');
                categoryOption.value = data.categoryNo;
                categoryOption.text = data.categoryName;
                categorySelect.appendChild(categoryOption);
                categorySelect.value = data.categoryNo;

                const templateSelect = document.querySelector('#templateNo');
                templateSelect.innerHTML = '';
                const templateOption = document.createElement('option');
                templateOption.value = data.templateNo;
                templateOption.text = data.templateTitle;
                templateSelect.appendChild(templateOption);
                templateSelect.value = data.templateNo;

                // 폼 필드에 데이터 채우기
                $('input[name="urgent"][value="' + data.urgent + '"]').prop('checked', true);
                $('#title').val(data.title);
                $('#summernote').summernote('code', data.content);

                // 참조인 목록 업데이트
                let referrerListHtml = '';
                data.referencerVoList.forEach(function(referrer) {
                    referrerListHtml += '[' + referrer.deptName + '] ' + referrer.referrerName + ' ' + referrer.positionName + ', ';
                });
                $('#referrerList').html(referrerListHtml);

                // 결재선 업데이트
                displayApprovalProcess(data.approvalLineVoList);
            },
            error: function(e) {
                alert('문서 데이터 로드 중 오류가 발생했습니다.');
            }
        });
    }

    // 문서 번호를 가져와서 수정 데이터 로드
    const docNo = new URLSearchParams(window.location.search).get('docNo');
    if (docNo) {
        editDocument(docNo);
    }
});

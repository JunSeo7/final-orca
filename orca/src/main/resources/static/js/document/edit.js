document.addEventListener("DOMContentLoaded", function() {
    // 결재선 프로세스 업데이트
    function updateApprovalProcess(approvers) {
        const processContainer = document.querySelector('.approval-process');
        processContainer.innerHTML = '';

        approvers.forEach((approver, index) => {
            const approverDiv = document.createElement('span');
            approverDiv.textContent = `${approver.seq} ${approver.deptName} ${approver.approverName} ${approver.positionName}`;
            processContainer.appendChild(approverDiv);

            // 인풋 태그 - 결재선 정보 숨기기
            const hiddenSeqInput = document.createElement('input');
            hiddenSeqInput.type = 'hidden';
            hiddenSeqInput.name = 'seq';
            hiddenSeqInput.value = approver.seq;
            processContainer.appendChild(hiddenSeqInput);

            const hiddenApproverNoInput = document.createElement('input');
            hiddenApproverNoInput.type = 'hidden';
            hiddenApproverNoInput.name = 'approverNo';
            hiddenApproverNoInput.value = approver.approverNo;
            processContainer.appendChild(hiddenApproverNoInput);

            const hiddenDeptCodeInput = document.createElement('input');
            hiddenDeptCodeInput.type = 'hidden';
            hiddenDeptCodeInput.name = 'deptCode';
            hiddenDeptCodeInput.value = approver.deptCode;
            processContainer.appendChild(hiddenDeptCodeInput);

            const hiddenPositionCodeInput = document.createElement('input');
            hiddenPositionCodeInput.type = 'hidden';
            hiddenPositionCodeInput.name = 'positionCode';
            hiddenPositionCodeInput.value = approver.positionCode;
            processContainer.appendChild(hiddenPositionCodeInput);

            const hiddenApproverClassificationNoInput = document.createElement('input');
            hiddenApproverClassificationNoInput.type = 'hidden';
            hiddenApproverClassificationNoInput.name = 'approverClassificationNo';
            hiddenApproverClassificationNoInput.value = approver.approverClassificationNo;
            processContainer.appendChild(hiddenApproverClassificationNoInput);

            if (index < approvers.length - 1) {
                const arrowDiv = document.createElement('span');
                arrowDiv.classList.add('arrow');
                arrowDiv.textContent = ' ⇨ '; // 화살표 추가
                processContainer.appendChild(arrowDiv);
            }
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
                updateApprovalProcess(data.approverVoList);
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

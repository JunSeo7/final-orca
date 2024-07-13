document.addEventListener("DOMContentLoaded", function() {
    // 결재 등록 결재양식 제목 가져오기
    function fetchTemplatesByCategory(categoryNo) {
        $.ajax({
            url: '/orca/document/template/list',
            method: 'GET',
            data: { categoryNo: categoryNo },
            success: function(templates) {
                const templateSelect = document.querySelector('#templateNo');
                templateSelect.innerHTML = '';
                templates.forEach(template => {
                    const option = document.createElement('option');
                    option.value = template.templateNo;
                    option.text = template.title;
                    templateSelect.appendChild(option);
                });

                // 기본 - 첫 번째 템플릿의 내용으로 로드
                if (templates.length > 0) {
                    fetchTemplateContent(templates[0].templateNo);
                }
            },
            error: function(error) {
                console.error('Error fetching templates:', error);
            }
        });
    }

    // 결재 등록 카테고리 가져오기
    function fetchCategories() {
        $.ajax({
            url: '/orca/document/categorie/list',
            method: 'GET',
            success: function(categories) {
                const categorySelect = document.querySelector('#categoryNo');
                categorySelect.innerHTML = '';

                categories.forEach(category => {
                    const option = document.createElement('option');
                    option.value = category.categoryNo;
                    option.text = category.categoryName;
                    categorySelect.appendChild(option);
                });

                if (categories.length > 0) {
                    fetchTemplatesByCategory(categories[0].categoryNo);
                }
            },
            error: function(error) {
                console.error('error:', error);
                console.log(error);
            }
        });
    }

    // 결재 양식 내용, 결재선 불러오기
    function fetchTemplateContent(templateNo) {
        // 결재 양식 내용 불러오기
        $.ajax({
            url: '/orca/document/template/content',
            method: 'GET',
            data: { templateNo: templateNo },
            success: function(data) {
                $('#title').val(data.title); // 템플릿의 제목
                $('#summernote').summernote('code', ''); // 내용 초기화
                $('#summernote').summernote("pasteHTML",data.content);// 템플릿의 내용
                $('#templateNo').val(data.templateNo);

                // 결재선 불러오기
                fetchApprovalLine(templateNo);
            },
            error: function() {
                alert('결재 양식 내용 불러오기 오류가 발생했습니다.');
            }
        });
    }

    // 결재선 불러오기
    function fetchApprovalLine(templateNo) {
        $.ajax({
            url: '/orca/document/template/apprline',
            method: 'GET',
            data: { templateNo: templateNo },
            success: function(data) {
                updateApprovalProcess(data.approverVoList);
                console.log(data);
                console.log(data.approverVoList);
            },
            error: function() {
                alert('결재선 불러오기 오류가 발생했습니다.');
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
                // 폼 필드에 데이터 채우기
                $('#categoryNo').val(data.categoryNo);
                $('#templateNo').val(data.templateNo);
                $('input[name="urgent"][value="' + data.urgent + '"]').prop('checked', true);
                $('#title').val(data.title);
                $('#summernote').val(data.content);
                // 참조인 목록 업데이트
                let referrerListHtml = '';
                data.referencerVoList.forEach(function(referrer) {
                    referrerListHtml += '[' + referrer.deptName + '] ' + referrer.referrerName + ' ' + referrer.positionName + ', ';
                });
                $('#referrerList').html(referrerListHtml);
                // 결재선 업데이트 (필요 시 추가)
                updateApprovalProcess(data.approvalLineVoList);
            },
            error: function(e) {
                console.log("실패");
                alert('문서 데이터 로드 중 오류가 발생했습니다.');
            }
        });
    }

    // 결재선 업데이트
    function updateApprovalProcess(approverVoList) {
        const approvalProcess = document.querySelector('.approval-process');
        approvalProcess.innerHTML = '';
        approverVoList.forEach(approver => {
            const approverDiv = document.createElement('div');
            approverDiv.className = 'approver';
            approverDiv.textContent = `${approver.positionName} ${approver.approverName}`;
            approvalProcess.appendChild(approverDiv);
        });
    }

    // 초기 데이터 로드
    fetchCategories();

    // 문서 번호를 가져와서 수정 데이터 로드
    const docNo = new URLSearchParams(window.location.search).get('docNo');
    if (docNo) {
        editDocument(docNo);
    }
});

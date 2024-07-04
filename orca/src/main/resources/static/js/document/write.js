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
                $('#content').val(data.content); // 템플릿의 내용
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
                },
                error: function() {
                    alert('결재선 불러오기 오류가 발생했습니다.');
                }
            });
        }

        // 결재선 프로세스 업데이트
        function updateApprovalProcess(approvers) {
            const processContainer = document.querySelector('.approval-process');
            processContainer.innerHTML = '';

                    approvers.forEach((approver, index) => {
                        const approverDiv = document.createElement('div');
                        approverDiv.textContent = `${approver.seq} ${approver.deptName} ${approver.approverName} ${approver.positionName}`;
                        processContainer.appendChild(approverDiv);

                    // 인풋태그 - 숨길 정보 만들기
                    const hiddenInputs = `
                        <input type="hidden" name="approvalLineVoList[${index}].seq" value="${approver.seq}">
                        <input type="hidden" name="approvalLineVoList[${index}].approverNo" value="${approver.approverNo}">
                        <input type="hidden" name="approvalLineVoList[${index}].deptCode" value="${approver.deptCode}">
                        <input type="hidden" name="approvalLineVoList[${index}].positionCode" value="${approver.positionCode}">
                        <input type="hidden" name="approvalLineVoList[${index}].approverClassificationNo" value="${approver.approverClassificationNo}">
                        <input type="hidden" name="approvalLineVoList[${index}].comment" value="${approver.comment}">
                    `;

                    processContainer.innerHTML += hiddenInputs;

                    if (index < approvers.length - 1) {
                        const arrowDiv = document.createElement('span');
                        arrowDiv.classList.add('arrow');
                        arrowDiv.textContent = '⇨'; // 화살표 추가
                        processContainer.appendChild(arrowDiv);
                    }
                });
            }


    // 카테고리 변경 - 템플릿 목록 업데이트
    $('#categoryNo').change(function() {
        let categoryNo = $(this).val();
        fetchTemplatesByCategory(categoryNo);
    });

    // 결재 양식 선택 - 내용 업데이트
    $('#templateNo').change(function() {
        let templateNo = $(this).val();
        fetchTemplateContent(templateNo);
    });

    fetchCategories(); // 페이지 로드 - 카테고리 가져오기
});


// 참조인(조직도 가져오기)
// 모달 열기
    function openOrganizationModal() {
        document.getElementById('organizationModal').style.display = 'block';
        fetchOrganization();
    }

    // 모달 닫기
    function closeOrganizationModal() {
        document.getElementById('organizationModal').style.display = 'none';
    }

    // 조직도 가져오기
    function fetchOrganization() {
        $.ajax({
            url: '/orca/apprline/organization/list',
            method: 'GET',
            success: function(data) {
                const treeData = buildTreeData(data);
                $('#jstree').jstree({
                    'core' : {
                        'data' : treeData
                    },
                    "checkbox" : {
                        "keep_selected_style" : false
                    },
                    "plugins" : [ "wholerow", "checkbox" ]
                });
            },
            error: function(error) {
                console.error('error:', error);
            }
        });
    }

    // 트리 데이터 빌드
    function buildTreeData(data) {
        const tree = [];
        const departments = {};

        data.forEach(user => {
            if (!departments[user.partName]) {
                departments[user.partName] = {
                    text: `${user.partName}`,
                    icon: "fas fa-folder",
                    children: []
                };
                tree.push(departments[user.partName]);
            }

            const department = departments[user.partName];

            let team = department.children.find(team => team.text.startsWith(user.teamName));
            if (!team) {
                team = {
                    text: `${user.teamName}`,
                    icon: "fas fa-folder",
                    children: []
                };
                department.children.push(team);
            }

            const userNode = {
                id: `user_${user.empNo}`,
                text: `${user.name} (${user.nameOfPosition})`,
                icon: "fas fa-user"
            };
            team.children.push(userNode);
        });

        return tree;
    }

    // 선택한 참조인 추가
    function confirmSelection() {
        const selectedNodes = $('#jstree').jstree("get_checked", true);
        const selectedReferrers = selectedNodes.map(node => ({
            id: node.id,
            text: node.text
        }));

        addReferrers(selectedReferrers);
        closeOrganizationModal();
    }

    function addReferrers(referrers) {
        const referrerList = document.getElementById('referrerList');
        referrerList.innerHTML = '';  // 초기화
        referrers.forEach(referrer => {
            const referrerDiv = document.createElement('div');
            referrerDiv.innerText = referrer.text;
            referrerList.appendChild(referrerDiv);

            // 추가된 참조인을 숨겨진 input에 저장
            const hiddenInput = document.createElement('input');
            hiddenInput.type = 'hidden';
            hiddenInput.name = 'referrers';
            hiddenInput.value = referrer.id.replace('user_', '');  // id에서 'user_' 제거
            document.getElementById('documentForm').appendChild(hiddenInput);
        });

        // 추가 버튼을 수정 버튼으로 변경
        document.querySelector('button[onclick="openOrganizationModal()"]').innerText = '수정';
    }


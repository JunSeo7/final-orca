let approvalLines = []; // 전역 변수로 approvalLines 선언

// 모달 열기/닫기
function openOrganizationModal() {
    console.log("openOrganizationModal 열림");
    document.querySelector('#modalOverlay').style.display = 'block';
    document.querySelector('#organizationModal').style.display = 'block';
    fetchOrganization(); // 모달이 열릴 때 조직도 데이터를 가져옴
}

function closeOrganizationModal() {
    document.querySelector('#modalOverlay').style.display = 'none';
    document.querySelector('#organizationModal').style.display = 'none';
}

function openApprovalLinePopup() {
    console.log("openApprovalLinePopup 열림");
    document.querySelector('#popupOverlay').style.display = 'block';
    document.querySelector('#myApprovalLineModal').style.display = 'block';
    fetchMyApprovalLines(); // 팝업을 열 때 결재선 데이터를 가져옴
}

function closeApprovalLinePopup() {
    document.querySelector('#popupOverlay').style.display = 'none';
    document.querySelector('#myApprovalLineModal').style.display = 'none';
}

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
        }
    });
}

// 결재 양식 내용, 결재선 불러오기
function fetchTemplateContent(templateNo) {
    $.ajax({
        url: '/orca/document/template/content',
        method: 'GET',
        data: { templateNo: templateNo },
        success: function(data) {
            $('#title').val(data.title); // 템플릿의 제목
            $('#summernote').summernote('code', ''); // 내용 초기화
            $('#summernote').summernote("pasteHTML", data.content); // 템플릿의 내용
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

// 결재선 프로세스 업데이트
function updateApprovalProcess(approvers) {
    const processContainer = document.querySelector('.approval-process');
    processContainer.innerHTML = '';

    approvers.forEach((approver, index) => {
        const approverDiv = document.createElement('span');
        approverDiv.textContent = `${approver.seq} ${approver.deptName} ${approver.approverName} ${approver.positionName}`;
        processContainer.appendChild(approverDiv);

        // 인풋태그 - 결재선 정보 숨기기
        const hiddenInputs = `
            <input hidden name="seq" value="${approver.seq}">
            <input hidden name="approverNo" value="${approver.approverNo}">
            <input hidden name="deptCode" value="${approver.deptCode}">
            <input hidden name="positionCode" value="${approver.positionCode}">
            <input hidden name="approverClassificationNo" value="${approver.approverClassificationNo}">
        `;

        processContainer.innerHTML += hiddenInputs;

        if (index < approvers.length - 1) {
            const arrowDiv = document.createElement('span');
            arrowDiv.classList.add('arrow');
            arrowDiv.textContent = '  ⇨  '; // 화살표 추가
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

// 결재 수정 - 문서 데이터 가져오기
const docNo = new URLSearchParams(window.location.search).get('docNo');
if (docNo) {
    editDocument(docNo);
}

// 나만의 결재선 팝업 열기
document.querySelector('#myApprovalLineBtn').addEventListener('click', openApprovalLinePopup);
document.querySelector('#popupOverlay').addEventListener('click', function(event) {
    if (event.target === document.querySelector('#popupOverlay')) {
        closeApprovalLinePopup();
    }
});

// 참조인 모달 열기
document.querySelector('#referrerAddBtn').addEventListener('click', openOrganizationModal);
document.querySelector('#modalOverlay').addEventListener('click', function(event) {
    if (event.target === document.querySelector('#modalOverlay')) {
        closeOrganizationModal();
    }
});

// 조직도 가져오기
function fetchOrganization() {
    console.log("조직도 가져오자");
    $.ajax({
        url: '/orca/apprline/organization/list',
        method: 'GET',
        success: function(data) {
            console.log("data:", data);
            const treeData = buildTreeData(data);
            console.log("treeData:", treeData);
            $('#jstree').jstree({
                'core': {
                    'data': treeData,
                    'themes': {
                        'name': 'default',
                        'dots': false,
                        'icons': true
                    }
                },
                'checkbox': {
                    'keep_selected_style': false, // 선택된 항목에 스타일 유지 여부
                    'three_state': false, // 체크박스의 상태를 세 가지로 설정 (부분 선택)
                    'cascade': 'undetermined', // 부모 및 자식 노드의 체크박스 동작 설정
                    'tie_selection': false // 체크박스와 노드 선택 상태를 연결할지 여부
                },
                'plugins': ['checkbox', 'themes', 'json_data']
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
            id: user.empNo,
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
    const referrerList = document.querySelector('#referrerList');
    referrerList.innerHTML = '';  // 초기화
    referrers.forEach(referrer => {
        const referrerDiv = document.createElement('span');
        referrerDiv.innerText = referrer.text;
        referrerList.appendChild(referrerDiv);

        // 숨겨진 input에 empNo 저장
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'referencerNo';
        hiddenInput.value = referrer.id;
        document.querySelector('#documentForm').appendChild(hiddenInput);
    });

    // 추가 버튼을 수정 버튼으로 변경
    document.querySelector('#referrerAddBtn').innerText = '수정';
}

// 나만의 결재선 가져오기
function fetchMyApprovalLines() {
    $.ajax({
        url: '/orca/myapprline/list/writeDocument',
        method: 'GET',
        success: function(lines) {

        console.log(lines);

            approvalLines = lines; // 전역 변수에 데이터 저장
            const approvalLineContent = document.querySelector('#approvalLineContent');
            approvalLineContent.innerHTML = "";

            if (approvalLines.length === 0) {
                approvalLineContent.innerHTML = '<div class="no-approval-lines">등록된 개인 결재선이 없습니다.</div>';
            } else {
                approvalLines.forEach(line => {
                    const lineDiv = document.createElement('div');
                    lineDiv.className = 'approval-lines';
                    lineDiv.dataset.lineId = line.apprLineNo; // 각 결재선에 고유한 ID 추가

                    const lineName = document.createElement('span');
                    lineName.className = 'approval-lines-name';
                    lineName.textContent = line.apprLineName;

                    const lineTitle = document.createElement('span');
                    lineTitle.className = 'approval-title';
                    lineTitle.textContent = `[${line.categoryName}] ${line.title}`;

                    lineDiv.appendChild(lineName);
                    lineDiv.appendChild(document.createElement('br'));
                    lineDiv.appendChild(lineTitle);
                    lineDiv.appendChild(document.createElement('br'));

                    line.approverVoList.forEach((approver, index) => {
                        const approverSpan = document.createElement('span');
                        approverSpan.className = 'approver';
                        approverSpan.textContent = `${approver.approverClassificationNo == 2 ? '합의' : '결재'}: ${approver.approverName} ${approver.positionName} (${approver.approverNo})`;
                        lineDiv.appendChild(approverSpan);

                        if (index < line.approverVoList.length - 1) {
                            const arrowSpan = document.createElement('span');
                            arrowSpan.textContent = ' ▶ ';
                            lineDiv.appendChild(arrowSpan);
                        }
                    });
                    // 클릭 이벤트 추가
                    lineDiv.addEventListener('click', function() {
                        const selectedLine = document.querySelector('.approval-lines.selected');
                        if (selectedLine) {
                            selectedLine.classList.remove('selected');
                        }
                        lineDiv.classList.add('selected');
                    });

                    approvalLineContent.appendChild(lineDiv);
                });
            }
        },
        error: function(error) {
            alert("결재선을 가져오는 중 오류가 발생했습니다.");
        }
    });
}

// 나만의 결재선 저장
function saveApprovalLine() {
    const selectedLine = document.querySelector('.approval-lines.selected');
    if (selectedLine) {
        const lineId = selectedLine.dataset.lineId;

        // 선택된 결재선의 정보 가져오기
        const approvalLine = approvalLines.find(line => line.apprLineNo == lineId);
        if (approvalLine) {
            console.log("Selected approval line:", approvalLine); // 디버그 정보 추가
            updateApprovalProcess(approvalLine.approverVoList);
        }
    } else {
        alert('결재선을 선택해주세요.');
    }
    closeApprovalLinePopup();
}

// textarea 라이브러리 초기화
$(document).ready(function() {
    $('#summernote').summernote({
        height: 400,
        minHeight: 300, // 최소
        maxHeight: 500, // 최대
        minWidth: 200, // 최소
        maxWidth: 1000, // 최대
        focus: true
    });
});

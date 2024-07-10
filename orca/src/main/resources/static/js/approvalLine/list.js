document.addEventListener("DOMContentLoaded", function() {
    $('#jstree').on('loaded.jstree', function() {
        $(this).jstree('open_all');
        $('#jstree li a').attr('draggable', true);

        // 더블 클릭 이벤트 막기
        $(document).on('dblclick', '#jstree li a', function (dblclickEvent) {
            dblclickEvent.preventDefault(); // 더블 클릭 시 기본 동작 방지
            dblclickEvent.stopPropagation(); // 이벤트 전파 중지
            return false; // 추가적인 전파 방지
        });
    });

    // 드래그 시작 이벤트
    $(document).on('dragstart', '#jstree li a', function(event) {
        var nodeId = $(this).parent().attr('id');
        event.originalEvent.dataTransfer.setData('text/plain', nodeId); // 노드 ID를 데이터로 설정
    });

    // 초기 슬롯 생성
    createSlots();

    // 결재선 삭제 버튼 이벤트 바인딩
    $(document).on('click', '.delete-btn', function(event) {
        event.stopPropagation();  // 부모 요소 이벤트 전파 막기
        const apprLineNo = $(this).data('apprline-no');
        console.log('apprLine No:', apprLineNo);

        $.ajax({
            url: '/orca/apprline/delete',
            method: 'POST',
            data: { apprLineNo: apprLineNo },
            success: function(data) {
                console.log('apprLine deleted:', data);
                location.reload();
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });

    // 조직도 데이터를 가져오는 함수 호출
    fetchOrganization();

    // jstree 노드 선택 시 이벤트 핸들러
    $('#jstree').on("select_node.jstree", function (e, data) {
        var selectedNode = data.node;
        if (selectedNode.original.type === 'user') { // 사용자 노드만 허용
            addApprovalSelection(selectedNode);
        }
    });
});

// 결재선 등록 팝업 드래그 앤 드롭 함수들
function allowDrop(event) {
    event.preventDefault();
    event.target.classList.add('dragover');
}

function removeDragover(event) {
    event.target.classList.remove('dragover');
}

function drop(event) {
    event.preventDefault();
    removeDragover(event);
    var nodeId = event.dataTransfer.getData('text/plain');
    var node = $('#jstree').jstree('get_node', nodeId);

    if (node) {
        var slot = event.target.closest('.slot');
        if (slot && !slot.querySelector(`[data-id="${node.id}"]`)) {
            var existingNode = slot.querySelector('.employee');
            if (existingNode) {
                existingNode.remove();
            }

            var newNode = document.createElement('div');
            newNode.className = 'employee';
            newNode.textContent = `${node.id}: ${node.text}`;
            newNode.dataset.id = node.id;
            newNode.draggable = true;
            newNode.ondragstart = function(ev) {
                ev.dataTransfer.setData('text/plain', node.id);
            };

            // 삭제 버튼 추가
            var deleteButton = document.createElement('button');
            deleteButton.textContent = '삭제';
            deleteButton.className = 'delete-btn';
            deleteButton.onclick = function() {
                newNode.remove();
                // 삭제 시 숨겨진 input에서도 제거
                removeHiddenInput(node.id);
            };

            newNode.appendChild(deleteButton);
            slot.appendChild(newNode);

            // 추가된 참조인 정보를 addReferrers 함수로 처리
            addReferrers([{ id: node.id, text: node.text }]);
        }
    }
}

// 선택한 유저의 정보를 받아 숨겨진 input에 저장하는 함수
function addReferrers(referrers) {
    const referrerList = document.querySelector('#referrerList');
    referrerList.innerHTML = '';  // 초기화
    referrers.forEach(referrer => {
        const referrerDiv = document.createElement('div');
        referrerDiv.innerText = referrer.text;
        referrerList.appendChild(referrerDiv);

        // 추가된 참조인의 approverNo를 가져오기
        const approverNo = referrer.id;

        // 숨겨진 input에 approverNo 저장
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'approverNo';
        hiddenInput.value = approverNo;
        document.getElementById('approvalLineForm').appendChild(hiddenInput);
    });
}

// 숨겨진 input에서 참조인 제거
function removeHiddenInput(empId) {
    const hiddenInputs = document.querySelectorAll('input[name="approverNo"]');
    hiddenInputs.forEach(input => {
        if (input.value === empId) {
            input.remove();
        }
    });
}

// 결재자 및 합의자 슬롯 생성 함수
function createSlots() {
    var numSlots = document.getElementById('numSlots').value; // 슬롯 개수
    var approvalSelection = document.getElementById('approvalSelection');
    approvalSelection.innerHTML = '';

    for (var i = 0; i < numSlots; i++) {
        var slot = document.createElement('div');
        slot.className = 'slot';
        slot.ondrop = drop;
        slot.ondragover = allowDrop;
        slot.ondragleave = removeDragover;

        var label = document.createElement('div');
        label.className = 'slot-label';
        var select = document.createElement('select');
        select.className = 'role-select';
        select.innerHTML = '<option value="합의">합의</option><option value="결재">결재</option>';

        select.onchange = (function(label, select) {
            return function() {
                label.textContent = select.value;
            };
        })(label, select);

        label.textContent = select.value;
        slot.appendChild(label);
        slot.appendChild(select);

        approvalSelection.appendChild(slot);
    }
}

// jstree 노드 선택 시 결재자/합의자 추가 함수
function addApprovalSelection(node) {
    var approvalSelection = $('#approvalSelection');
    var newApproval = $('<div>').addClass('approval-item').text(node.text);
    approvalSelection.append(newApproval);
}

// 결재선 등록 결재양식 제목 가져오기
function fetchTemplatesByCategory(categoryNo) {
    $.ajax({
        url: '/orca/apprline/template/list',
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
        },
        error: function(error) {
            console.error('Error fetching templates:', error);
        }
    });
}

// 결재선 등록 카테고리 가져오기
function fetchCategories() {
    $.ajax({
        url: '/orca/apprline/categorie/list',
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

// 결재선 등록 조직도 가져오기
function fetchOrganization() {
    $.ajax({
        url: '/orca/apprline/organization/list',
        method: 'GET',
        success: function(data) {
            console.log("data:", data);
            const treeData = buildTreeData(data);
            console.log("treeData:", treeData);
            $('#jstree').jstree({
                'core' : {
                    'data' : treeData,
                    'themes' : {
                        'name': 'default',
                        'dots': false,
                        'icons': true
                    }
                }
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
            id: user.empNo,  // 사용자 노드의 ID를 empNo로 설정
            text: `${user.name} (${user.nameOfPosition})`,
            icon: "fas fa-user",
            type: 'user' // 사용자 노드 타입 지정
        };
        team.children.push(userNode);
    });

    return tree;
}

// 팝업 관련 함수들
function showApprovalLinePopup() {
    document.querySelector('#popupOverlay').style.display = 'block';
    document.querySelector('#approvalLinePopup').style.display = 'block';
    fetchCategories();
    fetchOrganization();
}

function closeApprovalLinePopup() {
    document.querySelector('#popupOverlay').style.display = 'none';
    document.querySelector('#approvalLinePopup').style.display = 'none';
}

function saveApprovalLine(event) {
    event.preventDefault();
    let slots = document.querySelectorAll('.approval-selection .slot div[data-id]');
    let approvers = [];
    slots.forEach(function(slot) {
        approvers.push(slot.dataset.id);
    });

    $.ajax({
        url: '/orca/apprline/add',
        method: 'POST',
        data: {
            categoryNo: document.querySelector('#categoryNo').value,
            templateNo: document.querySelector('#templateNo').value,
            apprLineName: document.querySelector('#apprLineName').value,
            approvers: approvers
        },
        success: function(response) {
            alert('결재선이 저장되었습니다.');
            closeApprovalLinePopup();
            location.reload();
        },
        error: function() {
            alert('결재선 저장 중 오류가 발생했습니다.');
        }
    });
}

function openModal() {
    document.getElementById('modalOverlay').style.display = 'block';
    document.getElementById('approvalModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
    document.getElementById('approvalModal').style.display = 'none';
}

function saveModalApprovalLine() {
    alert('결재선이 저장되었습니다.');
    closeModal();
}
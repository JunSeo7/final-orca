    document.addEventListener("DOMContentLoaded", function() {

        // jstree 노드에 draggable 속성 추가
        $('#jstree').on('loaded.jstree', function() {
            $(this).jstree('open_all');
            $('#jstree li a').attr('draggable', true);
        });

        // 드래그 시작 이벤트
        $(document).on('dragstart', '#jstree li a', function(event) {
            var nodeId = $(this).parent().attr('id');
            event.originalEvent.dataTransfer.setData('text/plain', nodeId);
        });

        // 초기 슬롯 생성
        createSlots();

        // 결재선 삭제 버튼 이벤트 바인딩
        $(document).on('click', '.delete-btn', function(event) {
            event.stopPropagation();  // 부모 요소 이벤트 전파 막기
            const apprLineNo = $(this).data('apprline-no');
            console.log('apprLine No:', apprLineNo);

            $.ajax({
                url: '/orca/myapprline/delete?apprLineNo=' + apprLineNo,
                method: 'POST',
                success: function(data) {
                    console.log('apprLine deleted:', data);
                    location.reload();
                },
                error: function(e) {
                    console.error('Error:', e);
                }
            });
        });
    });

    function allowDrop(event) {
        event.preventDefault();
        event.target.classList.add('dragover');
    }

    function drop(event) {
        event.preventDefault();
        event.target.classList.remove('dragover');
        var nodeId = event.dataTransfer.getData('text/plain');
        var node = $('#jstree').jstree('get_node', nodeId);

        if (node && !event.target.querySelector(`[data-id="${node.id}"]`)) {
            var newNode = document.createElement('div');
            newNode.textContent = node.text;
            newNode.dataset.id = node.id;
            newNode.draggable = true;
            newNode.ondragstart = function(ev) {
                ev.dataTransfer.setData('text/plain', node.id);
            };
            event.target.appendChild(newNode);
        }
    }

    function createSlots() {
        var numSlots = document.getElementById('numSlots').value;
        var approvalSelection = document.getElementById('approvalSelection');
        approvalSelection.innerHTML = '';

        for (var i = 0; i < numSlots; i++) {
            var slot = document.createElement('div');
            slot.className = 'slot';
            slot.ondrop = drop;
            slot.ondragover = allowDrop;

            var label = document.createElement('div');
            label.className = 'slot-label';
            var select = document.createElement('select');
            select.className = 'role-select';
            select.innerHTML = '<option value="합의">합의</option><option value="결재">결재</option>';
            select.onchange = function() {
                label.textContent = select.value;
            };
            label.textContent = select.value;
            slot.appendChild(label);
            slot.appendChild(select);

            approvalSelection.appendChild(slot);
        }
    }

    function fetchTemplatesByCategory(categoryNo) {
        $.ajax({
            url: '/orca/myapprline/template/list',
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

    function fetchCategories() {
        $.ajax({
            url: '/orca/myapprline/categorie/list',
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

    function fetchOrganization() {
        $.ajax({
            url: '/orca/myapprline/organization/list',
            method: 'GET',
            success: function(data) {
                console.log("data:", data);
                const treeData = buildTreeData(data);
                $('#jstree').jstree({
                    'core' : {
                        'data' : treeData
                    }
                });
            },
            error: function(error) {
                console.error('error:', error);
                console.log(error);
            }
        });
    }

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
            url: '/orca/myapprline/add',
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
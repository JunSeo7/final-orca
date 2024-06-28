
function showApprovalLinePopup() {
  // Ajax로 결재 양식 카테고리 가져오기
  $.ajax({
      url: '/orca/template/categories',
      method: 'GET',
      success: function(categories) {
          populateCategories(categories);
          // Ajax로 조직도 가져오기
          $.ajax({
              url: '/orca/apprline/organization/list',
              method: 'GET',
              success: function(organizationData) {
                  const treeData = buildTreeData(organizationData);
                  initializeJsTree(treeData);
                  // 결재선 등록 팝업 표시
                  document.getElementById('popupOverlay').style.display = 'block';
                  document.getElementById('approvalLinePopup').style.display = 'block';
              },
              error: function(error) {
                  console.error('Error fetching organization data:', error);
              }
          });
      },
      error: function(error) {
          console.error('Error fetching template categories:', error);
      }
  });
}

function populateCategories(categories) {
  const selectElement = document.getElementById('approvalProcess');
  selectElement.innerHTML = ''; // 기존 옵션 제거
  categories.forEach(category => {
      const option = document.createElement('option');
      option.value = category.categoryNo;
      option.textContent = category.name;
      selectElement.appendChild(option);
  });
}


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

//합의 결제 디브 생기기
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

function showApprovalLinePopup() {
  document.getElementById('popupOverlay').style.display = 'block';
  document.getElementById('approvalLinePopup').style.display = 'block';
}

function closeApprovalLinePopup() {
  document.getElementById('popupOverlay').style.display = 'none';
  document.getElementById('approvalLinePopup').style.display = 'none';
}

function saveApprovalLine() {
  var slots = document.querySelectorAll('.approval-selection .slot div[data-id]');
  var approvers = [];
  slots.forEach(function(slot) {
      approvers.push(slot.dataset.id);
  });


  // AJAX로 사원번호 전송
  $.ajax({
      url: 'submitApprovers.jsp',
      method: 'POST',
      data: { approvers: approvers },
      success: function(response) {
          alert('결재선이 저장되었습니다.');
          closeApprovalLinePopup();
      },
      error: function() {
          alert('결재선 저장 중 오류가 발생했습니다.');
      }
  });
}


//수정팝업
function openModal() {
    document.getElementById('modalOverlay').style.display = 'block';
    document.getElementById('approvalModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
    document.getElementById('approvalModal').style.display = 'none';
}

function saveApprovalLine() {
    // 여기에 결재선을 저장하는 로직을 추가하세요.
    alert('결재선이 저장되었습니다.');
    closeModal();
}
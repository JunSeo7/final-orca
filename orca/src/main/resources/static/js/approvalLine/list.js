document.addEventListener("DOMContentLoaded", function() {

  $('#jstree').jstree({
      'core' : {
          'data' : [
              {
                  "text" : "대표이사",
                  "state" : { "opened" : true },
                  "icon" : "fas fa-user-tie",
                  "children" : [
                      { "text" : "감사팀: 5명", "icon" : "fas fa-folder" },
                      { "text" : "시스템사업담당: 15명", "icon" : "fas fa-folder" },
                      {
                          "text" : "경영지원실: 50명",
                          "icon" : "fas fa-folder",
                          "children" : [
                              { "text" : "재무회계팀: 20명",
                                "icon" : "fas fa-folder",
                                "children": [
                                    { "text": "김철수 팀장", "icon": "fas fa-user", "id": "001" },
                                        { "text": "이영희 팀원", "icon": "fas fa-user", "id": "002" },
                                        { "text": "박민수 팀원", "icon": "fas fa-user", "id": "003" },
                                        { "text": "최수진 팀원", "icon": "fas fa-user", "id": "004" }
                                ]
                              },
                              { "text" : "인사팀: 15명",
                                "icon" : "fas fa-folder",
                                "children": [
                                    { "text": "이민호 팀장", "icon": "fas fa-user", "id": "005" },
                                    { "text": "김하늘 팀원", "icon": "fas fa-user", "id": "006" },
                                    { "text": "정은지 팀원", "icon": "fas fa-user", "id": "007" }
                                ]
                              },
                              { "text" : "경영관리팀: 15명",
                                "icon" : "fas fa-folder",
                                "children": [
                                    { "text":  "박상우 팀장", "icon": "fas fa-user", "id": "008" },
                                    { "text": "오지민 팀원", "icon": "fas fa-user", "id": "009" },
                                    { "text": "유재석 팀원", "icon": "fas fa-user", "id": "010" }

                                ]
                              }
                          ]
                      },
                      {
                          "text" : "사업총괄: 150명",
                          "icon" : "fas fa-folder",
                          "children" : [
                              { "text" : "사업관리그룹: 40명", "icon" : "fas fa-folder" },
                              { "text" : "영업본부: 100명", "icon" : "fas fa-folder" },
                              { "text" : "연구본부: 10명", "icon" : "fas fa-folder" }
                          ]
                      },
                      {
                          "text" : "연구본부: 189명",
                          "icon" : "fas fa-folder",
                          "children" : [
                              { "text" : "시스템연구소: 60명", "icon" : "fas fa-folder" },
                              { "text" : "ISS연구소: 60명", "icon" : "fas fa-folder" },
                              { "text" : "디자인팀: 69명", "icon" : "fas fa-folder" }
                          ]
                      },
                      { "text" : "IT 인프라 팀: 20명", "icon" : "fas fa-folder" },
                      { "text" : "고객지원팀: 30명", "icon" : "fas fa-folder" },
                      { "text" : "마케팅 팀: 20명", "icon" : "fas fa-folder" },
                      { "text" : "제품관리팀: 15명", "icon" : "fas fa-folder" },
                      { "text" : "법무팀: 10명", "icon" : "fas fa-folder" }
                  ]
              }
          ]
      }
  });

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
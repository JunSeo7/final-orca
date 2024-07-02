/* 페이지 로드 함수 */
function loadPage(page) {
  fetch(page)  // 페이지 파일을 가져옴
      .then(response => response.text())
      .then(html => {
          document.getElementById('content').innerHTML = html;  // 가져온 페이지 내용을 메인 컨텐츠에 삽입
          hideProfile();  // 페이지 로드 시 프로필 숨김
      })
      .catch(error => {
          console.error('Error loading page:', error);  // 에러 발생 시 콘솔에 출력
      });
}

/* 사이드바 토글 함수 */
function toggleSidebar() {
  const sidebar = document.getElementById('sidebar');
  const content = document.getElementById('content');
  sidebar.classList.toggle('hidden');  // 사이드바 숨김/표시 토글
  content.classList.toggle('full');  // 메인 컨텐츠 너비 조정
}

/* 프로필 토글 함수 */
function toggleProfile() {
  const profileDetail = document.getElementById('profileDetail');
  profileDetail.classList.toggle('hidden');  // 프로필 상세 정보 숨김/표시 토글
}

/* 프로필 숨김 함수 */
function hideProfile() {
  const profileDetail = document.getElementById('profileDetail');
  if (!profileDetail.classList.contains('hidden')) {
      profileDetail.classList.add('hidden');  // 프로필 숨김
  }
}

/* 로그아웃 함수 */
function logout() {
  alert('로그아웃 되었습니다.');  // 로그아웃 알림
}

function toggleSubMenu(menuId) {
  const menu = document.getElementById(menuId);
  menu.classList.toggle('hidden');  // 서브메뉴 숨김/표시 토글
}

document.addEventListener("DOMContentLoaded", function() {
    // 결재선 등록 결재양식 제목 가져오기
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

    // 결재선 등록 카테고리 가져오기
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

    // 결재 양식 내용 불러오기
    function fetchTemplateContent(templateNo) {
        $.ajax({
            url: '/orca/document/template/content',
            method: 'GET',
            data: { templateNo: templateNo },
            success: function(data) {
                $('#title').val(data.title); // 템플릿의 제목
                $('#content').val(data.content); // 템플릿의 내용
                $('#templateNo').val(data.templateNo);
            },
            error: function() {
                alert('결재 양식 내용 불러오기 오류가 발생했습니다.');
            }
        });
    }

    // 카테고리 변경 - 템플릿 목록 업데이트
    $('#categoryNo').change(function() {
        var categoryNo = $(this).val();
        fetchTemplatesByCategory(categoryNo);
    });

    // 결재 양식 선택 - 내용 업데이트
    $('#templateNo').change(function() {
        var templateNo = $(this).val();
        fetchTemplateContent(templateNo);
    });

    fetchCategories(); // 페이지 로드 - 카테고리 가져오기
});

function submitDocument() {
    var formData = $('#documentForm').serialize();
    $.ajax({
        url: '/orca/document/write',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function() {
            alert('결재 작성이 완료되었습니다.');
            window.location.href = '/orca/document/list';
        },
        error: function() {
            alert('결재 작성 중 오류가 발생했습니다.');
        }
    });
}
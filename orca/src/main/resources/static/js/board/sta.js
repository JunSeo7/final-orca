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

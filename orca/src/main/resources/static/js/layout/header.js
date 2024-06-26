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


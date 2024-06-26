/* 페이지 로드 함수 */
function loadPage(page) {
  fetch(page)
    .then(response => response.text())
    .then(html => {
      document.getElementById('content').innerHTML = html;
      hideProfile();
    })
    .catch(error => {
      console.error('Error loading page:', error);
    });
}


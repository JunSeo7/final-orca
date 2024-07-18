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

let organizationChart = document.querySelector('.organizationChart');
console.log(organizationChart);
organizationChart.addEventListener('click', function(){
  window.location.href = "/orca/organizationChart/showOrganizationChart";
})
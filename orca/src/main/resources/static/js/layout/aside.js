document.addEventListener('DOMContentLoaded', function () {
  function updateClock() {
    const clockElement = document.getElementById('clock');
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    clockElement.textContent = `${hours}:${minutes}:${seconds}`;
  }
  updateClock();
  setInterval(updateClock, 1000);
});

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
  if (!profileDetail.classList.contains('hidden')) {
    $.ajax({
      url: "/orca/user/getUserVo",
      method: 'get',
      dataType: 'json',
      success: function (response) {

        let empNo = document.querySelector('#empNo');
        let partName = document.querySelector('#partName');
        let position = document.querySelector('#position');
        let phone = document.querySelector('#phone');
        let extensionCall = document.querySelector('#extensionCall');
        let email = document.querySelector('#email');


        empNo.textContent = '사번 : ' + response.empNo;
        partName.textContent = '부서명 : ' + response.partName;
        position.textContent = '직급 : ' + response.nameOfPosition;
        phone.textContent = '전화번호 : ' + response.phone;
        extensionCall.textContent = '내선번호 : ' + response.extensionCall;
        email.textContent = '이메일 : ' + response.email;
      },
      error: function (error) {
        console.log(error);
      }
    })
  }
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
  const userResponse = confirm("정말로 종료하시겠습니까?");
  if (userResponse) {
    $.ajax({
      url: "/orca/user/logout",
      method: 'get',
      dataType: 'json',
      success: function (response) {
        if (response === 1) {
          alert('로그아웃 되었습니다.');
          window.location.href = "/orca/user/login";
        } else {
          alert('로그아웃 실패');
        }
      },
      error: function (error) {
        console.log(error);
      }
    })
  }
}

let changePwd = document.querySelector("#change-password")
changePwd.addEventListener('click', function(){
  window.location.href = "/orca/user/showChangePassword";
})
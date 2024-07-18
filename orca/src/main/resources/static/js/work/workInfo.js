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

// 캘린더
document.addEventListener('DOMContentLoaded', function() {
    $.ajax({
        url: '/orca/re/work/list',
        method: 'GET',
        success: function(data) {
            var events = [];
            data.forEach(function(workInfo) {
                if (workInfo.workDate) {
                    events.push({
                        title: workInfo.startTime + " - " + workInfo.endTime,
                        start: workInfo.workDate,
                        color: getColor(workInfo)
                    });
                }
            });

            const calendarEl = document.querySelector('#calendar');
            const calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                events: events,
                height: 'auto',
                contentHeight: 'auto'
            });
            calendar.render();
        }
    });

    function getColor(workInfo) {
        // 정상출근, 지각, 조퇴 등의 조건에 따라 색상 설정
        if (isLate(workInfo)) {
            return 'red';
        } else if (isEarlyLeave(workInfo)) {
            return 'orange';
        } else {
            return 'green';
        }
    }

    function isLate(workInfo) {
        const requiredStartTime = "09:10:00";
        return workInfo.startTime > requiredStartTime;
    }

    function isEarlyLeave(workInfo) {
        const requiredEndTime = "17:50:00";
        return workInfo.endTime < requiredEndTime;
    }
});

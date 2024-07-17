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
document.addEventListener('DOMContentLoaded', () => {
    const calendarHeader = document.getElementById('calendarHeader');
    const daysContainer = document.getElementById('daysContainer');
    const prevMonthButton = document.getElementById('prevMonth');
    const nextMonthButton = document.getElementById('nextMonth');

    const attendanceData = [
        { date: '2020-07-01', start: '09:00', end: '18:00' },
        { date: '2020-07-02', start: '09:15', end: '18:00' },
        { date: '2020-07-03', start: '09:00', end: '17:50' },
        { date: '2020-07-04', start: '09:10', end: '18:10' }
    ];

    let currentYear = new Date().getFullYear();
    let currentMonth = new Date().getMonth();

    function getAttendanceStatus(startTime, endTime) {
        const normalStart = new Date(`1970-01-01T09:10:00`);
        const normalEnd = new Date(`1970-01-01T18:00:00`);
        const actualStart = new Date(`1970-01-01T${startTime}:00`);
        const actualEnd = new Date(`1970-01-01T${endTime}:00`);

        if (actualStart > normalStart) {
            return 'late';
        }
        if (actualEnd < normalEnd) {
            return 'early';
        }
        return 'normal';
    }

    function renderCalendar(year, month) {
        daysContainer.innerHTML = "";
        calendarHeader.innerText = `${year}년 ${month + 1}월`;

        const firstDayOfMonth = new Date(year, month, 1).getDay();
        const lastDateOfMonth = new Date(year, month + 1, 0).getDate();

        for (let i = 0; i < firstDayOfMonth; i++) {
            const dayElement = document.createElement("div");
            dayElement.classList.add("day", "off");
            daysContainer.appendChild(dayElement);
        }

        for (let date = 1; date <= lastDateOfMonth; date++) {
            const dayElement = document.createElement("div");
            dayElement.classList.add("day", "work-day");
            dayElement.innerText = date;

            const fullDate = `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
            const attendance = attendanceData.find(d => d.date === fullDate);

            if (attendance) {
                const status = getAttendanceStatus(attendance.start, attendance.end);
                const statusElement = document.createElement("div");
                statusElement.classList.add("status", status);
                statusElement.innerText = status === 'normal' ? '정상출근' : (status === 'late' ? '지각' : '조퇴');
                dayElement.appendChild(statusElement);
            }

            daysContainer.appendChild(dayElement);
        }
    }

    prevMonthButton.addEventListener('click', () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        renderCalendar(currentYear, currentMonth);
    });

    nextMonthButton.addEventListener('click', () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        renderCalendar(currentYear, currentMonth);
    });

    renderCalendar(currentYear, currentMonth);
});



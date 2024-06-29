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

function fetchData() {
  const startDate = document.getElementById('start-date').value;
  const endDate = document.getElementById('end-date').value;

  if (startDate && endDate) {
      // 로직을 추가
      // 임시 데이터
      const data = [
          { date: '2024-06-20', 출근: '09:00', 퇴근: '18:00', 지각: 5, 조퇴: 2, 결근: 1, 연장: 0, 휴무: 0 },
          { date: '2024-06-21', 출근: '09:00', 퇴근: '18:00', 지각: 6, 조퇴: 1, 결근: 2, 연장: 1, 휴무: 0 },
          // 추가 데이터...
      ];

      const filteredData = data.filter(d => d.date >= startDate && d.date <= endDate);
      const tableBody = document.getElementById('data-table-body');
      tableBody.innerHTML = '';

      const weekdays = ['일', '월', '화', '수', '목', '금', '토'];

      filteredData.forEach(d => {
          const row = document.createElement('tr');

          // 날짜를 파싱하고 요일을 계산
          const dateObj = new Date(d.date);
          const weekday = weekdays[dateObj.getUTCDay()];

          // 요일을 데이터에 추가하고 순서 정렬
          const dataWithWeekday = { date: d.date, 요일: weekday, ...d };

          Object.values(dataWithWeekday).forEach(value => {
              const cell = document.createElement('td');
              cell.textContent = value;
              row.appendChild(cell);
          });
          tableBody.appendChild(row);
      });
  } else {
      alert('날짜를 선택하세요.');
  }
}


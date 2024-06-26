window.onload = function () {
    const text1 = "ORCA GROUPWARE";
    const i1 = 0;
    const container1 = document.getElementById("text1");

    typeWriter(text1, i1, container1);

    setTimeout(function () {
        const text2 = "'협력으로 만드는 내일의 기쁨'";
        const i2 = 0;
        const container2 = document.getElementById("text2");
        typeWriter(text2, i2, container2);
    }, 1500);

    setTimeout(function () {
        const text3 = "지금 바로 시작해보세요.";
        const i3 = 0;
        const container3 = document.getElementById("text3");
        typeWriter(text3, i3, container3);
    }, 3000);
    setTimeout(function () {
        const hoverText = document.querySelector(".login-text");
        hoverText.classList.add("login-text-hover");
    }, 3500);
};
function typeWriter(text, i, container) {
    if (i < text.length) {
        container.innerHTML += text.charAt(i);
        i++;
        setTimeout(function () {
            typeWriter(text, i, container);
        }, 60); // 100밀리초마다 한 글자씩 출력
    }
}

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
document.addEventListener("DOMContentLoaded", function () {
    let currentYear = 2024;
    let currentMonth = 6; // June

    const yearMonthElement = document.getElementById("year-month");
    const mainDatesElement = document.getElementById("main-dates");
    const sideDatesElement = document.getElementById("side-dates");

    function renderCalendar(year, month, datesElement) {
        const firstDayIndex = new Date(year, month - 1, 1).getDay();
        const daysInMonth = new Date(year, month, 0).getDate();

        datesElement.innerHTML = '';

        for (let i = 0; i < firstDayIndex; i++) {
            const emptyCell = document.createElement("div");
            emptyCell.classList.add("empty");
            datesElement.appendChild(emptyCell);
        }

        for (let day = 1; day <= daysInMonth; day++) {
            const dateCell = document.createElement("div");
            dateCell.classList.add("date-cell");
            dateCell.innerHTML = `
                <div class="date">${day}</div>
                <div class="content">Content for ${day}</div>
            `;
            datesElement.appendChild(dateCell);
        }
    }

    function renderMainCalendar(year, month) {
        yearMonthElement.textContent = `${year}.${month.toString().padStart(2, '0')}`;
        renderCalendar(year, month, mainDatesElement);
    }

    document.getElementById("prevMonth").addEventListener("click", function () {
        currentMonth--;
        if (currentMonth < 1) {
            currentMonth = 12;
            currentYear--;
        }
        renderMainCalendar(currentYear, currentMonth);
        renderCalendar(currentYear, currentMonth, sideDatesElement);
    });

    document.getElementById("nextMonth").addEventListener("click", function () {
        currentMonth++;
        if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        }
        renderMainCalendar(currentYear, currentMonth);
        renderCalendar(currentYear, currentMonth, sideDatesElement);
    });

    renderMainCalendar(currentYear, currentMonth);
    renderCalendar(currentYear, currentMonth, sideDatesElement);
});
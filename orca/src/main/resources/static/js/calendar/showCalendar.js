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
function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');
    sidebar.classList.toggle('hidden');  // 사이드바 숨김/표시 토글
    content.classList.toggle('full');  // 메인 컨텐츠 너비 조정
}
function toggleProfile() {
    const profileDetail = document.getElementById('profileDetail');
    profileDetail.classList.toggle('hidden');  // 프로필 상세 정보 숨김/표시 토글
}
function hideProfile() {
    const profileDetail = document.getElementById('profileDetail');
    if (!profileDetail.classList.contains('hidden')) {
        profileDetail.classList.add('hidden');  // 프로필 숨김
    }
}
function logout() {
    alert('로그아웃 되었습니다.');
}


function toggleCheck(element) {
    element.parentNode.classList.toggle('checked');
}



// 캘린더

const calendarElement = document.getElementById('calendar');
const sidebarCalendarElement = document.getElementById('sidebarCalendar');
const date = new Date();
let year = date.getFullYear();
let month = date.getMonth();

function renderCalendar(calendarEl, year, month) {
    calendarEl.innerHTML = '';

    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

    // Header
    const header = document.createElement('div');
    header.className = 'calendar-header';

    const prevButton = document.createElement('button');
    prevButton.textContent = '◀';
    prevButton.onclick = () => changeMonth(-1);

    const nextButton = document.createElement('button');
    nextButton.textContent = '▶';
    nextButton.onclick = () => changeMonth(1);

    const title = document.createElement('div');
    title.textContent = `${year} ${monthNames[month]}`;

    header.appendChild(prevButton);
    header.appendChild(title);
    header.appendChild(nextButton);

    calendarEl.appendChild(header);

    const grid = document.createElement('div');
    grid.className = 'calendar-grid';

    for (const day of daysOfWeek) {
        const dayElement = document.createElement('div');
        dayElement.className = 'day';
        dayElement.textContent = day;
        grid.appendChild(dayElement);
    }

    const firstDay = new Date(year, month, 1).getDay();
    const lastDate = new Date(year, month + 1, 0).getDate();

    const prevLastDate = new Date(year, month, 0).getDate();
    for (let i = firstDay - 1; i >= 0; i--) {
        const dateElement = document.createElement('div');
        dateElement.className = 'date other-month';
        dateElement.innerHTML = `<div class="number">${prevLastDate - i}</div><div class="empty"></div>`;
        grid.appendChild(dateElement);
    }

    for (let date = 1; date <= lastDate; date++) {
        const dayOfWeek = new Date(year, month, date).getDay();
        const dateElement = document.createElement('div');
        dateElement.className = 'date';
        if (dayOfWeek === 0) {
            dateElement.classList.add('sunday');
        } else if (dayOfWeek === 6) {
            dateElement.classList.add('saturday');
        }

        const today = new Date();
        if (year === today.getFullYear() && month === today.getMonth() && date === today.getDate()) {
            dateElement.classList.add('today');
        }
        dateElement.innerHTML = `<div class="number">${date}</div><div class="empty"></div>`;

        grid.appendChild(dateElement);
    }

    const totalCells = firstDay + lastDate;
    const nextDays = (7 - (totalCells % 7)) % 7;
    for (let i = 1; i <= nextDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.className = 'date other-month';
        dateElement.innerHTML = `<div class="number">${i}</div><div class="empty"></div>`;
        grid.appendChild(dateElement);
    }

    calendarEl.appendChild(grid);
}

// 사이드 캘린더
function renderSideCalendar(calendarEl, year, month) {
    calendarEl.innerHTML = '';

    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

    const header = document.createElement('div');
    header.className = 'side-calendar-header';

    const prevButton = document.createElement('button');
    prevButton.textContent = '◀';
    prevButton.onclick = () => changeMonth(-1);

    const nextButton = document.createElement('button');
    nextButton.textContent = '▶';
    nextButton.onclick = () => changeMonth(1);

    const title = document.createElement('div');
    title.textContent = `${year} ${monthNames[month]}`;


    header.appendChild(prevButton);
    header.appendChild(title);
    header.appendChild(nextButton);
    calendarEl.appendChild(header);

    const grid = document.createElement('div');
    grid.className = 'calendar-grid side-calendar';

    for (const day of daysOfWeek) {
        const dayElement = document.createElement('div');
        dayElement.className = 'day';
        dayElement.textContent = day;
        grid.appendChild(dayElement);
    }

    const firstDay = new Date(year, month, 1).getDay();
    const lastDate = new Date(year, month + 1, 0).getDate();

    const prevLastDate = new Date(year, month, 0).getDate();
    for (let i = firstDay - 1; i >= 0; i--) {
        const dateElement = document.createElement('div');
        dateElement.className = 'side-date other-month';
        dateElement.innerHTML = `<div class="number">${prevLastDate - i}</div>`;
        grid.appendChild(dateElement);
    }

    for (let date = 1; date <= lastDate; date++) {
        const dayOfWeek = new Date(year, month, date).getDay();
        const dateElement = document.createElement('div');
        dateElement.className = 'side-date';
        if (dayOfWeek === 0) {
            dateElement.classList.add('sunday');
        } else if (dayOfWeek === 6) {
            dateElement.classList.add('saturday');
        }

        const today = new Date();
        if (year === today.getFullYear() && month === today.getMonth() && date === today.getDate()) {
            dateElement.classList.add('today');
        }
        dateElement.innerHTML = `<div class="number">${date}</div>`;

        grid.appendChild(dateElement);
    }

    const totalCells = firstDay + lastDate;
    const nextDays = (7 - (totalCells % 7)) % 7;
    for (let i = 1; i <= nextDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.className = 'side-date other-month';
        dateElement.innerHTML = `<div class="number">${i}</div>`;
        grid.appendChild(dateElement);
    }

    calendarEl.appendChild(grid);
}

function changeMonth(offset) {
    month += offset;
    if (month < 0) {
        month = 11;
        year--;
    } else if (month > 11) {
        month = 0;
        year++;
    }
    yearSelect.value = year;
    monthSelect.value = month;
    renderCalendar(calendarElement, year, month);
    renderSideCalendar(sidebarCalendarElement, year, month);

    const week = document.querySelectorAll('.day');
    for (let i = 0; i < week.length; i++) {
        if (week[i].textContent === '일') {
            week[i].classList.add('redColor');
        } else if (week[i].textContent === '토') {
            week[i].classList.add('blueColor');
        }
    }
}

renderCalendar(calendarElement, year, month);
renderSideCalendar(sidebarCalendarElement, year, month);

// 주말 색 변경
const week = document.querySelectorAll('.day');
for (let i = 0; i < week.length; i++) {
    if (week[i].textContent === '일') {
        week[i].classList.add('redColor');
    } else if (week[i].textContent === '토') {
        week[i].classList.add('blueColor');
    }
}

//일정 등록
let lastClickedDiv = null;
let KeyCnt = 0;
document.addEventListener('DOMContentLoaded', function () {
    const calendarDiv = document.getElementById('calendar');

    calendarDiv.addEventListener('click', function (event) {
        const clickedDiv = event.target.closest('.date');

        if (clickedDiv) {
            KeyCnt++;
            if (KeyCnt % 2 == 0) {
                hideNewEventForm();
            } else if (KeyCnt % 2 != 0) {
                showNewEventForm(clickedDiv);
            }
        }
    });
    document.addEventListener('keydown', function (event) {
        if (event.ctrlKey && event.key === 'c') {
            KeyCnt++;
            if (KeyCnt % 2 == 0) {
                hideNewEventForm();
            } else if (KeyCnt % 2 != 0) {
                showNewEventForm();
            }
        }
    });
});

function showNewEventForm() {
    const form = document.getElementById('newEventForm');
    form.style.display = 'block';

    const formRect = form.getBoundingClientRect();
    const left = (window.innerWidth - formRect.width) / 2;
    const top = (window.innerHeight - formRect.height) / 2;

    form.style.left = `${left}px`;
    form.style.top = `${top}px`;

}

function showNewEventForm(dateDiv) {
    const form = document.getElementById('newEventForm');
    form.style.display = 'block';

    const rect = dateDiv.getBoundingClientRect();
    const formRect = form.getBoundingClientRect();

    let left = rect.right + 195;

    if (left + formRect.width > window.innerWidth + 300) {
        left = rect.left - formRect.width + 185;
    }

    let top = rect.top + 145;


    if (top < 0) {
        top = 0;
    }

    form.style.left = `${left}px`;
    form.style.top = `${top}px`;

    form.scrollIntoView({ behavior: 'smooth', block: 'start' });
}

function hideNewEventForm() {
    const form = document.getElementById('newEventForm');
    form.style.display = 'none';
}


document.getElementById('eventForm').addEventListener('submit', function (event) {
    alert('일정이 등록되었습니다.');
    hideNewEventForm();
});

let isDraggingForm = false;
let initialClickX = 0;
let initialClickY = 0;
let initialFormX = 0;
let initialFormY = 0;

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('newEventForm');

    form.addEventListener('mousedown', function (event) {
        if (event.target.classList.contains('form-header') || event.target.closest('.form-header')) {
            isDraggingForm = true;
            initialClickX = event.clientX;
            initialClickY = event.clientY;
            initialFormX = form.offsetLeft;
            initialFormY = form.offsetTop;
            document.addEventListener('mousemove', onDragForm);
            document.addEventListener('mouseup', onStopDragForm);
        }
    });
});

function onDragForm(event) {
    if (isDraggingForm) {
        const deltaX = event.clientX - initialClickX;
        const deltaY = event.clientY - initialClickY;
        const newFormX = initialFormX + deltaX;
        const newFormY = initialFormY + deltaY;

        const form = document.getElementById('newEventForm');
        form.style.left = `${newFormX}px`;
        form.style.top = `${newFormY}px`;
    }
}

function onStopDragForm() {
    isDraggingForm = false;
    document.removeEventListener('mousemove', onDragForm);
    document.removeEventListener('mouseup', onStopDragForm);
}

function submitNewEvent() {
    KeyCnt++;
    if (KeyCnt % 2 == 0) {
        hideNewEventForm();
    } else if (KeyCnt % 2 != 0) {
        showNewEventForm();
    }
}


const yearSelect = document.getElementById('yearSelect');
const monthSelect = document.getElementById('monthSelect');
const currentDate = new Date();


const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
for (let i = 0; i < 12; i++) {
    const option = document.createElement('option');
    option.value = i;
    option.textContent = monthNames[i];
    monthSelect.appendChild(option);
}

const currentYear = currentDate.getFullYear();
for (let year = currentYear - 5; year <= currentYear + 5; year++) {
    const option = document.createElement('option');
    option.value = year;
    option.textContent = year;
    yearSelect.appendChild(option);
}

yearSelect.value = currentDate.getFullYear();
monthSelect.value = currentDate.getMonth().toString();

function handleMonthChange() {
    year = parseInt(yearSelect.value);
    month = parseInt(monthSelect.value);
    renderCalendar(calendarElement, year, month);
    renderSideCalendar(sidebarCalendarElement, year, month);
}





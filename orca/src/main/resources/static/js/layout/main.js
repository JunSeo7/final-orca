const workDate = new Date().toISOString().split('T')[0]; // 현재 날짜 부분만 추출

// 출근 버튼
function startWorkClick() {

    $.ajax({
        url: "/orca/re/work/goWork",
        method: 'post',
        data: {
            workDate: workDate,
        },
        success: function (response) {
            if (response.success) {
                alert('출근 시간이 기록되었습니다.');
                location.reload();
            } else {
                alert('출근 시간 기록에 실패했습니다.');
            }
        },
        error: function (error) {
            console.error('Error:', error);
            alert('출근 시간 기록에 에러.');
        }
    });

}

// 퇴근 버튼
function endWorkClick() {

    $.ajax({
        url: "/orca/re/work/leaveWork",
        method: 'post',
        data: {
            workDate: workDate,
        },
        success: function (response) {
            if (response.success) {
                alert('퇴근 시간이 기록되었습니다.');
                location.reload();
            } else {
                alert('퇴근 시간 기록에 실패했습니다. ' + response.message);
            }
        },
        error: function (error) {
            console.error('Error:', error);
            alert('퇴근 시간 기록에 에러.');
        }
    });

}

// 출, 퇴근 시간 화면 출력
function loadWorkTimes() {
    $.ajax({
        url: "/orca/re/work/getStartWorkTime",
        method: 'get',
        data: {
            workDate: workDate,
        },
        success: function (response) {
            if (response.success) {
                if (response.startWorkTime) {
                    var sWorkTimeElement = document.getElementById('sWorkTime');
                    if (sWorkTimeElement) {
                        var justTime = response.startWorkTime.substring(11, 19);
                        sWorkTimeElement.innerText = justTime;
                    }
                }
            } else {
                console.error('시간 불러오기 실패', response.message);
            }
        },
        error: function (error) {
            console.error('출근 시간 불러오기 에러', error);
        }
    });

    $.ajax({
        url: "/orca/re/work/getEndWorkTime",
        method: 'get',
        data: {
            workDate: workDate,
        },
        success: function (response) {
            if (response.success) {
                if (response.endWorkTime) {
                    var eWorkTimeElement = document.getElementById('eWorkTime');
                    if (eWorkTimeElement) {
                        var justTime = response.endWorkTime.substring(11, 19);
                        eWorkTimeElement.innerText = justTime;
                    }
                }
            } else {
                console.error('시간 불러오기 실패', response.message);
            }
        },
        error: function (error) {
            console.error('퇴근 시간 불러오기 에러', error);
        }
    });
}

$(document).ready(function () {
    loadWorkTimes();
    fetchDocuments();
});

// 오늘 날자 불러오기
var today = new Date();

var year = today.getFullYear();
var month = ('0' + (today.getMonth() + 1)).slice(-2);
var day = ('0' + today.getDate()).slice(-2);
var week = ['일', '월', '화', '수', '목', '금', '토'];
var dayOfWeek = week[today.getDay()];

var formattedDate = year + '.' + month + '.' + day + '(' + dayOfWeek + ')';

document.getElementById('date').innerText = formattedDate;


//결재 문서 불러오기
function fetchDocuments() {
    $.ajax({
        url: "/orca/document/api/received-documents",
        method: 'GET',
        success: function (response) {
            displayDocuments(response);
        },
        error: function (error) {
            console.error('Error:', error);
            alert('문서를 가져오는 중 오류가 발생했습니다.');
        }
    });
}

function displayDocuments(documents) {
    const documentList = $('#document-list');
    documentList.empty(); // 기존 내용을 비웁니다.

    // status가 2인 문서만 필터링
    const filteredDocuments = documents.filter(doc => doc.status === 2);

    if (filteredDocuments.length === 0) {
        documentList.append('<div class="no-documents">결재할 문서가 없습니다.</div>');
        return;
    }

    // 최신 4개 문서만 표시
    const latestDocuments = filteredDocuments.slice(0, 4);

    latestDocuments.forEach(doc => {
        const docDiv = $('<div>').addClass('document');
        docDiv.html(`
                <span>${doc.title}</span>
                <br>
                <span>기안자: ${doc.writerName}</span>
                <span>기안일: ${doc.creditDate}</span>
            `);
        documentList.append(docDiv);
    });
}

//캘린더~

const calendarElement = document.getElementById('calendar');

const calendarDate = new Date();
let calendarYear = calendarDate.getFullYear();
let calendarMonth = calendarDate.getMonth();

renderCalendar(calendarElement, calendarYear, calendarMonth);

function renderCalendar(calendarEl, year, month) {
    calendarEl.innerHTML = '';

    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

    // 캘린더 추가
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
        dateElement.className = 'date other-month before-month';
        dateElement.innerHTML = `<div class="number">${prevLastDate - i}</div>`;
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
        dateElement.innerHTML = `<div class="number">${date}</div>`;
        grid.appendChild(dateElement);
    }

    const totalCells = firstDay + lastDate;
    const nextDays = (7 - (totalCells % 7)) % 7;
    for (let i = 1; i <= nextDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.className = 'date other-month after-month';
        dateElement.innerHTML = `<div class="number">${i}</div>`;
        grid.appendChild(dateElement);
    }

    calendarEl.appendChild(grid);
}

function changeMonth(offset) {
    calendarMonth += offset;
    if (calendarMonth < 0) {
        calendarMonth = 11;
        calendarYear--;
    } else if (calendarMonth > 11) {
        calendarMonth = 0;
        calendarYear++;
    }
    
    renderCalendar(calendarElement, calendarYear, calendarMonth);

    const week = document.querySelectorAll('.day');
    for (let i = 0; i < week.length; i++) {
        if (week[i].textContent === '일') {
            week[i].classList.add('redColor');
        } else if (week[i].textContent === '토') {
            week[i].classList.add('blueColor');
        }
    }
    todayText();
}


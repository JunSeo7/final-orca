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

const loginUserVo = {};

$.ajax({
    type: 'post',
    url: '/orca/user/getUserVo',
    dataType: 'json',
    success: function (response) {
        Object.assign(loginUserVo, response);
    },
    error: function (error) {
        console.error('유저 데이터 로드 실패', error);
    }
});



function logout() {
    alert('로그아웃 되었습니다.');
}


function toggleCheck(element) {
    element.parentNode.classList.toggle('checked');
}
//------------------------------------------------------------------------------------------------------------------------
// 캘린더
//-- 캐린더 바 요청 보내기
//-- 캐린더 바 변수 설정
let CalendarBar = document.querySelectorAll('.showCalendarBar');
let showCalendarBarCnt = [0, 0, 0];
let calendarBar = {
    company: [],
    individual: [],
    department: []
};
let isCalendarBarVisible = [false, false, false];  // 각 범위의 클릭 상태를 저장

// 클릭 이벤트 핸들러를 설정할 때 범위를 정의합니다.
CalendarBar[0].addEventListener('click', function () {
    toggleCalendarBar("company", 0);
});
CalendarBar[1].addEventListener('click', function () {
    toggleCalendarBar("individual", 1);
});
CalendarBar[2].addEventListener('click', function () {
    toggleCalendarBar("department", 2);
});

function toggleCalendarBar(range, index) {
    isCalendarBarVisible[index] = !isCalendarBarVisible[index];
    if (isCalendarBarVisible[index]) {
        $.ajax({
            type: 'get',
            url: '/orca/calendar/showCalendarBarContent',
            dataType: 'json',
            data: { range: range },
            success: function (response) {
                calendarBar[range] = response;  // response 데이터를 calendarBar에 저장
                //저장된 데이터를 객체화 해서 변수에 저장
                let combinedEvents = [].concat(...Object.values(calendarBar));
                renderCalendar(calendarElement, year, month, combinedEvents);
                todayText();
            },
            error: function (error) {
                console.error('CalendarBar 클릭 실패:', error);
            }
        });
    } else {
        calendarBar[range] = []; // 해당 범위 배열 초기화
        let combinedEvents = [].concat(...Object.values(calendarBar));
        renderCalendar(calendarElement, year, month, combinedEvents);
        todayText();
    }
}
//-- 캘린더 변수 설정
const calendarElement = document.getElementById('calendar');
const sidebarCalendarElement = document.getElementById('sidebarCalendar');

const date = new Date();
let year = date.getFullYear();
let month = date.getMonth();
let lastClickedview = null;
let viewCnt = 0;
let originalData = {};
let isEditCalendar = false;

function renderCalendar(calendarEl, year, month, events = []) {
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
        dateElement.className = 'date other-month after-month';
        dateElement.innerHTML = `<div class="number">${i}</div><div class="empty"></div>`;
        grid.appendChild(dateElement);
    }

    // 캘린더 바 추가
    events.forEach(event => {
        let startDate = new Date(event.startDate);
        let endDate = new Date(event.endDate);
        let range = event.range;

        while (startDate <= endDate) {
            let day = startDate.getDate();
            let dayElements = grid.querySelectorAll('.date');

            dayElements.forEach(el => {
                let dayNumber = parseInt(el.querySelector('.number').textContent);
                let isCurrentMonth = startDate.getFullYear() === year && startDate.getMonth() === month;
                let beforeMonth = startDate.getFullYear() === year && startDate.getMonth() === month - 1;
                let afterMonth = startDate.getFullYear() === year && startDate.getMonth() === month + 1;

                if ((isCurrentMonth && !el.classList.contains('other-month')) ||
                    (beforeMonth && el.classList.contains('before-month')) ||
                    (afterMonth && el.classList.contains('after-month'))) {

                    if (dayNumber === day) {
                        let eventBar = document.createElement('div');
                        eventBar.classList.add('event-bar');
                        eventBar.textContent = event.title;

                        if (range === 'company') {
                            eventBar.classList.add('company-bar');
                        } else if (range === 'department') {
                            eventBar.classList.add('department-bar');
                        } else if (range === 'individual') {

                        }
                        if (el.classList.contains('other-month')) {
                            eventBar.classList.add('other-bar');
                        }
                        el.querySelector('.empty').appendChild(eventBar);
                        // 클릭 이벤트 핸들러 추가
                        eventBar.addEventListener('click', function () {
                            handleEventBarClick(event, eventBar); // 이벤트 바 클릭 시 호출할 함수
                        });
                    }
                }
            });

            startDate.setDate(startDate.getDate() + 1);
        }
    });

    // 이벤트 바 클릭 시 호출할 함수
    // 일정 상세조회
    function handleEventBarClick(event, barDiv) {

        const viewDeleteButton = document.querySelector('.view-delete-button');
        // 새로운 이벤트 리스너 추가
        viewDeleteButton.removeEventListener('click', viewDeleteButton.currentListener);
        viewDeleteButton.currentListener = newDeleteEventListener;
        viewDeleteButton.addEventListener('click', newDeleteEventListener);

        originalData = {
            calendarNo: event.calendarNo,
            title: event.title,
            content: event.content,
            startDate: event.startDate,
            endDate: event.endDate,
            range: event.range,
            writerNo: event.writerNo
        };

        // 클릭한 이벤트 바에 대한 처리를 여기에 구현합니다.
        const titleElement = document.querySelector('.view-calendar-title');
        const enrollDateElement = document.querySelector('.view-calendar-enroll-date');
        const writerElement = document.querySelector('.view-calendar-writer');
        const partnameElement = document.querySelector('.view-calendar-partName');
        const contentElement = document.getElementById('viewEventContent');
        const startDateElement = document.getElementById('viewStartDate');
        const endDateElement = document.getElementById('viewEndDate');
        const rangeElement = document.getElementById('viewRange');

        titleElement.textContent = event.title;
        enrollDateElement.textContent = event.enrollDate;
        writerElement.textContent = event.writer;
        partnameElement.textContent = event.partName;
        contentElement.value = event.content;
        startDateElement.value = event.startDate;
        endDateElement.value = event.endDate;
        if (event.range === 'company') {
            rangeElement.value = '사내';
        } else if (event.range === 'department') {
            rangeElement.value = '부서';
        } else if (event.range === 'individual') {
            rangeElement.value = '개인';
        }

        viewCnt++;
        if (viewCnt % 2 == 0) {
            hideNewEventView();
        } else if (viewCnt % 2 != 0) {
            showNewEventView(barDiv);
        }

    }
    calendarEl.appendChild(grid);
}
const newDeleteEventListener = function () {
    let range = originalData.range;
    if (range === 'company') {
        alert("관련 부서에 문의바랍니다.")
    } else {
        if (originalData.writerNo != loginUserVo.empNo) {
            alert("권한이 부족합니다.");
        } else {
            const userResponse = confirm("정말로 삭제하시겠습니까?");
            if (userResponse) {
                deleteEvent(originalData);
            } else {

            }
        }
    }
};

//일정 삭제
function deleteEvent(originalData) {
    viewCnt++;
    hideNewEventView();
    let calendarNo = originalData.calendarNo;
    $.ajax({
        type: 'post',
        url: '/orca/calendar/deleteCalendar',
        dataType: 'json',
        data: { calendarNo: calendarNo },
        success: function (response) {
            if (response === 1) {
                alert("캘린더 삭제 성공!");
                isCalendarBarVisible[0] = !isCalendarBarVisible[0];
                isCalendarBarVisible[1] = !isCalendarBarVisible[1];
                isCalendarBarVisible[2] = !isCalendarBarVisible[2];
                toggleCalendarBar("company", 0);
                toggleCalendarBar("individual", 1);
                toggleCalendarBar("department", 2);
            } else {
                alert("캘린더 삭제 실패!");
            }

        },
        error: function (error) {
            console.log(error);
            alert("캘린더 삭제 실패!");
        }
    });
}
const viewEditButton = document.querySelector('.view-edit-button');
viewEditButton.addEventListener('click', function () {
    if (isEditCalendar === false) {
        editEvent(originalData);
    }
});
//일정 수정
function editEvent(Data) {
    if (originalData.writerNo != loginUserVo.empNo) {
        alert("작성자가 아닙니다.");
    } else {
        let range = Data.range;
        if (range === 'company') {
            alert("관련 부서에 문의바랍니다.")
        } else {
            isEditCalendar = true;
            // 일정 제목을 input 요소로 변환
            const titleSpan = document.querySelector('.view-calendar-title');
            const titleInput = document.createElement('input');
            titleInput.type = 'text';
            titleInput.value = Data.title; // 현재 텍스트 값을 가져와서 설정
            titleInput.classList.add('edit-title');
            titleSpan.parentNode.replaceChild(titleInput, titleSpan);

            // readOnly 속성 제거
            document.getElementById('viewEventContent').readOnly = false;
            document.getElementById('viewStartDate').readOnly = false;
            document.getElementById('viewEndDate').readOnly = false;

            // 공유 범위를 select 요소로 변환
            const viewRangeContainer = document.getElementById('viewRange');
            const selectElement = document.createElement('select');
            selectElement.id = 'edit-range';
            selectElement.name = 'range';

            // 옵션 추가
            const individualOption = document.createElement('option');
            individualOption.value = 'individual';
            individualOption.textContent = '개인';
            selectElement.appendChild(individualOption);

            const departmentOption = document.createElement('option');
            departmentOption.value = 'department';
            departmentOption.textContent = '부서';
            selectElement.appendChild(departmentOption);

            if (range === 'tedepartmentam') {
                selectElement.value = 'department';
            }

            // 기존 readOnly input 요소를 숨기고 select 요소를 추가
            viewRangeContainer.parentNode.replaceChild(selectElement, viewRangeContainer);

            //일정 수정 유효성 검사
            // 등록 버튼과 관련된 요소를 가져옵니다.
            const editSubmitBtn = document.querySelector('.view-edit-button');
            const editTitleInput = document.querySelector('.edit-title');
            const editContentInput = document.getElementById('viewEventContent');
            const editStartDateInput = document.getElementById('viewStartDate');
            const editEndDateInput = document.getElementById('viewEndDate');
            // 입력 필드에 변화가 생기면 체크하는 함수를 정의합니다.
            function checkInputs() {
                const eventTitle = editTitleInput;
                const eventContent = editContentInput;
                const startDateValue = editStartDateInput.value;
                const endDateValue = editEndDateInput.value;
                const startDate = new Date(editStartDateInput.value);
                const endDate = new Date(editEndDateInput.value);

                if (eventTitle.value.length > 13) {
                    eventTitle.value = eventTitle.value.substring(0, 13);
                    editSubmitBtn.classList.remove('opacity');
                    editSubmitBtn.disabled = true;
                    alert("글자수가 최대입니다.");
                }
                if (eventContent.value.length > 332) {
                    eventTitle.value = eventTitle.value.substring(0, 332);
                    editSubmitBtn.classList.remove('opacity');
                    editSubmitBtn.disabled = true;
                    alert("글자수가 최대입니다.");
                }

                if (eventTitle.value !== '' && startDateValue !== '' && endDateValue !== '') {

                    if (endDate < startDate) {
                        editSubmitBtn.classList.remove('opacity');
                        editSubmitBtn.disabled = true;
                        alert("시작일은 종료일보다 빨라야합니다.");
                    } else {
                        editSubmitBtn.classList.add('opacity');
                        editSubmitBtn.disabled = false;
                    }
                } else {
                    editSubmitBtn.classList.remove('opacity');
                    editSubmitBtn.disabled = true;
                }
            }

            // 입력 필드의 변화를 감지하여 checkInputs 함수를 호출합니다.
            editTitleInput.addEventListener('input', checkInputs);
            editContentInput.addEventListener('input', checkInputs);
            editStartDateInput.addEventListener('change', checkInputs);
            editEndDateInput.addEventListener('change', checkInputs);

            // 버튼 텍스트 변경 및 이벤트 리스너 추가
            const viewDeleteButton = document.querySelector('.view-delete-button');
            const viewCancelButton = document.querySelector('.view-cancel-button');
            const viewCalendarForm = document.querySelector('.view-calendar-form');

            viewCalendarForm.style.height = '540px';

            editSubmitBtn.textContent = '확인';
            viewDeleteButton.textContent = '취소';

            editSubmitBtn.removeEventListener('click', handleEditButtonClick);
            viewDeleteButton.removeEventListener('click', handleCancelButtonClick);
            viewDeleteButton.removeEventListener('click', newDeleteEventListener);

            editSubmitBtn.addEventListener('click', handleEditButtonClick);
            viewDeleteButton.addEventListener('click', handleCancelButtonClick);

            viewCancelButton.removeEventListener('click', newCancelEventListener);
            viewCancelButton.addEventListener('click', newCancelEventListener);

            function handleEditButtonClick() {
                const titleElement = document.querySelector('.edit-title').value;
                const contentElement = document.querySelector('#viewEventContent').value;
                const startDateElement = document.querySelector('#viewStartDate').value;
                const endDateElement = document.querySelector('#viewEndDate').value;
                const rangeElement = document.querySelector('#edit-range').value;

                let vo = {
                    calendarNo: Data.calendarNo
                }

                let updateCnt = 0;

                if (titleElement !== Data.title) {
                    vo.title = titleElement;
                    updateCnt++;
                }
                if (contentElement !== Data.content) {
                    vo.content = contentElement;
                    updateCnt++;
                }
                if (startDateElement !== Data.startDate) {
                    vo.startDate = startDateElement;
                    updateCnt++;
                }
                if (endDateElement !== Data.endDate) {
                    vo.endDate = endDateElement;
                    updateCnt++;
                }
                if (rangeElement !== Data.range) {
                    vo.range = rangeElement;
                    updateCnt++;
                }

                restoreOriginalState();

                if (updateCnt < 1) {
                    alert("수정된 내용이 없습니다.");
                } else {
                    $.ajax({
                        type: 'post',
                        url: '/orca/calendar/editCalendar',
                        dataType: 'json',
                        contentType: 'application/json',
                        data: JSON.stringify(vo),
                        success: function (response) {
                            if (response === 1) {
                                getCalendarDetail();
                            } else {
                                alert("캘린더 수정 실패!");
                            }
                        },
                        error: function (error) {
                            console.log(error);
                            alert("캘린더 수정 실패!");
                        }
                    })
                }
            }

            function handleCancelButtonClick() {
                // 취소 버튼 클릭 시 원래의 HTML 형식으로 복원
                restoreOriginalState();
            }

            function newCancelEventListener() {
                // 취소 버튼 클릭 시 원래의 HTML 형식으로 복원
                restoreOriginalState();
            }

            function restoreOriginalState() {
                viewCalendarForm.style.height = '518px';
                isEditCalendar = false;
                // 일정 제목을 다시 span 요소로 변환
                const editedTitle = document.querySelector('.edit-title');
                const titleSpan = document.createElement('span');
                titleSpan.classList.add('view-calendar-title');
                titleSpan.textContent = originalData.title;
                editedTitle.parentNode.replaceChild(titleSpan, editedTitle);

                // readOnly 속성 추가
                let content = document.getElementById('viewEventContent')
                let startDate = document.getElementById('viewStartDate')
                let endDate = document.getElementById('viewEndDate')

                content.readOnly = true;
                startDate.readOnly = true;
                endDate.readOnly = true;

                content.value = originalData.content;
                startDate.value = originalData.startDate;
                endDate.value = originalData.endDate;

                // select 요소를 다시 readOnly input 요소로 변경
                const readOnlyInput = document.createElement('input');
                readOnlyInput.type = 'text';
                readOnlyInput.id = 'viewRange';
                readOnlyInput.name = 'range';
                if (originalData.range === 'company') {
                    readOnlyInput.value = '사내';
                } else if (originalData.range === 'department') {
                    readOnlyInput.value = '부서';
                } else if (originalData.range === 'individual') {
                    readOnlyInput.value = '개인';
                }
                readOnlyInput.readOnly = true;

                selectElement.parentNode.replaceChild(readOnlyInput, selectElement);

                // 버튼 텍스트를 원래대로 변경
                viewEditButton.textContent = '수정';
                viewDeleteButton.textContent = '삭제';

                // 이전의 이벤트 리스너를 다시 추가
                viewEditButton.removeEventListener('click', handleEditButtonClick);
                viewDeleteButton.removeEventListener('click', handleCancelButtonClick);

                viewCancelButton.removeEventListener('click', newCancelEventListener);
                viewDeleteButton.addEventListener('click', newDeleteEventListener);

            }
        }
    }
}
function getCalendarDetail() {
    isCalendarBarVisible[0] = !isCalendarBarVisible[0];
    isCalendarBarVisible[1] = !isCalendarBarVisible[1];
    isCalendarBarVisible[2] = !isCalendarBarVisible[2];
    toggleCalendarBar("company", 0);
    toggleCalendarBar("individual", 1);
    toggleCalendarBar("department", 2);
    closeViewEvent();
}






//--------------------------------------------------------------------------------------



//일정 상세 조회 View
function showNewEventView(barDiv) {
    const form = document.getElementById('viewEventDetailsForm');
    form.style.display = 'block';
    const rect = barDiv.getBoundingClientRect();
    const formRect = form.getBoundingClientRect();

    let left = rect.right + 195;
    if (left + formRect.width > window.innerWidth + 300) {
        left = rect.left - formRect.width + 185;
    }

    let top = rect.top + 140;

    if (top < 0) {
        top = 0;
    }
    console.log("r.b : " + rect.bottom);
    console.log("f.h : " + formRect.height);
    console.log("w.i : " + window.innerHeight);


    if (rect.bottom + formRect.height > window.innerHeight + 200) {
        top = 367;
        console.log("top : " + top);
    }
    if (rect.bottom + formRect.height > 890 && rect.bottom + formRect.height < 1060) {
        top = 393;
        console.log("top2 : " + top);
    }
    console.log("top3 : " + top);
    form.style.left = `${left}px`;
    form.style.top = `${top}px`;
    form.scrollIntoView({ behavior: 'smooth', block: 'start' });
}
//일정 상세조회
function hideNewEventView() {
    const form = document.getElementById('viewEventDetailsForm');
    form.style.display = 'none';
}
// 일정 상세조회 드래그 앤 드랍
let isDraggingView = false;
let viewClickX = 0;
let viewClickY = 0;
let viewX = 0;
let viewY = 0;
//일정 상세 조회 close
function closeViewEvent() {
    if (isEditCalendar) {
    } else {
        viewCnt++;
        hideNewEventView();
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('viewEventDetailsForm');

    form.addEventListener('mousedown', function (event) {
        if (event.target.classList.contains('view-form-header') || event.target.closest('.view-form-header')) {
            isDraggingView = true;
            viewClickX = event.clientX;
            viewClickY = event.clientY;
            viewX = form.offsetLeft;
            viewY = form.offsetTop;
            document.addEventListener('mousemove', onDragView);
            document.addEventListener('mouseup', onStopDragView);
        }
    });
});

function onDragView(event) {
    if (isDraggingView) {
        const deltaX = event.clientX - viewClickX;
        const deltaY = event.clientY - viewClickY;
        const newFormX = viewX + deltaX;
        const newFormY = viewY + deltaY;

        const form = document.getElementById('viewEventDetailsForm');
        form.style.left = `${newFormX}px`;
        form.style.top = `${newFormY}px`;
    }
}

function onStopDragView() {
    isDraggingView = false;
    document.removeEventListener('mousemove', onDragForm);
    document.removeEventListener('mouseup', onStopDragForm);
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
        dateElement.className = 'side-date other-month before-month';
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
            dateElement.classList.add('side-today');
        }
        dateElement.innerHTML = `<div class="number">${date}</div>`;

        grid.appendChild(dateElement);
    }

    const totalCells = firstDay + lastDate;
    const nextDays = (7 - (totalCells % 7)) % 7;
    for (let i = 1; i <= nextDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.className = 'side-date other-month after-month';
        dateElement.innerHTML = `<div class="number">${i}</div>`;
        grid.appendChild(dateElement);
    }
    calendarEl.appendChild(grid);
}

function todayText() {
    let todayNumber = document.querySelector('.calendar-grid .date.today .number');
    let todayText = document.createElement('div');
    if (todayNumber) {
        todayText.textContent = 'today'; // 'D' 텍스트를 추가
        todayText.classList.add('today-text');
        todayNumber.appendChild(todayText);
    }
}

// 캘린더/사이드 캘린더 함수 호출
renderCalendar(calendarElement, year, month, []);
renderSideCalendar(sidebarCalendarElement, year, month);
todayText();

//월 이동 함수
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

    let combinedEvents = [].concat(...Object.values(calendarBar));
    renderCalendar(calendarElement, year, month, combinedEvents);
    renderSideCalendar(sidebarCalendarElement, year, month);

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

// 캘린더 주말 색 변경
const week = document.querySelectorAll('.day');
for (let i = 0; i < week.length; i++) {
    if (week[i].textContent === '일') {
        week[i].classList.add('redColor');
    } else if (week[i].textContent === '토') {
        week[i].classList.add('blueColor');
    }
}
//--------------------------------------------------------------------------------------------------
//일정 등록
// 일정 등록 ajax
function createCalendar() {
    const title = document.getElementById('eventTitle');
    const content = document.getElementById('eventContent');
    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');
    const range = document.getElementById('range');

    $.ajax({
        url: "/orca/calendar/createCalendar",
        method: "post",
        data: {
            title: title.value,
            content: content.value,
            startDate: startDate.value,
            endDate: endDate.value,
            range: range.value
        },
        success: function (response) {
            if (response === 1) {
                alert("일정이 등록되었습니다.");
                isCalendarBarVisible[0] = !isCalendarBarVisible[0];
                isCalendarBarVisible[1] = !isCalendarBarVisible[1];
                isCalendarBarVisible[2] = !isCalendarBarVisible[2];
                toggleCalendarBar("company", 0);
                toggleCalendarBar("individual", 1);
                toggleCalendarBar("department", 2);
                document.getElementById('eventTitle').value = "";
                document.getElementById('eventContent').value = "";
                document.getElementById('startDate').value = "";
                document.getElementById('endDate').value = "";
            } else {
                alert("일정 등록 실패");
            }
        },
        error: function (error) {
            alert("일정 등록 실패");
        }
    });
    submitNewEvent();
}

//일정 등록 화면 표시
let lastClickedDiv = null;
let KeyCnt = 0;
document.addEventListener('DOMContentLoaded', function () {
    const calendarDiv = document.getElementById('calendar');

    calendarDiv.addEventListener('click', function (event) {
        const clickedDiv = event.target.closest('.date');
        const clickedDivBar = event.target.closest('.event-bar');
        const clickDay = clickedDiv.querySelector('.number').textContent;

        if (clickedDiv && !clickedDivBar) {
            KeyCnt++;
            if (KeyCnt % 2 == 0) {
                hideNewEventForm();
            } else if (KeyCnt % 2 != 0) {
                showNewEventForm(clickedDiv, clickDay);
            }
        }
    });
    document.addEventListener('keydown', function (event) {
        if (event.ctrlKey && event.key === '.') {
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

function showNewEventForm(dateDiv, clickDay) {
    const startDate = document.querySelector('#startDate');

    let clickDate = new Date(year, month, clickDay);
    const newYear = clickDate.getFullYear();
    const newMonth = (clickDate.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더함
    const newDay = clickDate.getDate().toString().padStart(2, '0');

    // 날짜 형식을 'YYYY-MM-DD'로 설정
    const formattedDate = newYear + '-' + newMonth + '-' + newDay;
    startDate.value = formattedDate;

    const form = document.getElementById('newEventForm');
    form.style.display = 'block';
    const rect = dateDiv.getBoundingClientRect();
    const formRect = form.getBoundingClientRect();

    let left = rect.right + 195;
    if (left + formRect.width > window.innerWidth + 300) {
        left = rect.left - formRect.width + 185;
    }
    let top = rect.top + 145;
    if (top > 200) {
        top = 399.40000915527344;
    }
    form.style.left = `${left}px`;
    form.style.top = `${top}px`;
    form.scrollIntoView({ behavior: 'smooth', block: 'start' });
}

function hideNewEventForm() {
    const form = document.getElementById('newEventForm');
    form.style.display = 'none';
}

function closeFormEvent() {
    KeyCnt++;
    hideNewEventForm();
}

function submitNewEvent() {
    KeyCnt++;
    if (KeyCnt % 2 == 0) {
        hideNewEventForm();
    } else if (KeyCnt % 2 != 0) {
        showNewEventForm();
    }
}

//일정 등록 화면 드래그 앤 드랍
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



//일정 등록 유효성 검사
// 등록 버튼과 관련된 요소를 가져옵니다.
const submitBtn = document.getElementById('submitBtn');
const eventTitleInput = document.getElementById('eventTitle');
const eventContentInput = document.getElementById('eventContent');
const startDateInput = document.getElementById('startDate');
const endDateInput = document.getElementById('endDate');

let alertShown = false; // 알람 메시지가 이미 한 번 떴는지를 체크하는 변수

// 입력 필드에 변화가 생기면 체크하는 함수를 정의합니다.
function checkInputs() {
    const eventTitle = eventTitleInput.value;
    const eventContent = eventContentInput.value;
    const startDateValue = startDateInput.value;
    const endDateValue = endDateInput.value;
    const startDate = new Date(startDateValue);
    const endDate = new Date(endDateValue);

    // 초기화
    submitBtn.classList.remove('opacity');
    submitBtn.disabled = false;
    submitBtn.style.backgroundColor = '#6eadff';

    // 제목 길이 체크
    if (eventTitle.length > 13) {
        eventTitleInput.value = eventTitle.substring(0, 13);
        alert("제목은 최대 13글자까지 입력 가능합니다.");
    }

    // 내용 길이 체크
    if (eventContent.length > 332) {
        eventContentInput.value = eventContent.substring(0, 332);
        alert("내용은 최대 332글자까지 입력 가능합니다.");
    }

    // 날짜 유효성 체크
    if (startDateValue !== '' && endDateValue !== '') {
        if (endDate < startDate) {
            if (!alertShown) {
                alert("시작일은 종료일보다 빨라야 합니다.");
                alertShown = true; // 알람 메시지 플래그 설정
            }
            submitBtn.disabled = true;
            submitBtn.style.backgroundColor = '#ccc';
        }
    } else {
        submitBtn.disabled = true;
        submitBtn.style.backgroundColor = '#ccc';
    }
}

// 입력 필드의 변화를 감지하여 checkInputs 함수를 호출합니다.
eventTitleInput.addEventListener('input', checkInputs);
eventContentInput.addEventListener('input', checkInputs);
startDateInput.addEventListener('change', function () {
    checkInputs();
    alertShown = false; // 알람 메시지 플래그 초기화
});
endDateInput.addEventListener('change', function () {
    checkInputs();
    alertShown = false; // 알람 메시지 플래그 초기화
});

// 폼이 처음 로드될 때도 한 번 호출하여 초기 상태를 설정합니다.
checkInputs();


//---------------------------------------------------------------------------------------------------

//연도 이동
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
    let combinedEvents = [].concat(...Object.values(calendarBar));
    renderCalendar(calendarElement, year, month, combinedEvents);
    renderSideCalendar(sidebarCalendarElement, year, month);
    todayText();
}

let calendarBars = document.querySelectorAll('.empty .event-bar');
// 각 calendarBars 요소에 클릭 이벤트 핸들러 추가
calendarBars.forEach(function (bar) {
    bar.addEventListener('click', function (event) {
        event.stopPropagation();
    });
});
//--------------------------------------------------------------














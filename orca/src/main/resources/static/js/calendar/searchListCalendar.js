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

const sidebarCalendarElement = document.getElementById('sidebarCalendar');
const date = new Date();
let year = date.getFullYear();
let month = date.getMonth();

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
    renderSideCalendar(sidebarCalendarElement, year, month);
    todayText();
}

// 캘린더 검색
const page = 1;
const urlParams = new URLSearchParams(window.location.search);
let keyword = urlParams.get('keyword');

searchListCalendarPage(page, keyword);

function searchListCalendarPage(page, keyword) {
    const pagination = {};
    let totalPage = document.querySelector('.totalPage');
    console.log(keyword);
    $.ajax({
        type: 'get',
        url: '/orca/calendar/searchListCalendarPage',
        data: {
            page: page,
            keyword: keyword
        },
        dataType: 'json',
        success: function (response) {
            Object.assign(pagination, response);
            if (pagination.totalRecordCount > 0) {
                while (totalPage.firstChild) {
                    totalPage.removeChild(totalPage.firstChild);
                }

                if (pagination.existPrevPage) {
                    let existPrevPage = document.createElement('a');
                    existPrevPage.textContent = '이전';
                    totalPage.appendChild(existPrevPage);
                    existPrevPage.addEventListener('click', function () {
                        searchListCalendarPage(pagination.startPage - 1, keyword);
                    });
                }

                for (let i = pagination.startPage; i <= pagination.endPage; i++) {
                    let aPage = document.createElement('a');
                    aPage.textContent = i;
                    if (i == page) {
                        aPage.classList.add('current-page');
                    }
                    totalPage.appendChild(aPage);
                    aPage.addEventListener('click', function () {
                        searchListCalendarPage(i, keyword);
                    });
                }

                if (pagination.existNextPage) {
                    let existNextPage = document.createElement('a');
                    existNextPage.textContent = '이후';
                    totalPage.appendChild(existNextPage);
                    existNextPage.addEventListener('click', function () {
                        searchListCalendarPage(pagination.endPage + 1, keyword);
                    });
                }
                searchListCalendarData(pagination, keyword);
            } else {
                let pageFooter = $('.pageFooter');
                let keywordValue = $('<div>');
                let notFound = $('<div>');
                let notFoundText = $('<div>');

                notFoundText.addClass('notFoundText');

                keywordValue.text("'" + keyword + "'");
                keywordValue.addClass('keyword');
                notFound.text("에 대한 검색결과 입니다. 0건");

                notFoundText.append(keywordValue);
                notFoundText.append(notFound);

                let instructions = $('<p>');
                instructions.append('- 단어의 철자가 정확한지 확인해 보세요.<br>');
                instructions.append('- 한글을 영어로 혹은 영어를 입력했는지 확인해 보세요.<br>');
                instructions.append('- 검색어의 단어 수를 줄이거나, 보다 일반적인 검색어로 다시 검색해 보세요.<br>');
                instructions.append('- 두 단어 이상의 검색어인 경우, 띄어쓰기를 확인해 보세요.');
                instructions.addClass('check-list');

                pageFooter.append(notFoundText);
                pageFooter.append(instructions);
            }
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function searchListCalendarData(pagination, keyword) {

    let startNum = pagination.startNum;
    let endNum = pagination.endNum;
    $.ajax({
        type: 'get',
        url: '/orca/calendar/searchListCalendarData',
        data: {
            startNum: startNum,
            endNum: endNum,
            keyword: keyword
        },
        dataType: 'json',
        success: function (response) {
            console.log(response);
            let tbody = $('.custom-table tbody');
            tbody.empty(); // 기존의 내용을 모두 지웁니다.

            // 각 데이터를 테이블에 추가합니다.
            $.each(response, function (index, item) {
                let calendarNo = item.calendarNo;
                let row = $('<tr>');
                let range = item.range;
                if (range === 'company') {
                    range = '사내';
                } else if (range === 'individual') {
                    range = '개인';
                } else if (range === 'department') {
                    range = '부서';
                }

                row.append($('<td>').text(item.title));
                row.append($('<td>').text(item.content).addClass('content-text')); // 내용 부분을 텍스트 오버라이드합니다.
                row.append($('<td>').text(item.writer));
                row.append($('<td>').text(item.startDate));
                row.append($('<td>').text(item.endDate));
                row.append($('<td>').text(item.enrollDate));
                row.append($('<td>').text(range));
                tbody.append(row);
                row.on('click', function () {
                    detailCalendar(calendarNo);
                });

            });
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

const mainDiv = document.querySelector('#content');

//사내 캘린더 상세조회
//상세 조회로 꺼내온 데이터를 담을 객체
let originalData = {};
//수정 여부 확인 변수 설정
let isEditCalendar = false;

function detailCalendar(calendarNo) {
    console.log(calendarNo);
    $.ajax({
        type: 'get',
        url: '/orca/calendar/searchDetailCalendar',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            getCalendarByOne(calendarNo);
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function getCalendarByOne(calendarNo) {
    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/getCalendarByOne',
        data: {
            calendarNo: calendarNo
        },
        dataType: 'json',
        success: function (response) {
            console.log(response);
            //캘린더 삭제 버튼 이벤트 추가
            const viewDeleteButton = document.querySelector('.view-delete-button');
            // 새로운 이벤트 리스너 추가
            viewDeleteButton.removeEventListener('click', viewDeleteButton.currentListener);
            viewDeleteButton.currentListener = newDeleteEventListener;
            viewDeleteButton.addEventListener('click', newDeleteEventListener);

            //캘린더 수정 버튼 이벤트 추가

            const viewEditButton = document.querySelector('.view-edit-button');
            viewEditButton.addEventListener('click', function () {
                let range = originalData.range;
                if (range === 'company') {
                    alert("관련 부서에 문의바랍니다.")
                } else {
                    if (originalData.writerNo != loginUserVo.empNo) {
                        alert("작성자가 아닙니다.");
                    } else {
                        if (isEditCalendar === false) {
                            editEvent(originalData);
                        }
                    }
                }
            });

            const titleElement = document.querySelector('.view-calendar-title');
            const enrollDateElement = document.querySelector('.view-calendar-enroll-date');
            const writerElement = document.querySelector('.view-calendar-writer');
            const partnameElement = document.querySelector('.view-calendar-partName');
            const contentElement = document.getElementById('viewEventContent');
            const startDateElement = document.getElementById('viewStartDate');
            const endDateElement = document.getElementById('viewEndDate');
            const rangeElement = document.getElementById('viewRange');

            originalData = {
                calendarNo: response.calendarNo,
                title: response.title,
                content: response.content,
                startDate: response.startDate,
                endDate: response.endDate,
                writerNo: response.writerNo,
                range: response.range
            };

            titleElement.textContent = response.title;
            enrollDateElement.textContent = response.enrollDate;
            writerElement.textContent = response.writer;
            partnameElement.textContent = response.partName;
            contentElement.value = response.content;
            startDateElement.value = response.startDate;
            endDateElement.value = response.endDate;
            if (response.range === 'company') {
                rangeElement.value = '사내';
            } else if (response.range === 'department') {
                rangeElement.value = '부서';
            } else if (response.range === 'individual') {
                rangeElement.value = '개인';
            }
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}
//사내 캘린더 삭제
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

function deleteEvent(originalData) {
    let calendarNo = originalData.calendarNo;
    $.ajax({
        type: 'post',
        url: '/orca/calendar/deleteCalendar',
        dataType: 'json',
        data: { calendarNo: calendarNo },
        success: function (response) {
            if (response === 1) {
                alert("캘린더 삭제 성공!");
                window.location.href = window.location.href;
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

// 사내 캘린더 수정
function editEvent(Data) {
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

    if (Data.range === 'department') {
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

    viewCalendarForm.style.height = '560px';

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
                        alert("캘린더 수정 성공!");
                        detailCalendar(vo.calendarNo);
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
        const viewEditButton = document.querySelector('.view-edit-button');
        const viewDeleteButton = document.querySelector('.view-delete-button');

        viewCalendarForm.style.height = '550px';
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

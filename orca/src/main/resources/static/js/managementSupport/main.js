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

const approval = document.querySelector('.approval');
const approvalList = document.querySelector('.approval-list');
const calnedar = document.querySelector('.calnedar');
const calnedarLink = document.querySelector('.calendar-link');

let sidebarCnt = [0, 0];

approval.addEventListener('click', function () {
    sidebarCnt[0]++;
    if (sidebarCnt[0] % 2 == 0) {
        approvalList.style.display = '';
    } else {
        approvalList.style.display = 'none';
    }
});
calnedar.addEventListener('click', function () {
    sidebarCnt[1]++;
    if (sidebarCnt[1] % 2 == 0) {
        calnedarLink.style.display = '';
    } else {
        calnedarLink.style.display = 'none';
    }
});

const toggleElements = document.querySelectorAll('.toggle');
let selectedElement = null;

toggleElements.forEach(function (element) {
    element.addEventListener('click', function () {
        // 기존에 선택된 요소의 색상을 원래대로 돌리기
        if (selectedElement && selectedElement !== element) {
            selectedElement.textContent = selectedElement.textContent.replace('◾', '◽');
        }
        // 새로 선택된 요소의 색상을 파란색으로 변경
        if (element !== selectedElement) {
            element.textContent = element.textContent.replace('◽', '◾');
            selectedElement = element;
        }
    });
});

//캘린더 작성
function createCalendarCompany() {
    const submit = document.getElementById('submit');
    const eventTitle = document.getElementById('title');
    const eventDescription = document.getElementById('content');
    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');

    // 실시간 유효성 검사
    eventTitle.addEventListener('input', function () {
        if (eventTitle.value.length >= 13) {
            alert('제목은 13글자 미만이어야 합니다.');
            eventTitle.value = eventTitle.value.substring(0, 12);
        }
    });

    eventDescription.addEventListener('input', function () {
        if (eventDescription.value.length >= 332) {
            alert('내용은 332글자 미만이어야 합니다.');
            eventDescription.value = eventDescription.value.substring(0, 331);
        }
    });

    submit.addEventListener('click', function (event) {
        // 날짜 유효성 검사
        if (new Date(startDate.value) > new Date(endDate.value)) {
            event.preventDefault();
            alert('시작일은 종료일보다 이전이어야 합니다.');
        } else {
            const title = document.getElementById('title');
            const content = document.getElementById('content');
            const startDate = document.getElementById('startDate');
            const endDate = document.getElementById('endDate');
            $.ajax({
                type: 'post',
                dataType: 'json',
                url: '/orca/managementSupport/createCalendarCompany',
                data: {
                    title: title.value,
                    content: content.value,
                    startDate: startDate.value,
                    endDate: endDate.value
                },
                success: function (response) {
                    if (response == 1) {
                        alert("캘린더 삭제 성공!");
                        document.getElementById('title').value = '';
                        document.getElementById('content').value = '';
                        document.getElementById('startDate').value = '';
                        document.getElementById('endDate').value = '';
                    } else {
                        alert("캘린더 삭제 실패");
                    }
                },
                error: function (error) {
                    alert("캘린더 삭제 실패");
                }
            });
        }
    });


}

const mainDiv = document.querySelector('.main');
const calnedarWrite = document.querySelector('.calendar-wirte');
calnedarWrite.addEventListener('click', function () {
    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/createCalendar',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            createCalendarCompany();
        },
        error: function (error) {
            console.error('유저 데이터 로드 실패', error);
        }
    });
});

//캘린더 조회
const calendarList = document.querySelector('.calendar-list');
calendarList.addEventListener('click', function () {
    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/listCalendar',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            let page = 1;
            listCalendarPage(page);
        },
        error: function (error) {
            console.error('유저 데이터 로드 실패', error);
        }
    });
});
function listCalendarPage(page) {
    const pagination = {};
    let totalPage = document.querySelector('.totalPage');

    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/listCalendarPage',
        data: {
            page: page
        },
        dataType: 'json',
        success: function (response) {
            Object.assign(pagination, response);

            while (totalPage.firstChild) {
                totalPage.removeChild(totalPage.firstChild);
            }

            if (pagination.existPrevPage) {
                let existPrevPage = document.createElement('a');
                existPrevPage.textContent = '이전';
                totalPage.appendChild(existPrevPage);
                existPrevPage.addEventListener('click', function () {
                    listCalendarPage(pagination.startPage - 1);
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
                    listCalendarPage(i);
                });
            }

            if (pagination.existNextPage) {
                let existNextPage = document.createElement('a');
                existNextPage.textContent = '이후';
                totalPage.appendChild(existNextPage);
                existNextPage.addEventListener('click', function () {
                    listCalendarPage(pagination.endPage + 1);
                });
            } 

            listCalendarData(pagination);
        },
        error: function (error) {
            console.error('유저 데이터 로드 실패', error);
        }
    });
}


function listCalendarData(pagination) {

    console.log(pagination);

    let startNum = pagination.startNum;
    let endNum = pagination.endNum;

    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/listCalendarData',
        data: {
            startNum: startNum,
            endNum: endNum
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

                row.append($('<td>').text(item.title));
                row.append($('<td>').text(item.content).addClass('content-text')); // 내용 부분을 텍스트 오버라이드합니다.
                row.append($('<td>').text(item.writer));
                row.append($('<td>').text(item.startDate));
                row.append($('<td>').text(item.endDate));
                row.append($('<td>').text(item.enrollDate));
                tbody.append(row);
                row.on('click', function () {
                    detailCalendar(calendarNo);
                });
            });
        },
        error: function (error) {
            console.error('유저 데이터 로드 실패', error);
        }
    });
}

function detailCalendar(calendarNo) {
    console.log(calendarNo);
    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/detailCalendar',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            listCalendarPage();
        },
        error: function (error) {
            console.error('유저 데이터 로드 실패', error);
        }
    });
}
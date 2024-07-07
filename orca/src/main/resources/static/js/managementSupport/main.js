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
        if (element === selectedElement) {
            element.textContent = element.textContent.replace('◾', '◽');
            selectedElement = null;
        } else {
            element.textContent = element.textContent.replace('◽', '◾');
            selectedElement = element;
        }
    });
});


function setupEventHandlers() {
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
let alnedarWriteCnt = 0;
calnedarWrite.addEventListener('click', function () {
    if (alnedarWriteCnt % 2 == 0) {
        $.ajax({
            type: 'get',
            url: '/orca/managementSupport/createCalendar',
            dataType: 'html',
            success: function (response) {
                while (mainDiv.firstChild) {
                    mainDiv.removeChild(mainDiv.firstChild);
                }
                mainDiv.innerHTML = response;
                setupEventHandlers();
            },
            error: function (error) {
                console.error('유저 데이터 로드 실패', error);
            }
        });
    } else {
        while (mainDiv.firstChild) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
        }
    }
    alnedarWriteCnt++;
});

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
    if (!profileDetail.classList.contains('hidden')) {
        $.ajax({
            url: "/orca/user/getUserVo",
            method: 'get',
            dataType: 'json',
            success: function (response) {

                let empNo = document.querySelector('#empNo');
                let partName = document.querySelector('#partName');
                let position = document.querySelector('#position');
                let phone = document.querySelector('#phone');
                let extensionCall = document.querySelector('#extensionCall');
                let email = document.querySelector('#email');


                empNo.textContent = '사번 : ' + response.empNo;
                partName.textContent = '부서명 : ' + response.partName;
                position.textContent = '직급 : ' + response.nameOfPosition;
                phone.textContent = '전화번호 : ' + response.phone;
                extensionCall.textContent = '내선번호 : ' + response.extensionCall;
                email.textContent = '이메일 : ' + response.email;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
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
    const userResponse = confirm("정말로 종료하시겠습니까?");
    if (userResponse) {
        $.ajax({
            url: "/orca/user/logout",
            method: 'get',
            dataType: 'json',
            success: function (response) {
                if (response === 1) {
                    alert('로그아웃 되었습니다.');
                    window.location.href = "/orca/user/login";
                } else {
                    alert('로그아웃 실패');
                }
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

let changePwd = document.querySelector("#change-password")
changePwd.addEventListener('click', function () {
    window.location.href = "/orca/user/changePassword";
})

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

//사내 캘린더 작성
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
            console.error('데이터 로드 실패', error);
        }
    });
});

//사내 캘린더 전체 조회
const calendarList = document.querySelector('.calendar-list');
calendarList.addEventListener('click', function () {
    getCalendarList();
});

function getCalendarList() {
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
            searchListCalendar();
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

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
            console.error('데이터 로드 실패', error);
        }
    });
}

function listCalendarData(pagination) {

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
            console.error('데이터 로드 실패', error);
        }
    });
}

//사내 캘린더 상세조회
//상세 조회로 꺼내온 데이터를 담을 객체
let originalData = {};
//수정 여부 확인 변수 설정
let isEditCalendar = false;

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
                if (isEditCalendar === false) {
                    editEvent(originalData);
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
                writerNo: response.writerNo
            };

            titleElement.textContent = response.title;
            enrollDateElement.textContent = response.enrollDate;
            writerElement.textContent = response.writer;
            partnameElement.textContent = response.partName;
            contentElement.value = response.content;
            startDateElement.value = response.startDate;
            endDateElement.value = response.endDate;
            rangeElement.value = '사내';
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}
//사내 캘린더 삭제
const newDeleteEventListener = function () {
    const userResponse = confirm("정말로 삭제하시겠습니까?");
    if (userResponse) {
        deleteEvent(originalData);
    }
};

function deleteEvent(originalData) {
    let calendarNo = originalData.calendarNo;
    $.ajax({
        type: 'post',
        url: '/orca/managementSupport/deleteCalendar',
        dataType: 'json',
        data: { calendarNo: calendarNo },
        success: function (response) {
            if (response === 1) {
                alert("캘린더 삭제 성공!");
                getCalendarList();
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
        restoreOriginalState();

        if (updateCnt < 1) {
            alert("수정된 내용이 없습니다.");
        } else {
            $.ajax({
                type: 'post',
                url: '/orca/managementSupport/editCalendar',
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

function searchListCalendar() {
    $(document).ready(function () {
        // 폼 제출 시 Ajax로 데이터를 전송한다.
        $('#calendar-search-form').on('submit', function (event) {
            console.log("입력됨");
            event.preventDefault();
            // 아래 변수에 담아주는 건 뭐냐?
            // 사용자가 form에 입력한 데이터를 수집하여 이를 Ajax 요청으로 서버에 전송하기 위해 작성되었다.
            const keyword = $('.search-text').val();
            $.ajax({
                // 위에 form에서 이미 post로 /messenger/write에 보내주고 있다.
                // 그렇기에, form의 action 속성 값을 사용한다.
                url: $(this).attr('action'),
                method: "get",
                dataType: 'html',
                success: (response) => {
                    console.log(response);
                    while (mainDiv.firstChild) {
                        mainDiv.removeChild(mainDiv.firstChild);
                    }
                    mainDiv.innerHTML = response;
                    let page = 1;
                    searchListCalendarPage(page, keyword);
                    searchListCalendar();
                },
                error: (xhr, status, error) => {
                    console.log(error);
                }
            });
        });
    });
}

function searchListCalendarPage(page, keyword) {
    const pagination = {};
    let totalPage = document.querySelector('.totalPage');
    console.log(keyword);
    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/searchListCalendarPage',
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
    console.log(keyword);
    $.ajax({
        type: 'get',
        url: '/orca/managementSupport/searchListCalendarData',
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
            console.error('데이터 로드 실패', error);
        }
    });
}

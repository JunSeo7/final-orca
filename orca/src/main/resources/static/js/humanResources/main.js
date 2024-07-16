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

const mainDiv = document.querySelector('.main');
const employeeRegistration = document.querySelector('.employee-registration');
employeeRegistration.addEventListener('click', function () {
    $.ajax({
        type: 'get',
        url: '/orca/humanResources/showEmployeeRegistration',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            getSelects();
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
});

const showVacationCode = document.querySelector('.showVacationCode');
showVacationCode.addEventListener('click', function () {
    $.ajax({
        type: 'get',
        url: '/orca/vacationRef/VCode',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            getSelects();
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });

    $.ajax({
            url: "http://127.0.0.1:8080/orca/re/vacation",
            method: "get",
            success: function(data) {
                const x = document.querySelector("#vacationCodesTable tbody");
                console.log(x);
                let str = "";

                for(let i = 0; i < data.length; i++) {
                    str += "<tr>";
                    str += "<td><input type='checkbox' class='rowCheckbox'></td>";
                    str += "<td>" + data[i].vacationCode + "</td>";
                    str += "<td>" + data[i].vacationName + "</td>";
                    str += "</tr>";
                }
                x.innerHTML = str;
            },
            error: function(error) {
                console.error("데이터를 가져오는데 실패했습니다.", error);
            }
        });
});


function getSelects() {
    $.ajax({
        type: 'get',
        url: '/orca/humanResources/getSelects',
        dataType: 'json',
        success: function (response) {
            console.log(response);  // 받아온 데이터를 콘솔에 출력하여 확인

            // 부서 데이터를 추출하여 옵션으로 추가
            const deptSelect = $('#dept');
            response[0].forEach(dept => {
                const option = $('<option></option>');
                option.val(dept.deptCode);
                option.text(dept.partName);
                deptSelect.append(option);
            });

            // 팀 데이터를 추출하여 옵션으로 추가
            const teamSelect = $('#team');
            response[1].forEach(team => {
                const option = $('<option></option>');
                option.val(team.teamCode);
                option.text(team.teamName);
                teamSelect.append(option);
            });

            // 직급 데이터를 추출하여 옵션으로 추가
            const positionSelect = $('#position');
            response[2].forEach(position => {
                const option = $('<option></option>');
                option.val(position.positionCode);
                option.text(position.nameOfPosition);
                positionSelect.append(option);
            });
            validation();
            inputEmployeeRegistration();
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function validation() {
    const ssnInput = document.getElementById('social-security-no');

    ssnInput.addEventListener('input', function () {
        let value = this.value.replace(/\D/g, ''); // 숫자 이외의 문자 제거

        // 앞자리 6글자, 뒷자리 7글자로 자르기
        let formattedValue = value.substring(0, 6) + '-' + value.substring(6, 13);

        // 입력 필드에 형식에 맞게 출력
        this.value = formattedValue;
    });
}

// 사원 등록
function inputEmployeeRegistration() {
    $(document).ready(function () {
        // 폼 제출 시 Ajax로 데이터를 전송한다.
        $('#employee-registration').on('submit', function (event) {
            console.log("입력됨");
            event.preventDefault();

            // FormData 객체 생성
            const formData = new FormData();

            // 사용자가 입력한 데이터를 FormData에 추가한다.
            formData.append('name', $('#name').val());
            formData.append('positionCode', $('#position').val());
            formData.append('deptCode', $('#dept').val());
            formData.append('teamCode', $('#team').val());
            formData.append('gender', $('#gender').val());
            formData.append('socialSecurityNo', $('#social-security-no').val().replace(/-/g, ''));
            formData.append('password', $('#password').val());
            formData.append('phone', $('#phone').val());
            formData.append('extensionCall', $('#ext').val());
            formData.append('email', $('#email').val()); // 이메일에서 '-' 제거
            formData.append('address', $('#address').val());
            formData.append('height', $('#height').val());
            formData.append('weight', $('#weight').val());
            formData.append('bloodType', $('#bloodType').val());
            formData.append('religion', $('#religion').val());
            formData.append('salary', $('#salary').val());

            const bankName = $('#bankName').val();
            let bankNumber = $('#bankNumber').val();
            bankNumber = bankName + ' ' + bankNumber;

            formData.append('bankNumber', bankNumber);

            // 이미지 파일 추가
            const imageFile = $('#image')[0].files[0];
            if (imageFile) {
                formData.append('image', imageFile);
            }
            // 필수 입력 사항 검증
            if (!$('#gender').val()) {
                alert('성별을 선택해주세요.');
                return;
            }
            if (!$('#bloodType').val()) {
                alert('혈액형을 선택해주세요.');
                return;
            }
            if (!$('#bankName').val()) {
                alert('은행을 선택해주세요.');
                return;
            }

            // Ajax 요청 전송
            $.ajax({
                url: $(this).attr('action'), // 폼의 action 속성 값 사용
                method: "post",
                data: formData,
                processData: false,  // 필수: FormData를 문자열로 변환하지 않음
                contentType: false,  // 필수: 기본 content-type 설정 방지
                success: function (data) {
                    console.log('서버 응답:', data);
                    if (data === 1) {
                        alert("사원 등록 성공");
                        // 폼 필드 초기화
                        $('#name').val('');
                        $('#position').val('');
                        $('#dept').val('');
                        $('#team').val('');
                        $('#gender').val('');
                        $('#social-security-no').val('');
                        $('#password').val('');
                        $('#phone').val('');
                        $('#ext').val('');
                        $('#email').val('');
                        $('#address').val('');
                        $('#height').val('');
                        $('#weight').val('');
                        $('#bloodType').val('');
                        $('#religion').val('');
                        $('#salary').val('');
                        $('#bankName').val('');
                        $('#bankNumber').val('');
                        $('#image').val(''); // 파일 필드 초기화
                    } else {
                        alert("사원 등록 실패");
                    }
                },
                error: function (xhr, status, error) {
                    console.error('데이터 전송 중 오류 발생:', error);
                }
            });
        });
    });
}

const employeeList = document.querySelector('.employee-list');
employeeList.addEventListener('click', function () {
    let page = 1;
    $.ajax({
        type: 'get',
        url: '/orca/humanResources/showEmployeeList',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            listEmployeePage(page);
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
});

function listEmployeePage(page) {
    const pagination = {};
    let totalPage = document.querySelector('.totalPage');

    $.ajax({
        type: 'get',
        url: '/orca/humanResources/listEmployeePage',
        dataType: 'json',
        data: {
            page: page
        },
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
                    listEmployeePage(pagination.startPage - 1);
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
                    listEmployeePage(i);
                });
            }

            if (pagination.existNextPage) {
                let existNextPage = document.createElement('a');
                existNextPage.textContent = '이후';
                totalPage.appendChild(existNextPage);
                existNextPage.addEventListener('click', function () {
                    listEmployeePage(pagination.endPage + 1);
                });
            }
            listEmployeeData(pagination);
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function listEmployeeData(pagination) {

    let startNum = pagination.startNum;
    let endNum = pagination.endNum;

    $('.employee-container').empty();

    $.ajax({
        type: 'get',
        url: '/orca/humanResources/listEmployeeData',
        data: {
            startNum: startNum,
            endNum: endNum
        },
        dataType: 'json',
        success: function (data) {
            data.forEach(function(employee) {
                let imageUrl = '/upload/user/' + employee.imgChangeName;
                let employeeDiv = `
                    <div class="employee-info">
                        <img src="${imageUrl}" alt="img" class="employee-profile">
                        <div class="employee-name">${employee.name}</div>(<div class="employee-position">${employee.nameOfPosition.trim()}</div>/<div class="employee-department">${employee.partName}</div>)
                    </div>`;
                $('.employee-container').append(employeeDiv);
            });
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}
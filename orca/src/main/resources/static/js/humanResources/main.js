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
            // createCalendarCompany();
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
});

// 사원 등록
$(document).ready(function () {
    // 폼 제출 시 Ajax로 데이터를 전송한다.
    $('#employee-registration').on('submit', function (event) {
        console.log("입력됨");
        event.preventDefault();
        // 아래 변수에 담아주는 건 뭐냐?
        // 사용자가 form에 입력한 데이터를 수집하여 이를 Ajax 요청으로 서버에 전송하기 위해 작성되었다.
        const name = $('#name').val();
        const positionCode = $('#position').val();
        const deptCode = $('#dept').val();
        const teamCode = $('#team').val();
        const gender = $('#gender').val();
        const socialSecurityNo = $('#social-security-no').val();
        const password = $('#password').val();
        const phone = $('#phone').val();
        const extensionCall = $('#ext').val();
        const email = $('#email').val();
        const address = $('#address').val();
        const height = $('#height').val();
        const weight = $('#weight').val();
        const bloodType = $('#bloodType').val();
        const religion = $('#religion').val();
        const salary = $('#salary').val();
        const bankNumber = $('#bankNumber').val();

        console.log('name:', name);
        console.log('positionCode:', positionCode);
        console.log('deptCode:', deptCode);
        console.log('teamCode:', teamCode);
        console.log('gender:', gender);
        console.log('socialSecurityNo:', socialSecurityNo);
        console.log('password:', password);
        console.log('phone:', phone);
        console.log('extensionCall:', extensionCall);
        console.log('email:', email);
        console.log('address:', address);
        console.log('height:', height);
        console.log('weight:', weight);
        console.log('bloodType:', bloodType);
        console.log('religion:', religion);
        console.log('salary:', salary);
        console.log('bankNumber:', bankNumber);


        $.ajax({
            // 위에 form에서 이미 post로 /messenger/write에 보내주고 있다.
            // 그렇기에, form의 action 속성 값을 사용한다.
            url: $(this).attr('action'),
            method: "post",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                name: name,
                positionCode: positionCode,
                deptCode: deptCode,
                teamCode: teamCode,
                gender: gender,
                socialSecurityNo: socialSecurityNo,
                password: password,
                phone: phone,
                extensionCall: extensionCall,
                email: email,
                address: address,
                height: height,
                weight: weight,
                bloodType: bloodType,
                religion: religion,
                salary: salary,
                bankNumber: bankNumber
            }),
            success: function (data) {
                console.log('Server response:', data);
            },
            error: function (xhr, status, error) {
                console.error('Error sending data:', error);
            }
        });
    });
});

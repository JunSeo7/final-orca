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
            bankNumber = bankName + '-' + bankNumber;

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
    showEmployeeList();
});

function showEmployeeList() {
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
}

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
    let employeeContainer = document.querySelector('.employee-container');
    employeeContainer.innerHTML = '';
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
            data.forEach(function (employee) {
                let imageUrl = '/upload/user/' + employee.imgChangeName;

                // employee-info 요소 생성
                let employeeDiv = document.createElement('div');
                employeeDiv.classList.add('employee-info');

                // 이미지 요소 생성
                let img = document.createElement('img');
                img.src = imageUrl;
                img.alt = 'img';
                img.classList.add('employee-profile');
                employeeDiv.appendChild(img);

                // 이름, 직위, 부서 정보 추가
                let employeeName = document.createElement('div');
                employeeName.textContent = `${employee.name} (${employee.nameOfPosition.trim()}/${employee.partName})`;
                employeeDiv.appendChild(employeeName);

                employeeDiv.addEventListener('click', function () {
                    showEmployeeDetails(employee.empNo);
                })

                employeeContainer.appendChild(employeeDiv);
            });


        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function showEmployeeDetails(empNo) {
    $.ajax({
        type: 'get',
        url: '/orca/humanResources/showEmployeeDetails',
        dataType: 'html',
        success: function (response) {
            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            let backList = document.querySelector('.backList');
            backList.addEventListener('click', function () {
                showEmployeeList();
            });
            getEmployeeDetails(empNo);
            let editBtn = document.querySelector('.btn-primary')
            editBtn.addEventListener('click', function () {
                showEmployeeEdit(empNo);
            });

            let delBtn = document.querySelector('.btn-danger')
            delBtn.addEventListener('click', function () {
                const userResponse = confirm("정말로 삭제하시겠습니까?");
                if (userResponse) {
                    deleteEmployee(empNo);
                }
            });
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
};

function deleteEmployee(empNo){
    $.ajax({
        type: 'post',
        url: `/orca/humanResources/deleteEmployee`,
        data: {
            empNo: empNo
        },
        dataType: 'json',
        success: function (response) {
            console.log(response);
            if(response === 1){
                alert("사원 삭제 성공!");
                showEmployeeList();
            }else{
                alert("사원 삭제 실패");
            }
        },
        error: function (error) {
            console.error('사원 삭제 실패', error);
        }
    });
}

function getEmployeeDetails(empNo) {
    $.ajax({
        type: 'get',
        url: `/orca/humanResources/getEmployeeDetails`,
        data: {
            empNo: empNo
        },
        dataType: 'json',
        success: function (data) {
            $('#empNo').text(data.empNo);
            $('#name').text(data.name);
            $('#gender').text(data.gender);
            $('#social-security-no').text(data.socialSecurityNo);
            $('#phone').text(data.phone);
            $('#extensionCall').text(data.extensionCall);
            $('#email').text(data.email);
            $('#address').text(data.address);
            $('#dateOfEmployment').text(data.dateOfEmployment);
            $('#height').text(data.height);
            $('#weight').text(data.weight);
            $('#bloodType').text(data.bloodType);
            $('#religion').text(data.religion);
            $('#bankNumber').text(data.bankNumber);
            $('#partName').text(data.partName);
            $('#nameOfPosition').text(data.nameOfPosition);
            $('#teamName').text(data.teamName);
            $('#profileImage').attr('src', '/upload/user/' + data.imgChangeName); // 이미지 경로 설정

        },
        error: function (error) {
            console.error('사원 상세 조회 실패', error);
        }
    });
}

function showEmployeeEdit(empNo) {
    $.ajax({
        type: 'get',
        url: '/orca/humanResources/showEmployeeEdit',
        dataType: 'html',
        success: function (response) {

            while (mainDiv.firstChild) {
                mainDiv.removeChild(mainDiv.firstChild);
            }
            mainDiv.innerHTML = response;
            getEditSelects(empNo);

            let backList = document.querySelector('.backList');
            backList.addEventListener('click', function () {
                showEmployeeDetails(empNo);
            });
            let cancelBtn = document.querySelector('#cancelEdit');
            cancelBtn.addEventListener('click', function () {
                showEmployeeDetails(empNo);
            });


            document.querySelector('.btn-primary').addEventListener('click', function () {
                employeeEdit(empNo);
            });
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function getEditSelects(empNo) {
    $.ajax({
        type: 'get',
        url: '/orca/humanResources/getSelects',
        dataType: 'json',
        success: function (response) {
            console.log(response);  // 받아온 데이터를 콘솔에 출력하여 확인

            // 부서 데이터를 추출하여 옵션으로 추가
            const deptSelect = $('#deptCode');
            response[0].forEach(dept => {
                const option = $('<option></option>');
                option.val(dept.deptCode);
                option.text(dept.partName);
                deptSelect.append(option);
            });

            // 팀 데이터를 추출하여 옵션으로 추가
            const teamSelect = $('#teamCode');
            response[1].forEach(team => {
                const option = $('<option></option>');
                option.val(team.teamCode);
                option.text(team.teamName);
                teamSelect.append(option);
            });

            // 직급 데이터를 추출하여 옵션으로 추가
            const positionSelect = $('#positionCode');
            response[2].forEach(position => {
                const option = $('<option></option>');
                option.val(position.positionCode);
                option.text(position.nameOfPosition);
                positionSelect.append(option);
            });
            validation();
            getEmployeeEditData(empNo);
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

let updatedData = {};

function getEmployeeEditData(empNo) {
    $.ajax({
        type: 'get',
        url: `/orca/humanResources/getEmployeeDetails`,
        data: {
            empNo: empNo
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#empNo').text(data.empNo);  // 예시: 사원번호는 span 태그에 텍스트로 설정
            $('#name').val(data.name);  // 이름은 input 태그의 value에 설정
            $('#gender').val(data.gender);  // 성별은 select 태그의 value에 설정
            $('#social-security-no').val(data.socialSecurityNo);  // 주민등록번호는 input 태그의 value에 설정
            $('#phone').val(data.phone);  // 전화번호는 input 태그의 value에 설정
            $('#extensionCall').val(data.extensionCall);  // 내선번호는 input 태그의 value에 설정
            $('#email').val(data.email);  // 이메일은 input 태그의 value에 설정
            $('#address').val(data.address);  // 주소는 input 태그의 value에 설정
            $('#dateOfEmployment').val(data.dateOfEmployment);  // 입사일은 input 태그의 value에 설정
            $('#height').val(data.height);  // 키는 input 태그의 value에 설정
            $('#weight').val(data.weight);  // 몸무게는 input 태그의 value에 설정
            $('#bloodType').val(data.bloodType);  // 혈액형은 select 태그의 value에 설정
            $('#religion').val(data.religion);  // 종교는 input 태그의 value에 설정
            const parts = data.bankNumber.split('-');
            const bankName = parts[0];
            const bankNumber = parts[1];
            $('#bankName').val(bankName);
            $('#bankNumber').val(bankNumber);
            $('#deptCode').val(data.deptCode);  // 부서명은 input 태그의 value에 설정
            $('#positionCode').val(data.positionCode);  // 직위명은 input 태그의 value에 설정
            $('#teamCode').val(data.teamCode);  // 팀명은 input 태그의 value에 설정
            $('#profileImage').attr('src', '/upload/user/' + data.imgChangeName); // 이미지 경로 설정

            updatedData.empNo = data.empNo;
            updatedData.name = data.name;
            updatedData.gender = data.gender;
            updatedData.socialSecurityNo = data.socialSecurityNo;
            updatedData.phone = data.phone;
            updatedData.extensionCall = data.extensionCall;
            updatedData.email = data.email;
            updatedData.address = data.address;
            updatedData.dateOfEmployment = data.dateOfEmployment;
            updatedData.height = data.height;
            updatedData.weight = data.weight;
            updatedData.bloodType = data.bloodType;
            updatedData.religion = data.religion;
            updatedData.bankNumber = data.bankNumber;
            updatedData.deptCode = data.deptCode;
            updatedData.positionCode = data.positionCode;
            updatedData.teamCode = data.teamCode;
        },
        error: function (error) {
            console.error('사원 상세 조회 실패', error);
        }
    });
}

function employeeEdit(empNo) {
    console.log(empNo);
    let formData = new FormData();  // FormData 객체 생성
    let isUpdate = false;

    function appendFormData(fieldId, fieldName, updatedFieldValue) {
        let fieldValue = $(fieldId).val();

        if (fieldId === '#bankNumber') {
            const bankName = $('#bankName').val();
            fieldValue = bankName + '-' + fieldValue;
            console.log(fieldValue);
        }

        if (fieldValue !== updatedFieldValue) {
            if (fieldValue.trim() === '') {
                if (updatedFieldValue !== null) {
                    isUpdate = true;
                    formData.append(fieldName, fieldValue);
                }
            } else {
                formData.append(fieldName, fieldValue);
                isUpdate = true;
            }
        }
    }


    appendFormData('#name', 'name', updatedData.name);
    appendFormData('#gender', 'gender', updatedData.gender);
    appendFormData('#social-security-no', 'socialSecurityNo', updatedData.socialSecurityNo);
    appendFormData('#phone', 'phone', updatedData.phone);
    appendFormData('#extensionCall', 'extensionCall', updatedData.extensionCall);
    appendFormData('#email', 'email', updatedData.email);
    appendFormData('#address', 'address', updatedData.address);
    appendFormData('#dateOfEmployment', 'dateOfEmployment', updatedData.dateOfEmployment);
    appendFormData('#height', 'height', updatedData.height);
    appendFormData('#weight', 'weight', updatedData.weight);
    appendFormData('#bloodType', 'bloodType', updatedData.bloodType);
    appendFormData('#religion', 'religion', updatedData.religion);
    appendFormData('#bankNumber', 'bankNumber', updatedData.bankNumber);
    appendFormData('#deptCode', 'deptCode', updatedData.deptCode);
    appendFormData('#positionCode', 'positionCode', updatedData.positionCode);
    appendFormData('#teamCode', 'teamCode', updatedData.teamCode);


    // 파일 업로드를 위한 처리
    let fileInput = document.getElementById('image');
    if (fileInput.files.length > 0) {
        formData.append('image', fileInput.files[0]);
        isUpdate = true;
    }
    console.log(isUpdate);

    // 변경된 데이터가 있는 경우에만 Ajax를 통해 서버로 전송
    if (isUpdate) {
        formData.append('empNo', empNo);
        $.ajax({
            type: 'post',  // 예시로 post로 설정
            url: '/orca/humanResources/updateEmployee',  // 데이터 업데이트를 처리할 서버 URL
            data: formData,
            contentType: false,  // 필수
            processData: false,  // 필수
            dataType: 'json',
            success: function (response) {
                // 성공적으로 업데이트된 경우 처리
                console.log('데이터 업데이트 성공', response);
                if (response === 1) {
                    alert("사원 정보 수정 성공!")
                } else {
                    alert("사원 정보 수정 실패")
                }
            },
            error: function (error) {
                console.error('데이터 업데이트 실패', error);
            }
        });
    } else {
        alert("변경된 내용이 없습니다.");
    }
}
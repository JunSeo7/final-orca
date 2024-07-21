$(document).ready(function () {
    $('#departmentLogin').on('submit', function (event) {
        event.preventDefault();
        const password = $('#password').val();
        $.ajax({
            url: $(this).attr('action'),
            method: "post",
            dataType: 'json',
            data: {
                password: password
            },
            success: (response) => {
                if (response === null) {
                    alert("로그인 실패!");
                } else {
                    alert("로그인 성공!")
                    let deptCode = response.deptCode;
                    if (deptCode === 2) {
                        window.location.href = "/orca/humanResources/main";
                    } else if (deptCode === 3) {
                        window.location.href = "/orca/managementSupport/main";
                    } else if (deptCode === 4) {
                        window.location.href = "/orca/accountingDivision/salaryMain";
                    } else {
                        alert("개설되지 않은 부서페이지 입니다. 관리자에게 문의하세요")
                    }
                }
            },
            error: (xhr, status, error) => {
                console.log('Status Code:', xhr.status);
                console.log('Status Text:', xhr.statusText);
                console.log('Error:', error);
                console.log('Response Text:', xhr.responseText);
                alert("해당하는 부서의 비밀번호를 다시 확인해주세요");
            }
        });
    });
});
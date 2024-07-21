const passwordForm = document.getElementById('passwordForm');
$(document).ready(function () {
    passwordForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const currentPassword = document.getElementById('currentPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        // 여기에 비밀번호 검증 로직 추가 (예: 길이 확인, 일치 여부 확인 등)
        if (currentPassword === newPassword) {
            alert("기존 비밀번호와 동일합니다. 다시 확인해주세요");
        } else if (newPassword !== confirmPassword) {
            alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다. 다시 확인해주세요.");
        } else {
            const userResponse = confirm("정말로 변경하시겠습니까?");
            if (userResponse) {
                $.ajax({
                    url: "/orca/user/changePassword",
                    method: 'post',
                    dataType: 'json',
                    data: {
                        currentPassword: currentPassword,
                        newPassword: newPassword,
                    },
                    success: function (response) {
                        if (response === 1) {
                            alert("비밀번호 변경 성공!");
                            window.location.href = "/orca/user/login";
                        } 
                    },
                    error: function (xhr, status, error) {
                        console.log('Status Code:', xhr.status);
                        console.log('Status Text:', xhr.statusText);
                        console.log('Error:', error);
                        console.log('Response Text:', xhr.responseText);
                        if(xhr.responseText === '-1'){
                            alert('현재 비밀번호를 다시 확인해주세요.');
                        }else{
                            alert('비밀번호 변경 실패');
                        }
                    }
                })
            }
        }

    });
})






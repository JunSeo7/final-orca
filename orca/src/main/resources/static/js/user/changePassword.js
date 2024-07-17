const passwordForm = document.getElementById('passwordForm');

passwordForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const currentPassword = document.getElementById('currentPassword').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    // 여기에 비밀번호 검증 로직 추가 (예: 길이 확인, 일치 여부 확인 등)

    // 비밀번호 변경 API 호출 등의 로직 추가 가능 (서버와의 통신)

    // 예시로 경고창을 띄우는 코드
    alert(`Current Password: ${currentPassword}\nNew Password: ${newPassword}\nConfirm Password: ${confirmPassword}`);
});

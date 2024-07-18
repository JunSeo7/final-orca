<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>salary write</title>
    <!-- 파비콘 -->
    <link rel="icon" href="img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/managementSupport/main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/managementSupport/main.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/salary/write.css">
</head>

<body>
    <header>
        <div class="header-left">
            <img src="" alt="Logo" class="logo">
            <h2 class="header-title">ORCA</h2>
        </div>
        <div class="header-right">
            <span class="icon">알림</span>
            <span class="icon">조직도</span>
            <span class="icon">설정</span>
        </div>
    </header>

    <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>
    <aside id="sidebar" class="sidebar">
        <div class="profile" onclick="toggleProfile()">
            <img src="profile.png" alt="Profile Picture" class="profile-pic">
            <p class="profile-info">회계 1팀 | <span>양파쿵야</span></p>
        </div>
        <hr>
        <nav>
            <div class="sidebar-nav">
                <div class="approval title-toggle">◾ 급여 관리</div>
                <div class="approval-list">
                    <div class="toggle"><a href="/orca/salaryWrite">◽ 급여 입력</a></div>
                    <div class="toggle"><a href="/orca/salaryList">◽ 급여 목록</a></div>
                </div>
                <div class="calnedar title-toggle">◾ 4대보험</div>
                <div class="calendar-link link">
                    <div class="calendar-wirte toggle">◽ 조회 - 수정</div>
                </div>
            </div>
        </nav>
    </aside>
    <main>
        <div class="main" id="content">
            <h1>급여 입력</h1>

            <form action="/orca/salary/write" method="post" id="salaryForm">
                사원번호 :<input type="text" name="empNo" >
                <br>
                직급수당: <input type="text" name="position" >
                <br>
                상여금: <input type="text" name="bonus" >
                <br>
                식대: <input type="text" name="meals" >
                <br>
                휴일근무시간: <input type="text" name="holidayTime" >
                <br>
                연장근무시간: <input type="text" name="overTime" >
                <br>
                자녀 수: <input type="text" name="person" >
                <br>
                <input type="submit" name="작성하기">
            </form>
            
        </div>
    </main>
</body>

</html>

<script>
    $(document).ready(function() {
        $('#salaryForm').submit(function(e) {
            e.preventDefault(); // 폼 제출 기본 동작 방지
console.log("123");
            // 폼 데이터를 가져오기
            var formData = {
                empNo: $('#empNo').val(),
                position: $('#position').val(),
                bonus: $('#bonus').val(),
                meals: $('#meals').val(),
                holidayTime: $('#holidayTime').val(),
                overTime: $('#overTime').val(),
                person: $('#person').val()
            };

            // AJAX 요청
            $.ajax({
                url: 'http://127.0.0.1:8080/orca/salary/write',
                method: 'post',
                dataType: 'json',
                data: formData,
                success: function(response) {
                    // 성공적으로 데이터를 서버에 전송한 후 처리할 내용
                    console.log('데이터 전송 성공');
                   
                    if(response == 1){
                        alert("급여 작성 성공하셨습니다.");
                        window.location.href="/orca/salaryList";
                    }else{
                        alert("급여 작성 실패하셨습니다.");
                        window.location.href="orca/salaryWrite";
                    }
                },
                error: function(xhr, status, error) {
                    // 오류 발생 시 처리
                    console.error('데이터 전송 실패:', error);
                    // 추가적인 오류 처리 로직을 여기에 추가할 수 있습니다.
                }
            });
        });
    });
</script>
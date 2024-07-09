<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>ORCA</title>

    <link rel="stylesheet" href="/css/layout/aside.css">
    <script defer src="/js/layout/aside.js"></script>

</head>
<body>
        <aside id="sidebar">
            <div class="profile" onclick="toggleProfile()">
                <img src="img/profile.png" alt="Profile Picture" class="profile-pic">
                <p class="profile-info">SW팀 | <span>양파쿵야</span></p>
            </div>
            <hr>
            <button class="on">출근</button>
                <hr>

              <div id="profileDetail" class="profile-detail hidden">
                  <p>상태 설정</p>
                  <p>상태 메시지</p>
                  <p>@멘션 확인하기</p>
                  <p>파일 리스트</p>
                  <p>직책</p>
                  <p>생년월일</p>
                  <p>휴대전화</p>
                  <p>raji1004@naver.com</p>
                  <button onclick="logout()">로그아웃</button>
              </div>

            <ul class="nav-list">
                <li class="nav-item"><a href="#">채팅</a></li>
                <li class="nav-item"><a href="/orca/calendar/showCalendar">캘린더/할일</a></li>
                <li class="nav-item"><a href="/orca/document/list?status=2">전자결재</a></li>
                <li class="nav-item"><a href="/orca/work/workInfo">근태</a></li>
                <li class="nav-item"><a href="#">투표</a></li>
                <li class="nav-item"><a href="#">드라이브</a></li>
                <li class="nav-item"><a href="#">메일</a></li>
                <li class="nav-item"><a href="#">설정</a></li>
            </ul>
        </aside>
</body>
</html>
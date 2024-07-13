<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>ORCA</title>
    <!-- 파비콘 -->
     <link rel="icon" href="/img/header/logo.png" type="image/png">

    <!--JSTREE 라이브러리 연결 / 사이드바-->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/themes/default/style.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/jstree.min.js"></script>

      <link rel="stylesheet" href="/css/template/aside.css">
      <script defer src="/js/template/aside.js"></script>

</head>
<body>

      <aside id="sidebar" class="sidebar">
        <div class="profile" onclick="toggleProfile()">
          <img src="/img/header/profile.png" alt="Profile Picture" class="profile-pic">
          <p class="profile-info">SW팀 | <span>양파쿵야</span></p>
        </div>
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
        <nav>
            <div class="sidebar-nav">
                <div class="approval title-toggle">◾ 결재 관리</div>
                <div class="approval-list">
                    <div class="toggle">◽ 결재 양식 관리</div>
                    <div class="toggle">◽ 기본 결재선 관리</div>
                </div>
                <div class="calnedar title-toggle">◾ 사내 캘린더 관리</div>
                <div class="calendar-link link">
                    <div class="calendar-list toggle">◽ 조회</div>
                    <div class="calendar-wirte toggle">◽ 작성</div>
                </div>
            </div>
        </nav>
    </aside>
</body>
</html>
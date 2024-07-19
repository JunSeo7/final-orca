<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.groupware.orca.user.vo.UserVo" %>

<html>
<head>

    <title>ORCA</title>
    <!-- 파비콘 -->
    <link rel="icon" href="/img/header/logo.png" type="image/png">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

      <link rel="stylesheet" href="/css/template/aside.css">
      <script defer src="/js/template/aside.js"></script>

</head>
<body>

      <aside id="sidebar" class="sidebar">
          <% UserVo loginUserVo=(UserVo) session.getAttribute("loginUserVo"); String
              imgChangeName=(loginUserVo.getImgChangeName() !=null) ? loginUserVo.getImgChangeName()
              : "profile.png" ; %>
              <div class="profile" onclick="toggleProfile()">
                  <img src="/upload/user/<%= imgChangeName %>" alt="Profile Picture" class="profile-pic">
                  <p class="profile-info">
                      <%= loginUserVo.getTeamName() %> | <span>
                              <%= loginUserVo.getName() %>
                          </span>
                  </p>
              </div>
              <hr>
              <div id="profileDetail" class="profile-detail hidden">
                  <div id="empNo"></div>
                  <div id="partName"></div>
                  <div id="position"></div>
                  <div id="phone"></div>
                  <div id="extensionCall"></div>
                  <div id="email"></div>
                  <div id="change-password">비밀번호 변경</div>
                  <button onclick="logout()">로그아웃</button>
              </div>

              <nav>
                  <div class="sidebar-nav">
                      <div class="approval title-toggle">◾ 결재 관리</div>
                      <div class="approval-list">
                          <div class="toggle" onclick="location.href='/orca/template/list'">◽ 결재 양식 관리</div>
                          <div class="toggle" onclick="location.href='/orca/apprline/list'">◽ 기본 결재선 관리</div>
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
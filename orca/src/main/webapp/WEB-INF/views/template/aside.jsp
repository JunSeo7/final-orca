<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>ORCA</title>
    <!-- 파비콘 -->
     <link rel="icon" href="/img/logo.png" type="image/png">

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
      <img src="profile.png" alt="Profile Picture" class="profile-pic">
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
      <a href="/orca/template/add"><button class="draft_write">결재 양식 추가</button></a>
      <hr>
      <div id="navTree">
        <ul>
          <li data-jstree='{"icon":"bi bi-folder", "opened": true}'>시스템 관리
            <ul>
              <li data-jstree='{"icon":"bi bi-file-earmark"}'><a href="#" onclick="loadPage('home.jsp')">양식카테고리</a></li>
              <li data-jstree='{"icon":"bi bi-file-earmark"}'><a href="#" onclick="loadPage('home.jsp')">결재양식</a></li>
              <li data-jstree='{"icon":"bi bi-file-earmark"}'><a href="#" onclick="loadPage('home.jsp')">기본결재선</a></li>
            </ul>
          </li>
        </ul>
      </div>
      </nav>
  </aside>
</body>
</html>
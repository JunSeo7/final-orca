<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>ORCA</title>
    <!-- 파비콘 -->
    <link rel="icon" href="/img/logo.png" type="image/png">

    <!--Bootstrap Icons 라이브러리 연결-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="/css/document/aside.css">
    <script defer src="/js/document/aside.js"></script>
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
            <a href="/orca/document/write"><button class="draft_write">결재 작성</button></a>
            <hr>
            <ul class="nav-list">
                <li class="nav-item"><i class="bi bi-folder"></i> 결재현황
                    <ul class="sub-nav-list">
                    <!-- 1: 임시저장 2: 기안 3: 종결 4: 반려  5: 결재취소 --!>
                        <ul class="sub-nav-list">
                          <li class="nav-item"><a href="/orca/document/list?status=1"><i class="bi bi-file-earmark"></i> 임시 보관함</a></li>
                          <li class="nav-item"><a href="/orca/document/list?status=2"><i class="bi bi-file-earmark"></i> 기안</a></li>
                          <li class="nav-item"><a href="/orca/document/received"><i class="bi bi-file-earmark"></i> 결재</a></li>
                          <li class="nav-item"><a href="/orca/document/list?status=3"><i class="bi bi-file-earmark"></i> 종결</a></li>
                          <li class="nav-item"><a href="/orca/document/list?status=4"><i class="bi bi-file-earmark"></i> 반려</a></li>
                          <li class="nav-item"><a href="/orca/document/list?status=5"><i class="bi bi-file-earmark"></i> 삭제함</a></li>
                    </ul>
                </li>
                <li class="nav-item"><i class="bi bi-gear"></i> 사용자 설정
                    <ul class="sub-nav-list">
                        <li class="nav-item"><a href="#"><i class="bi bi-file-earmark"></i> 개인환경설정</a></li>
                        <li class="nav-item"><a href="/orca/myapprline/list"><i class="bi bi-file-earmark"></i> 개인 결재선</a></li>
                        <li class="nav-item"><a href="/orca/document/public"><i class="bi bi-file-earmark"></i> 공유함 관리</a></li>
                    </ul>
                </li>
            </ul>
    </nav>
</aside>

</body>
</html>

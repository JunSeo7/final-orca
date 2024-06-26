<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>ORCA</title>

    <link rel="icon" href="/img/logo.png" type="image/png">

    <link rel="stylesheet" href="/css/layout/header.css">
    <script defer src="/js/layout/header.js"></script>

</head>
<body>
    <header>
        <div class="header-left">
            <img src="img/logo.png" alt="Logo" class="logo">
            <h2 class="header-title">ORCA</h2>
        </div>
        <div class="header-right">
            <span class="icon">알림</span>
            <span class="icon">조직도</span>
            <span class="icon">설정</span>
        </div>
    </header>

    <nav class="top-nav">
        <ul>
            <li><a href="#">캘린더/할일</a></li>
            <li><a href="#">게시판</a></li>
            <li><a href="#">전자결재</a></li>
            <li><a href="#">급여관리</a></li>
            <li><a href="#">근태관리</a></li>
            <li><a href="#">메뉴</a></li>
        </ul>
    </nav>
     <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>
</body>
</html>
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
            <a href="/orca/home"><img src="/img/header/logo.png" alt="Logo" class="logo"></a>
            <a href="/orca/home" style="text-decoration: none; color:black;">
                <h2>ORCA</h2>
            </a>
        </div>
        <div class="header-right">
            <span class="icon"><img src="/img/header/bell.png" alt="bell" class="icon"></span>
            <span class="icon"><img src="/img/header/organization-chart.png" alt="organization-chart" class="icon"></span>
            <span class="icon"><img src="/img/header/settings.png" alt="settings" class="icon"></span>
        </div>
    </header>

    <nav class="top-nav">
        <ul>
            <li><a href="/orca/calendar/showCalendar">캘린더/할일</a></li>
            <li><a href="/orca/board">게시판</a></li>
            <li><a href="/orca/document/list">전자결재</a></li>
            <li><a href="">급여관리</a></li>
            <li><a href="/orca/work/workInfo">근태관리</a></li>
            <li><a href="">메뉴</a></li>
        </ul>
    </nav>
     <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>
</body>
</html>
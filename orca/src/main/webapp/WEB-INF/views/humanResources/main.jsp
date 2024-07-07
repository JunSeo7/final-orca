<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Home</title>
            <!-- 파비콘 -->
            <link rel="icon" href="img/logo.png" type="image/png">
            <link rel="stylesheet" href="/css/humanResources/main.css">
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script defer src="/js/humanResources/main.js"></script>
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
                    <p class="profile-info">경영지원부 1팀 | <span>양파쿵야</span></p>
                </div>
                <hr>
                <nav>
                    <div class="sidebar-nav">
                        <div class="approval title-toggle">◾ 결재 관리</div>
                        <div class="approval-list">
                            <div class="toggle">◽ 결재 양식 관리</div>
                            <div class="toggle">◽ 기본 결재선 관리</div>
                        </div>
                        <div class="calnedar title-toggle">◾ 사내 캘린더 관리</div>
                        <div class="calendar-link link">
                            <div class="toggle">◽ 조회</div>
                            <div class="calendar-wirte toggle">◽ 작성</div>
                        </div>
                    </div>
                </nav>
            </aside>
            <main>
                <div class="main">
                    <h1>경영지원부</h1>
                </div>
            </main>
        </body>

        </html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <html>

            <head>

                <title>ORCA</title>

                <link rel="stylesheet" href="/css/layout/aside.css">
                <script defer src="/js/layout/aside.js"></script>

                <link rel="icon" href="/img/logo.png" type="image/png">
                <link rel="stylesheet" href="/css/layout/header.css">
                <script defer src="/js/layout/header.js"></script>

            </head>

            <body>
                <% UserVo loginUserVo=(UserVo) session.getAttribute("loginUserVo"); String
                    imgChangeName=(loginUserVo.getImgChangeName() !=null) ? loginUserVo.getImgChangeName()
                    : "profile.png" ; %>

                    <header>
                        <div class="header-left">
                            <a href="/orca/home"><img src="/img/header/logo.png" alt="Logo" class="logo"></a>
                            <a href="/orca/home" style="text-decoration: none; color:black;">
                                <h2>ORCA</h2>
                            </a>
                        </div>
                        <div class="header-right">
                            <span class="icon"><img src="/img/header/bell.png" alt="bell" class="icon"></span>
                            <span class="icon"><img src="/img/header/organization-chart.png" alt="organization-chart"
                                    class="icon organizationChart"></span>
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
                    <aside id="sidebar">
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

                        <ul class="nav-list">
                            <li class="nav-item"><a href="#">채팅</a></li>
                            <li class="nav-item"><a href="/orca/calendar/showCalendar">캘린더/할일</a></li>
                            <li class="nav-item"><a href="/orca/document/list">전자결재</a></li>
                            <li class="nav-item"><a href="/orca/work/workInfo">근태</a></li>
                            <li class="nav-item"><a href="#">투표</a></li>
                            <li class="nav-item"><a href="#">드라이브</a></li>
                            <li class="nav-item"><a href="#">메일</a></li>
                            <li class="nav-item"><a href="/orca/user/showDepartmentLogin">부서 로그인</a></li>
                            <li class="nav-item"><a href="/orca/personList">개인명세서 목록 / 조회</a></li>
                        </ul>
                    </aside>

                    <main class="personSalary">
                        <h1>개인명세서 목록</h1>
                    </main>
            </body>

            </html>
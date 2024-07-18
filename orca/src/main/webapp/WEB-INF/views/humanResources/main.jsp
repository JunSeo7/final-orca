<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Home</title>
                <!-- 파비콘 -->
                <link rel="stylesheet" href="/css/humanResources/main.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                <script defer src="/js/humanResources/main.js"></script>
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                        <span class="icon"><img src="/img/header/organization-chart.png" alt="organization-chart"
                                class="icon"></span>
                        <span class="icon"><img src="/img/header/settings.png" alt="settings" class="icon"></span>
                    </div>
                </header>

                <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>
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
                            <div class="calnedar title-toggle">◾ 인사 관리</div>
                            <div class="calendar-link link">
                                <div class="employee-list toggle">◽ 전체 사원 조회</div>
                                <div class="employee-registration toggle">◽ 사원 등록</div>
                            </div>
                            <div class="approval title-toggle">◾ 근퇴 관리</div>
                            <div class="approval-list">
                                <div class="toggle">◽ 전체 사원 근무 관리</div>
                                <div class="showVacationCode toggle">◽ 휴가 코드 관리</div>
                                <div class="toggle">◽ 기타 코드 관리</div>
                            </div>
                        </div>
                    </nav>
                </aside>
                <main>
                    <div class="main" id="content">
                        <h1>인사관리부</h1>
                    </div>
                </main>
            </body>

            </html>
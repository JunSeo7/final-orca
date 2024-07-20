<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Home</title>
                <!-- 파비콘 -->
                <link rel="icon" href="img/logo.png" type="image/png">
                <link rel="stylesheet" href="/css/managementSupport/main.css">
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script defer src="/js/managementSupport/main.js"></script>
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
                                class="icon organizationChart"></span>
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
                <main class="main" id="content">
                    <div class="main-inner">
                        <div class="left">
                            <h2>결재 관리</h2>
                            <h3>결재 양식 목록</h3>
                            <div id="template-list"></div>
                            <h3>기본 결재선 목록</h3>
                            <div id="apprline-list"></div>
                        </div>
                        <div class="right">
                             <h2>사내 캘린더 관리</h2>
                             <h3>결재 양식 목록</h3>
                             <h3>기본 결재선 목록</h3>
                        </div>
                    </div>
                </main>
            </body>

            </html>
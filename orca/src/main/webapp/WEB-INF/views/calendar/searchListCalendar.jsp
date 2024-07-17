<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Calendar</title>
                <!-- 파비콘 -->
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <link rel="stylesheet" href="/css/calendar/searchListCalendar.css">
                <script defer src="/js/calendar/searchListCalendar.js"></script>

                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
            </head>

            <header>
                <div class="header-left" onclick="loadPage('home.jsp')">
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
            <button id="toggleSidebar" onclick="toggleSidebar()">메뉴</button>
            <aside id="sidebar">
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
                    <div class="calendar-link"><a href="showCalendar">내 캘린더</a></div>

                    <div class="selectContainer">
                        <select id="yearSelect" onchange="handleMonthChange()"></select>
                        <select id="monthSelect" onchange="handleMonthChange()"></select>
                    </div>
                    <div id="sidebarCalendarContainer">
                        <div id="sidebarCalendar"></div>
                    </div>

            </aside>
            <main id="content">
                <div class="container">
                    <span>
                        <h2>캘린더 검색</h2>
                    </span>
                    <span class="search-container">
                        <form action="searchListCalendar" method="get" id="calendar-search-form">
                            <input type="text" name="keyword" class="search-text">
                        </form>
                        <i class="fas fa-search search-icon"></i>
                    </span>
                    <table class="custom-table">
                        <thead>
                            <tr>
                                <th>제목</th>
                                <th>내용</th>
                                <th>작성자</th>
                                <th>시작일</th>
                                <th>종료일</th>
                                <th>등록일</th>
                                <th>공유 범위</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- 여기에 데이터를 추가하세요 -->
                        </tbody>
                    </table>
                    <div class="pageFooter">
                        <div class="totalPage"></div>
                    </div>
                </div>
            </main>

            </body>

            </html>
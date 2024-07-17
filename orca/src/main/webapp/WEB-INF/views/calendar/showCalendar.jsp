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
                <link rel="stylesheet" href="/css/calendar/showCalendar.css">
                <script defer src="/js/calendar/showCalendar.js"></script>
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
            </head>

            <body>
                <header>
                    <div class="header-left" onclick="loadPage('home.jsp')">
                        <a href="/orca/home"><img src="/img/header/logo.png" alt="Logo" class="logo"></a>
                        <h2>ORCA</h2>
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
                    <div class="profile" onclick="toggleProfile()">
                        <% UserVo loginUserVo=(UserVo) session.getAttribute("loginUserVo"); %>
                            <img src="/upload/user/<%= loginUserVo.getImgChangeName() %>" alt="Profile Picture"
                                class="profile-pic">
                            <p class="profile-info">
                                <%= loginUserVo.getTeamName() %> | <span>
                                        <%= loginUserVo.getName() %>
                                    </span>
                            </p>
                    </div>

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
                    <div class="aside-Calendar-sell">
                        <div class="aside-Calendar">
                            <div class="new-schedule" onclick="submitNewEvent()">
                                일정 등록
                            </div>
                        </div>
                        <div class="selectContainer">
                            <select id="yearSelect" onchange="handleMonthChange()"></select>
                            <select id="monthSelect" onchange="handleMonthChange()"></select>
                        </div>
                        <div id="sidebarCalendarContainer">
                            <div id="sidebarCalendar"></div>
                        </div> <!-- 사이드 캘린더 추가 -->
                    </div>

                    <hr>
                    <nav>
                        <ul>
                            <div class="my-calendar-title">내 캘린더</div>
                            <div class="my-calendar">
                                <li>
                                    <span class="showCalendarBar" onclick="toggleCheck(this); return false;">사내
                                        캘린더</span>
                                </li>
                                <li>
                                    <span class="showCalendarBar" onclick="toggleCheck(this); return false;">내
                                        캘린더</span>
                                </li>
                                <li>
                                    <span class="showCalendarBar" onclick="toggleCheck(this); return false;">부서
                                        캘린더</span>
                                </li>
                            </div>
                        </ul>
                    </nav>
                </aside>
                <main id="content">
                    <div id="calendar"></div>
                    <form action="searchListCalendar" method="get" id="calendar-search-form">
                        <input type="text" name="keyword" class="search-text">
                        <i class="fas fa-search search-icon"></i>
                    </form>
                </main>

                <div class="new-calendar-form" id="newEventForm">
                    <div class="form-header">
                        <h2>일정 등록</h2>
                        <button class="cancel-button" onclick="closeFormEvent()">✖</button>
                    </div>

                    <div class="form-body">
                        <div class="form-group">
                            <label for="eventTitle">일정 제목</label>
                            <input type="text" id="eventTitle" name="title" required>
                        </div>
                        <div class="form-group">
                            <label for="content">일정 내용</label>
                            <textarea id="eventContent" name="content" rows="4"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="startDate">시작일</label>
                            <input type="date" id="startDate" name="startDate" required>
                        </div>
                        <div class="form-group">
                            <label for="endDate">종료일</label>
                            <input type="date" id="endDate" name="endDate" required>
                        </div>
                        <div class="form-group">
                            <label for="range">공유 범위</label>
                            <select id="range" name="range">
                                <option value="individual">개인</option>
                                <option value="department">부서</option>
                            </select>
                        </div>
                        <div class="form-footer">
                            <button id="submitBtn" type="submit" onclick="createCalendar();">일정 등록</button>
                        </div>
                    </div>
                </div>

                <div class="view-calendar-form" id="viewEventDetailsForm">
                    <div class="view-form-header">
                        <div><span class="view-calendar-title">일정 제목</span> <span
                                class="view-calendar-enroll-date">2024.06.21</span></div>
                        <button class="view-cancel-button" onclick="closeViewEvent()">✖</button>
                    </div>

                    <div class="view-form-body">
                        <div class="view-form-group view-calendar-name">
                            <span class="view-calendar-writer">작성자</span>
                            <span class="view-calendar-partName">부서</span>
                        </div>
                        <div class="view-form-group">
                            <label for="viewEventContent">일정 내용</label>
                            <textarea id="viewEventContent" name="content" rows="4.5" readonly></textarea>
                        </div>
                        <div class="view-form-group">
                            <label for="viewStartDate">시작일</label>
                            <input type="date" id="viewStartDate" name="startDate" readonly required>
                        </div>
                        <div class="view-form-group">
                            <label for="viewEndDate">종료일</label>
                            <input type="date" id="viewEndDate" name="endDate" readonly required>
                        </div>
                        <div class="view-form-group">
                            <label for="viewRange">공유 범위</label>
                            <input type="text" id="viewRange" class="edit-range" name="range" readonly>
                        </div>
                        <div class="view-form-footer">
                            <button class="view-edit-button">수정</button>
                            <button class="view-delete-button">삭제</button>
                        </div>
                    </div>
                </div>

            </body>

            </html>
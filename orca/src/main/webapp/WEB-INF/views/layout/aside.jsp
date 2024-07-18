<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <html>

            <head>

                <title>ORCA</title>

                <link rel="stylesheet" href="/css/layout/aside.css">
                <script defer src="/js/layout/aside.js"></script>

            </head>

            <body>
                <% UserVo loginUserVo=(UserVo) session.getAttribute("loginUserVo"); String
                    imgChangeName=(loginUserVo.getImgChangeName() !=null) ? loginUserVo.getImgChangeName()
                    : "profile.png" ; %>

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
                        </ul>
                    </aside>
            </body>

            </html>
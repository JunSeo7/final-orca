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
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

                <!-- my css -->
                <link rel="stylesheet" href="/css/salary/main.css">

                <!-- 공용 css -->
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

                    <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>

                    <aside id="sidebar" class="sidebar">

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

                        <!-- <div class="profile" onclick="toggleProfile()">
                            <img src="profile.png" alt="Profile Picture" class="profile-pic">
                            <p class="profile-info">회계 1팀 | <span>양파쿵야</span></p>
                        </div> -->
                        <hr>
                        <nav>
                            <div class="sidebar-nav">
                                <div class="approval title-toggle">◾ 급여 관리</div>
                                <div class="approval-list">
                                    <div class="toggle"><a href="/orca/accountingDivision/salaryWrite">◽ 급여 입력</a></div>
                                    <div class="toggle"><a href="/orca/accountingDivision/salaryList">◽ 급여 목록</a></div>
                                </div>
                                <div class="calnedar title-toggle">◾ 4대보험</div>
                                <div class="calendar-link link">
                                    <div class="calendar-wirte toggle"><a href="/orca/accountingDivision/ratesByOne">◽ 조회 - 수정</a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </aside>

                    <main>
                        <div class="main" id="content">
                            <h1>회계부</h1>

                            <!-- 메인페이지 뭐 할지 생각해보기 -->
                        </div>
                    </main>
            </body>

            </html>

            <style>
                /* main 요소 중앙 배치 */
                main {
                    display: flex;
                    justify-content: center;
                    /* 수평 중앙 정렬 */
                    align-items: center;
                    /* 수직 중앙 정렬 */
                    height: 100vh;
                    /* 전체 화면 높이 */
                }

                /* 메인 콘텐츠 박스 스타일 */
                .main {
                    width: 80%;
                    max-width: 800px;
                    background-color: #ffffff;
                    /* 흰색 배경 */
                    padding: 40px;
                    border-radius: 8px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    text-align: center;
                    /* 가운데 정렬 */
                    box-sizing: border-box;
                    margin-bottom: 50%;
                }

                /* 제목 스타일 */
                h1 {
                    font-size: 36px;
                    color: #333333;
                    /* 어두운 회색 */
                    margin: 0;
                    padding-bottom: 20px;
                    border-bottom: 2px solid #87CEEB;
                    /* 하늘색 밑줄 */
                }

                /* 기본 a 태그 스타일 */
                a {
                    color: #000000;
                    /* 기본 글자색을 검정색으로 설정 */
                    text-decoration: none;
                    /* 밑줄 제거 */
                }

                /* a 태그에 마우스를 올렸을 때 스타일 */
                a:hover {
                    color: #87CEEB;
                    /* 하늘색으로 변경 */
                }
            </style>
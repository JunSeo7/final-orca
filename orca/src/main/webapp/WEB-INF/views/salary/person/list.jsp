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
                        <h1 id="salary-list">개인명세서 목록</h1>

                        <table class="salaryPersonList">
                            <thead>
                                <tr>
                                    <th>글 번호</th>
                                    <th>사번</th>
                                    <th>이름</th>
                                    <th>최종 급여(원)</th>
                                    <th>날짜</th>
                                    <th>상세조회</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- 데이터가 여기에 삽입됩니다. -->
                            </tbody>
                        </table>

                        <div id="detailArea">
                            <!-- 상세조회 내용이 여기에 삽입됩니다. -->
                        </div>
                    </main>
            </body>

            </html>


            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            <script>

                salaryPersonList();

                function salaryPersonList() {
                    $.ajax({
                        url: "http://127.0.0.1:8080/orca/salary/person/list",
                        method: "get",
                        success: function (x) {
                            console.log(x);
                            $('div.editArea').remove();

                            const voList = x;
                            console.log(voList);

                            const tbody = document.querySelector("tbody");
                            let str = "";

                            // 날짜에서 시간 부분을 제거하는 함수
                            function formatDate(dateTime) {
                                // YYYY-MM-DD HH:mm:ss -> YYYY-MM-DD
                                return dateTime.split(' ')[0];
                            }

                            for (let i = 0; i < voList.length; ++i) {
                                str += "<tr>";
                                str += "<td>" + voList[i].payrollNo + "</td>";
                                str += "<td>" + voList[i].empNo + "</td>";
                                str += "<td>" + voList[i].name + "</td>";
                                str += "<td>" + voList[i].totalSalary + "</td>";
                                str += "<td>" + formatDate(voList[i].paymentDate) + "</td>"; // 날짜에서 시간 제거
                                str += "<td><button onclick='detail(" + voList[i].payrollNo + ");'>조회</button></td>";
                                str += "</tr>";
                            }

                            tbody.innerHTML = str;
                        },
                    });
                }



            </script>

            <script>


                function detail(payrollNo) {
                    $('h1.salary-list').remove();
                    $('table.salaryPersonList').remove();

                    console.log(payrollNo);

                    $.ajax({
                        url: "/orca/salary/person/detail",
                        method: "GET",
                        data: {
                            payrollNo: payrollNo
                        },
                        success: function (data) {
                            const detailArea = document.querySelector("#detailArea");
                            console.log(data);

                            // 날짜에서 시간 부분을 제거하는 함수
                            function formatDate(dateTime) {
                                // YYYY-MM-DD HH:mm:ss -> YYYY-MM-DD
                                return dateTime.split(' ')[0];
                            }

                            let str = "";
                            str += "<h1>급여 상세조회</h1>";
                            str += "<div class='detail-item'><span>번호 :</span><span>" + data.payrollNo + "</span></div>";
                            str += "<div class='detail-item'><span>사원번호 :</span><span>" + data.empNo + "</span></div>";
                            str += "<div class='detail-item'><span>이름 :</span><span>" + data.name + "</span></div>";
                            str += "<div class='detail-item'><span>국민연금 :</span><span>" + data.nationalPension + "</span></div>";
                            str += "<div class='detail-item'><span>건강보험 :</span><span>" + data.healthInsurance + "</span></div>";
                            str += "<div class='detail-item'><span>장기요양보험 :</span><span>" + data.longCare + "</span></div>";
                            str += "<div class='detail-item'><span>고용보험 :</span><span>" + data.employmentInsurance + "</span></div>";
                            str += "<div class='detail-item'><span>소득세 :</span><span>" + data.incomeTax + "</span></div>";
                            str += "<div class='detail-item'><span>지방소득세 :</span><span>" + data.localIncomeTax + "</span></div>";
                            str += "<div class='detail-item'><span>직급수당 :</span><span>" + data.position + "</span></div>";
                            str += "<div class='detail-item'><span>보너스 :</span><span>" + data.bonus + "</span></div>";
                            str += "<div class='detail-item'><span>휴일근무수당 :</span><span>" + data.holiday + "</span></div>";
                            str += "<div class='detail-item'><span>연장근로수당 :</span><span>" + data.overTimeWork + "</span></div>";
                            str += "<div class='detail-item'><span>식대 :</span><span>" + data.meals + "</span></div>";
                            str += "<div class='detail-item'><span>최종급여 :</span><span>" + data.totalSalary + "</span></div>";
                            str += "<div class='detail-item'><span>지급날짜 :</span><span>" + formatDate(data.paymentDate) + "</span></div>";

                            str += "<a href='http://127.0.0.1:8080/orca/personList'>목록으로 돌아가기</a>";

                            detailArea.innerHTML = str;
                        },
                    });
                }


            </script>




            <style>
                /* 메인 컨테이너 스타일 */
                .personSalary {
                    width: 100%;
                    max-width: 1000px;
                    /* 너비를 최대 1000px로 설정하여 중앙 정렬 유지 */
                    margin: 20px auto;
                    /* 자동 좌우 여백으로 중앙 정렬 */
                    padding: 20px;
                    background-color: #ffffff;
                    /* 배경색을 흰색으로 설정 */
                    border-radius: 8px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    box-sizing: border-box;
                }

                /* 제목 스타일 */
                #salary-list {
                    font-size: 24px;
                    color: #333333;
                    margin-bottom: 20px;
                    text-align: center;
                    /* 제목 중앙 정렬 */
                }

                /* 테이블 스타일 */
                .salaryPersonList {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 20px;
                }

                .salaryPersonList th,
                .salaryPersonList td {
                    padding: 12px;
                    text-align: center;
                    border: 1px solid #cccccc;
                }

                .salaryPersonList th {
                    background-color: #f1f1f1;
                }

                .salaryPersonList tr:nth-child(even) {
                    background-color: #f9f9f9;
                }

                .salaryPersonList button {
                    padding: 8px 16px;
                    background-color: #007bff;
                    color: #ffffff;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    transition: background-color 0.3s ease;
                    font-size: 14px;
                }

                .salaryPersonList button:hover {
                    background-color: #0056b3;
                }

                /* 상세조회 스타일 */
                #detailArea {
                    width: 100%;
                    max-width: 1000px;
                    /* 너비를 최대 1000px로 설정하여 중앙 정렬 유지 */
                    margin: 20px auto;
                    /* 자동 좌우 여백으로 중앙 정렬 */
                    padding: 20px;
                    background-color: #ffffff;
                    /* 배경색을 흰색으로 설정 */
                    border: 1px solid #cccccc;
                    /* 테두리 추가 */
                    border-radius: 8px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    box-sizing: border-box;
                }

                /* 상세조회 제목 스타일 */
                #detailArea h1 {
                    font-size: 24px;
                    color: #333333;
                    margin-bottom: 20px;
                    text-align: center;
                    /* 제목 중앙 정렬 */
                }

                /* 상세조회 항목 스타일 */
                #detailArea h3 {
                    font-size: 18px;
                    color: #555555;
                    margin: 10px 0;
                }

                /* 상세조회 항목 내용 스타일 */
                #detailArea .detail-item {
                    display: flex;
                    justify-content: space-between;
                    padding: 10px;
                    border-bottom: 1px solid #eeeeee;
                }

                #detailArea .detail-item:last-child {
                    border-bottom: none;
                }

                /* 돌아가기 링크 스타일 */
                #detailArea a {
                    display: inline-block;
                    margin-top: 20px;
                    padding: 10px 20px;
                    background-color: #007bff;
                    color: #ffffff;
                    text-decoration: none;
                    border-radius: 5px;
                    font-size: 16px;
                    transition: background-color 0.3s ease;
                }

                #detailArea a:hover {
                    background-color: #0056b3;
                }
            </style>
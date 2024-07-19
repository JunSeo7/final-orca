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


                       

                        <table class="salaryPsersonList">

                            <thead>
                                <tr>
                                    <th>글 번호</th>
                                    <th>사번</th>
                                    <th>이름</th>
                                    <th>최종 급여(원)</th>
                                    <th>날짜</th>
                                    <th>상세조회</th>
                                    <!-- <button onclick="detail();">조회</button> -->
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>


                        </table>

                        <div id="detailArea">

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
                            for (let i = 0; i < voList.length; ++i) {
                                str += "<tr>";
                                str += "<td>" + voList[i].payrollNo + "</td>";
                                str += "<td>" + voList[i].empNo + "</td>";
                                str += "<td>" + voList[i].name + "</td>";
                                str += "<td>" + voList[i].totalSalary + "</td>";
                                str += "<td>" + voList[i].paymentDate + "</td>";
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


                function detail(payrollNo) {
                    $('h1.salary-list').remove();
                    $('table.salaryPsersonList').remove();
                    $.ajax({
                        url: "http://127.0.0.1:8080/orca/salary/person/detail",
                        method: "GET",
                        data: {

                            payrollNo: payrollNo,
                            empNo: empNo

                        },
                        success: function (data) {
                            const detailArea = document.querySelector("#detailArea");
                            console.log(data);
                            let str = "";
                            str += "<h1>급여 상세조회</h1>";
                            str += "<h3>번호 : " + data.payrollNo + "</h3>";
                            str += "<h3>사원번호 : " + data.empNo + "</h3>";
                            str += "<h3>이름 : " + data.name + "</h3>";
                            str += "<h3>국민연금 : " + data.nationalPension + "</h3>";
                            str += "<h3>건강보험 : " + data.healthInsurance + "</h3>";
                            str += "<h3>장기요양보험 : " + data.longCare + "</h3>";
                            str += "<h3>고용보험 : " + data.employmentInsurance + "</h3>";
                            str += "<h3>소득세 : " + data.incomeTax + "</h3>";
                            str += "<h3>지방소득세 : " + data.localIncomeTax + "</h3>";
                            str += "<h3>직급수당 : " + data.position + "</h3>";
                            str += "<h3>보너스 : " + data.bonus + "</h3>";
                            str += "<h3>휴일근무수당 : " + data.holiday + "</h3>";
                            str += "<h3>연장근로수당 : " + data.overTimeWork + "</h3>";
                            str += "<h3>식대 : " + data.meals + "</h3>";
                            str += "<h3>최종급여 : " + data.totalSalary + "</h3>";
                            str += "<h3>지급날짜 : " + data.paymentDate + "</h3>";

                            str += "<a href='http://127.0.0.1:8080/orca/salaryList'>목록으로 돌아가기</a>";
                            detailArea.innerHTML = str;


                        },


                    });
                }



            </script>

            <style>
                .personSalary{
                    margin-top: 20%;
                    margin-left: 45%;
                }



            </style>

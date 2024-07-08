<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>

        <head>
            <title>휴가서 작성</title>
            <!-- 파비콘 -->
            <link rel="icon" href="img/logo.png" type="image/png">
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

            <script defer src="/js/vacation/vacationWrite.js"></script>
            <link rel="stylesheet" href="/css/vacation/vacationWrite.css">

        </head>

        <body>
            <%@ include file="/WEB-INF/views/layout/header.jsp" %>
                <aside id="sidebar">
                    <div class="profile" onclick="toggleProfile()">
                        <img src="profile.png" alt="Profile Picture" class="profile-pic">
                        <p>SW팀 | <span>양파쿵야</span></p>
                    </div>
                    <hr>
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
                    <nav>
                                <ul>
                                    <li><a href="#" onclick="toggleSubMenu('attendanceMenu')">근태</a>
                                        <ul id="attendanceMenu" class="submenu hidden">
                                                <li><a href="/orca/work/workInfo">근무정보</a></li>
                                                <li><a href="/orca/vacation/vacationWrite">휴가신청</a></li>
                                                <li><a href="/orca/sick/sickLeave">병가신청</a></li>
                                                <li><a href="/orca/etc/etcWrite">기타신청</a></li>
                                            </ul>
                                    </li>
                                </ul>
                            </nav>
                </aside>

                <main>
                    <h1>휴가 신청서</h1>
                    <form id="documentForm" method="post" action="/orca/vacation/vacationWrite">
                        <table class="document-table">
                            <tr>
                                <th>결재선 프로세스</th>
                                <td>
                                    <select id="categoryNo" name="categoryNo"
                                        onChange="fetchTemplatesByCategory(this.value)">
                                        <!-- 카테고리 옵션 -->
                                    </select>
                                </td>
                                <th>결재 양식</th>
                                <td>
                                    <select id="templateNo" name="templateNo">
                                        <!-- 결재 양식 옵션 -->
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>결재선</th>
                                <td colspan='3'>
                                    <select id="approver" name="approver" required>
                                        <!-- 디폴트로 양식에 있는 기본결재선이 나오고, 수정버튼을 누르면 나만의 결재선 제목목록을 보여줌 -->
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>결재선 프로세스</th>
                                <td colspan='3'>
                                    <div class="approval-process" name="approvalLineVoList">
                                        <!-- 결재선 프로세스 -->
                                    </div>
                                    <input type="button" value="수정">
                                </td>
                            </tr>
                            <tr>
                                <th>공람(참조인)</th>
                                <td colspan='3'>
                                    <select id="approver" name="approver" required>
                                        <!-- 디폴트로 양식에 있는 기본결재선이 나오고, 수정버튼을 누르면 나만의 결재선 제목목록을 보여줌 -->
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <table class="document-table">
                            <tr>
                                <th>제목</th>
                                <td>
                                    <input type="text" name="title" id="title">
                                </td>
                                <th>휴가 종류</th>
                                <td>
                                    <select id="vacationCode" name="vacationCode"
                                        onChange="fetchVacationCode(this.value)">

                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th colspan='4'> 요청 내용 </th>
                            </tr>
                            <tr>
                                <th>날짜 선택</th>
                                <td colspan='4'>
                                    시작 날짜 : <input type="date" id="start-date" name="start-date"
                                        style="color: dimgray;">
                                    <br>
                                    종료 날짜 : <input type="date" id="end-date" name="end-date" style="color: dimgray;">
                                </td>
                            </tr>
                            <tr>
                                <th>사유</th>
                                <td colspan='4'><textarea name="reason" id="reason" style="width: 100%;"></textarea>
                                </td>
                            </tr>
                            </td>
                            </tr>
                            <tr>
                                <th>첨부파일</th>
                                <td colspan='4'>
                                    <input type="file" name="attachment">
                                </td>
                            </tr>
                            <tr>
                                <th>상태</th>
                                <td colspan='4'>
                                    <select name="status" id="status">
                                        <option value="1">임시저장</option>
                                        <option value="2">기안</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <br>
                        <div class="div-container">
                            <button type="button" onclick="submitDocument()" class="approval-btn">기안</button>
                        </div>
                    </form>
                </main>
        </body>

        </html>
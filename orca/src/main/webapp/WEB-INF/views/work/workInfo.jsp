<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>ORCA</title>

            <link rel="stylesheet" href="/css/work/workInfo.css">
            <script defer src="/js/work/workInfo.js"></script>

        </head>

        <body>
            <%@ include file="/WEB-INF/views/layout/header.jsp" %>
                <%@ include file="/WEB-INF/views/enroll/aside.jsp" %>

                    <main id="content">

                        <div class="container">
                            <h1>근무 정보</h1>
                            <div class="date-selection">
                                <label for="start-date">기간 : </label>
                                <input type="date" id="start-date" name="start-date" style="color: dimgray;">
                                <label for="end-date">-</label>
                                <input type="date" id="end-date" name="end-date" style="color: dimgray;">
                                <button onclick="fetchData()" class="t-btn">조회</button>
                            </div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>날짜</th>
                                        <th>요일</th>
                                        <th>출근</th>
                                        <th>퇴근</th>
                                        <th>지각</th>
                                        <th>조퇴</th>
                                        <th>결근</th>
                                        <th>연장(시간)</th>
                                        <th>휴무(시간)</th>
                                    </tr>
                                </thead>
                                <tbody id="data-table-body">
                                    <!-- 데이터 표시 -->
                                </tbody>
                            </table>
                        </div>

                    </main>
        </body>

        </html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ORCA</title>
    <link rel="stylesheet" href="/css/work/workInfo.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <%@ include file="/WEB-INF/views/enroll/aside.jsp" %>

    <main id="content">
        <div class="calendar-container">
            <div class="calendar-header">
                <button id="prevMonth">&lt;</button>
                <div id="calendarHeader"></div>
                <button id="nextMonth">&gt;</button>
            </div>
            <div class="calendar-body">
                <div class="weekdays">
                    <div>일</div>
                    <div>월</div>
                    <div>화</div>
                    <div>수</div>
                    <div>목</div>
                    <div>금</div>
                    <div>토</div>
                </div>
                <div class="days" id="daysContainer"></div>
            </div>
        </div>
    </main>

    <script src="/js/work/workInfo.js"></script>
</body>
</html>

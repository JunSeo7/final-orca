<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ORCA</title>
    <link rel="stylesheet" href="/css/work/workInfo.css">
        <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <%@ include file="/WEB-INF/views/enroll/aside.jsp" %>

    <main id="content">
        <div id="calendar">

        </div>
    </main>

    <script src="/js/work/workInfo.js"></script>
</body>
</html>

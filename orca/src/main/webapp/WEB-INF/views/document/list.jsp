<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재 목록</title>
    <link rel="icon" href="/img/logo.png" type="image/png">
        <!--파비콘-->
        <link rel="icon" href="/img/logo.png" type="image/png">

        <link rel="stylesheet" href="/css/document/list.css">
        <script defer src="/js/document/list.js"></script>
  </head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/document/aside.jsp" %>
<main id="content" class="main-content">
    <div class="container">
        <h2>기안서 목록</h2>
        <div class="search_box">
            <select class="search_select">
                <option>기안자</option>
                <option>제목</option>
                <option>내용</option>
            </select>
            <input class="search_text" type="text" placeholder="검색어 입력">
            <img class="search_img" src="/img/document/search.png" alt="검색 아이콘">
        </div>

        <c:forEach var="document" items="${documentList}">
            <div class="approval_status">
              <p>${document.creditDate}</p>
                <div class="status_box">
                    <div class="status_details">
                        <img src="/img/profile.png" alt="Profile Picture" class="profile-pic-small">
                        <span class="approval_title">${document.docNo}</span>
                        <span class="approval_title">${document.title}</span>
                        <span class="approval_title">[${document.categoryName}] ${document.templateTitle}</span>
                        <span class="approval_title">긴급여부: ${document.urgent}</span>
                        <div class="status_steps">
                            <div class="status_step">
                                <p>${document.statusName}</p>
                                <p>${document.deptName}</p>
                                <p>${document.writerName}[${document.positionName}]</p>
                                <p>${document.creditDate}</p>
                            </div>
                            <!-- 결재 진행 상태 추가 -->
                            <c:forEach var="approver" items="${document.approverVoList}">
                                <div class="status_step">
                                    <p>${approver.seq}</p>
                                    <p>${approver.apprStageName}</p>
                                    <p>${approver.deptName}</p>
                                    <p>${approver.approverName}[${approver.positionName}]</p>
                                    <p>${approver.approvalDate}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</main>
</body>
</html>

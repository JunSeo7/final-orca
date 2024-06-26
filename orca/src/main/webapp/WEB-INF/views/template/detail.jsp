<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재양식 상세</title>
    <link rel="icon" href="/img/logo.png" type="image/png">

    <link rel="stylesheet" href="/css/template/css">
    <script defer src="/js/template/js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/template/aside.jsp" %>

<main>
    <h2>결재양식 상세</h2>
             <div class="approval-lines-btn">
                 <img class="edit_img" src="/img/edit.png" alt="수정 아이콘">
                 <img class="delete_img" src="/img/delete.png" alt="삭제 아이콘">
             </div>
        <div class="template-detail-box">
            <div class="template-detail">

                <div class="template-detail-box">
                    <div class="template-detail">
                        <span class="detail-title">카테고리: ${templateDetail.categoryName}</span><br>
                        <span class="detail-title">양식명: ${templateDetail.title}</span><br>
                        <span class="detail-content">내용: ${templateDetail.content}</span><br>
                        <span class="detail-enroll">생성날짜: ${templateDetail.enrollDate}</span><br>
                    </div>

                    <div class="approver-detail">
                        <c:forEach var="approver" items="${templateDetail.apprLineList}">
                            <div class="approver-item">
                                <span class="detail-approver">결재자: ${approver.approverName}</span><br>
                                <span class="detail-dept">부서: ${approver.deptName}</span><br>
                                <span class="detail-team">팀: ${approver.teamName}</span><br>
                                <span class="detail-position">직위: ${approver.positionName}</span><br>
                                <span class="detail-comment">코멘트: ${approver.comment}</span><br>
                            </div>
                            <hr>
                        </c:forEach>
                    </div>
                </div>



                </div>

        </div>

    </main>

</body>
</html>

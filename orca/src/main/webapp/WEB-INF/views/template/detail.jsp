<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재양식 상세</title>
    <link rel="icon" href="/img/logo.png" type="image/png">

    <link rel="stylesheet" href="/css/template/detail.css">
    <script defer src="/js/template/detail.js"></script>
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
             <table class="document-body">
               <!-- User -->
               <tbody>
                 <tr>
                   <td colspan="6">양식번호: <span>${templateDetail.templateNo}</span></td>
                 </tr>
                 <tr>
                   <td class="user-info-header">카테고리</td>
                   <td class="user-info-data">${templateDetail.categoryName}</td>
                   <td class="user-info-header">생성날짜</td>

                   <td class="user-info-data">
                     ${templateDetail.enrollDate}
                     <fmt:formatDate pattern='yyyy-MM-dd' value='${list.edocVO.edocDt}' />
                   </td>
                 </tr>
               </tbody>
             </table>

                     <table class="document-body">
                       <!-- Draft -->
                       <tbody>
                         <tr>
                           <td class="document-body-header">양식명</td>
                           <td class="document-body-data"> ${templateDetail.title}</td>
                         </tr>
                         <tr>
                           <td class="document-body-header" colspan="2">양식내용</td>
                         </tr>
                         <tr>
                           <td class="document-body-data" colspan="2">
                             ${templateDetail.content}
                           </td>
                         </tr>
                       </tbody>
                     </table>

                    <div class="approval-table">
                        <c:forEach var="approver" items="${templateDetail.apprLineList}">
                            <div class="approver-item">
                                <span class="detail-approver">결재자: ${approver.approverName}</span><br>
                                <span class="detail-dept">부서: ${approver.deptName}</span><br>
                                <span class="detail-team">팀: ${approver.teamName}</span><br>
                                <span class="detail-position">직위: ${approver.positionName}</span><br>
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

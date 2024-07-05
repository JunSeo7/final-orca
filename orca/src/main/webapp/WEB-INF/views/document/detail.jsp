<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재 상세 보기</title>
    <link rel="icon" href="/img/logo.png" type="image/png">
    <!--파비콘-->
    <link rel="icon" href="/img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/document/detail.css">
    <script defer src="/js/document/detail.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/document/aside.jsp" %>
<main>
    <div class="document-content">
        <!-- 문서 내용 표시 -->
        <div class="document-header">
            <!-- Header -->
            <div class="form-title">${document.templateTitle}</div>
            <div class="approval-section">
                <div class="approval-row">
                    <div class="approval-cell">
                        <p>${document.statusName}</p>
                        <p>${document.deptName}</p>
                        <p>${document.writerName}[${document.positionName}]</p>
                        <p>${document.creditDate}</p>
                    </div>
                    <c:forEach var="approver" items="${document.approverVoList}">
                        <div class="approval-cell">
                            <p>${approver.seq}</p>
                            <p>${approver.approverClassificationNo}</p>
                            <p>${approver.apprStageName}</p>
                            <p>${approver.deptName}</p>
                            <p>${approver.approverName}[${approver.positionName}]</p>
                            <p>${approver.approvalDate}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <table class="document-body">
            <!-- User -->
            <tbody>
            <tr>
                <td colspan="6">문서번호: <span>${document.docNo}</span></td>
            </tr>
            <tr>
                <td class="user-info-header">기안자</td>
                <td class="user-info-data">${document.writerName}</td>
                <td class="user-info-header">기안부서</td>
                <td class="user-info-data">${document.deptName}</td>
                <td class="user-info-header">기안일자</td>
                <td class="user-info-data">${document.creditDate}</td>
            </tr>
            <tr>
                <td class="user-info-header">공람(참조인)</td>
                <td class="user-info-data" colspan="5">참조인 이름 (부서) 리스트~</td>
            </tr>
            </tbody>
        </table>
        <table class="document-body">
            <!-- Draft -->
            <tbody>
            <tr>
                <td class="document-body-header">제목</td>
                <td class="document-body-data">${document.title}</td>
            </tr>
            <tr>
                <td class="document-body-header" colspan="2">요청내용</td>
            </tr>
            <tr>
                <td class="document-body-data" colspan="2">${document.content}</td>
            </tr>
            </tbody>
            <tr>
                <td class="document-body-header">첨부파일</td>
                <td class="document-body-data">
                    <c:set var="fileList" value="${files}" />
                    <c:choose>
                        <c:when test="${empty files}">
                            <p>첨부된 파일이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="file" items="${files}">
                                <a href="/resources/upload${file.changeName}" download>${file.changeName}</a><br />
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
           <tr>
               <td class="document-body-header" colspan="2">코멘트</td>
           </tr>
           <c:forEach var="approver" items="${document.approverVoList}">
               <tr>
                   <td class="document-body-header">${approver.approverName}[${approver.positionName}]</td>
                   <td class="document-body-data">
                       <c:choose>
                           <c:when test="${empty approver.comment}">
                               <p>코멘트가 없습니다.</p>
                           </c:when>
                           <c:otherwise>
                               <p>${approver.comment}</p>
                           </c:otherwise>
                       </c:choose>
                   </td>
               </tr>
           </c:forEach>
       <form id="approvalForm" method="post" action="/orca/document/status">
          <tr>
              <td class="document-body-header">${sessionScope.loginUser.name}[${sessionScope.loginUser.nameOfPosition}]</td>
               <td class="document-body-data">
                   <textarea id="myComment" name="myComment" placeholder="코멘트를 작성하세요"></textarea>
               </td>
         </tr>
        </table>
    </div>
    <div class="button-container">
       <c:if test="${sessionScope.loginUser.empNo == document.writerNo}">
           <button type="button" class="approval-btn" onclick="submitForm(4)">결재 취소</button> <!-- 4: 결재 취소 -->
       </c:if>

       <c:forEach var="approver" items="${document.approverVoList}">
           <c:if test="${sessionScope.loggedInUser.empNo == approver.approverNo}">
               <button type="button" class="approval-btn" onclick="submitForm(3)">승인</button> <!-- 3: 승인 -->
               <button type="button" class="approval-btn" onclick="submitForm(2)">반려</button> <!-- 2: 반려 -->
           </c:if>
       </c:forEach>
    </div>
</main>
</body>
</html>

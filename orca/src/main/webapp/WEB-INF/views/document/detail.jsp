<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
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
<main id="content">
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
                        <p>${document.writerName} ${document.positionName}</p>
                        <p>${document.creditDate}</p>
                    </div>
                    <c:forEach var="approver" items="${document.approverVoList}">
                        <div class="approval-cell">
                            <p>${approver.seq}</p>
                            <p>${approver.approverClassificationNo}</p>
                            <p>${approver.apprStageName}</p>
                            <p>${approver.deptName}</p>
                            <p>${approver.approverName} ${approver.positionName}</p>
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
                <td class="user-info-header">공람(참조자)</td>
                <td class="user-info-data" colspan="5">

                <c:set var="referencers" value="${document.referencerVoList}" />
                    <c:choose>
                        <c:when test="${empty referencers}">
                            <p>참조인이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="referencer" items="${referencers}">
                               [${referencer.deptName}] ${referencer.referrerName} ${referencer.positionName},
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </td>
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


                <form id="approvalForm" method="post" action="/orca/apprline/status">
                     <input hidden name="docNo" value="${document.docNo}">
                    <table>
                        <tr>
                            <td class="document-body-data">
                                <textarea id="comment" name="comment" placeholder="코멘트를 작성하세요"></textarea>
                            </td>
                        </tr>
                    </table>

                        <div>
                            <input type="radio" name="approvalStage" id="approve" class="radio-input" value="2" checked="checked" /><!-- 2: 승인 -->
                            <label for="approve" class="radio-label">승인</label>
                            <input type="radio" name="approvalStage" id="reject" class="radio-input" value="3" /><!-- 3: 반려 -->
                            <label for="reject" class="radio-label">반려</label>
                        </div>
                    <button type="submit" class="approval-btn">결과처리</button>
               </form>


</main>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <title>결재 상세 보기</title>
    <!--파비콘-->
    <link rel="icon" href="/img/header/logo.png" type="image/png">
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
                    <div class="approval-cell">
                        <div class="apprClassification">기안자</div>
                        <div class="status">${document.statusName}</div>
                        <div>${document.writerName} ${document.positionName}</div>
                        <div>${document.deptName}</div>
                    </div>
                    <c:forEach var="approver" items="${document.approverVoList}">
                        <div class="approval-cell">
                            <c:choose>
                                <c:when test="${approver.approverClassificationNo == 1}">
                                    <div class="apprClassification">결재자</div>
                                </c:when>
                                <c:when test="${approver.approverClassificationNo == 2}">
                                   <div class="apprClassification">합의자</div>
                                </c:when>
                                <c:otherwise>
                                    ${approver.approverClassificationNo}
                                </c:otherwise>
                            </c:choose>
                            <div class="stage stage_${approver.approvalStage}">${approver.apprStageName}</div>
                            <div>${approver.approverName} ${approver.positionName}</div>
                            <div>${approver.deptName}</div>
                        </div>
                    </c:forEach>

            </div>
        </div>

        <table class="document-body">
            <!-- User -->
            <tbody>
            <tr>
                <td colspan="5">문서번호: <span>${document.docNo}</span></td>
                <!-- 수정 및 삭제 버튼 -->
                <th>
                    <c:if test="${document.status == 1}">
                        <div>
                            <a href="/orca/document/edit?docNo=${document.docNo}"  doc-no="${document.docNo}">
                                <img class="edit-btn" src="/img/document/edit.png" alt="수정 아이콘">
                            </a>
                            <a onclick="/orca/document/delete?docNo=${document.docNo}" doc-no="${document.docNo}">
                                <img class="delete-btn" src="/img/document/delete.png" alt="삭제 아이콘">
                            </a>
                        <div>
                    </c:if>
                </th>
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
                            <span>참조인이 없습니다.</span>
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
                    <c:set var="files" value="${document.fileVoList}" />
                    <c:choose>
                        <c:when test="${empty files}">
                            <p>첨부된 파일이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="file" items="${files}">
                                <img src="/static/upload/document/${file.changeName}" alt="${file.changeName}" class="attachment-img"><br/>
                                <a href="/static/upload/document/${file.changeName}" download> ${file.changeName}</a><br/>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:if test="${not empty document.approverVoList}">
                <c:set var="commentExists" value="false" />
                <c:forEach var="approver" items="${document.approverVoList}">
                    <c:if test="${not empty approver.comment}">
                        <c:if test="${commentExists == false}">
                            <tr>
                                <td class="document-body-header" colspan="2">코멘트</td>
                            </tr>
                            <c:set var="commentExists" value="true" />
                        </c:if>
                        <tr>
                            <td class="document-body-header">${approver.approverName}[${approver.positionName}]</td>
                            <td class="document-body-data">
                                <span>${approver.comment}</span>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                <c:if test="${commentExists == false}">
                    <tr>
                        <td class="document-body-data" colspan="2"><span>코멘트가 없습니다.</span></td>
                    </tr>
                </c:if>
            </c:if>



            <c:if test="${document.myTurn}">
                <form id="approvalForm" method="post" action="/orca/apprline/status">
                     <input hidden name="docNo" value="${document.docNo}">

                    <table class="document-body">
                        <tr>
                            <td class="document-body-data">
                                <textarea class="comment" id="comment" name="comment" placeholder="코멘트를 작성하세요"></textarea>
                            </td>

                            <td>
                                <input type="radio" name="approvalStage" id="approve" class="radio-input" value="2" checked="checked" /><!-- 2: 승인 -->
                                <label for="approve" class="radio-label">승인</label>
                                <input type="radio" name="approvalStage" id="reject" class="radio-input" value="3" /><!-- 3: 반려 -->
                                <label for="reject" class="radio-label">반려</label>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <button type="submit" class="approval-btn">결과처리</button>
               </form>
            </c:if>

</main>
</body>
</html>

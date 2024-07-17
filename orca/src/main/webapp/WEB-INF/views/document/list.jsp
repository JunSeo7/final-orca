<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>결재 목록</title>
        <!--파비콘-->
        <link rel="icon" href="/img/header/logo.png" type="image/png">

        <link rel="stylesheet" href="/css/document/list.css">
        <script src="/js/document/list.js"></script>
  </head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/document/aside.jsp" %>
<main id="content">
    <div class="container">

    <div class ="content-title">
        <h2>기안서 목록</h2>
        <div class="search_box">

            <select class="search_select" id="searchType">
                <option value="writerName">기안자</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select>

            <input class="search_text" type="text" id="searchText" placeholder="검색어 입력">
            <img class="search_img" src=alt="검색 아이콘" id="searchButton" alt="검색 아이콘" id="searchButton">
        </div>
    </div>

    <hr>

<c:choose>
     <c:when test="${empty documentList}">
            <div class="no-document">해당 상태의 결재문서가 없습니다.</div>
    </c:when>
    <c:otherwise>
            <div id="documentList">
                <c:forEach var="document" items="${documentList}">
                    <div class="document">
                      <p>${document.creditDate}</p>
                        <div class="status_box status_box_${document.status}" data-doc-no="${document.docNo}">
                            <div class="status_details">

                                <div class="document_info">
                                <div class="document_info_inner">
                                    <img src="/img/header/profile.png" alt="Profile Picture" class="profile-pic-small">

                                    <div>
                                        <div class="docTitle">${document.title} [${document.docNo}]</div>
                                        <div class="docTemplate">[${document.categoryName}] ${document.templateTitle}</div>
                                    </div>
                                    </div>
                                    <c:if test="${document.urgent == 'Y'}">
                                        <div class="urgent_yn">🔴</div>
                                    </c:if>
                                </div>

                                <div class="status_steps">
                                    <div class="status_step writer_${document.status}">
                                        <div class="statusName">${document.statusName}[${document.status}]</div>
                                        <div class="writerName">${document.writerName}[${document.positionName}]</div>
                                        <div>${document.deptName}</div>
                                        <div>${document.creditDate}</div>
                                    </div>
                                    <img class="rightChevronIcon" src="/img/document/right-chevron.png" alt="화살표 아이콘" id="rightChevron">

                                    <!-- 결재 진행 상태 추가 -->
                                    <c:forEach var="approver" items="${document.approverVoList}" varStatus="status">
                                        <div class="status_step appr_${approver.approvalStage}">
                                            <div class="stageName">[${approver.seq}][${approver.apprStageName}]${approver.approvalStage}</div>
                                            <div class="approverName">${approver.approverName}[${approver.positionName}]</div>
                                            <div>[${approver.approverClassificationNo}]${approver.deptName}</div>
                                            <div>${approver.approvalDate}</div>
                                        </div>
                                        <c:if test="${status.index != document.approverVoList.size() - 1}">
                                            <img class="rightChevronIcon" src="/img/document/right-chevron.png" alt="화살표 아이콘" id="rightChevron">
                                        </c:if>
                                    </c:forEach>


                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</main>
</body>
</html>

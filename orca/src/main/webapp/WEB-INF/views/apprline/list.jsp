<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>결재선 목록</title>
    <link rel="icon" href="/img/logo.png" type="image/png">
        <link rel="stylesheet" href="/css/approvalLine/list.css">
        <script defer src="/js/approvalLine/list.js"></script>

         <!--Bootstrap Icons 라이브러리 연결-->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/themes/default/style.min.css" />
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <!--js 라이브러리 연결-->
            <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/jstree.min.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/template/aside.jsp" %>

<main id="content">
    <h2>기본 결재선 관리</h2>
    <hr>
    <p>* 결재양식의 기본 결재선을 등록하고 관리합니다.</p>
    <button class="approval-btn" onclick="showApprovalLinePopup()">+ 결재선 등록</button>

<c:choose>
     <c:when test="${empty approvalLines}">
            <div class="no-approval-lines">등록된 기본 결재선이 없습니다.</div>
    </c:when>
    <c:otherwise>
     <div class="approval-lines-box">
        <c:forEach var="approvalLines" items="${approvalLines}">
            <div class="approval-lines">
                <!-- 결재선 이름 및 템플릿 제목 -->
                <span class="approval-lines-name">${approvalLines.apprLineName}</span>
                <span class="approval-title">[${approvalLines.categoryName}] ${approvalLines.title}</span>
                <br>
                <!-- 결재자 목록 -->
                <c:forEach var="approver" items="${approvalLines.approverVoList}">
                    <c:choose>
                        <c:when test="${approver.approverClassificationNo == 2}">
                            <span class="approver">합의:</span>
                        </c:when>
                        <c:otherwise>
                            <span class="approver">결재:</span>
                        </c:otherwise>
                    </c:choose>
                    <span class="approver">${approver.approverName} ${approver.positionName}</span>
                    <c:if test="${!status.last}">
                        <span>▶</span>
                    </c:if>
                </c:forEach>
                <hr>
                <!-- 수정 및 삭제 버튼 -->
                <a class="approval-lines-btn" onclick="openModal()"><img class="edit_img" src="/img/document/edit.png" alt="수정 아이콘"></a>
               <a class="approval-lines-btn delete-btn" data-apprline-no="${approvalLines.apprLineNo}">
                  <img class="delete_img" src="/img/document/delete.png" alt="삭제 아이콘">
               </a>
            </div>
            </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</main>


<!-- 결재선 선택 팝업 -->
<div class="popup-overlay" id="popupOverlay" onclick="closeApprovalLinePopup()"></div>
<div class="popup" id="approvalLinePopup">
    <div class="popup-header">
        <h2>결재선 선택</h2>
        <button onclick="closeApprovalLinePopup()" class="close">X</button>
    </div>
     <form id="approvalLineForm" action="/orca/apprline/add" method="post">
    <div class="popup-body">
        <div class="popup-body-left">
            <table>
                <tr>
                    <td class="t-title">결재선 프로세스</td>
                    <td>
                        <select id="categoryNo" name="categoryNo" onChange="fetchTemplatesByCategory(this.value)">
                        <!-- 카테고리 옵션들이 여기 추가될 예정 -->
                        </select>
                    </td>
                </tr>
                 <tr>
                    <td class="t-title">템플릿</td>
                    <td>
                        <select id="templateNo" name="templateNo">
                            <!-- 템플릿 옵션들이 여기 추가될 예정 -->
                        </select>
                    </td>
                 </tr>
                <tr>
                    <td class="t-title">결재선 이름</td>
                    <td><input type="text" name="title"></td>
                </tr>

            </table>
                <p>* 결재프로세스를 선택하고 결재선 이름을 입력하세요.</p>
                <p>* 선택한 결재프로세스에 맞게 결재선을 지정합니다</p>
                    <div class="approval-line" id="approvalLinePreview">
                        <!-- 선택된 결재선 미리보기 -->
                    </div>
                    <h4>결재자 선택</h4>
                    <div class="approval-role">
                        <label for="numSlots">합의/결재 칸 수:</label>
                        <input type="number" id="numSlots" value="3" min="1" max="5" onchange="createSlots()">
                    </div>
                </div>
                <div class="popup-body-right">
                <div id="jstree"></div>
            </div>
            <div class="popup-body-bottom">
                <div class="approval-selection" id="approvalSelection">
                    <!-- 동적으로 생성될 합의/결재 칸 -->
                </div>
            </div>
        </div>
    <div class="popup-footer">
    <button onclick="saveApprovalLine()" class="submit_btn">확인</button>
</div>
</form>
</div>

<!-- 결재선 수정 모달 -->
<div class="modal-overlay" id="modalOverlay" onclick="closeModal()"></div>
<div class="modal" id="approvalModal">
    <div class="modal-header">
        <h2>결재선 수정</h2>
        <button onclick="closeModal()" class="close">X</button>
    </div>
    <div class="modal-body">
        <div class="modal-body-left">
            <table>
                <tr>
                    <td class="title">결재선 프로세스</td>
                    <td>
                        <select id="modalProcessSelect">
                            <option value="휴가어쩌고">휴가어쩌고</option>
                            <option value="프로세스2">프로세스 2</option>
                            <option value="프로세스3">프로세스 3</option>
                            <option value="프로세스4">프로세스 4</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="title">결재선 이름</td>
                    <td><input type="text" id="modalApprovalLineName"></td>
                </tr>
            </table>
            <p>* 결재프로세스를 선택하고 결재선 이름을 입력하세요.</p>
            <p>* 선택한 결재프로세스에 맞게 결재선을 지정합니다</p>
            <div class="approval-preview" id="modalApprovalPreview">
                <!-- 선택된 결재선 미리보기 -->
            </div>
            <h4>결재자 선택</h4>
            <div class="approval-role">
                <label for="modalApprovalSlots">합의/결재 칸 수:</label>
                <input type="number" id="modalApprovalSlots" value="3" min="1" max="5" onchange="createModalSlots()">
            </div>
        </div>
        <div class="modal-body-right">
            <div id="treeView"></div>
        </div>
    </div>
    <div class="modal-body-bottom">
        <div class="approval-slots" id="modalApprovalSlotsContainer">
            <!-- 합의/결재 칸 -->
        </div>
    </div>
    <div class="modal-footer">
        <button onclick="saveModalApprovalLine()">확인</button>
        <button onclick="closeModal()">닫기</button>
    </div>
</div>
</body>
</html>

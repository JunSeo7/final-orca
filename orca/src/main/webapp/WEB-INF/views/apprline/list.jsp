<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재 양식 목록</title>
    <link rel="icon" href="/img/logo.png" type="image/png">
    <!--파비콘-->
    <link rel="icon" href="/img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/approvalLine/list.css">
    <script defer src="/js/approvalLine/list.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/document/aside.jsp" %>

<main id="content">
    <h2>개인 결재선 관리</h2>
    <hr>
    <p>* 개인적으로 사용할 결재선을 등록하고 관리합니다.</p>
    <button class="approval-btn" onclick="showApprovalLinePopup()">+ 결재선 등록</button>

    <c:choose>
        <c:when test="${empty approvalLines}">
            <div class="no-approval-lines">등록된 개인 결재선이 없습니다.</div>
        </c:when>
        <c:otherwise>
            <div class="approval-lines-box">
                <c:forEach var="template" items="${approvalLines}">
                    <div class="approval-lines">
                        <span class="approval-lines-name">${template.apprLineName}</span>
                        <span class="approval-title">[${template.categoryName}] ${template.title}</span>
                        <br>
                        <c:forEach var="approver" items="${template.apprLineList}">
                            <c:choose>
                                <c:when test="${approver.approverClassification == 2}">
                                    <span class="approver">합의:</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="approver">결재:</span>
                                </c:otherwise>
                            </c:choose>
                            <span class="approver">${approver.approverName} ${approver.positionName}</span>
                            <c:if test="${!approver.last}">
                                <span>▶</span>
                            </c:if>
                        </c:forEach>
                        <hr>
                        <a class="approval-lines-btn" onclick="openModal()"><img class="edit_img" src="/img/edit.png" alt="수정 아이콘"></a>
                        <a class="approval-lines-btn"><img class="delete_img" src="/img/delete.png" alt="삭제 아이콘"></a>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<div class="popup-overlay" id="popupOverlay" onclick="closeApprovalLinePopup()"></div>
<div class="popup" id="approvalLinePopup">
    <div class="popup-header">
        <h2>결재선 선택</h2>
        <button onclick="closeApprovalLinePopup()">X</button>
    </div>
    <div class="popup-body">
        <div class="popup-body-left">
            <table>
                <tr>
                    <td class="t-title">결재선 프로세스 </td>
                    <td>
                        <select>
                            <option>휴가어쩌고</option>
                            <option></option>
                            <option></option>
                            <option></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="t-title">결재선 이름</td>
                    <td><input type="text"></td>
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
        <button onclick="saveApprovalLine()">확인</button>
        <button onclick="closeApprovalLinePopup()">닫기</button>
    </div>
</div>

<div class="modal-overlay" id="modalOverlay" onclick="closeModal()"></div>
<div class="modal" id="approvalModal">
    <div class="modal-header">
        <h2>결재선 수정</h2>
        <button onclick="closeModal()">X</button>
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
            <!-- 동적으로 생성될 합의/결재 칸 -->
        </div>
    </div>
    <div class="modal-footer">
        <button onclick="saveModalApprovalLine()">확인</button>
        <button onclick="closeModal()">닫기</button>
    </div>
</div>
</body>
</html>

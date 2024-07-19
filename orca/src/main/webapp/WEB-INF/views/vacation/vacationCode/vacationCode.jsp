<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>휴가 관리</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #6eadff;
        }
        .container {
            width: 80%;
            margin: auto;
            padding-top: 50px;
        }
        .button-container {
            text-align: right;
            margin-bottom: 20px;
        }
        .button {
            background-color: #6eadff;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border: none;
            border-radius: 4px;
        }
        .button.edit {
            background-color: #6eadff;
        }

        .modal {
            display: none; /* 초기 상태에서 모달을 숨김 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 500px; /* 최대 너비 설정 */
            background-color: #fefefe;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>휴가 관리</h2>
        <div class="button-container">
            <button class="button" id="addVacationCodeBtn">휴가 등록</button>
            <button class="button delete" id="deleteVacationCodeBtn">휴가 삭제</button>
        </div>
        <table id="vacationCodesTable">
            <thead>
                <tr>
                    <th><input type="checkbox" id="checkAll"></th>
                    <th>휴가 코드</th>
                    <th>휴가 이름</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <div class="modal" id="vacationModal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>휴가 등록</h2>
            <form id="vacationForm">
                <div>
                    <label for="vacationCode">휴가 코드</label>
                    <br />
                    <input type="text" id="vacationCode" name="vacationCode" required>
                </div>
                <div>
                    <label for="vacationName">휴가 이름</label>
                    <br />
                    <input type="text" id="vacationName" name="vacationName" required>
                </div>
                <div style="margin-top: 20px;">
                    <button type="submit" class="button">등록</button>
                    <button type="button" class="button" id="closeModalBtn">닫기</button>
                </div>
            </form>
        </div>
    </div>

    <div id="editVacationCodeModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>휴가 코드 수정</h2>
            <form id="editVacationCodeForm">
                <div>
                    <label for="editVacationCode">휴가 코드:</label>
                    <br />
                    <input type="text" id="editVacationCode" name="vacationCode">
                </div>
                <div>
                    <label for="editVacationName">휴가 이름:</label>
                    <br />
                    <input type="text" id="editVacationName" name="vacationName">
                </div>
                <div style="margin-top: 20px;">
                    <button type="submit" class="button">저장</button>
                    <button type="button" class="button" id="closeEditModalBtn">닫기</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>

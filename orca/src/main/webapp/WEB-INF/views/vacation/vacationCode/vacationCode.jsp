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

         }
        .modal {
            position: fixed;
            display: none;
            justify-content: center;
            align-items: center;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal_body {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
            width: 300px;
            transform: translate(-50%, -50%);
            position: absolute;
            top: 50%;
            left: 50%;
        }
        .modal_body h2 {
            margin-top: 0;
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
            <div class="modal_body">
                <h2>휴가 등록</h2>
                <form id="vacationForm">
                    <div>
                        <label for="vacationCode">휴가 코드:</label>
                        <input type="text" id="vacationCode" name="vacationCode" required>
                    </div>
                    <div>
                        <label for="vacationName">휴가 이름:</label>
                        <input type="text" id="vacationName" name="vacationName" required>
                    </div>
                    <div style="margin-top: 20px;">
                        <button type="submit" class="button">등록</button>
                        <button type="button" class="button" id="closeModalBtn">닫기</button>
                    </div>
                </form>
            </div>
        </div>

</body>
</html>

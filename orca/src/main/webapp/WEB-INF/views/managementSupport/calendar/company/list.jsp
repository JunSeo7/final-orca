<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="container">
        <span>
            <h2>사내 캘린더 조회</h2>
        </span>
        <span class="search-container">
            <form action="searchListCalendar" method="get" id="calendar-search-form">
                <input type="text" name="keyword" class="search-text">
            </form>
            <i class="fas fa-search search-icon"></i>
        </span>
        <table class="custom-table">
            <thead>
                <tr>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성자</th>
                    <th>시작일</th>
                    <th>종료일</th>
                    <th>등록일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 여기에 데이터를 추가하세요 -->
            </tbody>
        </table>
        <div class="pageFooter">
            <div class="totalPage"></div>
        </div>
    </div>
    <style>
        .pageFooter {
            text-align: center;
            margin: 15px 0;
        }

        .pageFooter a {
            display: inline-block;
            margin: 0 3px;
            padding: 3px 10px;
            color: black;
            border: 1px solid #ddd;
            border-radius: 4px;
            transition: background-color 0.3s, color 0.3s;
            line-height: 1.5;
            /* 높이 동일하게 */
        }

        .pageFooter a:hover {
            background-color: #f1f1f1;
            color: #0056b3;
        }

        .pageFooter a.current-page {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
            font-weight: bold;
        }

        .pageFooter a.disabled {
            pointer-events: none;
            color: #aaa;
            border-color: #ddd;
        }

        .container h2 {
            display: flex;
            justify-content: center;
            margin-top: 0px;
            margin-bottom: 7px;
        }

        .container {
            width: 1100px;
            height: 570px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 25px;
            padding-bottom: 10px;
            border-radius: 10px;
            margin-bottom: 25px;
        }

        .custom-table {
            width: 100%;
            border-collapse: collapse;
        }

        .custom-table th {
            height: 11px;
        }

        .custom-table th,
        .custom-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            height: 12.5px;
        }

        .custom-table tr:hover {
            background-color: #007bff;
            color: white;
        }

        .custom-table thead tr:first-child {
            pointer-events: none;
        }

        /* 각 열에 대한 고정 너비 지정 */
        .custom-table th:nth-child(1),
        .custom-table td:nth-child(1) {
            width: 180px;
            /* 제목의 너비 */
        }

        .custom-table th:nth-child(2),
        .custom-table td:nth-child(2) {
            width: 250px;
            /* 내용의 너비 */
        }

        .custom-table th:nth-child(3),
        .custom-table td:nth-child(3) {
            width: 60px;
            /* 작성자의 너비 */
        }

        .custom-table th:nth-child(4),
        .custom-table td:nth-child(4) {
            width: 100px;
            /* 시작일의 너비 */
        }

        .custom-table th:nth-child(5),
        .custom-table td:nth-child(5) {
            width: 100px;
            /* 종료일의 너비 */
        }

        .custom-table th:nth-child(6),
        .custom-table td:nth-child(6) {
            width: 100px;
            /* 등록일의 너비 */
        }

        .custom-table th {
            background-color: #f2f2f2;
            cursor: pointer;
        }

        .search-container {
            display: flex;
            justify-content: end;
            align-items: center;
            margin-bottom: 15px;
        }

        .search-text {
            width: 130px;
            height: 7px;
            padding: 10px 40px 10px 10px;
            /* 오른쪽 패딩을 충분히 주어 아이콘 공간 확보 */
            border: 2px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        .search-text:focus {
            border-color: #66afe9;
            box-shadow: 0 0 8px rgba(102, 175, 233, 0.6);
            outline: none;
        }

        .search-icon {
            position: relative;
            top: 10px;
            right: 25px;
            transform: translateY(-50%);
            font-size: 20px;
            color: #aaa;
            pointer-events: none;
            /* 아이콘이 클릭되지 않도록 설정 */
        }

        .keyword {
            color: #007bff;
            font-weight: 600;
        }

        .notFoundText {
            display: flex;
            justify-content: center;
            font-size: 17px;
        }

        .check-list {
            font-size: 14px;
            color: #666666;
            line-height: 1.5;
            margin: 12px 0;
        }
    </style>
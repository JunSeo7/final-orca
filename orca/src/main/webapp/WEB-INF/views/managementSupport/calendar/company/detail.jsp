<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="view-calendar-form" id="viewEventDetailsForm">
        <div class="view-form-header">
            <div><span class="view-calendar-title">일정 제목</span> <span
                    class="view-calendar-enroll-date">2024.06.21</span></div>
            <button class="view-cancel-button" onclick="closeViewEvent()">✖</button>
        </div>

        <div class="view-form-body">
            <div class="view-form-group view-calendar-name">
                <span class="view-calendar-writer">작성자</span>
                <span class="view-calendar-partName">부서</span>
            </div>
            <div class="view-form-group">
                <label for="viewEventContent">일정 내용</label>
                <textarea id="viewEventContent" name="content" rows="4.5" readonly></textarea>
            </div>
            <div class="view-form-group">
                <label for="viewStartDate">시작일</label>
                <input type="date" id="viewStartDate" name="startDate" readonly required>
            </div>
            <div class="view-form-group">
                <label for="viewEndDate">종료일</label>
                <input type="date" id="viewEndDate" name="endDate" readonly required>
            </div>
            <div class="view-form-group">
                <label for="viewRange">공유 범위</label>
                <input type="text" id="viewRange" class="edit-range" name="range" readonly>
            </div>
            <div class="view-form-footer">
                <button class="view-edit-button">수정</button>
                <button class="view-delete-button">삭제</button>
            </div>
        </div>
    </div>

    <style>
        main {
            margin-top: 60px;
            margin-left: 220px;
            padding: 20px;
            flex-grow: 1;
            overflow-y: auto;
            transition: margin-left 0.3s ease;
            padding-left: 200px;
            padding-right: 200px;
            word-break: keep-all;
        }
        main.full{
            margin-left: 20px;
        }

        .main {
            display: flex;
            width: 100%;
            height: 100%;
            justify-content: center;
        }

        .view-calendar-form {
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            padding: 15px;
            max-width: 400px;
            width: 520px;
            height: 550px;
            border-radius: 10px;
            font-family: 'Arial', sans-serif;
        }

        .view-calendar-form .view-form-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 19px;
            width: 100%;
        }

        .view-calendar-name {
            display: flex;
            justify-content: end;
            align-items: end;
        }

        .view-calendar-title {
            font-weight: 900;
            font-size: 1.23rem;
            color: #333;
            margin-left: 4px;
        }

        .view-calendar-enroll-date {
            font-size: 10px;
            font-weight: 600;
            color: rgb(181, 177, 177);
        }

        .view-calendar-writer {
            font-size: 1.1rem;
            font-weight: 900;
            color: #333;
            margin-left: 4px;
        }

        .view-calendar-partName {
            font-size: 0.6rem;
            font-weight: 600;
            color: rgb(181, 177, 177);
            margin-left: 4px;
            padding-bottom: 2px;
        }

        .view-calendar-form .view-cancel-button {
            background: none;
            border: none;
            font-size: 1rem;
            cursor: pointer;
        }

        .view-calendar-form .view-cancel-button:hover {
            color: red;
        }

        .view-calendar-form .view-form-body {
            margin-bottom: 10px;
        }

        .view-calendar-form .view-form-group {
            margin-bottom: 21px;
        }

        .view-calendar-form label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #333;
        }

        .view-calendar-form input[type="text"],
        .view-calendar-form input[type="date"],
        .view-calendar-form textarea,
        #edit-range {
            width: 100%;
            padding: 8px;
            font-size: 0.9rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .view-calendar-form textarea {
            resize: vertical;
            min-height: 90px;
            resize: none;
            height: 80px;
        }

        .view-form-footer {
            text-align: right;
            margin-top: 28px;
        }

        .view-form-footer button {
            padding: 7px 14.5px;
            font-size: 0.85rem;
            background-color: #368dff;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;

        }

        .view-form-footer button:hover {
            background-color: #2672e8;
        }
    </style>
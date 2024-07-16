<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    /* 추가적인 CSS 스타일링 */
    .container {
        width: 900px;
        margin: 0 auto;
        padding: 15px;
    }

    .mb-4 {
        margin-bottom: 1.5rem;
    }

    .mt-3 {
        margin-top: 1rem;
    }

    .mr-2 {
        margin-right: 0.5rem;
    }

    .btn {
        display: inline-block;
        font-weight: 400;
        text-align: center;
        white-space: nowrap;
        vertical-align: middle;
        user-select: none;
        border: 1px solid transparent;
        padding: 0.325rem 0.70rem;
        font-size: 0.84rem;
        line-height: 1.5;
        border-radius: 0.25rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    }

    .btn-primary {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
    }

    .btn-primary:hover {
        background-color: #0667ce;
        cursor: pointer;
    }

    .btn-danger {
        color: #fff;
        background-color: #dc3545;
        border-color: #dc3545;
    }

    .btn-danger:hover {
        background-color: #bb2b39;
        cursor: pointer;
    }

    .row {
        display: flex;
        flex-wrap: wrap;
        margin-bottom: 20px;
    }

    .col-md-6 {
        width: 50%;
        padding: 0 15px;
        box-sizing: border-box;
    }

    .detail-label {
        font-weight: bold;
        width: 150px;
        display: inline-block;
    }

    .profile-img {
        max-width: 100%;
        height: auto;
        margin-bottom: 10px;
    }

    .detail-row {
        margin-bottom: 2px;
    }

    .btn-container {
        text-align: center;
        margin-top: 1rem;
    }

    /* 추가적인 CSS 스타일링 */
    .employee-details {
        max-width: 800px;
        margin: 10px;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #f5f5f5;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .row {
        margin-bottom: 13px;
    }

    .detail-label {
        font-weight: bold;
        width: 150px;
        display: inline-block;
    }

    .profile-img {
        width: 140px;
        height: 160px;
        margin-bottom: 10px;
    }
    .img-content{
        display: flex;
        justify-content: center;
    }

    .detail-row {
        margin-bottom: 2px;
    }
    .detail-header{
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 20px;
    }
    .backList{
        font-weight: 900;
        cursor: pointer;
    }
</style>
<div class="container">
    <div class="employee-details">
        <div class="detail-header">
            <h2 class="text-center mb-4">사원 정보 수정</h2>
            <div class="backList">↩</div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="detail-row">
                    <span class="detail-label">사원번호:</span>
                    <span id="empNo"></span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">이름:</span>
                    <input type="text" id="name" class="form-control" name="name">
                </div>
                <div class="detail-row">
                    <span class="detail-label">성별:</span>
                    <select id="gender" class="form-control" name="gender">
                        <option value="M">남성</option>
                        <option value="F">여성</option>
                    </select>
                </div>
                <div class="detail-row">
                    <span class="detail-label">주민등록번호:</span>
                    <input type="text" id="socialSecurityNo" class="form-control" name="socialSecurityNo">
                </div>
                <div class="detail-row">
                    <span class="detail-label">전화번호:</span>
                    <input type="text" id="phone" class="form-control" name="phone">
                </div>
                <div class="detail-row">
                    <span class="detail-label">내선번호:</span>
                    <input type="text" id="extensionCall" class="form-control" name="extensionCall">
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-row img-content">
                    <img id="profileImage" src="" alt="프로필 사진" class="profile-img">
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-row">
                    <span class="detail-label">이메일:</span>
                    <input type="email" id="email" class="form-control" name="email">
                </div>
                <div class="detail-row">
                    <span class="detail-label">주소:</span>
                    <input type="text" id="address" class="form-control" name="address">
                </div>
                <div class="detail-row">
                    <span class="detail-label">입사일:</span>
                    <input type="text" id="dateOfEmployment" class="form-control" name="dateOfEmployment">
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-row img-content">
                    <input type="file" id="image" name="image" class="form-control-file">
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="detail-row">
                    <span class="detail-label">키:</span>
                    <input type="text" id="height" class="form-control" name="height">
                </div>
                <div class="detail-row">
                    <span class="detail-label">몸무게:</span>
                    <input type="text" id="weight" class="form-control" name="weight">
                </div>
                <div class="detail-row">
                    <span class="detail-label">혈액형:</span>
                    <select id="bloodType" class="form-control" name="bloodType">
                        <option value="A">A형</option>
                        <option value="B">B형</option>
                        <option value="O">O형</option>
                        <option value="AB">AB형</option>
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-row">
                    <span class="detail-label">종교:</span>
                    <input type="text" id="religion" class="form-control" name="religion">
                </div>
                <div class="detail-row">
                    <span class="detail-label">은행 계좌번호:</span>
                    <input type="text" id="bankNumber" class="form-control" name="bankNumber">
                </div>
                <!-- 추가적인 필드들은 필요에 따라 추가 -->
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="detail-row">
                    <span class="detail-label">부서명:</span>
                    <input type="text" id="partName" class="form-control" name="partName">
                </div>
                <div class="detail-row">
                    <span class="detail-label">직위명:</span>
                    <input type="text" id="nameOfPosition" class="form-control" name="nameOfPosition">
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-row">
                    <span class="detail-label">팀명:</span>
                    <input type="text" id="teamName" class="form-control" name="teamName">
                </div>
                <!-- 추가적인 필드들은 필요에 따라 추가 -->
            </div>
        </div>
        <div class="btn-container text-center mt-3">
            <button type="submit" class="btn btn-primary mr-2">저장</button>
            <button type="button" class="btn btn-danger" id="cancelEdit">취소</button>
        </div>
    </div>
</div>
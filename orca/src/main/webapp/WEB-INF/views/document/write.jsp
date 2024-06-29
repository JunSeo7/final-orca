<!DOCTYPE html>
<html>
<head>
    <title>결재 작성</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>결재 작성</h1>

    <!-- 결재 양식 목록 -->
    <label for="templateList">결재 양식 선택:</label>
    <select id="templateList"></select>

    <!-- 결재 양식 내용 -->
    <div id="templateContent"></div>

    <!-- 결재 작성 폼 -->
    <form id="documentForm">
        <input type="hidden" name="templateNo" id="templateNo">
        <label for="title">제목:</label>
        <input type="text" name="title" id="title"><br>
        <label for="content">내용:</label>
        <textarea name="content" id="content"></textarea><br>
        <label for="status">상태:</label>
        <select name="status" id="status">
            <option value="1">임시저장</option>
            <option value="2">기안(대기)</option>
        </select><br>
        <button type="button" onclick="submitDocument()">제출</button>
    </form>

    <script>
        $(document).ready(function() {
            // 결재 양식 목록 불러오기
            $.ajax({
                url: '/orca/document/write',
                method: 'GET',
                success: function(data) {
                    var templateList = $('#templateList');
                    templateList.empty();
                    data.forEach(function(item) {
                        var option = $('<option></option>')
                            .attr('value', item.templateNo)
                            .text(item.categoryName + ' - ' + item.title);
                        templateList.append(option);
                    });
                },
                error: function() {
                    alert('결재 양식 목록 불러오기 오류가 발생했습니다.');
                }
            });

            // 결재 양식 선택시 내용 불러오기
            $('#templateList').change(function() {
                var templateNo = $(this).val();
                if (templateNo) {
                    $.ajax({
                        url: '/orca/document/gettemplate',
                        method: 'GET',
                        data: { templateNo: templateNo },
                        success: function(data) {
                            $('#templateContent').text(data.content);
                            $('#templateNo').val(data.templateNo);
                        },
                        error: function() {
                            alert('결재 양식 내용 불러오기 오류가 발생했습니다.');
                        }
                    });
                }
            });
        });

        function submitDocument() {
            var formData = $('#documentForm').serialize();
            $.ajax({
                url: '/orca/document/write',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function() {
                    alert('결재 작성이 완료되었습니다.');
                    window.location.href = '/orca/document/list';
                },
                error: function() {
                    alert('결재 작성 중 오류가 발생했습니다.');
                }
            });
        }
    </script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 수정</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/froala-editor/css/froala_editor.pkgd.min.css">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            padding: 0;
            background-color: #e0f7fa;
            color: #0277bd;
            font-family: Arial, sans-serif;
        }
        .form-container {
            max-width: 800px;
            margin: auto;
            display: flex;
            flex-direction: column;
            height: 100%;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #01579b;
        }
        #edit {
            flex-grow: 1;
            min-height: 400px;
            height: calc(100% - 150px); /* 높이를 계산하여 설정 */
            border: 1px solid #0288d1;
        }
        form {
            display: flex;
            flex-direction: column;
            height: 100%;
        }
        input[type="text"], input[type="submit"] {
            width: 100%;
            box-sizing: border-box;
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #0288d1;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #0288d1;
            color: #ffffff;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0277bd;
        }
        label {
            color: #01579b;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .fr-toolbar .fr-command.fr-btn {
            color: #ffffff;
        }
        .fr-toolbar .fr-command.fr-btn:hover, .fr-toolbar .fr-command.fr-btn:focus {
            background-color: #0277bd;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>게시물 수정</h1>
        <form id="postForm" action="/orca/board/update" method="post">
            <input type="hidden" id="boardNo" name="boardNo" value="${board.boardNo}">
            <input type="hidden" id="categoryNo" name="categoryNo" value="${board.categoryNo}">

            <label for="title">제목</label>
            <input type="text" id="title" name="title" value="${board.title}" required>

            <label for="content">내용</label>
            <div id="edit">${board.content}</div>
            <input type="hidden" id="content" name="content">

            <input type="submit" value="수정">
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/froala-editor/js/froala_editor.pkgd.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function() {
            var editor = new FroalaEditor('#edit', {
                height: '80%', // 에디터 높이를 80%로 설정
                imageUploadURL: '/orca/board/uploadImage',
                imageUploadParam: 'file',
                imageUploadMethod: 'POST',
                imageAllowedTypes: ['jpeg', 'jpg', 'png'],
                imageMaxSize: 2 * 1024 * 1024
            });

            $('#postForm').on('submit', function() {
                $('#content').val(editor.html.get());
            });
        });
    </script>
</body>
</html>

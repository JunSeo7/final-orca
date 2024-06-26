<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 수정</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/froala-editor/css/froala_editor.pkgd.min.css">
</head>
<body>
    <div class="form-container">
        <h1>게시물 수정</h1>
        <form id="postForm" action="/board/update" method="post">
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
                imageUploadURL: '/board/uploadImage',
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

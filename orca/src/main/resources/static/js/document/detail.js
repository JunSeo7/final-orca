 function deleteDocument(element) {
        var docNo = $(element).data('doc-no');
        if (confirm('문서를 삭제하시겠습니까?')) {
            $.ajax({
                url: `/orca/document/delete`,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ docNo: docNo }),
                success: function() {
                    // 요청이 성공했을 경우, 문서 목록 페이지로 이동
                    alert('문서 삭제에 성공했습니다.');
                    window.location.href = '/orca/document/list';
                },
                error: function() {
                    // 요청이 실패했을 경우, 오류 메시지를 표시
                    alert('문서 삭제에 실패했습니다.');
                }
            });
        }
    }
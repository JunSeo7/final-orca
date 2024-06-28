 const detailDivs = document.querySelectorAll(".template-div");
 const deleteBtns = document.querySelectorAll(".delete-btn");

//상세보기
 detailDivs.forEach(function(detailDiv) {
     detailDiv.addEventListener("click", function(event) {
         const templateNo = detailDiv.getAttribute('data-template-no');
         console.log('Template No:', templateNo);
         location.href = '/orca/template/detail?templateNo=' + templateNo;
     });
 });

//삭제
  deleteBtns.forEach(function(deleteBtn) {
         deleteBtn.addEventListener("click", function(event) {
             event.stopPropagation();  // 부모 요소 이벤트 전파막기!
             const templateNo = deleteBtn.getAttribute('data-template-no');
             console.log('Template No:', templateNo);

             $.ajax({
                 url: '/orca/template/delete?templateNo=' + templateNo,
                 method: 'POST',
                 success: function(data) {
                     console.log('Template deleted:', data);
                     location.reload();
                 },
                 error: function(e) {
                     console.error('Error:', e);
             }
         });
     });
 });
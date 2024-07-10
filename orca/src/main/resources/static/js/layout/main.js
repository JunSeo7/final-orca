const empNo = '<%= ((UserVo) session.getAttribute("loginUserVo")).getEmpNo() %>';
const workNo = '<%= session.getAttribute("workNo") %>';
const workDate = new Date().toISOString().split('T')[0]; // 현재 날짜 부분만 추출


function getCurrentTime() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0'); // 시간을 두 자리수로 맞추기 위해 padStart 사용
    const minutes = now.getMinutes().toString().padStart(2, '0'); // 분을 두 자리수로 맞추기 위해 padStart 사용
    return `${hours}:${minutes}`;
}


function startWorkClick() {

    $.ajax({
        url: "/orca/re/work/goWork",
        method: 'post',
        data: {
            empNo: empNo,
            workDate: workDate,
        },
        success: function(response) {
            if(response.success) {
                document.getElementById('startWorkTime').innerText = response.startWorkTime;
                alert('출근 시간이 기록되었습니다.');
            } else {
                alert('출근 시간 기록에 실패했습니다.');
            }
        },
        error: function(error) {
            console.error('Error:', error);
            alert('출근 시간 기록에 에러.');
        }
    });
}

function endWorkClick() {

    const endTime = getCurrentTime();

    $.ajax({
        url: "/orca/re/work/leaveWork",
        method: 'post',
        data: {
            empNo: empNo,
            workNo: workNo,
            workDate: workDate,
            endTime: endTime,
        },
        success: function(response) {
            if(response.success) {
                document.getElementById('endWorkTime').innerText = response.endWorkTime;
                alert('퇴근 시간이 기록되었습니다.');
            } else {
                alert('퇴근 시간 기록에 실패했습니다. ' + response.message);
            }
        },
        error: function(error) {
            console.error('Error:', error);
            alert('퇴근 시간 기록에 에러.');
        }
    });
}
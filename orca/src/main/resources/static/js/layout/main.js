function startWorkClick() {
    $.ajax({
        url: "/orca/re/work/goWork",
        method: 'post',
        data: {
            empNo: 1,
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
            alert('출근 시간 기록에 실패했습니다.');
        }
    });
}

function endWorkClick() {
    $.ajax({
        url: '/orca/re/work/leaveWork',
        method: 'post',
        data: {
            workNo: 23,
            empNo: 1,
        },
        success: function(response) {
            if(response.success) {
                document.getElementById('endWorkTime').innerText = response.endWorkTime;
                alert('퇴근 시간이 기록되었습니다.');
            } else {
                alert('퇴근 시간 기록에 실패했습니다.');
            }
        },
        error: function(error) {
            console.error('Error:', error);
            alert('퇴근 시간 기록에 실패했습니다.');
        }
    });
}
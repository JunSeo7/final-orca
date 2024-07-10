const empNo = '<%= ((UserVo) session.getAttribute("loginUserVo")).getEmpNo() %>';
const workNo = '<%= session.getAttribute("workNo") %>';
const workDate = new Date().toISOString().split('T')[0]; // 현재 날짜 부분만 추출

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
                alert('출근 시간이 기록되었습니다.');
                location.reload();
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

    $.ajax({
        url: "/orca/re/work/leaveWork",
        method: 'post',
        data: {
            empNo: empNo,
            workNo: workNo,
        },
        success: function(response) {
            if(response.success) {
                alert('퇴근 시간이 기록되었습니다.');
                location.reload();
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

function loadWorkTimes() {
    $.ajax({
        url: "/orca/re/work/getStartWorkTime",
        method: 'get',
        data: {
            workDate: workDate,
        },
        success: function(response) {
            if (response.success) {
                if (response.startWorkTime) {
                    var sWorkTimeElement = document.getElementById('sWorkTime');
                    if (sWorkTimeElement) {
                        var justTime = response.startWorkTime.substring(11, 19);
                        sWorkTimeElement.innerText = justTime;
                    }
                }
            } else {
                console.error('시간 불러오기 실패', response.message);
            }
        },
        error: function(error) {
            console.error('출근 시간 불러오기 에러', error);
        }
    });

    $.ajax({
        url: "/orca/re/work/getEndWorkTime",
        method: 'get',
        data: {
            workDate: workDate,
        },
        success: function(response) {
            if (response.success) {
                if (response.endWorkTime) {
                    var eWorkTimeElement = document.getElementById('eWorkTime');
                    if (eWorkTimeElement) {
                        var justTime = response.endWorkTime.substring(11, 19);
                        eWorkTimeElement.innerText = justTime;
                    }
                }
            } else {
                console.error('시간 불러오기 실패', response.message);
            }
        },
        error: function(error) {
            console.error('퇴근 시간 불러오기 에러', error);
        }
    });
}

$(document).ready(function() {
    loadWorkTimes();
});
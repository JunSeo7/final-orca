$(document).ready(function () {
    getDepartment();

    $('#dept').on('change', function () {
        const selectedDeptCode = $(this).val();
        if (selectedDeptCode) {
            getTeam(selectedDeptCode);
        }
    });

    $('#organizationChart').on('submit', function (event) {
        event.preventDefault();

        const deptCode = $('#dept').val();
        const teamCode = $('#team').val();

        $.ajax({
            url: $(this).attr('action'),
            method: "get",
            dataType: 'json',
            data: {
                deptCode: deptCode,
                teamCode: teamCode
            },
            success: (response) => {
                updateOrgChart(response);
            },
            error: (xhr, status, error) => {
                console.log('Status Code:', xhr.status);
                console.log('Status Text:', xhr.statusText);
                console.log('Error:', error);
                console.log('Response Text:', xhr.responseText);
            }
        });
    });
});

function getDepartment() {
    $.ajax({
        type: 'get',
        url: '/orca/organizationChart/getDepartment',
        dataType: 'json',
        success: function (response) {

            const deptSelect = $('#dept');
            response.forEach(dept => {
                const option = $('<option></option>');
                option.val(dept.deptCode);
                option.text(dept.partName);
                deptSelect.append(option);
            });
        },
        error: function (xhr, status, error) {
            console.log('Status Code:', xhr.status);
            console.log('Status Text:', xhr.statusText);
            console.log('Error:', error);
            console.log('Response Text:', xhr.responseText);
        }
    });
}

function getTeam(deptCode) {
    $.ajax({
        type: 'get',
        url: '/orca/organizationChart/getTeam',
        dataType: 'json',
        data: {
            deptCode: deptCode
        },
        success: function (response) {

            const teamSelect = $('#team');
            teamSelect.empty();
            response.forEach(team => {
                const option = $('<option></option>');
                option.val(team.teamCode);
                option.text(team.teamName);
                teamSelect.append(option);
            });
        },
        error: function (xhr, status, error) {
            console.log('Status Code:', xhr.status);
            console.log('Status Text:', xhr.statusText);
            console.log('Error:', error);
            console.log('Response Text:', xhr.responseText);
        }
    });
}

function updateOrgChart(data) {

    if (!data || data.length === 0) {
        alert('해당 부서에 해당하는 사원이 존재하지 않습니다.');
        return;
    }

    const orgChart = $('#org-chart');
    orgChart.empty();  // 기존의 조직도 내용 제거

    const levels = {
        exaggeration: [],  // 부장
        manager: [],       // 대리
        chief: [],         // 주임
        staff: []          // 사원
    };

    // 직급에 따라 분류
    data.forEach(person => {
        const position = person.nameOfPosition.toLowerCase();  // 직급을 소문자로 변환하여 사용
        if (position.includes('부장')) {
            levels.exaggeration.push(person);
        } else if (position.includes('대리')) {
            levels.manager.push(person);
        } else if (position.includes('주임')) {
            levels.chief.push(person);
        } else if (position.includes('사원')) {
            levels.staff.push(person);
        }
    });

    // 조직도 생성
    for (const [level, persons] of Object.entries(levels)) {
        if (persons.length > 0) {
            const levelDiv = $('<div></div>').addClass('level').addClass(level);
            persons.forEach(person => {
                const box = $('<div></div>').addClass('box');
                const title = $('<div></div>').addClass('title').text(person.nameOfPosition);
                const name = $('<div></div>').addClass('name').text(person.name);
                box.append(title, name);
                levelDiv.append(box);
            });
            orgChart.append(levelDiv);
        }
    }
}

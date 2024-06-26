package com.groupware.orca.user.vo;

public class UserVo {

    private String empNo;
    private String name;
    private String positionCode;
    private String deptCode;
    private String teamCode;
    private String gender;
    private String socialSecurity_no;
    private String password;
    private String phone;
    private String extensionCall;
    private String email;
    private String address;
    private String dateOfEmployment;
    private String height;
    private String weight;
    private String bloodYype;
    private String religion;
    private String salary;
    private String bankNumber;
    private String leavingDate;
    private String imgOriginName;
    private String imgChangeName;
    private String otpKey;
    private String sickDate;
    private String vacationDate;

    public UserVo() {
        super();
    }

    public UserVo(String empNo, String name, String positionCode, String deptCode, String teamCode, String gender,
                String socialSecurity_no, String password, String phone, String extensionCall, String email, String address,
                String dateOfEmployment, String height, String weight, String bloodYype, String religion, String salary,
                String bankNumber, String leavingDate, String imgOriginName, String imgChangeName, String otpKey,
                String sickDate, String vacationDate) {
        super();
        this.empNo = empNo;
        this.name = name;
        this.positionCode = positionCode;
        this.deptCode = deptCode;
        this.teamCode = teamCode;
        this.gender = gender;
        this.socialSecurity_no = socialSecurity_no;
        this.password = password;
        this.phone = phone;
        this.extensionCall = extensionCall;
        this.email = email;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.height = height;
        this.weight = weight;
        this.bloodYype = bloodYype;
        this.religion = religion;
        this.salary = salary;
        this.bankNumber = bankNumber;
        this.leavingDate = leavingDate;
        this.imgOriginName = imgOriginName;
        this.imgChangeName = imgChangeName;
        this.otpKey = otpKey;
        this.sickDate = sickDate;
        this.vacationDate = vacationDate;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSocialSecurity_no() {
        return socialSecurity_no;
    }

    public void setSocialSecurity_no(String socialSecurity_no) {
        this.socialSecurity_no = socialSecurity_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExtensionCall() {
        return extensionCall;
    }

    public void setExtensionCall(String extensionCall) {
        this.extensionCall = extensionCall;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodYype() {
        return bloodYype;
    }

    public void setBloodYype(String bloodYype) {
        this.bloodYype = bloodYype;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getImgOriginName() {
        return imgOriginName;
    }

    public void setImgOriginName(String imgOriginName) {
        this.imgOriginName = imgOriginName;
    }

    public String getImgChangeName() {
        return imgChangeName;
    }

    public void setImgChangeName(String imgChangeName) {
        this.imgChangeName = imgChangeName;
    }

    public String getOtpKey() {
        return otpKey;
    }

    public void setOtpKey(String otpKey) {
        this.otpKey = otpKey;
    }

    public String getSickDate() {
        return sickDate;
    }

    public void setSickDate(String sickDate) {
        this.sickDate = sickDate;
    }

    public String getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(String vacationDate) {
        this.vacationDate = vacationDate;
    }

    @Override
    public String toString() {
        return "Text [empNo=" + empNo + ", name=" + name + ", positionCode=" + positionCode + ", deptCode=" + deptCode
                + ", teamCode=" + teamCode + ", gender=" + gender + ", socialSecurity_no=" + socialSecurity_no
                + ", password=" + password + ", phone=" + phone + ", extensionCall=" + extensionCall + ", email="
                + email + ", address=" + address + ", dateOfEmployment=" + dateOfEmployment + ", height=" + height
                + ", weight=" + weight + ", bloodYype=" + bloodYype + ", religion=" + religion + ", salary=" + salary
                + ", bankNumber=" + bankNumber + ", leavingDate=" + leavingDate + ", imgOriginName=" + imgOriginName
                + ", imgChangeName=" + imgChangeName + ", otpKey=" + otpKey + ", sickDate=" + sickDate
                + ", vacationDate=" + vacationDate + "]";
    }

}

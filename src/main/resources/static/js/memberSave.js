// const recieveNum = () => {
//     let memberPhone = document.querySelector("#savePhoneCheck").val();
//     sendSMS(memberPhone);
// }
// function sendSMS(memberPhone) {
//     let clientId = "ncp:sms:kr:309336187163:save_sms_service";
//     let apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/{" + clientId + "}/messages";
//     let data = {
//         sendNo: memberPhone.value,
//         recipientList: [{recipientNo: memberPhone}]
//     }
//     axios({
//         url: apiUrl,
//         type: "SMS",
//         contentType: "COMM",
//         countryCode: "82",
//         from: "01012345678",
//         content: "내용",
//         messages: [
//             {
//                 "to": "01012345678",
//                 "content": "위의 content와 별도로 해당 번호로만 보내는 내용(optional)"
//             }
//         ]
//
//     })
// }


// 아이디 정규식
const regularExpression = () => {
    const memberId = document.querySelector("#memberId");
    const exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,12}$/;
    <!-- 4. 영문소문자(필수), 숫자(필수) 6~12글자 -->
    if (!memberId.value.match(exp)) {
        return false;
    } else {
        return true;
    }
}

// 아이디 중복체크
const idDuCheck = () => {
    const memberId = document.querySelector("#memberId");
    const duResult = document.querySelector("#duResult");
    console.log(memberId.value);
    if(regularExpression()){
        axios({
            url: "/member/duCheck",
            method: "post",
            data: {
                memberId: memberId.value
            }
        }).then(res => {
            duResult.innerHTML = "중복된 아이디가 없습니다.";
            duResult.style.color = "green";
        }).catch(err => {
            duResult.innerHTML = "중복된 아이디 입니다."
            duResult.style.color = "red";
        })
    } else {
        duResult.innerHTML = "영소문자,숫자 6~12로 입력해주세요";
        duResult.style.color = "red"
    }
}


const idBlur = () => {
    const memberId = document.querySelector("#memberId");
    const duResult = document.querySelector("#duResult");
    if (memberId.value == "") {
        duResult.innerHTML = "";
    } else if (!regularExpression()) {
        duResult.innerHTML = "영소문자,숫자 6~12로 입력해주세요";
        duResult.style.color = "red"
    } else {
        duResult.innerHTML = "사용가능한 아이디입니다.";
        duResult.style.color = "green"
    }
}

// 아이디 정규식
// const idBlur = () => {
//     const memberId = document.querySelector("#memberId");
//     const duResult = document.querySelector("#duResult");
//     const exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,12}$/;
//     <!-- 4. 영문소문자(필수), 숫자(필수) 6~12글자 -->
//     if (memberId.value == "") {
//         duResult.innerHTML = "";
//     } else if (!memberId.value.match(exp)) {
//         duResult.innerHTML = "영소문자,숫자 6~12로 입력해주세요";
//         duResult.style.color = "red"
//     } else {
//         duResult.innerHTML = "";
//     }
// }


// 비밀번호 정규식
const writePass = () => {
    const memberPass = document.querySelector("#memberPass");
    const passCheck = document.querySelector("#passCheck");
    const pwCheck = document.querySelector("#pwCheck");
    const exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,12}$/;
    <!-- 4. 영문소문자(필수), 숫자(필수) 6~12글자 -->
    if (memberPass.value == "") {
        pwCheck.innerHTML = "";
        passCheck.disabled = true;
    } else if (!memberPass.value.match(exp)) {
        pwCheck.innerHTML = "영소문자,숫자 6~12로 입력해주세요";
        pwCheck.style.color = "red"
        passCheck.disabled = true;
    } else {
        pwCheck.innerHTML = "이용 가능한 비밀번호입니다";
        pwCheck.style.color = "green"
        passCheck.disabled = false;
    }
}


// 빈칸체크
const save = () => {
    const memberId = document.querySelector("#memberId");
    const memberPass = document.querySelector("#memberPass");
    const passCheck = document.querySelector("#passCheck");
    const passResult = document.querySelector("#passResult");
    const memberName = document.querySelector("#memberName");
    const pNum1 = document.querySelector("#pNum1");
    const pNum2 = document.querySelector("#pNum2");
    const pNum3 = document.querySelector("#pNum3");
    let memberPrivate = pNum1.value + "." + pNum2.value + "." + pNum3.value;
    const memberGender = document.querySelector("#memberGender");
    const email = document.querySelector("#saveEmailCheck");
    const domain = document.querySelector("#domain");
    let memberEmail = email.value + "@" + domain.value;
    const memberPhone = document.querySelector("#savePhoneCheck");
    console.log(memberId.value);
    console.log(domain.value);
    console.log(memberEmail);
    console.log(memberPhone.value);
    if (memberId.value == "") {
        memberId.focus();
        alert("아이디를 입력해주세요");
    } else if (memberPass.value == "") {
        memberPass.focus();
        passCheck.disable = true;
        alert("비밀번호를 입력해주세요");
    } else if (passCheck.value == "") {
        passCheck.focus();
        alert("비밀번호 확인란을 입력해주세요");
    } else if (memberName.value == "") {
        memberName.focus();
        alert("이름을 입력해주세요")
    } else if (pNum1.value == "" || pNum2.value == "" || pNum3.value == "") {
        if (pNum1.value == "") {
            pNum1.focus();
        } else if (pNum2.value == "") {
            pNum2.focus();
        } else if (pNum3.value == "") {
            pNum3.focus();
        }
        alert("생년월일을 입력해주세요")
    } else if (memberGender.value == "성별") {
        memberGender.focus();
        alert("성별을 선택해주세요");
    } else if (email.value == "" || domain.value == "이메일선택") {
        if (email.value == "") {
            email.focus();
            alert("이메일을 입력해주세요");
        } else if (domain.value == "이메일선택") {
            domain.focus();
            alert("도메인을 선택해주세요")
        }
    } else if (memberPhone.value == "") {
        memberPhone.focus();
        alert("전화번호를 입력해주세요");
    } else {
        axios({
            method: "post",
            url: "/member/save",
            data: {
                memberId: memberId.value,
                memberPass: memberPass.value,
                memberName: memberName.value,
                memberPrivate: memberPrivate,
                memberGender: memberGender.value,
                memberEmail: memberEmail,
                memberPhone: memberPhone.value
            }
        })
            .then(res => {
                location.href = "/member/login";
                alert("회원가입에 성공하셨습니다");
            })
            .catch(err => {
                alert("회원가입에 실패하셨습니다");
            });
    }
}






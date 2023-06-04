const recieveNum = () => {
    let memberPhone = document.querySelector("#savePhoneCheck").val();

    sendSMS(memberPhone);

}

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

const writePass = () => {
    const memberPass = document.querySelector("#memberPass");
    const passCheck = document.querySelector("#passCheck");
    if (memberPass.value == "") {
        passCheck.disabled = true;
    } else {
        passCheck.disabled = false;

    }
}

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
            url: "/member/ save",
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
                console.log("가입성공!");
            })
            .catch(error => {
                console.log("가입실패!");
            });
    }

}






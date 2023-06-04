const save = () => {
    const memberId = document.querySelector("#memberId").value;
    const memberPass = document.querySelector("#memberPass").value;
    const memberName = document.querySelector("#memberName").value;
    const pNum1 = document.querySelector("#pNum1").value;
    const pNum2 = document.querySelector("#pNum2").value;
    const pNum3 = document.querySelector("#pNum3").value;
    let memberPrivate = pNum1 + "." + pNum2 + "." + pNum3;
    const memberGender = document.querySelector("#memberGender").value;
    const domain = document.querySelector("#domain").value;
    const email = document.querySelector("#saveEmailCheck").value;
    let memberEmail = email + "@" + domain;

    const memberPhone = document.querySelector("#savePhoneCheck").value;
    console.log("pNum1: " + pNum1);
    console.log("email: " + email + " domain: " + domain);
    console.log("memberPrivate: " + memberPrivate + " memberEmail: " + memberEmail);
    console.log("memberGender: " + memberGender);
    axios({
        method: "post",
        url: "/member/save",
        data: {
            memberId: memberId,
            memberPass: memberPass,
            memberName: memberName,
            memberPrivate: memberPrivate,
            memberGender: memberGender,
            memberEmail: memberEmail,
            memberPhone: memberPhone
        }
    })
        .then(res => {
            console.log("가입성공!");
        })
        .catch(error => {
            console.log("가입실패!");
        });
}








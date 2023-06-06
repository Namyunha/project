
const login = () => {
    // 빈칸인 경우일 때 alert 발생, 그게 아니라면 axios 실행
    const memberId = document.querySelector("#memberId");
    const memberPass = document.querySelector("#memberPass");
    if (memberId.value == "" || memberPass.value == "") {
        alert("아이디 또는 비밀번호를 입력해주세요");
    } else {
        axios({
            method: "post",
            url: "/member/login",
            data: {
                memberId: memberId.value,
                memberPass: memberPass.value
            }
        }).then(res => {
            location.href = "/";
            alert(memberId.value + "님 환영합니다");
        }).catch(err => {
            alert("아이디 또는 비밀번호를 확인해주세요");
        })
    }
}
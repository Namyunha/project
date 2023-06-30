const login = () => {
    // 빈칸인 경우일 때 alert 발생, 그게 아니라면 axios 실행
    const memberId = document.querySelector("#memberId");
    const memberPass = document.querySelector("#memberPass");
    const redirectURI = document.querySelector('input[name="redirectURI"]').value;
    console.log("redirectURI: " + redirectURI.value);
    if (memberId.value == "" || memberPass.value == "") {
        alert("아이디 또는 비밀번호를 입력해주세요");
    } else {
        axios({
            method: "post",
            url: "/member/login/axios",
            data: {
                memberId: memberId.value,
                memberPass: memberPass.value
            }
        }).then(res => {
            location.href = redirectURI;
            // alert(memberId.value + "님 환영합니다");
        }).catch(err => {
            alert("아이디 또는 비밀번호를 확인해주세요");
        })
    }
}

const searchId = () => {
    const wrapper = document.querySelector("#wrapper");
    axios({
        url: "/member/searchId",
        method: "get"
    }).then(res => {
        let result = "<div class=\"saveForm\">\n" +
            "        <div id=\"loginIcon\">\n" +
            "            <span>ID찾기</span>\n" +
            "        </div>\n" +
            "        <input id=\"memberName\" class=\"loginInfo\" name=\"memberName\" type=\"text\" placeholder=\"이름\"><br>\n" +
            "        <input id=\"memberPrivate\" class=\"loginInfo\" name=\"memberPrivate\" type=\"date\" placeholder=\"생년월일\"><br>\n" +
            "        <div class=\"loginBtnGroup\">\n" +
            "            <input class=\"loginInput\" type=\"button\" onclick=\"checkNum()\" value=\"인증번호받기\">\n" +
            "            <input class=\"loginInput\" type=\"button\" onclick=\"searchPw()\" value=\"비밀번호찾기\">\n" +
            "            <input class=\"loginInput\" type=\"button\" onclick=\"goLogin()\" value=\"취소하기\">\n" +
            "        </div>\n" +
            "    </div>"
        wrapper.innerHTML = result;
    }).catch(err => {
        alert("에러발생");
    });
};

const searchPw = () => {
    const wrapper = document.querySelector("#wrapper");
    axios({
        url: "/member/searchPw",
        method: "get"
    }).then(res => {
        let result = "<div class=\"saveForm\">\n" +
            "        <div id=\"loginIcon\">\n" +
            "            <span>비밀번호찾기</span>\n" +
            "        </div>\n" +
            "        <input id=\"memberName\" class=\"loginInfo\" name=\"memberName\" type=\"text\" placeholder=\"이름\"><br>\n" +
            "        <input id=\"memberPrivate\" class=\"loginInfo\" name=\"memberPrivate\" type=\"text\" placeholder=\"아이디\"><br>\n" +
            "        <div class=\"loginBtnGroup\">\n" +
            "            <input class=\"loginInput\" type=\"button\" onclick=\"checkNum()\" value=\"인증번호받기\">\n" +
            "            <input class=\"loginInput\" type=\"button\" onclick=\"searchId()\" value=\"아이디찾기\">\n" +
            "            <input class=\"loginInput\" type=\"button\" onclick=\"goLogin()\" value=\"취소하기\">\n" +
            "        </div>\n" +
            "    </div>"
        wrapper.innerHTML = result;
    }).catch(err => {
        alert("에러발생");
    });
};

const goLogin = () => {
    location.href = "/member/searchId";
};

const goIndex = () => {
    location.href = "/";
}













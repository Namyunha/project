const member_detail = (id) => {
    const listArea = document.querySelector(".listArea");
    console.log("list: " + id);
    axios({
        method: "post",
        url: "/member/detail/" + id,
    }).then(res => {
        let result = "<table>\n" +
            "        <tr>\n" +
            "            <th>id</th>\n" +
            "            <td>" + res.data.id + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>아이디</th>\n" +
            "            <td>" + res.data.memberId + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>비밀번호</th>\n" +
            "            <td>" + res.data.memberPass + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>이름</th>\n" +
            "            <td>" + res.data.memberName + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>생년월일</th>\n" +
            "            <td>" + res.data.memberPrivate + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>성별</th>\n" +
            "            <td>" + res.data.memberGender + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>이메일</th>\n" +
            "            <td>" + res.data.memberEmail + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>휴대폰</th>\n" +
            "            <td>" + res.data.memberPhone + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <th>가입날짜</th>\n" +
            "            <td>" + res.data.createdAt + "</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td><button onclick='list()'>목록확인하기</button></td>\n" +
            "            <td><button onclick='update()'>수정하기</button></td>\n" +
            "            <td><button onclick='delete()'>삭제하기</button></td>\n" +
            "        </tr>\n" +
            "    </table>"
        listArea.innerHTML = result;
    }).catch(err => {
        listArea.innerHTML = "정보를 조회할 수 없습니다";
    })
}



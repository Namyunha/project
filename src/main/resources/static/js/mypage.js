const checkUser = () => {
    const viewArea = document.querySelector("#viewArea");
    const id = document.querySelector("#id").value;
    console.log(id);
    axios({
        url: "/member/" + id,
        method: "get"
    }).then(res => {
        const loginUser = res.data; // 서버에서 받아온 데이터를 loginUser 변수에 저장
        let result = "<table>\n" +
            "            <tbody>\n" +
            "            <tr>\n" +
            "                <th>회원번호</th>\n" +
            "                <td id='memberId'>" + loginUser.id + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>아이디</th>\n" +
            "                <td>" + loginUser.memberId + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>비밀번호</th>\n" +
            "                <td>" + loginUser.memberPass + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>이름</th>\n" +
            "                <td>" + loginUser.memberName + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>생년월일</th>\n" +
            "                <td>" + loginUser.memberPrivate + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>성별</th>\n" +
            "                <td>" + loginUser.memberGender + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>이메일</th>\n" +
            "                <td>" + loginUser.memberEmail + "</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>휴대번호</th>\n" +
            "                <td>" + loginUser.memberPhone + "</td>\n" +
            "            </tr>\n" +
            "            </tbody>\n" +
            "        </table>";
        viewArea.innerHTML = result;
    }).catch(err => {
        alert("정보를 불러올 수 없습니다.");
    });
};

const updateUser = () => {
    const viewArea = document.querySelector("#viewArea");
    const id = document.querySelector("#memberId").innerText;
    axios({
        url: "/member/" + id,
        method: "get"
    }).then(res => {
        const loginUser = res.data; // 서버에서 받아온 데이터를 loginUser 변수에 저장
        let result = "<table>\n" +
            "            <tbody>\n" +
            "            <tr>\n" +
            "                <th>회원번호</th>\n" +
            "                <td><input id='id' type=\"text\" value=\"" + loginUser.id + "\" readonly></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>아이디</th>\n" +
            "                <td><input id='memberId' type=\"text\" value=\"" + loginUser.memberId + "\" readonly></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>비밀번호</th>\n" +
            "                <td><input id='memberPass' type=\"text\" value=\"" + loginUser.memberPass + "\"></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>이름</th>\n" +
            "                <td><input id='memberName' type=\"text\" value=\"" + loginUser.memberName + "\"></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>생년월일</th>\n" +
            "                <td><input id='memberPrivate' type=\"date\" value=\"" + loginUser.memberPrivate + "\"></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>성별</th>\n" +
            "                <td><input id='memberGender' type=\"text\" value=\"" + loginUser.memberGender + "\"></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>이메일</th>\n" +
            "                <td><input id='memberEmail' type=\"text\" value=\"" + loginUser.memberEmail + "\"></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <th>휴대번호</th>\n" +
            "                <td><input id='memberPhone' type=\"text\" value=\"" + loginUser.memberPhone + "\"></td>\n" +
            "            </tr>\n" +
            "            </tbody>\n" +
            "        </table>" +
            "<td><button onclick='goUpdate()'>수정하기</button></td>";
        viewArea.innerHTML = result;
    }).catch(err => {
        alert("업데이트가 불가합니다");
    });
};

const goUpdate = () => {
    const viewArea = document.querySelector("#viewArea");
    const id = document.querySelector("#id").value;
    const memberId = document.querySelector("#memberId").value;
    const memberPass = document.querySelector("#memberPass").value;
    const memberName = document.querySelector("#memberName").value;
    const memberPrivate = document.querySelector("#memberPrivate").value;
    const memberGender = document.querySelector("#memberGender").value;
    const memberEmail = document.querySelector("#memberEmail").value;
    const memberPhone = document.querySelector("#memberPhone").value;
    axios({
        url: "/member/" + id,
        method: "put",
        data: {
            id: id,
            memberId: memberId,
            memberPass: memberPass,
            memberName: memberName,
            memberPrivate: memberPrivate,
            memberGender: memberGender,
            memberEmail: memberEmail,
            memberPhone: memberPhone
        }
    }).then(res => {
        checkUser();
        alert("수정완료");
    }).catch(err => {
        console.log("업데이트 실패");
    })
}

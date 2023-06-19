const saveForm = document.querySelector("#saveForm")
const back = () => {
    history.back();
}

const saveBoard = (event) => {
    event.preventDefault();
    const boardTitle = document.querySelector("#boardTitle");
    const boardContents = document.querySelector("#boardContents");
    const boardCategory = document.querySelector("#boardCategory");
    const boardPassword = document.querySelector("#boardPassword");
    if (boardTitle.value == "") {
        boardTitle.focus();
        alert("게시글의 제목을 입력해주세요")
    } else if (boardContents.value == "") {
        boardContents.focus();
        alert("게시글의 내용을 입력해주세요")
    } else if (boardCategory.value == "") {
        boardCategory.focus();
        alert("게시글 종류를 선택해주세요")
    } else if (boardPassword.value == "") {
        boardPassword.focus();
        alert("게시글의 비밀번호를 입력해주세요")
    } else {
        saveForm.submit();
    }
}
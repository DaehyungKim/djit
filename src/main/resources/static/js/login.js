const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('error')) {
        alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.');
    }
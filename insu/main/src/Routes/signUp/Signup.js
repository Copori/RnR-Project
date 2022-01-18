import "../style/signUp/style.css";


function Signup() {
  return (
    <div className="signup">
      <div className="signup__box">
        <div className="signup__header">
          <span className="signup__header--logo">Read&amp;Review</span>
        </div>
        <div className="signup__body">
          <div className="signup__body--input">
            <span className="signup__body--id-title">아이디</span>
            <input
              type="email"
              className="signup__body--input-id"
              placeholder="아이디를 입력하세요."
            />
            <span className="signup__body--pw-title">비밀번호</span>
            <input
              type="password"
              className="signup__body--input-pw"
              placeholder="비밀번호를 입력하세요."
            />
            <span className="signup__body--pwcheck-title">비밀번호 확인</span>
            <input
              type="password"
              className="signup__body--input-pwcheck"
              placeholder="비밀번호를 다시 입력해주세요."
            />
          </div>
        </div>
        <div className="signup__btn">
          <button type="button" className="signup__btn--join">
            가입하기
          </button>
        </div>
        <div className="signup__footer">
          <span className="signup__footer--info">
          </span>
            <span className="signup__footer--login"></span>
        </div>
      </div>
    </div>
  );
}

export default Signup;

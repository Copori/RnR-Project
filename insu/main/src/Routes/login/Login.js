import React, { useState } from "react";
import "../style/login/style.css";
import {Link} from"react-router-dom";

function Login() {
  const [inputId, setInputId] = useState("");
  const [inputPw, setInputPw] = useState("");

  const handleInputId = (e) => {
    setInputId(e.target.value);
  };

  const handleInputPw = (e) => {
    setInputPw(e.target.value);
  };

  const onClickLogin = () => {};

  const onClickSignin = () => {};

  return (
    <div className="login">
      <div className="login__box">
        <div className="login__header">
          <span className="login__header--logo">Read&amp;Review</span>
        </div>
        <div className="login__body">
          <div className="login__body--id">
            <label htmlFor="input_id" className="login__body--id-icon">
              {" "}
            </label>
            <input
              type="text"
              name="input_id"
              value={inputId}
              className="login__body--id-input"
              id="input_id"
              placeholder="아이디를 입력하세요."
              onChange={handleInputId}
            />
          </div>
          <div className="login__body--pw">
            <label htmlFor="input_pw" className="login__body--pw-icon">
              {" "}
            </label>
            <input
              type="password"
              name="input_pw"
              value={inputPw}
              className="login__body--pw-input"
              id="input_pw"
              placeholder="비밀번호를 입력하세요."
              onChange={handleInputPw}
            />
          </div>
        </div>
        <div className="login__btn">
          <button
            type="button"
            className="login__btn--signin"
            onClick={onClickLogin}
          >
            로그인
          </button>
          <Link to="/signUp">
          <button
            type="button"
            className="login__btn--signup"
            onClick={onClickSignin}
          >
            회원가입
          </button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Login;
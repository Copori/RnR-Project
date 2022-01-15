import React, { useState, useEffect } from "react";
import "./LogIn.css";

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
          <h2 className="login__header--logo">R&amp;R</h2>
        </div>
        <div className="login__body">
          <div className="login__body--id">
            <label htmlFor="input_id" className="login__body--id-icon">
              ID &nbsp;&nbsp;:{" "}
            </label>
            <input
              type="text"
              name="input_id"
              value={inputId}
              className="login__body--id-input"
              onChange={handleInputId}
            />
          </div>
          <div className="login__body--pw">
            <label htmlFor="input_pw" className="login__body--pw-icon">
              PW :{" "}
            </label>
            <input
              type="password"
              name="input_pw"
              value={inputPw}
              className="login__body--pw-input"
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
            Sign In
          </button>
          <button
            type="button"
            className="login__btn--signup"
            onClick={onClickSignin}
          >
            회원가입
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;

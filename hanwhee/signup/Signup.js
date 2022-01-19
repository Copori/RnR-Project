import React from "react";
import useForm from "./useForm";
import validate from "./validate";
import "./style/style.css";

function Signup() {
  const { values, errors, submitting, handleChange, handleSubmit } = useForm({
    initialValues: { id: "", email: "", password: "", passwordcheck: "" },
    onSubmit: (values) => {
      alert(JSON.stringify(values, null, 2));
    },
    validate,
  });

  return (
    <form onSubmit={handleSubmit} noValidate>
      <div className="signup">
        <div className="signup__box">
          <div className="signup__header">
            <span className="signup__header--logo">Read&amp;Review</span>
          </div>
          <div className="signup__body">
            <div className="signup__body--input">
              <span className="signup__body--id-title">아이디</span>
              <input
                type="text"
                name="id"
                className="signup__body--input-id"
                placeholder="아이디를 입력하세요."
                value={values.id}
                onChange={handleChange}
              />
              {errors.id && (
                <span className="signup__body--id-error">{errors.id}</span>
              )}
              <span className="signup__body--email-title">이메일</span>
              <input
                type="email"
                name="email"
                className="signup__body--input-email"
                placeholder="이메일을 입력하세요."
                value={values.email}
                onChange={handleChange}
              />
              {errors.email && (
                <span className="signup__body--email-error">
                  {errors.email}
                </span>
              )}
              <span className="signup__body--pw-title">비밀번호</span>
              <input
                type="password"
                name="password"
                className="signup__body--input-pw"
                placeholder="비밀번호를 입력하세요."
                value={values.password}
                onChange={handleChange}
              />
              {errors.password && (
                <span className="signup__body--pw-error">
                  {errors.password}
                </span>
              )}
              <span className="signup__body--pwcheck-title">비밀번호 확인</span>
              <input
                type="password"
                name="passwordcheck"
                className="signup__body--input-pwcheck"
                placeholder="비밀번호를 다시 입력해주세요."
                value={values.passwordcheck}
                onChange={handleChange}
              />
              {errors.passwordcheck && (
                <span className="signup__body--pwck-error">
                  {errors.passwordcheck}
                </span>
              )}
            </div>
          </div>
          <div className="signup__btn">
            <button type="submit" className="signup__btn--join">
              가입하기
            </button>
          </div>
        </div>
      </div>
    </form>
  );
}

export default Signup;

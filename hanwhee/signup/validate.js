export default function validate({ id, email, password, passwordcheck }) {
  const errors = {};

  if (!id) {
    errors.id = "※아이디가 입력되지 않았습니다.";
  } else if (!/^.{1,20}$/i.test(id)) {
    errors.id = "※아이디는 20글자 이내로 입력해주세요.";
  }

  if (!email) {
    errors.email = "※이메일이 입력되지 않았습니다.";
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(email)) {
    errors.email = "※입력된 이메일이 형식에 맞지 않습니다.";
  }

  if (!password) {
    errors.password = "※비밀번호가 입력되지 않았습니다.";
  }

  if (password !== passwordcheck) {
    errors.passwordcheck = "※비밀번호가 일치하지 않습니다.";
  }
  return errors;
}

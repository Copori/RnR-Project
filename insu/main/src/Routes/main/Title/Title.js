import { useState } from "react";

import { AiOutlineRead, AiOutlineKey } from "react-icons/ai";
import { BsFillPersonFill } from "react-icons/bs";

function Title() {
  const [toggle, setToggle] = useState(false);
  function onToggleCLick() {
    setToggle((toggle) => !toggle);
  }
  return (
    <div className="main__title">
      <div className="main__title__box-text">
        <span>Read&Review</span>
      </div>
      <div className="main__title__box-icon">
        <div className="box-icon__contents">
          <AiOutlineRead />
          <span>서재</span>
        </div>
        <div onClick={onToggleCLick}>
          {toggle ? (
            <div className="box-icon__contents">
              <AiOutlineKey />
              <span>로그인</span>
            </div>
          ) : (
            <div className="box-icon__contents">
              <BsFillPersonFill />
              <span>내 정보</span>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Title;

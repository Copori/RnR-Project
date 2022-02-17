import { useState } from "react";

import {Link} from "react-router-dom";
import { AiOutlineRead, AiOutlineKey } from "react-icons/ai";
import { BsFillPersonFill } from "react-icons/bs";

function Title() {
  const [toggle, setToggle] = useState();
  return (
    <div className="main__title">
      <div className="main__title__box-text">
        <span>Read&Review</span>
      </div>
      <div className="main__title__box-icon">
          <Link to="/Detail">
        <div className="box-icon__contents">
          <div className="box-icon__contents--icon"><AiOutlineRead /></div>
          <span className="box-icon__contents--text">서재</span>
          </div>
          </Link>
          {toggle ? (
            <Link to="/login">
            <div className="box-icon__contents">
              <div><AiOutlineKey /></div>
              <span className="box-icon__contents--text">로그인</span>
              </div>
            </Link>
          ) : (
              <Link to="/profile">
            <div className="box-icon__contents">
              <div className="box-icon__contents--icon"><BsFillPersonFill /></div>
              <span className="box-icon__contents--text">내 정보</span>
              </div>
              </Link>
          )}
      </div>
    </div>
  );
}

export default Title;

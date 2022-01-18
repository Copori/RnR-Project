import { useState, useEffect } from "react";

import View from "./View";

import {
  GiSpellBook,
  GiBookmarklet,
  GiSecretBook,
  GiBlackBook,
} from "react-icons/gi";

function GoodBooks() {
  //API variables
  const API_URL = "http://api.kcisa.kr/openapi/service/rest/meta4/getKCPG0506";
  const SECRET_KEY = process.env.REACT_APP_RECOMMEND_SERVICE_KEY;
  let pageNum = 0; //total books = 825
  let countBook = 5; // 불러올 책의 개수

  //icon variablaes
  const [bookToggle, setBookToggle] = useState(true);

  //아이콘 변경 토글 함수
  function onChoiceBookToggle() {
    setBookToggle((bookToggle) => (bookToggle = false));
  }

  function onRecommandBookToggle() {
    setBookToggle((bookToggle) => (bookToggle = true));
  }

  //react variables
  const [loading, setLoading] = useState(true);
  const [books, setBooks] = useState([]);
  const [booklists, setBookLists] = useState([]);

  //API를 읽어서 json에 담는 함수
  const getBooks = async () => {
    let bookList = [];
    for(let i = 1; i<10; i++){
      const json = await ( await fetch(
        `${API_URL}?serviceKey=${SECRET_KEY}&numOfRows=${countBook}&pageNo=${pageNum + i}`,
        {
          headers: {
            accept: "application/json",
          },
        }
      )
    ).json();
      bookList.push(json.response.body.items.item);
      console.log(bookList[i-1]);
    }
    const json = await (
      await fetch(
        `${API_URL}?serviceKey=${SECRET_KEY}&numOfRows=825&pageNo=1`,
        {
          headers: {
            accept: "application/json",
          },
        }
      )
    ).json();
    setBooks(json.response.body.items.item);
    bookList = bookList.concat();
    setBookLists(bookList);
    console.log(bookList.concat());
    setLoading(false);
  };
  
  // useEffect
  useEffect(() => {
    getBooks();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  //View
  return (
    <div className="GoodBooks">
      <div className="GoodBooks__title">
        <div className="GoodBooks__title--left">
          <div
            className="GoodBooks__title--icon"
            onClick={onRecommandBookToggle}
          >
            {bookToggle ? <GiSpellBook /> : <GiSecretBook />}
          </div>
          <span onClick={onRecommandBookToggle}>추천도서</span>
          <div className="GoodBooks__title--icon" onClick={onChoiceBookToggle}>
            {bookToggle ? <GiBlackBook /> : <GiBookmarklet />}
          </div>
          <span onClick={onChoiceBookToggle}>선택도서</span>
        </div>
        <div className="GoodBooks__title--right"></div>
      </div>
      {loading ? (
        <h1>Loading...</h1>
      ) : (
        <div>
          <View totalBooks={booklists} />
        </div>
      )}
    </div>
  );
}

export default GoodBooks;

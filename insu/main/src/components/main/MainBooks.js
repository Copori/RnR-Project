/* eslint-disable jsx-a11y/alt-text */
import { useState, useEffect } from "react";

function MainBooks() {
  //API variables
  const API_URL = "http://api.kcisa.kr/openapi/service/rest/meta4/getKCPG0506";
  const SECRET_KEY = process.env.REACT_APP_RECOMMEND_SERVICE_KEY;
  let pageNum = 1; //max page = 81
  let countBook = 10;

  //react variables
  const [loading, setLoading] = useState(true);
  const [books, setBooks] = useState([]);

  //API를 읽어서 json에 담는 함수
  const getBooks = async () => {
    const json = await (
      await fetch(
        `${API_URL}?serviceKey=${SECRET_KEY}&numOfRows=${countBook}&pageNo=${pageNum}`,
        {
          headers: {
            accept: "application/json",
          },
        }
      )
    ).json();
    setBooks(json.response.body.items.item);
    setLoading(false);
  };

  // useEffect
  useEffect(() => {
    getBooks();
    console.log(books);
  }, []);

  //View
  return (
    <div>
      {loading ? (
        <h1>Loading...</h1>
      ) : (
        <div>
          {books.map((book) => (
            <div key={book.url}>
              <a href={book.url}>
                <img src={book.referenceIdentifier} />
                <br />

                <span>{book.title}</span>
                <br />
              </a>
              <span>출판사: {book.extent}</span>
              <br />
              <span>출간일: {book.issuedDate}</span>
              <br />
              <br />
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default MainBooks;

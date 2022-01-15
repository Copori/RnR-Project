import GoodBooks from "./GoodBooks";
import { useState, useEffect } from "react";

function MainBooks() {
  //API variables
  const API_URL = "http://api.kcisa.kr/openapi/service/rest/meta4/getKCPG0506";
  const SECRET_KEY = process.env.REACT_APP_RECOMMEND_SERVICE_KEY;
  let pageNum = 1; //total books = 825
  let countBook = 20; // 불러올 책의 개수

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
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  //View
  return (
    <div>
      {loading ? (
        <h1>Loading...</h1>
      ) : (
        <div>
          {books.map((book) => (
            <GoodBooks
              bookImg={book.referenceIdentifier}
              bookTitle={book.title}
              bookPublisher={book.extent}
              bookPublishDate={book.issuedDate}
              bookDetailURL={book.url}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default MainBooks;

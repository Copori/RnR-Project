import View from "./View";

function Slider({ totalBooks }) {
  const bookScreen1 = totalBooks.slice(0, 5);
  const bookScreen2 = totalBooks.slice(5, 10);
  const bookScreen3 = totalBooks.slice(10, 15);
  const bookScreen4 = totalBooks.slice(15, 20);
  return (
    <div className="GoodBooks__Slider">
      <input type="radio" name="check" id="check1" defaultChecked />
      <input type="radio" name="check" id="check2" />
      <input type="radio" name="check" id="check3" />
      <input type="radio" name="check" id="check4" />

      <ul className="GoodBooks__Slider__box">
        <li className="GoodBooks__Slider__contents">
          {bookScreen1.map((book) => (
            <View
              key={book.url}
              bookImg={book.referenceIdentifier}
              bookTitle={book.title}
              bookDetailURL={book.url}
              bookAuthor={book.rights}
              bookIntroducer={book.subDescription}
            />
          ))}
        </li>
        <li className="GoodBooks__Slider__contents">
          {bookScreen2.map((book) => (
            <View
              key={book.url}
              bookImg={book.referenceIdentifier}
              bookTitle={book.title}
              bookDetailURL={book.url}
              bookAuthor={book.rights}
              bookIntroducer={book.subDescription}
            />
          ))}
        </li>
        <li className="GoodBooks__Slider__contents">
          {bookScreen3.map((book) => (
            <View
              key={book.url}
              bookImg={book.referenceIdentifier}
              bookTitle={book.title}
              bookDetailURL={book.url}
              bookAuthor={book.rights}
              bookIntroducer={book.subDescription}
            />
          ))}
        </li>
        <li className="GoodBooks__Slider__contents">
          {bookScreen4.map((book) => (
            <View
              key={book.url}
              bookImg={book.referenceIdentifier}
              bookTitle={book.title}
              bookDetailURL={book.url}
              bookAuthor={book.rights}
              bookIntroducer={book.subDescription}
            />
          ))}
        </li>
      </ul>
      <p className="balls">
        <label htmlFor="check1">1</label>
        <label htmlFor="check2">2</label>
        <label htmlFor="check3">3</label>
        <label htmlFor="check4">4</label>
      </p>
    </div>
  );
}

export default Slider;

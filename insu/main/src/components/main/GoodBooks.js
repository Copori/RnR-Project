function GoodBooks({
  bookImg,
  bookTitle,
  bookPublisher,
  bookPublishDate,
  bookDetailURL,
}) {
  return (
    <div key={bookDetailURL}>
      <a href={bookDetailURL}>
        <img src={bookImg} alt="noImg" />
        <br />

        <span>{bookTitle}</span>
        <br />
      </a>
      <span>출판사: {bookPublisher}</span>
      <br />
      <span>출간일: {bookPublishDate}</span>
      <br />
      <br />
    </div>
  );
}

export default GoodBooks;

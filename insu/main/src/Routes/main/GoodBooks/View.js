function View({ bookImg, bookTitle, bookDetailURL, bookAuthor }) {
  return (
    <div className="GoodBooks__View__box" key={bookDetailURL}>
      <img src={bookImg} alt="noImg" />
      <div className="GoodBooks__View__box__contents--text">
        <span>{bookTitle}</span>
        <span>{bookAuthor}</span>
      </div>
    </div>
  );
}

export default View;

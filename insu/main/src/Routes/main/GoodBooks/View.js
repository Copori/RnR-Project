import {Link} from "react-router-dom";

function View({ totalBooks }) {
  return (
    <div className="GoodBooks__Slide">

      <div>
          {totalBooks.map((books, index) => (
        <div className="GoodBooks__View" key={index}>
          {books.map((book)=>(
            <div className="GoodBooks__View__box" key={book.url}>
              <div className="GoodBooks__View__box--cover"></div>
              <img src={book.referenceIdentifier} alt="n"/>
              
              <div className="GoodBooks__View__box__contents--text">
              <span>{book.title}</span>
              <span>{book.rights}</span>
              </div>
            </div>
          ))}
        </div>
          ))}
      
      </div>
    </div>
  );
}

export default View;

import {FiHeart} from "react-icons/fi"
import {GoBook} from "react-icons/go"

function View({ totalBooks }) {

  return (
    <div className="GoodBooks__Slide">

      <div>
          {totalBooks.map((books, index) => (
        <div className="GoodBooks__View" key={index}>
          {books.map((book)=>(
            <div className="GoodBooks__View__box" key={book.url}>
      
              <div className="GoodBooks__View__box--cover">
                <div><FiHeart/></div>
                <div><a href={book.url} target="_blank"><GoBook/></a></div>
              </div>
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

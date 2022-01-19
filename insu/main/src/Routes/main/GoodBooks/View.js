import {HiOutlineHeart, HiHeart} from "react-icons/hi"
import {GoBook} from "react-icons/go"
import {useState} from "react"



function View({ totalBooks, booksArray, bookToggle }) {
  const [chooseList, setChooseList] = useState([]);

  const onClick = (e) => {
    const urll = e.target.parentElement.title;
    console.dir(urll);
    setChooseList(chooseList.concat(totalBooks.filter((book)=>book.title == urll)));
    console.log(chooseList);
    console.log(bookToggle);
  }

  return (
    <div className="GoodBooks__Slide">
      {bookToggle?
      <div>
          {booksArray.map((books, index) => (
        <div className="GoodBooks__View" key={index}>
          {books.map((book)=>(
            <div className="GoodBooks__View__box" key={book.url}>
      
              <div className="GoodBooks__View__box--cover">
                <div><a onClick={onClick} title={book.title}><HiOutlineHeart/></a></div>
                <div><a target="_blank" href={book.url} rel="noreferrer"><GoBook/></a></div>
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
      :
      <div>
        {chooseList.map((book, index)=>(
           <div key={index}>
             <img src={book.referenceIdentifier}/>
             {book.title}
           </div>
           ))}
      </div>
      }
    </div>
  );
}

export default View;

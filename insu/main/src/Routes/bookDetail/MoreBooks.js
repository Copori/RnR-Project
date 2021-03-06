import {ImCancelCircle} from "react-icons/im"
import {HiOutlineHeart} from "react-icons/hi"
import {GoBook} from "react-icons/go"
import {useState} from "react"
import {Link} from "react-router-dom";


const MoreBooks = ({moreBooks, bookToggle, totalBooks}) => {
    const [chooseList, setChooseList] = useState([]);
    const [moreBookLists, setMoreBookLists] = useState([]);
    const [loading, setLoading] = useState(true);
  const setting = new Set(chooseList);
  const settingList = [...setting];
  setMoreBookLists(...moreBooks);

    if(moreBookLists.length > 0) {
        setLoading(false);
    }

  const onBookClick = (e) => {
    const urll = e.target.parentElement.title;
    console.dir(urll);
    setChooseList(chooseList.concat(totalBooks.filter((book)=>book.title == urll)));
    console.log(chooseList);
    console.log(bookToggle);
  }

  const onCancelClick = (e) => {
    const urll = e.target.parentElement.title;
    console.dir(urll);
    setChooseList(settingList.filter((book)=>book.title !== urll));
  }

    return(
        
        <div className="GoodBooks__Slide">
        {bookToggle?
        <div>
            {moreBookLists.map((books, index) => (
          <div className="GoodBooks__View" key={index}>
            {books.map((book)=>(
              <div className="GoodBooks__View__box" key={book.url}>
        
                <div className="GoodBooks__View__box--cover">
                  <div className="GoodBooks__View__box__cover--like" onClick={onBookClick} title={book.title}><HiOutlineHeart/></div>
                  <Link to={{
                    pathname: "/books",
                    search: `?query=${book.url}`
                  }}>
                    <div className="GoodBooks__View__box__cover--detail"><GoBook/></div></Link>
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
          {settingList.map((book, index)=>(
             <div className="GoodBooks__CheckBook" key={index}>
               <div className="GoodBooks__CheckBook--cancel" onClick={onCancelClick} title={book.title}><ImCancelCircle/></div>
               <div className="CheckBook--img">
               <img src={book.referenceIdentifier} alt="no"/>
               </div>
               <div className="GoodBooks__CheckBooks__text">
               <span className="GoodBooks__CheckBooks__text--title"> {book.title}</span>
               <span className="GoodBooks__CheckBooks__text--author">???/?????? : {book.rights}</span>
               <span className="GoodBooks__CheckBooks__text--publishDate">????????? : {book.issuedDate}</span>
               <span className="GoodBooks__CheckBooks__text--recommender">????????? : {book.subDescription}</span>
               </div>
             </div>
             ))}
        </div>
        }
      </div>
    );
}

export default MoreBooks;
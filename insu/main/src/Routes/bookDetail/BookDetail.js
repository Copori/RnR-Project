const BookDetail = () => {
  return (
    <div className="BookDetail">
      <div className = "BookDetail__Containter">
      <span className="BookDetail__Container--text">Read&Review</span>
      <div className="BookDetail__Container__box">
      <div className="BookDetail__Container__box--img">
        <div>
          Img
        </div>
      </div>
      <div className="BookDetail__Container__box--text">
        <div>
        <span> 이덕환의 사이언스 토크토크</span>
        <span>저/역자 : 이덕환</span>
        <span>추천자 : 장경애(과학동아 경영 기획실장)</span>
        <span>출판일 : 2010.07.30</span>
        <span>총 페이지 : 318p</span>
        </div>
      </div>
      </div>
      <span className="BookDetail__Container__subTitle">Book Description</span>
      <div className="BookDetail__Container__subContents">
      <p>
      ‘칼국수 같은 면류에 공업용 에탄올을 넣은 식품 제조업자가 구속됐다’는
      뉴스를 보고 사람들은 한동안 면류 사먹기를 꺼린다. 이에 대해 저자는 술의
      주성분이기도 한 에탄올이 식용과 공업용으로 구분되지 않으며 공업용 에탄올에
      불순물이더 들어있다는 주장도 잘못됐다고 이야기한다. 그러면서 주세를 내지
      않은 에탄올을 식용으로 사용하지 못하게 의도적으로 첨가물(마실 수 없을
      정도로 쓴맛을 내는 물질이나 인체에 독성이 강한 첨가제)을 넣은 변성알코올을
      설명한다. 즉 국수 사건의 핵심은 주세를 낸 술 대신 변성 알코올을 사용한 탈세
      행위라는 것이다. 저자는 일상에서 경험하는 문제를 합리적으로 파악하고 이웃과
      소통하고 독자적으로 판단하는 과학적 인성을 길러 주는 것이 과학교육의 목표이며
      이러한 과학적 인성을 기르도록 도와주는 훌륭한 소재가 바로 뉴스라고 말한다.
      ‘숨 쉬는 그릇 옹기의 진실, 비행기를 많이 탈수록 우주 방사선에 노출된다, 홍시
      숙성시키는 카바이드의 정체, 천연치클 껌이 더 좋을까, 화산재가 항공대란 일으킨
      이유, 천안함 인양의 어려움을 설명한 표면장력은 오류, 이천 냉동고 화재 속 폴리
      우레탄의 정체, 광우병 논란 속 프리온과 변형 프리온, 유가 인상에 대한 정부 대처의
      문제점, 세포 관찰하는 해파리의 형광단백질’ 등 다양한 뉴스 속에 등장하는 과학적
      개념과 오류를 집어낸다.
        </p>
        </div>
      </div>
      <div className="BookDetail__Review">
        <span className="BookDetail__Review__title">Review</span>
        <div className="BookDetail__Reivew__contents">
          <div className="BookDetail__Reivew__contents__box">
          <div className="BookDetail__Reivew__contents__box--star">
            <span>❤❤❤❤❤</span>
          </div>
          <div className="BookDetail__Reivew__contents__box--id">
            <span>닉네임: insu</span>
          </div>
          <div className="BookDetail__Reivew__contents__box--text">
            <span>너무 잘 읽었습니다. 추천합니다.</span>
          </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookDetail;

import React, { useState } from "react";
import { Drawer, Button } from "antd";
import styled from "styled-components";
import { QuestionOutlined } from "@ant-design/icons";

const StyleDrawer: React.FC = () => {
  const [visible, setVisible] = useState(false);
  const showDrawer = () => {
    setVisible(true);
  };
  const onClose = () => {
    setVisible(false);
  };
  return (
    <>
      <InfoBtn onClick={showDrawer}>
        <QuestionOutlined />
      </InfoBtn>
      <Drawer
        title="와인 정보"
        placement="right"
        onClose={onClose}
        visible={visible}
      >
        <div>
          <span className="자" style={{ marginRight: "10px" }}>
            당도
          </span>
          dry or sweet 드라이한지 달달한지
          <p>
            와인에서 느껴지는 단맛의 정도를 가리키는 말. 당도가 낮을수록 Dry
            하다고 표현한다. 하지만 완숙된 과일향이 풍부한 와인의 경우, 실제로는
            드라이해도 달게 여겨지는 경우도 있다.
          </p>
        </div>
        <br />
        <div>
          <span className="헬스왕" style={{ marginRight: "10px" }}>
            산도
          </span>
          soft or acidic 부드러운지 신지
          <p>
            와인에서 느껴지는 신맛의 정도를 가리키는 말. 신맛은 산도가 높을수록
            강하다{" "}
          </p>
        </div>
        <br />
        <div>
          <span className="환경미화원" style={{ marginRight: "10px" }}>
            바디
          </span>
          light or bold 가벼운지 무거운지{" "}
          <p>
            입안에서 인지되는 와인의 질감 혹은 무게감을 일컬으며, 알코올 함량에
            따라 다르게 느껴진다. 일반적으로 알코올 함량이 높을수록 와인은 풀
            보디(Full bodied)에 가까워진다. 보통 우유는 무겁다고 말하고 녹차는
            가볍다고 말한다. 맛이 혀에 오래 머문다면 무거운 것이다
          </p>
        </div>
        <br />
        <div>
          <span className="넓고" style={{ marginRight: "10px" }}>
            타닌
          </span>
          smooth or tannic 부드러운지 거친지{" "}
          <p>
            포도의 씨, 껍질, 그리고 줄기, 또는 오크(oak/참나무) 배럴이 갖고 있는
            페놀(phenols) 성분으로, 와인을 생산하는 과정에서 와인에 자연스럽게
            첨가되는 성분이다. 탄닌은 방부제 작용을 하기 때문에 와인에 탄닌이
            많이 함유된 경우, 쉽게 변질되지 않는다. 또한 탄닌은 와인의 맛을
            복합적으로 만들어주는 중요한 작용을 하기도 한다. 타닌이 강할수록 덜
            익은 감을 먹었을 때와 같이 떫고 쌉싸름한 맛이 난다
          </p>
        </div>
        <br />
        <div>
          <span className="공부벌레" style={{ marginRight: "10px" }}>
            도수
          </span>
          다른 술의 알콜 함유량과 비교해보자
          <p>
            <div>참이슬 Fresh 16.9 도</div>
            <div>참이슬 오리지널 20.1 도</div>
            <div>테라 4.6 도</div>
            <div>장수막걸리 6 도</div>
            <div>백세주 13 도</div>
          </p>
        </div>
        <br />
        <div>
          <span className="바른" style={{ marginRight: "10px" }}>
            와인향
          </span>
          신선한 과실맛과 과실향을 머금은 와인을 뜻한다.
        </div>
        {/* <br />
        <div>
          <span className="취향입니다" style={{ marginRight: "10px" }}>
            포도종류
          </span>
          기타
        </div>
        <br />
        <div>
          <span className="아이돌" style={{ marginRight: "10px" }}>
            아이돌
          </span>
          완료
        </div>
        <br />
        <div>
          <span className="따뜻한" style={{ marginRight: "10px" }}>
            따뜻한
          </span>
          이상
        </div>
        <br />
        <div>
          <span className="뜨거운" style={{ marginRight: "10px" }}>
            뜨거운
          </span>
          이상
        </div>
        <br />
        <div>
          <span className="불타오르는" style={{ marginRight: "10px" }}>
            불타
          </span>
          이상
        </div>
        <br />
        <div>
          <span className="태양" style={{ marginRight: "10px" }}>
            태양
          </span>
          이상
        </div> */}
      </Drawer>
    </>
  );
};

const InfoBtn = styled(Button)`
  margin-top: 5px;
  // margin-right: 5px;
  border: 2px solid black;
  border-radius: 100%;
  padding: 2px 8px;
`;

export default StyleDrawer;

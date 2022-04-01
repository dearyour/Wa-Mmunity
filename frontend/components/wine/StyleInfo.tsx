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
          첫 도전 완료
        </div>
        <br />
        <div>
          <span className="헬스왕" style={{ marginRight: "10px" }}>
            산도
          </span>
          건강 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="환경미화원" style={{ marginRight: "10px" }}>
            바디
          </span>
          친환경 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="취향입니다" style={{ marginRight: "10px" }}>
            타닌
          </span>
          취미 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="공부벌레" style={{ marginRight: "10px" }}>
            도수
          </span>
          학습 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="바른" style={{ marginRight: "10px" }}>
            와인향
          </span>
          식습관 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="넓고" style={{ marginRight: "10px" }}>
            넓고 깊은
          </span>
          기타 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="아이돌" style={{ marginRight: "10px" }}>
            아이돌
          </span>
          외모관리 챌린지 5번 완료
        </div>
        <br />
        <div>
          <span className="따뜻한" style={{ marginRight: "10px" }}>
            따뜻한
          </span>
          온도 40도 이상
        </div>
        <br />
        <div>
          <span className="뜨거운" style={{ marginRight: "10px" }}>
            뜨거운
          </span>
          온도 60도 이상
        </div>
        <br />
        <div>
          <span className="불타오르는" style={{ marginRight: "10px" }}>
            불타오르는
          </span>
          온도 80도 이상
        </div>
        <br />
        <div>
          <span className="태양" style={{ marginRight: "10px" }}>
            태양
          </span>
          온도 100도 이상
        </div>
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

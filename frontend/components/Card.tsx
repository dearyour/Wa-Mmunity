import React from "react";
import styled from "@emotion/styled";
import Link from "next/link";
import { AiFillStar } from "react-icons/ai";
import Rating from "@mui/material/Rating";
interface Props {
  linkUrl: string;
  title: string;
  year: string;
  posterPath: string;
  voteAverage: number;
}

const StyledLink = styled(Link)`
  text-decoration: none;
  display: block;
  margin-inline: 10px;
`;

const Base = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const ImageWrapper = styled.div`
  width: 30%;
  // height: 300px;
  margin-left: 35%;
  cursor: pointer;
`;

const Image = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 4px;
`;

const Info = styled.div`
  text-align: left;
  width: 100%;
`;

const Title = styled.h4`
  color: #292a32;
  font-size: 16px;
  font-weight: 500;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 22px;
  margin-bottom: 3px;
  white-space: nowrap;
  // max-width: 200px;
  text-align: center;
  margin-top: 15px;
`;

const Keyword = styled.div`
  color: #292a32;
  padding-bottom: 1px;
  font-size: 14px;
  font-weight: 400;
  line-height: 21px;
  text-align: center;
`;

const Average = styled.div`
  color: #74747b;
  font-size: 13px;
  font-weight: 400;
  margin-top: 5px;
  padding-bottom: 15px;
  align-items: center;
  text-align: center;
`;

const Card: React.FC<Props> = ({
  linkUrl,
  title,
  posterPath,
  voteAverage,
  year,
}) => (
  <StyledLink href={linkUrl}>
    <Base>
      <ImageWrapper>
        <Image src={posterPath} alt={`${title} 의 포스터`} />
      </ImageWrapper>
      <Info>
        <Title>{title}</Title>
        <Keyword>최근 리뷰 : {year}</Keyword>
        <Average>
          <span>
            &nbsp;
            <div>
              <Rating
                name="text-feedback"
                value={Number(voteAverage.toFixed(1))}
                readOnly
                precision={0.5}
                size="large"
                // emptyIcon={
                //   <StarIcon style={{ opacity: 0.55 }} fontSize="inherit" />
                // }
              />
            </div>
            {/* <AiFillStar /> */}
          </span>
          <span>평점</span>
          <span>{voteAverage.toFixed(1)}</span>
        </Average>
      </Info>
    </Base>
  </StyledLink>
);

export default Card;

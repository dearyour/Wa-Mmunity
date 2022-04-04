import React from 'react';
import styled from '@emotion/styled';

const ContentSection = styled.section`
  padding: 11px 15px;
  border-bottom: 1px solid #ededed;
`;

const ContentHeaderWrapper = styled.div``;

const ContentHeader = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Link = styled.a`
  text-decoration: none;
  color: #ff2f6e;
`;

const ContentTitle = styled.h2`
  color: #000;
  font-size: 19px;
  font-weight: 700;
  margin: 8px 0;
`;

const Summary = styled.div`
  color: #4a4a4a;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
`;

const MoreSee = styled.div``;

interface Props {
  title: string;
  year: string;
  genres: string;
  runtime: number;
  overview: string;
}

const DefaultInfo: React.FC<Props> = ({ title, year, genres, runtime, overview }) => {
  const hour = Math.ceil(runtime / 60);
  const minute = runtime % 60;

  return (
    <ContentSection>
      <ContentHeaderWrapper>
        <ContentHeader>
          <ContentTitle>기본 정보</ContentTitle>
          <Link href="/overview">
            <MoreSee>더보기</MoreSee>
          </Link>
        </ContentHeader>
        <Summary>
          {title}
          <br />
          {year} ・ {genres}
          <br />
          {hour}시간 {minute}분
          <br />
          <br />
          {overview}
        </Summary>
      </ContentHeaderWrapper>
    </ContentSection>
  )
}

export default DefaultInfo;

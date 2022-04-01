import React from 'react';
import styled from '@emotion/styled';
import useSimilarMovie from '../useMovieSimilar';

const Base = styled.section`
  padding: 11px 15px;
  border-bottom: 1px solid #ededed;
`;

const ContentHeaderWrapper = styled.div``;

const ContentHeader = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const ContentTitle = styled.h2`
  color: #000;
  font-size: 19px;
  font-weight: 700;
  margin: 8px 0;
`;

const ContentsWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  margin-top: 15px;
  row-gap: 24px;
`;

const CardContainer = styled.div`
  max-width: 140px;
`;

const PosterWrapper = styled.div`
  width: 140px;
  height: 204px;
  border: 1px solid rgb(234, 233, 232);
`;

const Poster = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 5px;
  vertical-align: top;
  object-fit: cover;
`;

const Info = styled.div`
  margin: 5px 10px 0px 0px;
`;

const Title = styled.div`
  color: rgb(41, 42, 50);
  font-size: 16px;
  font-weight: 500;
  line-height: 22px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const VoteAverage = styled.div`
  margin-top: 2px;
  color: rgb(120, 120, 120);
  font-size: 13px;
  font-weight: 400;
  line-height: 18px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const Link = styled.a`
  text-decoration: none;
`;

interface Props {
  id: string;
}

interface MovieProps {
  id: number;
  posterPath: string;
  title: string;
  voteAverage: number;
}

const Card: React.FC<MovieProps> = ({ id, posterPath, title, voteAverage }) => {
  return (
    <Link href={`/movie/${id}`} target="_blank">
      <CardContainer>
        <PosterWrapper>
          <Poster src={`${process.env.REACT_APP_IMAGE_PREFIX}/${posterPath}`} />
        </PosterWrapper>
        <Info>
          <Title>{title}</Title>
          <VoteAverage>평균 ★ {voteAverage}</VoteAverage>
        </Info>
      </CardContainer>
    </Link>
  )
}

const Similar: React.FC<Props> = ({ id }) => {
  const { isLoading, data } = useSimilarMovie(id);

  return (
    <Base>
      <ContentHeaderWrapper>
        <ContentHeader>
          <ContentTitle>비슷한 작품</ContentTitle>
        </ContentHeader>
      </ContentHeaderWrapper>
      <ContentsWrapper>
        {
          isLoading || !data ? (
            <div>Loading...</div>
          ) : (
            data.results.map(result => (
              <Card
                id={result.id}
                posterPath={result.poster_path}
                title={result.title}
                voteAverage={result.vote_average}
              />
            ))
          )
        }
      </ContentsWrapper>
    </Base>
  )
}

export default Similar;

import React from "react";
import AppLayout from "../components/layout/AppLayout";
import MyPage from "../components/member/MyPage";
import WordCloud from "../components/wordcloud/WordCloud";
import WishList from "../components/member/WishList";
function myPage(): any {
  return (
    <AppLayout>
      <MyPage />
      <WordCloud />
      <WishList />
    </AppLayout>
  );
}

export default myPage;

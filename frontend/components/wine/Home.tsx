import React, { useEffect, useState, useCallback } from "react";
import EmptyView from "../../components/common/EmptyView";
import FilterPanel from "../../components/Home/FilterPanel";
import List from "../../components/Home/List";
import SearchBar from "../../components/Home/SearchBar";
import { dataList } from "../../constants";
import axios from "axios";
import InfiniteScroll from "react-infinite-scroll-component";
import { useDispatch, useSelector } from "react-redux";
import { feedAction } from "store/slice/feed";
import { RootState } from "../../store/module";
import styled from "@emotion/styled";
// import "./styles.css";

const sortOptionList = [
  { value: "latest", name: "높은가격 순서" },
  { value: "oldest", name: "낮은가격 순서" },
  { value: "ratingDesc", name: "높은평점 순서" },
  { value: "ratingAsc", name: "낮은평점 순서" },
];

const filterOptionList = [
  { value: "latests", name: "높은평점 순서" },
  { value: "oldests", name: "낮은평점 순서" },
];

const ControlMenu = React.memo(({ value, onChange, optionList }: any) => {
  return (
    <select
      className="ControlMenu"
      value={value}
      onChange={(e) => onChange(e.target.value)}
    >
      {optionList.map((it: any, idx: number) => (
        <option key={idx} value={it.value}>
          {it.name}
        </option>
      ))}
    </select>
  );
});

const Home = () => {
  //인피니티 스크롤
  const dispatch = useDispatch();
  const [loading, setLoading] = useState<boolean>(false);
  const [nowFeedsnum, setNowFeedsNum] = useState(10);
  const [sortType, setSortType] = useState<String>("latest");
  const [filter, setFilter] = useState<String>("latests");

  const loadmoredata = () => {
    if (loading) {
      return;
    }
    setLoading(true);
    setTimeout(() => {
      setNowFeedsNum(nowFeedsnum + 5);
    }, 1000);
    setLoading(false);
  };

  const [wines, setWines] = useState([]); //프롭으로내려주자
  const [selectedCategory, setSelectedCategory] = useState<any>(null);
  const [selectedRating, setSelectedRating] = useState(null);
  const [selectedPrice, setSelectedPrice] = useState([1, 300000]);
  const [searchInput, setSearchInput] = useState("");
  const [list, setList] = useState(dataList); //이부분 axios 가져올것;

  const feedstate = useSelector((state: RootState) => state.feed.items);
  const __GetWineState = useCallback(() => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "wine",
      // url: "http://j6a101.p.ssafy.io:8080/api/wine",
    })
      .then((res) => {
        console.log(res);
        setWines(res.data.object);
        return res.data;
      })
      .catch((err) => {
        return err;
      });
  }, []);

  useEffect(() => {
    __GetWineState();
    dispatch(feedAction.getFeed());
  }, [__GetWineState, dispatch]);

  const [cuisines, setCuisines] = useState<any[]>([
    { id: 1, checked: false, label: "France" },
    { id: 2, checked: false, label: "Italy" },
    { id: 3, checked: false, label: "Spain" },
    { id: 4, checked: false, label: "Chile" },
    { id: 5, checked: false, label: "Germany" },
    { id: 6, checked: false, label: "Argentina" },
  ]);

  const [regions, setRegions] = useState<any[]>([
    { id: 1, checked: false, label: "Cabernet Sauvignon" },
    { id: 2, checked: false, label: "Shiraz/Syrah" },
    { id: 3, checked: false, label: "Pinot Noir" },
    { id: 4, checked: false, label: "Chardonnay" },
    { id: 5, checked: false, label: "Riesling" },
    { id: 6, checked: false, label: "Sauvignon Blanc" },
  ]);

  const [resultsFound, setResultsFound] = useState(true);

  const handleSelectCategory = (event: Event, value: any) =>
    !value ? null : setSelectedCategory(value);

  const handleSelectRating = (event: Event, value: any) =>
    !value ? null : setSelectedRating(value);

  const handleChangeChecked = (id: any) => {
    const cusinesStateList = cuisines;
    const changeCheckedCuisines = cusinesStateList.map((item) =>
      item.id === id ? { ...item, checked: !item.checked } : item
    );
    setCuisines(changeCheckedCuisines);
  };

  const handleChangeCheckeds = (id: any) => {
    const regionsStateList = regions;
    const changeCheckedRegions = regionsStateList.map((item) =>
      item.id === id ? { ...item, checked: !item.checked } : item
    );
    setRegions(changeCheckedRegions);
  };

  const handleChangePrice = (event: Event, value: any) => {
    setSelectedPrice(value);
  };

  const filterCallBack = (item: any) => {
    if (filter === "good") {
      return parseInt(item.ratingAvg) <= 723;
    } else if (filter === "bad") {
      return parseInt(item.ratingAvg) > 723;
    }
    // if (filter === "latests") {
    //   return (
    //     parseFloat(b.ratingAvg.toFixed(1)) - parseFloat(a.ratingAvg.toFixed(1))
    //   );
    // } else {
    //   return (
    //     parseFloat(a.ratingAvg.toFixed(1)) - parseFloat(b.ratingAvg.toFixed(1))
    //   );
    // }
  };
  const compare = (a: any, b: any) => {
    if (sortType === "latest") {
      return parseInt(b.price) - parseInt(a.price);
    } else if (sortType === "oldest") {
      return parseInt(a.price) - parseInt(b.price);
    } else if (sortType === "ratingDesc") {
      return (
        parseFloat(b.ratingAvg.toFixed(1)) - parseFloat(a.ratingAvg.toFixed(1))
      );
    } else if (sortType === "ratingAsc") {
      return (
        parseFloat(a.ratingAvg.toFixed(1)) - parseFloat(b.ratingAvg.toFixed(1))
      );
    }
  };

  //////////////////////////////////////////////////////////////////
  const applyFilters = useCallback(() => {
    if (feedstate) {
      let updatedList = feedstate;
      console.log(updatedList);

      // Rating Filter
      if (selectedRating) {
        updatedList = updatedList.filter(
          (item: any) =>
            item.ratingAvg >= parseFloat(selectedRating) &&
            item.ratingAvg <= parseFloat(selectedRating) + Number(0.6)
        );
      }

      // Category Filter 정확하지 않게 데이터 떨어질때, 중간문자열 포함
      if (selectedCategory) {
        // updatedList = updatedList.filter(
        //   (item) => item.wineStyle === selectedCategory
        // );
        updatedList = updatedList.filter(
          (item: any) =>
            // selectedCategory.includes(item.wineStyle)
            // item.wineStyle.includes(selectedCategory)
            item.cat1
              .toLowerCase()
              .search(selectedCategory.toLowerCase().trim()) !== -1
        );
      }

      // Cuisine Filter 정확하게 데이터 떨어질때
      const cuisinesChecked = cuisines
        .filter((item) => item.checked)
        .map((item) => item.label.toLowerCase());

      if (cuisinesChecked.length) {
        updatedList = updatedList.filter((item: any) =>
          cuisinesChecked.includes(item.country.toLowerCase())
        );
      }

      // regions Filter
      const regionsChecked = regions
        .filter((item) => item.checked)
        .map((item) => item.label.toLowerCase());

      if (regionsChecked.length) {
        updatedList = updatedList.filter((item: any) =>
          regionsChecked.includes(item.grape1.toLowerCase())
        );
      }

      // Search Filter
      if (searchInput) {
        updatedList = updatedList.filter(
          (item: any) =>
            item.name.toLowerCase().search(searchInput.toLowerCase().trim()) !==
            -1
        );
      }

      // Price Filter
      const minPrice = selectedPrice[0];
      const maxPrice = selectedPrice[1];
      updatedList = updatedList.filter(
        (item: any) => item.price >= minPrice && item.price <= maxPrice
      );
      //가격순 평점순 정렬
      // const newFilter = filter;
      // updatedList = updatedList.filter((it: any) => filterCallBack(it));
      // updatedList = updatedList.sort(filterCallBack);
      updatedList = updatedList.sort(compare);
      // setList(updatedList);
      setWines(updatedList);
      // setWines(updatedListr);
      console.log(updatedList);

      !updatedList.length ? setResultsFound(false) : setResultsFound(true);
    }
  }, [
    filter,
    sortType,
    cuisines,
    regions,
    searchInput,
    selectedCategory,
    selectedPrice,
    selectedRating,
    feedstate,
  ]);

  useEffect(() => {
    applyFilters();
  }, [
    selectedRating,
    selectedCategory,
    cuisines,
    regions,
    searchInput,
    selectedPrice,
    applyFilters,
  ]);

  return (
    <div className="home">
      {/* Search Bar */}
      <SearchBar
        value={searchInput}
        data={wines}
        changeInput={(e: any) => setSearchInput(e.target.value)}
      />
      <div className="home_panelList-wrap">
        {/* Filter Panel */}
        <div className="home_panel-wrap">
          <FilterPanel
            selectedCategory={selectedCategory}
            selectCategory={handleSelectCategory}
            selectedRating={selectedRating}
            selectedPrice={selectedPrice}
            selectRating={handleSelectRating}
            cuisines={cuisines}
            regions={regions}
            changeChecked={handleChangeChecked}
            changeCheckeds={handleChangeCheckeds}
            changePrice={handleChangePrice}
          />
          <div className="menu_wrapper">
            <div className="left_col">
              <ControlMenu
                value={sortType}
                onChange={setSortType}
                optionList={sortOptionList}
              />
              {/* <ControlMenu
                value={filter}
                onChange={setFilter}
                optionList={filterOptionList}
              /> */}
            </div>
          </div>
        </div>
        {/* List & Empty View */}
        <div className="home_list-wrap">
          {/* <Winielist /> */}
          {wines ? (
            <InfiniteScroll
              dataLength={wines.slice(0, nowFeedsnum).length} //This is important field to render the next data
              next={loadmoredata}
              hasMore={nowFeedsnum < wines.length}
              loader={<div style={{ textAlign: "center" }}>🌟Loading...🌟</div>}
              endMessage={
                <div style={{ textAlign: "center" }}>
                  <div>🚩 검색 완료 🚩</div>
                </div>
              }
            >
              {wines &&
                wines.slice(0, nowFeedsnum).map((item: any, idx: number) => {
                  // console.log(feeds);
                  // console.log(feedstate.length)
                  // console.log(nowFeedsnum)

                  return <List list={item} key={idx} />;
                })}
            </InfiniteScroll>
          ) : (
            <EmptyView />
          )}

          {/* {resultsFound ? <List list={wines} /> : <EmptyView />} */}
        </div>
      </div>
    </div>
  );
};

const SearchWrapper = styled.div`
  display: flex;
`;
export default Home;

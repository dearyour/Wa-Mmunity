import React, { useEffect, useState, useCallback } from "react";
import EmptyView from "../../components/common/EmptyView";
import FilterPanel from "../../components/Home/FilterPanel";
import List from "../../components/Home/List";
import SearchBar from "../../components/Home/SearchBar";
import { dataList } from "../../constants";
import axios from "axios";
import Card from "../card/card";
import InfiniteScroll from "react-infinite-scroll-component";
import { useDispatch, useSelector } from "react-redux";
import { feedAction } from "store/slice/feed";
import { RootState } from "../../store/module";
// import "./styles.css";
const Home = () => {
  //인피니티 스크롤
  const dispatch = useDispatch();
  const [loading, setLoading] = useState<boolean>(false);
  const [nowFeedsnum, setNowFeedsNum] = useState(10);
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
  const [selectedPrice, setSelectedPrice] = useState([1, 500000]);
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
    { id: 3, checked: false, label: "Hungary" },
    { id: 4, checked: false, label: "Portugal" },
    { id: 5, checked: false, label: "Germany" },
    { id: 6, checked: false, label: "Spain" },
  ]);

  const [regions, setRegions] = useState<any[]>([
    { id: 1, checked: false, label: "Duriense" },
    { id: 2, checked: false, label: "Central Valley" },
    { id: 3, checked: false, label: "Vin de Pays" },
    { id: 4, checked: false, label: "Mendoza" },
    { id: 5, checked: false, label: "Piemonte" },
    { id: 6, checked: false, label: "South Australia" },
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
  //////////////////////////////////////////////////////////////////
  const applyFilters = useCallback(() => {
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

    // Category Filter
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

    // Cuisine Filter
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
        regionsChecked.includes(item.region1.toLowerCase())
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

    // setList(updatedList);
    setWines(updatedList);
    console.log(updatedList);

    !updatedList.length ? setResultsFound(false) : setResultsFound(true);
  }, [
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

export default Home;

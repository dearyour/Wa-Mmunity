import React, { useEffect, useState, useCallback } from "react";
import EmptyView from "../../components/common/EmptyView";
import FilterPanel from "../../components/Home/FilterPanel";
import List from "../../components/Home/List";
import SearchBar from "../../components/Home/SearchBar";
import { dataList } from "../../constants";
import axios from "axios";
import Card from "../card/card";
import InfiniteScroll from "react-infinite-scroll-component";
// import "./styles.css";
const Home = () => {
  //인피니티 스크롤
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
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedRating, setSelectedRating] = useState(null);
  const [selectedPrice, setSelectedPrice] = useState([1000, 500000]);
  const [searchInput, setSearchInput] = useState("");
  const [list, setList] = useState(dataList); //이부분 axios 가져올것;

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
  }, []);

  const [cuisines, setCuisines] = useState([
    { id: 1, checked: false, label: "France" },
    { id: 2, checked: false, label: "Chinese" },
    { id: 3, checked: false, label: "Italian" },
    { id: 4, checked: false, label: "American" },
    { id: 5, checked: false, label: "Chinese" },
    { id: 6, checked: false, label: "Italian" },
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

  const handleChangePrice = (event: Event, value: Number) => {
    setSelectedPrice(value);
  };
  //////////////////////////////////////////////////////////////////
  const applyFilters = useCallback(() => {
    let updatedList = wines;
    console.log(updatedList);

    // Rating Filter
    if (selectedRating) {
      updatedList = updatedList.filter(
        (item) => Number(item.ratingAvg) === parseInt(selectedRating)
      );
    }

    // Category Filter
    if (selectedCategory) {
      // updatedList = updatedList.filter(
      //   (item) => item.wineStyle === selectedCategory
      // );
      updatedList = updatedList.filter((item) =>
        selectedCategory.includes(item.wineStyle)
      );
    }

    // Cuisine Filter
    const cuisinesChecked = cuisines
      .filter((item) => item.checked)
      .map((item) => item.label.toLowerCase());

    if (cuisinesChecked.length) {
      updatedList = updatedList.filter((item) =>
        cuisinesChecked.includes(item.country.toLowerCase())
      );
    }

    // Search Filter
    if (searchInput) {
      updatedList = updatedList.filter(
        (item) =>
          item.name.toLowerCase().search(searchInput.toLowerCase().trim()) !==
          -1
      );
    }

    // Price Filter
    const minPrice = selectedPrice[0];
    const maxPrice = selectedPrice[1];

    updatedList = updatedList.filter(
      (item) => item.price >= minPrice && item.price <= maxPrice
    );

    // setList(updatedList);
    setWines(updatedList);
    console.log(updatedList);

    !updatedList.length ? setResultsFound(false) : setResultsFound(true);
  }, [cuisines, searchInput, selectedCategory, selectedPrice, selectedRating]);

  useEffect(() => {
    applyFilters();
  }, [
    selectedRating,
    selectedCategory,
    cuisines,
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
            changeChecked={handleChangeChecked}
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
              loader={<h4 style={{ textAlign: "center" }}>Loading...</h4>}
              endMessage={
                <p style={{ textAlign: "center" }}>
                  <b>마지막입니다</b>
                </p>
              }
            >
              {wines &&
                wines.slice(0, nowFeedsnum).map((item: any, idx: number) => {
                  // console.log(feeds);
                  // console.log(feedstate.length)
                  // console.log(nowFeedsnum)

                  return <List list={item} index={idx} />;
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

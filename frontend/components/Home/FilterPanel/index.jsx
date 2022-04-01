import React from "react";
import { categoryList, ratingList } from "../../../constants";
import CheckboxProton from "../../common/CheckboxProton";
import CheckboxProtons from "../../common/CheckboxProtons";
import FilterListToggle from "../../common/FilterListToggle";
import SliderProton from "../../common/SliderProton";
// import './styles.css';

const FilterPanel = ({
  selectedCategory,
  selectCategory,
  selectedRating,
  selectedPrice,
  selectRating,
  cuisines,
  regions,
  changeChecked,
  changeCheckeds,
  changePrice,
}) => (
  <div>
    <div className="input-group category">
      <p className="label">와인 스타일</p>
      <FilterListToggle
        options={categoryList}
        value={selectedCategory} // useState값
        selectToggle={selectCategory} //온체인지 함수
      />
    </div>
    <div className="input-groups">
      <div className="input-group">
        <p className="label">원산지</p>
        {cuisines.map((cuisine) => (
          <CheckboxProton
            key={cuisine.id}
            cuisine={cuisine}
            changeChecked={changeChecked}
          />
        ))}
      </div>
      <div className="input-groupr">
        <p className="label">포도 품종</p>
        {regions.map((region) => (
          <CheckboxProtons
            key={region.id}
            region={region}
            changeCheckeds={changeCheckeds}
          />
        ))}
      </div>
    </div>
    <div className="input-group">
      <p className="label-range">가격 범위</p>
      <SliderProton value={selectedPrice} changePrice={changePrice} />
    </div>
    <div className="input-group">
      <p className="label">평균 평점</p>
      <FilterListToggle
        options={ratingList}
        value={selectedRating}
        selectToggle={selectRating}
      />
    </div>
  </div>
);

export default FilterPanel;

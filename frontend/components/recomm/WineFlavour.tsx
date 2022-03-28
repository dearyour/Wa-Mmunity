import React from 'react'

type SurveyProps = {
  prevStep: Function;
  nextStep: Function;
  handleChange: Function;
  values: string;
}


const WineFlavour = ({ prevStep, nextStep, handleChange, values }: SurveyProps) => {
  const Previous = (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    prevStep();
  }
  
  const Continue = (e: { preventDefault: () => void; }) => {
    e.preventDefault ();
    nextStep();
  }

  return (
    <div>
      <h1>와인 향</h1>
      <form action="">
        <input
          type="text"
          placeholder="향"
          onChange={handleChange('flavour')}
          defaultValue={values}
        />
        <button onClick={ Previous }>Previous</button>
        <button onClick={ Continue }>Next</button>
      </form>
    </div>
  )
}

export default WineFlavour
import React from 'react';
import Circle from './Circle';
import '../CSSFiles/CentralCircle.css';

const CentralCircle = ({ accountValue, amount }) => {
  return (
    <div className="container">
      <div className="glowing-swirl"></div>
      <Circle className="center">
      <div className="value-text">{accountValue}</div>
        <div className="amount-text">{amount}</div>
      </Circle>
    </div>
  )
};

export default CentralCircle;
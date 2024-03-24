import React from 'react';
import Circle from './Circle';
import '../CSSFiles/CentralCircle.css';

const CentralCircle = ({ text }) => {
  return (
    <div className="container">
      <div className="glowing-swirl"></div>
      <Circle className="center">
        <p>{text}</p>
      </Circle>
    </div>
  )
};

export default CentralCircle;
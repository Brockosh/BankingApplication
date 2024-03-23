import React from 'react';
import Circle from './Circle';
import '../CSSFiles/CentralCircle.css';

const CentralCircle = ({ text }) => {
  return (
  <Circle className="center">
    <p>{text}</p>
    </Circle>
  )
};

export default CentralCircle;
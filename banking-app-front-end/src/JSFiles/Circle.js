import React from 'react';
import '../CSSFiles/Circle.css';

const Circle = ({ text, className, style }) => {
  return (
    <div className={`circle ${className}`} style={style}>
      {text}
    </div>
  );
};

export default Circle;
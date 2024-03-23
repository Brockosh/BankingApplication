import React from 'react';
import '../CSSFiles/Circle.css';

const Circle = ({ children, className, style }) => {
  return (
    <div className={`circle ${className}`} style={style}>
      {children}
    </div>
  );
};

export default Circle;
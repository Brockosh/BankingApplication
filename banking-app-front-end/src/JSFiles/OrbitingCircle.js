import React from 'react';
import '../CSSFiles/OrbitingCircle.css';

const OrbitingCircle = ({ text, size = '100px', duration = '10s', delay = '0s', 
isHovering, onMouseEnter, onMouseLeave }) => {
  const style = {
    width: size,
    height: size,
    animationDuration: duration,
    animationDelay: delay,
    animationPlayState: isHovering ? 'paused' : 'running', 
  };

  return (
    <div className="orbiting-circle" style={style} onMouseEnter={onMouseEnter} 
    onMouseLeave={onMouseLeave}>
      <p>{text}</p>
    </div>
  );
};

export default OrbitingCircle;
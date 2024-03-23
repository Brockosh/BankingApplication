import React, { useState } from 'react';
import OrbitingCircle from './OrbitingCircle';
import "../CSSFiles/OrbitingCircleManager.css";

const OrbitingCircleManager = ({ centralContent, orbitingCircles }) => {
  const [isHovering, setIsHovering] = useState(false);

  return (
    <div className="orbiting-circle-manager">
      <div className="central-object">
        {centralContent}
      </div>
      {orbitingCircles.map((circleProps, index) => (
        <OrbitingCircle
          key={index}
          {...circleProps}
          isHovering={isHovering}
          onMouseEnter={() => setIsHovering(true)}
          onMouseLeave={() => setIsHovering(false)}
        />
      ))}
    </div>
  );
};

export default OrbitingCircleManager;
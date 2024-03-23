import React from 'react';
import OrbitingCircleManager from './OrbitingCircleManager';
import CentralCircle from './CentralCircle';

const LandingPage = () => {
    const centralContent = (
        <CentralCircle text="VALUE" />
      );
    
      const orbitingCircles = [
        { text:"View Accounts", size: '140px', duration: '30s', radius: 200 },
        { text:"Future Features",size: '140px', duration: '30s', delay: '-15s', radius: 200 },
      ];
    
      return (
        <div className="App">
          <OrbitingCircleManager centralContent={centralContent} orbitingCircles={orbitingCircles} radius={200} />
        </div>
      );
    };

export default LandingPage;
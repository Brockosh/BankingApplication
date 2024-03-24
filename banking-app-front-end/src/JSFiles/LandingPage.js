import React from 'react';
import OrbitingCircleManager from './OrbitingCircleManager';
import CentralCircle from './CentralCircle';
import AnimatedBackground from './AnimatedBackground';

const LandingPage = () => {
    const centralContent = (
        <CentralCircle text="ACCOUNT VALUE" />
      );
    
      const orbitingCircles = [
        { text:"View Accounts", size: '140px', duration: '30s', radius: 200 },
        { text:"Future Features",size: '140px', duration: '30s', delay: '-15s', radius: 200 },
      ];
    
      return (
        <div className="App">
          <AnimatedBackground/>
          <OrbitingCircleManager centralContent={centralContent} orbitingCircles={orbitingCircles} radius={200} />
        </div>
      );
    };

export default LandingPage;
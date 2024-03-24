import React from 'react';
import OrbitingCircleManager from './OrbitingCircleManager';
import CentralCircle from './CentralCircle';
import AnimatedBackground from './AnimatedBackground';

const LandingPage = () => {
    const centralContent = (
      <CentralCircle accountValue="ACCOUNT VALUE" amount="$1000" />
      );
    
      const orbitingCircles = [
        { text:"View Accounts", size: '140px', duration: '30s', radius: 200 },
        { text:"Future",size: '140px', duration: '30s', delay: '-7.5s', radius: 200 },
        { text:"Crypto",size: '140px', duration: '30s', delay: '-15s', radius: 200 },
        { text:"Finance News",size: '140px', duration: '30s', delay: '-22.5s', radius: 200 }
      ];
    
      return (
        <div className="App">
          <AnimatedBackground/>
          <OrbitingCircleManager centralContent={centralContent} orbitingCircles={orbitingCircles} radius={200} />
        </div>
      );
    };

export default LandingPage;
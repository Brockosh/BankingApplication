import React from 'react';

function Withdraw() {
  const handleWithdraw = () => {
    console.log('Withdraw logic goes here.');
  };

  return (
    <button onClick={handleWithdraw}>Withdraw</button>
  );
}

export default Withdraw;
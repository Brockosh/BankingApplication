import React, { useState } from 'react';
import ActionBoxContainer from './ActionBoxContainer';

function Withdraw() {
  const [amount, setAmount] = useState('');
  const [message, setMessage] = useState('');

  const handleWithdraw = () => {
    console.log('Withdraw logic goes here.');
    // Simulate withdraw action
    setMessage(`Withdrawal of ${amount} was successful!`);
  };

  return (
    <ActionBoxContainer>
      <div className="withdraw-content">
        <h2>Make a Withdrawal</h2>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Amount"
        />
        <button onClick={handleWithdraw}>Withdraw</button>
        {message && <p>{message}</p>}
      </div>
    </ActionBoxContainer>
  );
}

export default Withdraw;
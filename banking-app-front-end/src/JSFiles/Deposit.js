import React, { useState } from 'react';
import ActionBoxContainer from './ActionBoxContainer';

function Deposit() {
  const [amount, setAmount] = useState('');
  const [message, setMessage] = useState('');

  const handleDeposit = () => {
    console.log('Deposit logic goes here.');
    // Simulate deposit action
    setMessage(`Deposit of ${amount} was successful!`);
  };

  return (
    <ActionBoxContainer>
      <div className="deposit-content">
        <h2>Make a Deposit</h2>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Amount"
        />
        <button onClick={handleDeposit}>Deposit</button>
        {message && <p>{message}</p>}
      </div>
    </ActionBoxContainer>
  );
}

export default Deposit;
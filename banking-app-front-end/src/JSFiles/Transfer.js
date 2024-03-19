import React, { useState } from 'react';
import ActionBoxContainer from './ActionBoxContainer';

function Transfer() {
  const [amount, setAmount] = useState('');
  const [recipient, setRecipient] = useState('');
  const [message, setMessage] = useState('');

  const handleTransfer = () => {
    console.log('Transfer logic goes here.');
    // Simulate transfer action
    setMessage(`Transfer of ${amount} to ${recipient} was successful!`);
  };

  return (
    <ActionBoxContainer>
      <div className="transfer-content">
        <h2>Make a Transfer</h2>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Amount"
        />
        <input
          type="text"
          value={recipient}
          onChange={(e) => setRecipient(e.target.value)}
          placeholder="Receiving Account Number"
        />
        <button onClick={handleTransfer}>Transfer</button>
        {message && <p>{message}</p>}
      </div>
    </ActionBoxContainer>
  );
}

export default Transfer;
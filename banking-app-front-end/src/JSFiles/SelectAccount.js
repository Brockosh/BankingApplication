import React, { useState } from 'react';
import "../CSSFiles/SelectAccount.css";

function SelectAccount() {
  const [accountIndex, setAccountIndex] = useState(0);
  const accounts = ['Account 1', 'Account 2', 'Account 3'];

  const nextAccount = () => {
    setAccountIndex((prevIndex) => (prevIndex + 1) % accounts.length);
  };

  const prevAccount = () => {
    setAccountIndex((prevIndex) => (prevIndex - 1 + accounts.length) % accounts.length);
  };

  return (
    <div className="select-account-container">
      <p className="selected-account-text">Selected Account: {accounts[accountIndex]}</p>
      <div className="account-navigation-buttons">
        <button onClick={prevAccount} className="navigation-button">Prev</button>
        <button onClick={nextAccount} className="navigation-button">Next</button>
      </div>
    </div>
  );
}

export default SelectAccount;
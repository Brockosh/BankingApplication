import React from 'react';

function GuestPage() {
  const handleSelectAccount = () => {
    // Logic for selecting an account
  };

  const handleDeposit = () => {
    // Logic for depositing
  };

  const handleWithdraw = () => {
    // Logic for withdrawing
  };

  const handleTransfer = () => {
    // Logic for transferring
  };

  return (
    <div>
      <h1>Welcome, Guest!</h1>
      <button onClick={handleSelectAccount}>Select Account</button>
      <button onClick={handleDeposit}>Deposit</button>
      <button onClick={handleWithdraw}>Withdraw</button>
      <button onClick={handleTransfer}>Transfer</button>
    </div>
  );
}

export function GuestOptions() {
    return <div>Select an option: Account, Deposit, Withdraw, Transfer</div>;
  }
 export default GuestPage;

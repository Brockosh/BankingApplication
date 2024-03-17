import React from 'react';
import '../CSSFiles/AccountOptions.css';

function AccountOptions({ onActionClick }) {
  return (
    <div className="account-options-container">
      <button onClick={() => onActionClick('deposit')}>Deposit</button>
      <button onClick={() => onActionClick('withdraw')}>Withdraw</button>
      <button onClick={() => onActionClick('transfer')}>Transfer</button>
    </div>
  );
}

export default AccountOptions;
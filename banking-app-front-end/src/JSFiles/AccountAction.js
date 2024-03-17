import React from 'react';
import Deposit from '../Deposit'; 
import Withdraw from '../Withdraw'; 
import Transfer from '../Transfer';
import './AccountActions.css'; 

function AccountActions() {
  return (
    <div className="account-actions-container">
      <Deposit />
      <Withdraw />
      <Transfer />
    </div>
  );
}

export default AccountActions;
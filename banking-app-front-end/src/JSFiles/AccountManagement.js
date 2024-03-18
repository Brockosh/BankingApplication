import React from 'react';
import SelectAccount from './SelectAccount'; 
import AccountOptions from './AccountOptions'; 
import '../CSSFiles/AccountManagement.css'; 

function AccountManagement({ onActionClick }) {
  return (
    <div className="account-management-container">
      <SelectAccount />
      <AccountOptions onActionClick={onActionClick} />
    </div>
  );
}

export default AccountManagement;
import React from 'react';
import '../CSSFiles/AccountDetails.css'; 

function AccountDetails() {
  // Example account details
  const accountDetails = {
    name: "Example Name",
    type: "Business",
    balance: "Example Amount",
    number: "123456789",
    routing: "987654321",
    createdAt: "Jan 1, 2020",
  };

  return (
    <div className="account-details-container">
      <h1>Account Details</h1>
      <div className="account-detail"><span>Account Name:</span> <span>{accountDetails.name}</span></div>
      <div className="account-detail"><span>Account Type:</span> <span>{accountDetails.type}</span></div>
      <div className="account-detail"><span>Account Balance:</span> <span>{accountDetails.balance}</span></div>
      <div className="account-detail"><span>Account Number:</span> <span>{accountDetails.number}</span></div>
      <div className="account-detail"><span>Routing Number:</span> <span>{accountDetails.routing}</span></div>
      <div className="account-detail"><span>Created At:</span> <span>{accountDetails.createdAt}</span></div>
    </div>
  );
}

export default AccountDetails;
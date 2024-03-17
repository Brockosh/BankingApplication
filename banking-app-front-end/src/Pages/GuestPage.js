import React, { useState } from 'react';
import '../CSSFiles/GuestPage.css';
import AccountDetails from '../JSFiles/AccountDetails';
import Withdraw from '../JSFiles/Withdraw';
import Deposit from '../JSFiles/Deposit';
import Transfer from '../JSFiles/Transfer';
import AccountOptions from '../JSFiles/AccountOptions';
import ActionBoxContainer from '../JSFiles/ActionBoxContainer';

  function GuestPage() {
    const [actionComponent, setActionComponent] = useState(null);
  
    const handleActionClick = (action) => {
      switch (action) {
        case 'deposit':
          setActionComponent(<Deposit />);
          break;
        case 'withdraw':
          setActionComponent(<Withdraw />);
          break;
        case 'transfer':
          setActionComponent(<Transfer />);
          break;
        default:
          setActionComponent(null);
      }
    };
  
    return (
      <div className="content-and-action-container">
        <AccountOptions onActionClick={handleActionClick} />
        <AccountDetails />
        <div className="action-component">
          {actionComponent && <div className="action-component-container">{actionComponent}</div>}
        </div>
      </div>
    );
  
  }
  
  export default GuestPage;

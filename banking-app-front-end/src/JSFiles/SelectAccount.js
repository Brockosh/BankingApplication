// import React, { useState } from 'react';
// import "../CSSFiles/SelectAccount.css"

// function SelectAccount() {
//   const [selectedAccount, setSelectedAccount] = useState('');

//   const accounts = ['Account 1', 'Account 2', 'Account 3']; 

//   const handleChange = (event) => {
//     setSelectedAccount(event.target.value);
//   };

//   return (
//     <div className="select-account-container">
//       {selectedAccount ? (
//         <div>
//           <p className="selected-account-text">Selected Account: {selectedAccount}</p>
//           <button onClick={() => setSelectedAccount('')} className="change-account-button">Change Account</button>
//         </div>
//       ) : (
//         <select onChange={handleChange} value={selectedAccount} className="select-account-dropdown">
//           <option value="">Select Account</option>
//           {accounts.map((account) => (
//             <option key={account} value={account}>
//               {account}
//             </option>
//           ))}
//         </select>
//       )}
//     </div>
//   );
// }

// export default SelectAccount;

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
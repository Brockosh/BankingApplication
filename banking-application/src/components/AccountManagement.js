import React from 'react';
import { useNavigate } from 'react-router-dom';

function AccountManagement() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Account Management</h1>
      <p>Here you can withdraw, deposit, or transfer money.</p>
      {/* Implement forms or functionality for transactions here */}
      <button onClick={() => navigate('/dashboard')}>Back to Dashboard</button>
    </div>
  );
}

export default AccountManagement;
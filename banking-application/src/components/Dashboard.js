import React from 'react';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Dashboard</h1>
      <p>Welcome, [User]! Here are your accounts:</p>
      {/* Replace [User] with actual user data once you integrate authentication */}
      <button onClick={() => navigate('/account-management')}>Manage Account</button>
    </div>
  );
}

export default Dashboard;
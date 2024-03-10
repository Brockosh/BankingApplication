import React from 'react';
import { useNavigate } from 'react-router-dom';

function LandingPage() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Welcome to the Banking Application</h1>
      <button onClick={() => navigate('/dashboard')}>Log In</button>
    </div>
  );
}

export default LandingPage;
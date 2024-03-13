import React from 'react';
import '../AuthOptionsPage.css';
import { useNavigate } from 'react-router-dom';

function AuthOptionsPage() {

    let navigate = useNavigate();

  const handleLogin = () => {
    window.location.href = 'YOUR_OAUTH2_LOGIN_URL';
  };

  const handleCreateAccount = () => {
    window.location.href = 'YOUR_OAUTH2_SIGNUP_URL';
  };

  const handleGuestAccess = () => {
    navigate('/guest-options');;
  };

  return (
    <div className="auth-options-container">
      <button onClick={handleLogin}>Login with OAuth2</button>
      <button onClick={handleCreateAccount}>Create Account</button>
      <button onClick={handleGuestAccess}>Enter as Guest</button>
    </div>
  );
}



export default AuthOptionsPage;
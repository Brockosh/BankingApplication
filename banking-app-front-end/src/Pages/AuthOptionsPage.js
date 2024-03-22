import React from 'react';
import '../CSSFiles/AuthOptionsPage.css';
import { useNavigate } from 'react-router-dom';
import { useAuth0 } from '@auth0/auth0-react';
import AnimatedBackground from '../JSFiles/AnimatedBackground';
function AuthOptionsPage() {

    let navigate = useNavigate();
    const { loginWithRedirect } = useAuth0(); 

  const handleLogin = () => {
    loginWithRedirect();
  };

  const handleCreateAccount = () => {
    loginWithRedirect();
  };

  const handleGuestAccess = () => {
    navigate('/guest-options');;
  };

  return (
    <div className="auth-options-page-container">
      <AnimatedBackground/>{}
      <div className="page-header">
        <h1> Banking Application </h1>
      </div>
        <div className="auth-options-container">
          <button onClick={handleLogin}>Login with OAuth2</button>
          <button onClick={handleCreateAccount}>Create Account</button>
          <button onClick={handleGuestAccess}>Enter as Guest</button>
        </div>
    </div>
  );
}



export default AuthOptionsPage;
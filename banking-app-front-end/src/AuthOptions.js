import React from 'react';

function AuthOptions() {
  const handleLogin = () => {
    // Redirect the user to the OAuth2 login page
    // The exact URL will depend on your OAuth2 provider and setup
    window.location.href = 'YOUR_OAUTH2_LOGIN_URL';
  };

  const handleCreateAccount = () => {
    // Similarly, redirect to a sign-up page, which could also be handled by your OAuth provider
    // Or it could be a separate component/page within your application
    window.location.href = 'YOUR_OAUTH2_SIGNUP_URL';
  };

  const handleGuestAccess = () => {
    // Optionally handle guest access, which might simply bypass authentication
    // and take the user to a limited version of the application
    console.log('Entering as guest...');
    // Redirect to your app's guest-access page or dashboard
  };

  return (
    <div className="auth-options-container">
      <button onClick={handleLogin}>Login with OAuth2</button>
      <button onClick={handleCreateAccount}>Create Account</button>
      <button onClick={handleGuestAccess}>Enter as Guest</button>
    </div>
  );
}



export default AuthOptions;
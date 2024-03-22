import React from 'react';
import ReactDOM from 'react-dom/client';
// import './index.css';
// import './App.css'
import App from './JSFiles/App';
import reportWebVitals from './reportWebVitals';
import { Auth0Provider } from '@auth0/auth0-react';

const domain = "dev-e6ca820gcqb0jsvf.au.auth0.com";
const clientId = "N5PXMNtRtwj3O2dUzc23ubWbZQ2ZUkwC";
const redirectUri = `http://localhost:3000/guest-options`;

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Auth0Provider
      domain={domain}
      clientId={clientId}
      redirectUri={redirectUri}
    >
    <App />
    </Auth0Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

import '../CSSFiles/index.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AuthOptionsPage from '../Pages/AuthOptionsPage';
import GuestPage from '../Pages/GuestPage';
import OrbitingCircles from './OrbitingCircle';
import LandingPage from './LandingPage';

function App() {
  return (
    <Router>
      <div className="App">
        <div className="content">
          <Routes>
              {/* Route for AuthOptionsPage */}
              <Route path="/" element={<AuthOptionsPage />} />
              
              {/* Route for GuestPage */}
              {/* <Route path="/guest-options" element={<GuestPage />} /> */}
              <Route path="/guest-options" element={<GuestPage />} />
              
              {/* Define other routes for AccountSelection, Deposit, Withdraw, Transfer */}
          </Routes>
        </div> 
      </div>
    </Router>
  );
}

export default App;
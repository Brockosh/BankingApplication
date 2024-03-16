// import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// import AuthOptionsPage from './Pages/AuthOptionsPage';
// import GuestPage  from './Pages/GuestPage';
// import './index.css'
// import './App.css';



// function App() {
//   return (
//     <Router>
//       <div className="App">
//         <div className="content">
//           <h1> Banking Application </h1>
//           <AuthOptionsPage/>
//           <Routes>
//               <Route path="/AuthOptionsPage" element={<AuthOptionsPage />} />
//               <Route path="/guest-options" element={<GuestPage />} />
//               {/* Define other routes for AccountSelection, Deposit, Withdraw, Transfer */}
//           </Routes>
//         </div> 
//       </div>
//     </Router>
//   );
// }

// export default App;

import './index.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AuthOptionsPage from './Pages/AuthOptionsPage';
import GuestPage from './Pages/GuestPage';

function App() {
  return (
    <Router>
      <div className="App">
        <div className="content">
          <Routes>
              {/* Route for AuthOptionsPage */}
              <Route path="/" element={<AuthOptionsPage />} />
              
              {/* Route for GuestPage */}
              <Route path="/guest-options" element={<GuestPage />} />
              
              {/* Define other routes for AccountSelection, Deposit, Withdraw, Transfer */}
          </Routes>
        </div> 
      </div>
    </Router>
  );
}

export default App;
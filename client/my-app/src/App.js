import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Login from './components/login/Login';
import Home from './containers/Home';
import NavBar from './components/NavBar';
import Dashboard from './containers/Dashboard';
import Registration from './components/login/Registration';
import MPContainer from './containers/MPContainer'
import YouHelp from './containers/YouHelp';
import About from './containers/About';

function getSessionStorageOrDefault(key, defaultValue) {
  const stored = sessionStorage.getItem(key);
  if (!stored) {
    return defaultValue;
  }
  return JSON.parse(stored);
}

function App() {
  

  const [currentConstituency, setCurrentConstituency] = useState(
    getSessionStorageOrDefault("currentConstituency", null)
  );

  const [token, setToken] = useState(
    getSessionStorageOrDefault('token', null)
  )

  useEffect(() => {
    sessionStorage.setItem('token', JSON.stringify(token))
    sessionStorage.setItem("currentConstituency", JSON.stringify(currentConstituency))
  }, [token, currentConstituency])

  const onLogin = (token) => {
    setToken(token)
  }

  const onLogOut = () => {
    fetch("http://localhost:8080/api/users/logout",
    {
      method: "POST",
      headers: {
        "content-type": "application/json"
      },
      body: JSON.stringify(token)
    })

    setToken(null)
  }

  return (
    <>
      <BrowserRouter>
      <NavBar token={token} onLogOut={onLogOut} />
        <Routes> 
          {
            !token ?
            <>
              <Route exact path="/" element={<Navigate to="/home" />} /> 
              <Route path="/login" element={<Login onLogin={onLogin} token={token} />} />
              <Route path="/profile" element={<Navigate to="/login" />} />
              <Route path="/registration" element={<Registration />} />
            </>
            :
            <>
              <Route exact path="/" element={<Navigate to="/home" />} />
              <Route path="/login" element={<Navigate to="/" />} />
              <Route path="/profile" element={<Dashboard token={token} setCurrentConstituency={setCurrentConstituency} />} />
              <Route path="/registration" element={<Navigate to="/" />} /> 
            </>
          }
          
          <Route path="/home" element={<Home token={token} currentConstituency={currentConstituency} setCurrentConstituency={setCurrentConstituency} />} />
          <Route path="/about" element={<About /> } /> 
          <Route path="/youhelp" element={<YouHelp token={token} />} /> 
          {/* <Route path={`/constituency/${currentConstituency.constituency_id}`} element={<MPContainer currentConstituency={currentConstituency}/>}/> */}
          <Route path="/constituency/current" element={<MPContainer token={token} currentConstituency={currentConstituency} />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}


export default App;

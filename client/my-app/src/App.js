import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Login from './components/Login';
import Home from './components/Home';
import NavBar from './components/NavBar';
import Dashboard from './components/Dashboard';
import Registration from './components/Registration';
import MPContainer from './containers/MPContainer'
import YouHelp from './components/YouHelp';
import About from './components/About';

function getSessionStorageOrDefault(key, defaultValue) {
  const stored = sessionStorage.getItem(key);
  if (!stored) {
    return defaultValue;
  }
  return JSON.parse(stored);
}

function App() {

  const [currentConstituency, setCurrentConstituency] = useState({});

  const [token, setToken] = useState(
    getSessionStorageOrDefault('token', null)
  )

  useEffect(() => {
    sessionStorage.setItem('token', JSON.stringify(token))
  }, [token])

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
              <Route path="/login" element={<Login onLogin={onLogin} token={token}/>} />
              <Route path="/dashboard" element={<Navigate to="/login" />} />
              <Route path="/registration" element={<Registration />} />
              <Route path="/youhelp" element={<YouHelp token={token}/>}/> 
            </>
            :
            <>
              <Route exact path="/" element={<Navigate to="/home" />} />
              <Route path="/login" element={<Navigate to="/" />} />
              <Route path="/profile" element={<Dashboard token={token} />} />
              <Route path="/registration" element={<Navigate to="/" />} /> 
              <Route path="/youhelp" element={<YouHelp token={token}/>}/> 
            </>
          }
          
          <Route path="/home" element={<Home token={token} currentConstituency={currentConstituency} setCurrentConstituency={setCurrentConstituency}/>} />
          <Route path="/about" element={<About /> } /> 
          {/* <Route path={`/constituency/${currentConstituency.constituency_id}`} element={<MPContainer currentConstituency={currentConstituency}/>}/> */}
          <Route path={`/constituency/current`} element={<MPContainer token={token} currentConstituency={currentConstituency}/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}


export default App;

import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Login from './components/login/Login';
import Home from './components/Home';
import NavBar from './components/NavBar';
import Dashboard from './components/Dashboard';
import Registration from './components/login/Registration';
import MPContainer from './containers/MPContainer'

function getSessionStorageOrDefault(key, defaultValue) {
  const stored = sessionStorage.getItem(key);
  if (!stored) {
    return defaultValue;
  }
  return JSON.parse(stored);
}

function App() {
  const [user, setUser] = useState([])

  const [currentConstituency, setCurrentConstituency] = useState({constituency_id: 3345,
  constituency_name: "Bolton"});

  const [token, setToken] = useState(
    getSessionStorageOrDefault('token', null)
  )

  useEffect(() => {
    sessionStorage.setItem('token', JSON.stringify(token))
  }, [token])

  const onLogin = (token) => {
    setToken(token)
    console.log(user)
  }

  const onLogOut = () => {
    console.log(token)
    
    fetch(`http://localhost:8080/api/users/token`,
    {
      method: 'PATCH',
      headers: {
          "content-type": "text/plain;charset=UTF-8"
      },
      body: `${token}`
        
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
              <Route path="/login" element={<Login onLogin={onLogin} token={token} user={user} setUser={setUser}/>} />
              <Route path="/dashboard" element={<Navigate to="/login" />} />
              <Route path="/registration" element={<Registration />} />
            </>
            :
            <>
              <Route exact path="/" element={<Navigate to="/home" />} />
              <Route path="/login" element={<Navigate to="/" />} />
              <Route path="/profile" element={<Dashboard token={token} />} />
              <Route path="/registration" element={<Navigate to="/" />} /> 
            </>
          }
          
          <Route path="/home" element={<Home token={token} currentConstituency={currentConstituency} setCurrentConstituency={setCurrentConstituency}/>} /> 
          <Route path={`/constituency/${currentConstituency.constituency_name}`} element={<MPContainer currentConstituency={currentConstituency} user={user} token={token}/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;

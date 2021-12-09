import './App.css';
<<<<<<< HEAD
import MyMap from "./components/MyMap.js"
=======
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Login from './components/Login';
import Home from './components/Home';
import NavBar from './components/NavBar';
import Dashboard from './components/Dashboard';
import Registration from './components/Registration';

function getSessionStorageOrDefault(key, defaultValue) {
  const stored = sessionStorage.getItem(key);
  if (!stored) {
    return defaultValue;
  }
  return JSON.parse(stored);
}
>>>>>>> 78b51760453dde73122fed1c99b323c32d499c25

function App() {

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
<<<<<<< HEAD
    <div className="App">
      <MyMap/>
    </div>
=======
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
            </>
            :
            <>
              <Route exact path="/" element={<Navigate to="/home" />} />
              <Route path="/login" element={<Navigate to="/" />} />
              <Route path="/dashboard" element={<Dashboard token={token} />} />
              <Route path="/registration" element={<Navigate to="/" />} /> 
            </>
          }
          
          <Route path="/home" element={<Home token={token}/>} /> 
        </Routes>
      </BrowserRouter>
    </>
>>>>>>> 78b51760453dde73122fed1c99b323c32d499c25
  );
}

export default App;

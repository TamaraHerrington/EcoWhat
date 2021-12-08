import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Login from './components/Login';
import Home from './components/Home';

function getSessionStorageOrDefault(key, defaultValue) {
  const stored = sessionStorage.getItem(key);
  if (!stored) {
    return defaultValue;
  }
  return JSON.parse(stored);
}

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

  return (
    <BrowserRouter>
      <Routes>
        {
          !token ?
          <>
            <Route path="/" element={<Navigate to="/login" />} /> 
            <Route path="/login" element={<Login onLogin={onLogin} token={token}/>} />
          </>
          :
          <>
            <Route path="/" element={<Navigate to="/home" />} />
            <Route path="/login" element={<Navigate to="/" />} />  
          </>
        }

        {/* <Route path="/" element={<Navigate to="/login" />} />  */}
        {/* <Route path="/login" element={<Login onLogin={onLogin} token={token}/>} /> */}
        
        <Route path="/home" element={<Home token={token}/>} /> 
      </Routes>
    </BrowserRouter>
  );
}

export default App;

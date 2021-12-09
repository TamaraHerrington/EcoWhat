import React from 'react';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Registration from './Registration';

const Login = ({ onLogin, token }) => {

    const navigate = useNavigate()
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    useEffect(() => {
        setEmail("")
        setPassword("")
    }, [])

    const handleEmailChange = (event) => {
        setEmail(event.target.value)
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        fetch(`http://localhost:8080/api/users/token?email=${email}&&password=${password}`,
            {
                method: 'POST',
                headers: {
                    "content-type": "text/plain;charset=UTF-8"
                }   
            }
        )
        .then(response => response.text())
        .then(data => onLogin(data))
        
        if (token) {
            navigate("/home")
        }
        
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label forHtml="user-email">Email</label>
                <input type="text" id="user-email" onChange={handleEmailChange}/>

                <label forHtml="user-password">Password</label>
                <input type="password" id="user-password" onChange={handlePasswordChange}/>

                <input type="submit" />
            </form>

            <br />

            <Registration />
        </>
    )
}

export default Login

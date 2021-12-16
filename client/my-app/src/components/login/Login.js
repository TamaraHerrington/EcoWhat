import React from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

const Login = ({ onLogin }) => {

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const handleEmailChange = (event) => {
        setEmail(event.target.value)
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        fetch(`http://localhost:8080/api/users/login?email=${email}&&password=${password}`,
            {
                method: 'POST'   
            }
        )
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => {throw new Error(err.message)})
            }
            return response.json()    
        })
        .then(data => onLogin(data))
        .catch(err => {
            switch (err.message) {
                case "Invalid email address":
                    alert("Please enter a valid email address");
                    break;
                default:
                    alert(err.message)   
            }
        })
    }

    return (
        <section className="login">
            <main className="login-main">
                <form className="login-form" onSubmit={handleSubmit}>
                    <h1 className="login-header">Login</h1>

                    <label forhtml="user-email">Email</label>
                    <input type="text" id="user-email" value={email} required onChange={handleEmailChange}/>

                    <label forhtml="user-password">Password</label>
                    <input type="password" id="user-password" value={password} required onChange={handlePasswordChange}/>

                    <input className="login-btn" type="submit" value="Login"/>
                    
                    <div className="login-register">
                        <p>Need an account?</p>
                        <Link to="/registration">Sign Up</Link>
                    </div>
                </form>
            </main>  
        </section>
    )
}

export default Login

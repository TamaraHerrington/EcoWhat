import React from 'react'
import { useState, useEffect } from 'react'

const Login = () => {

    const [users, setUsers] = useState([])

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    useEffect(() => {
        setEmail("")
        setPassword("")

        fetch("http://localhost:8080")
        .then(response => response.json())
        .then(data => setUsers(data))
    }, [])

    const handleEmailChange = (event) => {
        setEmail(event.target.value)
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        

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
        </>
    )
}

export default Login

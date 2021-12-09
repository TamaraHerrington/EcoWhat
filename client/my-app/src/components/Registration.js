import { useState } from "react";

const Registration = () => {

    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const handleFirstNameChange = (event) => {
        setFirstName(event.target.value)
    }

    const handleLastNameChange = (event) => {
        setLastName(event.target.value)
    }

    const handleEmailChange = (event) => {
        setEmail(event.target.value)
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        const newUser = {
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "password": password
        }

        fetch("http://localhost:8080/api/users",
            {
                method: 'POST',
                headers: {
                    "content-type": "application/json"
                },
                body: JSON.stringify(newUser) 
            }
        )

        setFirstName("")
        setLastName("")
        setEmail("")
        setPassword("")
    }


    return (
        <form onSubmit={handleSubmit}>
            <label forHtml="first-name">First Name</label>
            <input type="text" id="first-name" value={firstName} required onChange={handleFirstNameChange}/>

            <label forHtml="last-name">Last Name</label>
            <input type="text" id="last-name" value={lastName} required onChange={handleLastNameChange}/>

            <label forHtml="email">Email</label>
            <input type="text" id="email" value={email} required onChange={handleEmailChange}/>

            <label forHtml="password">Password</label>
            <input type="password" id="password" value={password} required onChange={handlePasswordChange}/>

            <input type="submit" />
        </form>
    )
}

export default Registration;
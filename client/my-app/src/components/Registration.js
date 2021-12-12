import { useState } from "react";
import { useNavigate } from "react-router";

const Registration = () => {

    const navigate = useNavigate()

    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [error, setError] = useState(null)

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
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => {throw new Error(err.message)})
            } else {
                navigate("/login")
            }
        })
        .catch(err => setError(err))

    }


    return (
        <section className="signup">
            <main className="signup-main">
                <form className="signup-form" onSubmit={handleSubmit}>
                    <h1 className="signup-header">Register</h1>

                    <label forHtml="first-name">First Name</label>
                    <input type="text" id="first-name" value={firstName} required onChange={handleFirstNameChange}/>

                    <label forHtml="last-name">Last Name</label>
                    <input type="text" id="last-name" value={lastName} required onChange={handleLastNameChange}/>

                    <label forHtml="email">Email</label>
                    <input type="text" id="email" value={email} required onChange={handleEmailChange}/>

                    <label forHtml="password">Password</label>
                    <input type="password" id="password" value={password} required onChange={handlePasswordChange}/>

                    <input className="signup-btn" type="submit" value="Sign Up"/>

                    {(() => {
                        if (error) {
                            return (
                                <p>{error.message}</p>
                            )
                        }
                    })()}
                </form>
            </main>
        </section>
    )
}

export default Registration;
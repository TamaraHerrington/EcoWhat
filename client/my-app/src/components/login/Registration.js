import { useState } from "react";
import { useNavigate } from "react-router";

const Registration = () => {

    const navigate = useNavigate()

    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")
    const [postcode, setPostcode] = useState("")

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

    const handleConfirmPasswordChange = (event) => {
        setConfirmPassword(event.target.value)
    }

    const handlePostcodeChange = (event) => {
        setPostcode(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (password !== confirmPassword) {
            alert("Passwords do not match")
        } else {

            const newUser = {
                "firstName": firstName,
                "lastName": lastName,
                "email": email,
                "password": password,
                "postcode": postcode
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
            .catch(err => {
                switch (err.message) {
                    case "Invalid email address":
                        alert("Please enter a valid email address");
                        break;
                    case "Invalid password":
                        alert("Please enter a valid password");
                        break;
                    default:
                        alert(err.message);
                }
            })
        }
    }


    return (
        <section className="signup">
            <main className="signup-main">
                <form className="signup-form" onSubmit={handleSubmit}>
                    <h1 className="signup-header">Register</h1>

                    <label forhtml="first-name">First Name</label>
                    <input type="text" id="first-name" value={firstName} required onChange={handleFirstNameChange}/>

                    <label forhtml="last-name">Last Name</label>
                    <input type="text" id="last-name" value={lastName} required onChange={handleLastNameChange}/>

                    <label forhtml="email">Email</label>
                    <input type="text" id="email" value={email} required onChange={handleEmailChange}/>

                    <label forhtml="password">Password</label>
                    <input type="password" id="password" value={password} required onChange={handlePasswordChange}/>

                    <label forhtml="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" value={confirmPassword} required onChange={handleConfirmPasswordChange}/>

                    <p>Password must have:</p>
                    <ul>
                        <li>At least 8 characters</li>
                        <li>At least one lowercase letter</li>
                        <li>At least one uppercase letter</li>
                        <li>At least one number</li>
                        <li>At least one special character (@#$%^&+=)</li>
                        <li>No spaces</li>
                    </ul>
                    <br></br>

                    <label forhtml="postcode">Postcode</label>
                    <input type="text" id="postcode" value={postcode} required onChange={handlePostcodeChange}/>

                    <input className="signup-btn" type="submit" value="Sign Up"/>
                </form>
            </main>
        </section>
    )
}

export default Registration;
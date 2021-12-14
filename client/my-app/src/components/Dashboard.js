import { useState, useEffect } from "react"

const Dashboard = ({ token }) => {

    const [user, setUser] = useState([])

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/${token.userId}`,
        {
            method: 'POST',
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(token)
        })
        .then(response => response.json())
        .then(data => setUser(data))
    }, [token])

    return (
        <>
            {!user ?
            <p>Loading...</p>
            :
            <h1 className="profile-header">Welcome {user.firstName}</h1>
            }
        </>
    )
}

export default Dashboard
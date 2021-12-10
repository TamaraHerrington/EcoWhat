import { useState, useEffect } from "react"

const Dashboard = ({ token }) => {

    const [user, setUser] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/api/users/user",
        {
            method: 'POST',
            headers: {
                "content-type": "text/plain;charset=UTF-8"
            },
            body: `${token}`
        })
        .then(response => response.json())
        .then(data => setUser(data))
    }, [])

    return (
        <>
            <h1 className="profile-header">Welcome {user.firstName}</h1>
        </>
    )
}

export default Dashboard
import { useNavigate } from "react-router"
import { Link } from "react-router-dom"

const NavBar = ({ token, onLogOut }) => {

    const navigate = useNavigate()

    const handleLogOut = (event) => {
        event.preventDefault()

        navigate("/home")
        onLogOut()   
    }

    return (
        <>
            {token ? 
                <p>Welcome {token}</p>
                :
                <p>Not logged in</p>
            }

            <Link to="/home">Home</Link>

            {(() => {
                if (token) {
                    return(
                    <Link to="/dashboard">Dashboard</Link>
                    )
                }
            })()}


            {token ?
                <Link to="/home" onClick={handleLogOut}>Log Out</Link>
                :
                <Link to="/login">Log In</Link>
            }
        </>
    )
}

export default NavBar
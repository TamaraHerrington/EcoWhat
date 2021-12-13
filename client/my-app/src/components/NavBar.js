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
        <nav>
            <span className="navbar-logo">🌎</span>
            <ul className="navbar-links">
                <li><Link className="navbar-link" to="/home">Home</Link></li>
                <li><Link className="navbar-link" to="/home">About</Link></li>
                <li><Link className="navbar-link" to="/youhelp">What You Can Do</Link></li>
                {token ?
                    <>
                        <li><Link className="navbar-link" to="/profile">Profile</Link></li>
                        <li><Link className="navbar-link" to="/home" onClick={handleLogOut}>Log Out</Link></li>
                    </>
                    :
                    <>
                        <li><Link className="navbar-link" to="/login">Log In</Link></li>
                        <li><Link className="navbar-link" to="/registration">Sign Up</Link></li>
                    </>
                }
            </ul>
        </nav>
    )
}

export default NavBar
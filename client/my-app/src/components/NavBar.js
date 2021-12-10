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
            <ul>
                <li><Link to="/home">Home</Link></li>
                <li><Link to="/home">About</Link></li>
                <li><Link to="/home">What You Can Do</Link></li>
                {token ?
                    <>
                        <li><Link to="/dashboard">Dashboard</Link></li>
                        <li><Link to="/home" onClick={handleLogOut}>Log Out</Link></li>
                    </>
                    :
                    <li><Link to="/login">Log In</Link></li>
                }
            </ul>
        </nav>
    )
}

export default NavBar
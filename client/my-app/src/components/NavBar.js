import { useState } from "react"
import { Link } from "react-router-dom"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBars } from '@fortawesome/free-solid-svg-icons'


const NavBar = ({ token, onLogOut }) => {

    const [click, setClick] = useState(false);
    
    const handleClick = () => {
        setClick(!click);
    }

    const closeMenu = () => {
        setClick(false);
    }

    const handleLogOut = (event) => {
        event.preventDefault()

        onLogOut()   
    }

    return (
        <>
        <nav>
            <span className="navbar-logo">🌎 EcoWhat?</span>
            <ul className="navbar-links">
                <li><Link className="navbar-link" to="/home">Home</Link></li>
                <li><Link className="navbar-link" to="/about">About</Link></li>
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
                <li onClick={handleClick}>
                <FontAwesomeIcon  className="icon" icon={faBars} />
                </li>
            </ul>
        </nav>
            <ul className={click ? "mobile-nav active" : "mobile-nav"}>
                <li onClick={closeMenu}><Link className="navbar-link" to="/home">Home</Link></li>
                <li onClick={closeMenu}><Link className="navbar-link" to="/about">About</Link></li>
                <li onClick={closeMenu}><Link className="navbar-link" to="/youhelp">What You Can Do</Link></li>
                {token ?
                    <>
                        <li onClick={closeMenu}><Link className="navbar-link" to="/profile">Profile</Link></li>
                        <li onClick={closeMenu}><Link className="navbar-link" to="/" onClick={handleLogOut}>Log Out</Link></li>
                    </>
                    :
                    <>
                        <li onClick={closeMenu}><Link className="navbar-link" to="/login">Log In</Link></li>
                        <li onClick={closeMenu}><Link className="navbar-link" to="/registration">Sign Up</Link></li>
                    </>
                }
            </ul>
        </>
    )
}

export default NavBar
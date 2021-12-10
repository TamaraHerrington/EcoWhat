import { useNavigate } from "react-router"

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

            <a href="/home">Home</a>
            <a href="/YouHelp">What Can I Do?</a>

            {token ?
                <a href="/home" onClick={handleLogOut}>Log Out</a>
                :
                <a href="/login">Log In</a>
            }
            
            {/* <a href="#Footer">Contact Us</a> */}
        </>
    )
}

export default NavBar
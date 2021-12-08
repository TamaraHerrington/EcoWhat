
const Home = ({ token }) => {


    return (
        <>
            {token ? 
            <p>Welcome {token}</p>
            :
            <p>Not logged in</p>
            }
            
        </>
    )
}

export default Home
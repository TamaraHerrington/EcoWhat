const Vote = ({vote}) => {
    return (
        <div className="vote">
            <h2>Bill: {vote[0]}</h2>
            <h2>Voted: {vote[1]==true? "For": "Against"}</h2>
        </div>
    )
}

export default Vote;
const Vote = ({vote}) => {
    return (
        <div className="vote">
            <p>Bill: {JSON.stringify(vote)}</p>
        </div>
    )
}

export default Vote;
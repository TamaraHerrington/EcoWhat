const Vote = ({vote}) => {
    return (
        <div className="vote">
            <h2>Bill: {JSON.stringify(vote.PublishedDivision.Title)}</h2>
        </div>
    )
}

export default Vote;
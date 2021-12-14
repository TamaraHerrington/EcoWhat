const VotesList = ({mpVotes}) => {

    const voteComponentsList = mpVotes.map(votes=> {
        return (
            <>
            <h2>Bill: {votes.title}</h2>
            <h2>Voted: {votes.vote===true? "For": "Against"}</h2>
            </>
        )
    })

    return (
        <div className="votes-list-container">
            {voteComponentsList}
        </div>
    )

}

export default VotesList;
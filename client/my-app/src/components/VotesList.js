const VotesList = ({mpVotes}) => {

    const voteComponentsList = mpVotes.map(votes=> {
        return (
            <ul>
            <li><h3>Bill: {votes.title}</h3></li>
            <h3>Voted: {votes.vote===true? "For": "Against"}</h3>
            </ul>
        )
    })

    return (
        <div className="votes-list-container">
            {voteComponentsList}
        </div>
    )

}

export default VotesList;
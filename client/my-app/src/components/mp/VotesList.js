import Vote from "./Vote"

const VotesList = ({ mpVotes }) => {

    const voteComponentsList = mpVotes.map((vote, index) => {
        return (
            <Vote key={index} vote={vote} />
        )
    })

    return (
        <tbody className="votes-list-container">
            {voteComponentsList}
        </tbody>
    )

}

export default VotesList;
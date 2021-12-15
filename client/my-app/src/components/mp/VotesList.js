const VotesList = ({mpVotes}) => {

    const voteComponentsList = mpVotes.map(votes=> {
        return (
            <>
                    <tr>
                        <td>
                            <h2>{votes.title}</h2>
                        </td>

                        <td>
                            <h2>{votes.vote===true? "✅": "❌"}</h2>
                        </td>
                    </tr>
            </>
        )
    })

    return (
        <tbody className="votes-list-container">
            {voteComponentsList}
        </tbody>
    )

}

export default VotesList;
const Vote = ({ vote }) => {

    return (
        <>
            <tr>
                <td>
                    <h2>{vote.title}</h2>
                </td>

                <td>
                    <h2>{vote.vote===true? "✅": "❌"}</h2>
                </td>
            </tr>
        </>
    )
}

export default Vote;
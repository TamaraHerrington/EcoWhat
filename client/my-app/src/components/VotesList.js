import Vote from './Vote';
import {useState} from 'react';

const VotesList = ({mpVotes}) => {

    console.log(mpVotes[0].title);

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
            {/* <h2>Bill: {mpVotes[0].Title}</h2>
            <h2>Voted: {mpVotes[0].Vote==true? "For": "Against"}</h2> */}
        </div>
    )

}

export default VotesList;
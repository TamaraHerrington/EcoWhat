import Vote from './Vote';
import {useState} from 'react';

const VotesList = ({mpVotes}) => {


    const voteComponentsList = mpVotes.map(vote=> {
        return (
            <Vote vote={vote}/>
        )
    })
    return (
        <div className="votes-list-container">
            {voteComponentsList}
        </div>
    )

}

export default VotesList;
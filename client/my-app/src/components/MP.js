import {useState, useEffect} from 'react';
import VotesList from './VotesList';
import './MP.css'

const MP = ({mpData, mpVotes, email, twitter}) => {
    // id is mpData.items.value.id
    
    // placeholder text, maybe have a few options based on specific topics
    const tweetText = "Please care more about the environment!";
    const emailText = `Dear ${mpData[0].value.nameDisplayAs}, I am a constituent concerned about the environment, please help, From ${"userName"}`;
    
    return (
        
        <section key={mpData} className="mp-container">
            <main>
            <h1 className="mp-name">{mpData[0].value.nameDisplayAs}</h1>
            <img className="mp-img" src={mpData[0].value.thumbnailUrl}/>
            <h2>Party: {mpData[0].value.latestParty.name}</h2>
            <h2>Constituency: {mpData[0].value.latestHouseMembership.membershipFrom}</h2>
            
            <VotesList mpVotes={mpVotes}/>

            <div className='mp-contact'>
                    <a className="mp-twitter" href={`https://twitter.com/intent/tweet?text=@${twitter}%20${tweetText}`}>
                    Tweet
                    </a>
                
                <a className="mp-mail" href={`mailto:${email}?subject=${"The Environment"}&body=${emailText}`}>
                    E-mail
                    </a>
            </div>
            </main>

        </section>
        
    )

}

export default MP;
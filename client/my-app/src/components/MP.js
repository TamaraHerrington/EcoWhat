import {useState, useEffect} from 'react';
import VotesList from './VotesList';
import './MP.css'

const MP = ({mpData, mpVotes}) => {
    // id is mpData.items.value.id
    console.log("NAME: " + mpData.items[0].value.NameDisplayAs);
    // placeholder text, maybe have a few options based on specific topics
    const tweetText = "Please care more about the environment!";
    const emailText = `Dear ${"mpName"}, I am a constituent concerned about the environment, please help, From ${"userName"}`;
    
    return (
        
        <div key={mpData} className="mp-container">
            <h2>{JSON.stringify(mpData)}</h2>
            <VotesList mpVotes={mpVotes}/>

            <div className='contact'>
                <button className='contact-button'><a class="twitter-share-button"
                // put in mp twitter account in @
                    href={`https://twitter.com/intent/tweet?text=@${"twitter"}%20${tweetText}`}>
                    Tweet
                </a>
                </button>
                <br></br>
                <button className='contact-button'>
                <a href={`mailto:${"mpemail@mp.com"}?subject=${"The Environment"}&body=${emailText}`}>E-mail</a>
                </button>
            </div>

        </div>
        
    )

}

export default MP;
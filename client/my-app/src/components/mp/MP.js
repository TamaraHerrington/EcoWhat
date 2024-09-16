import VotesList from './VotesList';
import './MP.css'
import "../../App.css"

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { faEnvelope } from '@fortawesome/free-solid-svg-icons'
import {useState} from "react";

const MP = ({ mpData, mpVotes, user, token }) => {

    const tweetText = "Please care more about the environment!";
    const emailText = `Dear ${mpData.name}, I am a constituent concerned about the environment, please help, From ${user==null?"your costituent": user.firstName + " " + user.latName}`;
    const [open, setOpen] = useState(false);

    return (
        <section className="mp-section">

            <section className="mp-info">
                <header className="mp-info__header">
                    <h1 className="mp-name">{mpData.name}</h1>
                    <span className="mp__img-container">
                    <img className="mp__img" src={mpData.photoLink} alt="MP"/>
                    </span>
                    <h2>Party: {mpData.party}</h2>
                    <h2>Constituency: {mpData.constituencyName}</h2>
                    {token?
                    <>
                    <p>{mpData.twitter===""? "This MP doesn't have a Twitter on record. Click to tweet Downing Street instead!": ""}</p>
                    <footer className="mp-contact--icon">
                        <a className="mp-twitter--icon" href={mpData.twitter===""? `https://twitter.com/intent/tweet?text=@10DowningStreet%20${tweetText}`
                            :`https://twitter.com/intent/tweet?text=@${mpData.twitter.split('twitter.com/').at(-1)}%20${tweetText}`}>
                            <FontAwesomeIcon icon={faTwitter} />
                        </a>
                            
                        <a className="mp-mail--icon" href={`mailto:${mpData.emailAddress}?subject=${"The Environment"}&body=${emailText}`}>
                            <FontAwesomeIcon icon={faEnvelope} />
                        </a>
                    </footer>
                    </>
                    :
                    <h3>Sign in to contact this MP</h3>
}
                </header>
            </section>

            <section className="mp-votes">
                <main>
                    <div>
                        <h2>Environmental Voting History</h2>
                        <table>
                            <thead className="mp-vote-table">
                                <tr className="mp-vote-titles">
                                    <th>Bill</th>
                                    <th>Vote</th>
                                </tr>
                            </thead>
                            {open ? 
                            <>
                                <VotesList mpVotes={mpVotes}/>
                                <button type='button' className="collapse-votes-btn"onClick={() => setOpen(!open)}>See less votes ⬆</button>
                            </>
                            :
                                mpVotes.length > 0 ?
                                    mpVotes.length >= 5 ?
                                        <>
                                            <VotesList mpVotes={mpVotes.slice(0, 5)}/>
                                            <button type='button' className="collapse-votes-btn"onClick={() => setOpen(!open)}>See more votes ⬇</button>
                                        </>
                                    :
                                        <VotesList mpVotes={mpVotes.slice(0, mpVotes.length)}/>
                                :
                                <></>

                            }
                        </table>
                        
                    </div>
                </main>
            </section>

        </section>
    )
}

export default MP;
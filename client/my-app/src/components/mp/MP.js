import VotesList from './VotesList';
import './MP.css'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { faEnvelope } from '@fortawesome/free-solid-svg-icons'
import {useState} from "react";

const MP = ({ mpData, mpVotes, email, twitter, user }) => {

    const tweetText = "Please care more about the environment!";
    const emailText = `Dear ${mpData[0].value.nameDisplayAs}, I am a constituent concerned about the environment, please help, From ${user==null?"your costituent": user.firstName + " " + user.latName}`;
    const [open, setOpen] = useState(false);

    return (
        <section className="mp-section">

            <section className="mp-info">
                <header className="mp-info__header">
                    <h1 className="mp-name">{mpData[0].value.nameDisplayAs}</h1>
                    <span className="mp__img-container">
                    <img className="mp__img" src={mpData[0].value.thumbnailUrl} alt="MP"/>
                    </span>
                    <h2>Party: {mpData[0].value.latestParty.name}</h2>
                    <h2>Constituency: {mpData[0].value.latestHouseMembership.membershipFrom}</h2>

                    <p>{twitter==null? "This MP doesn't have a Twitter on record. Click to tweet Downing Street instead!": ""}</p>
                    <footer className="mp-contact--icon">
                        <a className="mp-twitter--icon" href={twitter===null? `https://twitter.com/intent/tweet?text=@10DowningStreet%20${tweetText}`
                            :`https://twitter.com/intent/tweet?text=@${twitter}%20${tweetText}`}>
                            <FontAwesomeIcon icon={faTwitter} />
                        </a>
                            
                        <a className="mp-mail--icon" href={`mailto:${email}?subject=${"The Environment"}&body=${emailText}`}>
                            <FontAwesomeIcon icon={faEnvelope} />
                        </a>
                    </footer>
                </header>
            </section>

            <section className="mp-votes">
                <main>
                    <div>
                        <h2>Voting History</h2>
                        <button type='button' onClick={() => setOpen(!open)}>Expand MP votes on environmental bills</button>
                        <table>
                            <thead className="mp-vote-table">
                                <tr className="mp-vote-titles">
                                    <th>Bill</th>
                                    <th>Vote</th>
                                </tr>
                            </thead>
                            {open ? 
                            <VotesList mpVotes={mpVotes}/>
                            :
                                mpVotes.length > 0 ?
                                    mpVotes.length >= 5 ?
                                        <VotesList mpVotes={mpVotes.slice(0, 5)}/>
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
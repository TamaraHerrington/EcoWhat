import MP from '../components/mp/MP'
import { useState, useEffect } from 'react';
import EnvironmentalData from '../components/EnvironmentalData';
import CommentsList from "../components/comments/CommentsList"
import CommentForm from '../components/comments/CommentForm';

const MPContainer = ({ currentConstituency, token }) => {
    const [mpData, setMpData] = useState("");
    const [mpTwitter, setMpTwitter] = useState("");
    const [mpEmail, setMpEmail] = useState("");
    const [mpVotesCarbon, setMpVotesCarbon] = useState([]);
    const [mpVotesClimate, setMpVotesClimate] = useState([]);
    const [mpVotesEvironment, setMpVotesEnvironment] = useState([]);
    const [comments, setComments] = useState([]);
    const [mpVotesEnergy, setMpVotesEnergy] = useState([])
    const [envData, setEnvData] = useState([])

    const getCountyData = () => {
        fetch(`http://localhost:8080/api/constituencies/${currentConstituency.constituency_id}/county`)
        .then(response => response.text())
        .then(data => {
        fetch(`http://localhost:8080/api/environment/${data}`)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            setEnvData(data)})})
    }

    const getMpData = () => {
        fetch("https://members-api.parliament.uk/api/Members/Search?ConstituencyId=" + currentConstituency.constituency_id + "&IsCurrentMember=true")
        .then(result => result.json())
        .then(data => setMpData(data.items))
    }

    const getMpEmail = () => {
        fetch("https://members-api.parliament.uk/api/Location/Constituency/" + currentConstituency.constituency_id)
        .then(response => response.json())
        .then(data => data.value.currentRepresentation.member.value.id)
        .then(data => fetch(`https://members-api.parliament.uk/api/Members/${data}/Contact`)
        .then(response => response.json())
        .then(data => data.value)
        .then(data => data[0].email)
        )
        .then(data => setMpEmail(data))
    }

    const getMpTwitter = () => {
        fetch("https://members-api.parliament.uk/api/Location/Constituency/" + currentConstituency.constituency_id)
        .then(response => response.json())
        .then(data => data.value.currentRepresentation.member.value.id)
        .then(data => fetch(`https://members-api.parliament.uk/api/Members/${data}/Contact`)
        .then(response => response.json())
        .then(data => data.value)
        .then(data => data.filter(d => d.type==="Twitter").length===0 ? null : data.filter(d => d.type==="Twitter")[0].line1.split('twitter.com/').at(-1))
        )
        .then(data => setMpTwitter(data))
    }

    

    const getMpVotesCarbon = () => {
        fetch("https://members-api.parliament.uk/api/Location/Constituency/" + currentConstituency.constituency_id)
        .then(response => response.json())
        .then(data => data.value.currentRepresentation.member.value.id)    
        .then(data =>  
        fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=
        ${data}
        &queryParameters.searchTerm=carbon`)
        .then(result => result.json())
        .then(data =>data.map(item => { 
            return {"title": item.PublishedDivision.Title, "vote": item.MemberVotedAye}
            }
        ))
        .then(data => setMpVotesCarbon(data))
        )
    }

    const getMpVotesClimate = () => {
        fetch("https://members-api.parliament.uk/api/Location/Constituency/" + currentConstituency.constituency_id)
        .then(response => response.json())
        .then(data => data.value.currentRepresentation.member.value.id)    
        .then(data =>  
        fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=
        ${data}
        &queryParameters.searchTerm=climate`)
        .then(result => result.json())
        .then(data =>data.map(item => { 
            return {"title": item.PublishedDivision.Title, "vote": item.MemberVotedAye}
            }
        ))
        .then(data => setMpVotesClimate(data))
        )
    }

    const getMpVotesEnvironment = () => {
        fetch("https://members-api.parliament.uk/api/Location/Constituency/" + currentConstituency.constituency_id)
        .then(response => response.json())
        .then(data => data.value.currentRepresentation.member.value.id)    
        .then(data =>  
        fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=
        ${data}
        &queryParameters.searchTerm=environment`)
        .then(result => result.json())
        .then(data =>data.map(item => { 
            return {"title": item.PublishedDivision.Title, "vote": item.MemberVotedAye}
            }
        ))
        .then(data => setMpVotesEnvironment(data))
        )
    }


    const getComments = () => {
        fetch(
            `http://localhost:8080/api/comments/constituency/${currentConstituency.constituency_id}`
        )
        .then(response => {
                if(!response.ok){
                    return response.json().then(err => {throw new Error(err.message)})
                }
                return response.json()})
        .then(data => setComments(data))
        .catch(err => {
                const errorList = [{comment_title: "No comments available. Please try reloading"}]
                setComments(errorList);
            })
    }

    const upvoteComment = (token, comment_id) => {

       if(token){
            const requestBody = JSON.stringify({
                "user_id": token.userId,
                "comment_id": comment_id,
                "upvoted": 1,
                "downvoted": 0
            })

            fetch("http://localhost:8080/api/usercommentvotes",
                {
                    method: 'PUT',
                    headers: {
                        "content-type": "application/json"
                    },
                    body: requestBody
                }
            )
            .then(() => getComments())
       }else{
           alert("Please log in to upvote comments")
       }
    }

    const downvoteComment = (token, comment_id) => {

        if(token){
            const requestBody = JSON.stringify({
                "user_id": token.userId,
                "comment_id": comment_id,
                "upvoted": 0,
                "downvoted": 1
            })

            fetch("http://localhost:8080/api/usercommentvotes",
                {
                    method: 'PUT',
                    headers: {
                        "content-type": "application/json"
                    },
                    body: requestBody
                }
            )
            .then(() => getComments())
        }else{
            alert("Please log in to downvote comments")
        }
    }
    
    const getMpVotesEnergy = () => {
        fetch("https://members-api.parliament.uk/api/Location/Constituency/" + currentConstituency.constituency_id)
        .then(response => response.json())
        .then(data => data.value.currentRepresentation.member.value.id)    
        .then(data =>  
        fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=
        ${data}
        &queryParameters.searchTerm=energy`)
        .then(result => result.json())
        .then(data =>data.map(item => { 
            return {"title": item.PublishedDivision.Title, "vote": item.MemberVotedAye}
            }
        ))
        .then(data => setMpVotesEnergy(data))
        )
    }

    useEffect(() => {
        getMpData();
        getComments();
        getMpEmail();
        getMpTwitter();
        getMpVotesCarbon();
        getMpVotesClimate();
        getMpVotesEnvironment();
        getMpVotesEnergy();
        getCountyData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    return (
        mpData !== "" ?
        <section className='mp-container'>   
            <MP mpData={mpData} mpVotes={[...mpVotesCarbon, ...mpVotesClimate, ...mpVotesEvironment, ...mpVotesEnergy]} 
                email={mpEmail} twitter={mpTwitter} envData={envData}/>
            <EnvironmentalData envData={envData}/>
            <section className="comments-section">
            <CommentForm getComments={getComments} token={token} currentConstituency={currentConstituency} />
            <CommentsList token={token} comments={comments} upvoteComment={upvoteComment} downvoteComment={downvoteComment}/>
            </section>
        </section>
        :
        <p>Loading...</p>
    )
}


export default MPContainer;

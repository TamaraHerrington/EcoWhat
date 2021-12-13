import MP from '../components/MP'
import {useState, useEffect} from 'react';

const MPContainer = ({currentConstituency}) => {
    const [mpData, setMpData] = useState("");
    const [mpTwitter, setMpTwitter] = useState("");
    const [mpEmail, setMpEmail] = useState("");
    const [mpVotes, setMpVotes] = useState([]);

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
        .then(data => data[3].twitter)
        )
        .then(data => setMpTwitter(data))
    }

    

    const getMpVotes = () => {
        if (mpData!=""){
            const allVotes = [];
        fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=${mpData.items[0].value.id}&queryParameters.searchTerm=climate`)
        .then(result => result.json())
        .then(data => allVotes.push(data))
        .then(fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=${mpData.items[0].value.id}&queryParameters.searchTerm=environment`)
        .then(result => result.json())
        .then(data => allVotes.push(data)))
        .then(fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=${mpData.items[0].value.id}&queryParameters.searchTerm=carbon`)
        .then(result => result.json())
        .then(data => allVotes.push(data)))
        .then(fetch(`https://commonsvotes-api.parliament.uk/data/divisions.json/membervoting?queryParameters.memberId=${mpData.items[0].value.id}&queryParameters.searchTerm=pollution`)
        .then(result => result.json())
        .then(data => allVotes.push(data)))
        .then(() => setMpVotes(allVotes.flat()))
    
        }
        
    }

    useEffect(() => {
        getMpData();
        getMpEmail();
        getMpTwitter();
    }, [])





    return (
        // ***
        mpData != ""?
        <>
        
        {/* <p>{JSON.stringify(mpData)}</p> */}
        <MP mpData={mpData} mpVotes={mpVotes} email={mpEmail} twitter={mpTwitter} />
        </>
        :
        <p>Loading...</p>
    )

}

export default MPContainer;

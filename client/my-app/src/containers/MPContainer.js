import MP from '../components/MP'
import {useState, useEffect} from 'react';
import EnvironmentalData from '../components/EnvironmentalData';

const MPContainer = ({currentConstituency, token}) => {
    const [mpData, setMpData] = useState("");
    const [mpTwitter, setMpTwitter] = useState("");
    const [mpEmail, setMpEmail] = useState("");
    const [mpVotesCarbon, setMpVotesCarbon] = useState([]);
    const [mpVotesClimate, setMpVotesClimate] = useState([]);
    const [mpVotesEvironment, setMpVotesEnvironment] = useState([]);
    const [user, setUser] = useState(null);
    const [mpVotesEnergy, setMpVotesEnergy] = useState([])
    const [envData, setEnvData] = useState(null)

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
        .then(data => data.filter(d => d.type=="Twitter").length==0? null: data.filter(d => d.type=="Twitter")[0].line1.split('twitter.com/').at(-1))
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

    const getUser = () => {
        fetch(`http://localhost:8080/api/users/user`,
    {
      method: 'POST',
      headers: {
          "content-type": "text/plain;charset=UTF-8"
      },
      body: `${token}`
        
    }).then(response => response.json())
    .then(data => setUser(data))
    }

    useEffect(() => {
        getMpData();
        getMpEmail();
        getMpTwitter();
        getMpVotesCarbon();
        getMpVotesClimate();
        getMpVotesEnvironment();
        getUser();
        getMpVotesEnergy();
        getCountyData();
        console.log(envData)
    }, [])

    return (
        mpData != ""?
        <div className='mp-container'>
        
        <MP user={user} mpData={mpData} mpVotes={[...mpVotesCarbon, ...mpVotesClimate, ...mpVotesEvironment, ...mpVotesEnergy]} 
        email={mpEmail} twitter={mpTwitter} envData={envData}/>
        <EnvironmentalData envData={envData}/>
        </div>
        :
        <p>Loading...</p>
    )

}

export default MPContainer;

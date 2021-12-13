import MP from '../components/MP'
import {useState, useEffect} from 'react';

const MPContainer = ({currentConstituency}) => {
    const [mpData, setMpData] = useState("");
    const [mpContact, setMpContact] = useState([]);
    const [mpVotes, setMpVotes] = useState([]);
    // console.log("MP DATA: " + mpData)
    

    const getMpData = () => {
        // get basic info and contact info in two calls
        fetch("https://members-api.parliament.uk/api/Members/Search?ConstituencyId=" + currentConstituency.constituency_id + "&IsCurrentMember=true")
        .then(result => result.json())
        .then(data => setMpData(data.items))
        // .filter(datum => datum.value.latestHouseMembership.membershipStatus.statusIsActive===true)))
        .then(getMpVotes)
        // console.log("MP DATA: " + mpData)
        // contact data
    }
    // const getMpContact = () => {
    //     fetch(`https://members-api.parliament.uk/api/Members/${mpData[0].value.id}/Contact`)
    //     .then(result => result.json())
    //     .then(data => data.value.filter(datum => datum.type=="Constituency" || datum.type=="Parliamentary")
    //     .then(data => setMpContact(data)))
    // }

    // 

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
        // getMpContact();
    }, [])


    return (
        // ***
        mpData != ""?
        <>
        
        {/* <p>{JSON.stringify(mpData)}</p> */}
        <MP mpData={mpData} mpVotes={mpVotes}/>
        </>
        :
        <p>Loading...</p>
    )

}

export default MPContainer;

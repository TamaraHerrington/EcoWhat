import React from 'react'
import {useState} from "react";

function SearchBar({setCurrentConstituency}) {

    const [searchTerm, setSearchTerm] = useState();

    const handleSubmit = (event, postcode) => {
        event.preventDefault();
        fetch(
            `http://localhost:8080/api/constituencies/${postcode}`, {
                method: "GET",
                headers: {
                "Content-Type": "application/json"
                }
            })
            .then(response => {
                if(!response.ok){
                    return response.json().then(err => {throw new Error(err.message)})
                }
                return response.json()})
            .then(data =>{
                console.log(data)
                setCurrentConstituency(data);
            })
            .catch(err => {
                alert("Please enter a valid postcode");
            })
        }
    

    return (
        <div>
            <form onSubmit={(event) => handleSubmit(event, searchTerm)}>
            <input type="text" placeholder="Find your constituency by postcode" 
            onChange={(event) => setSearchTerm(event.target.value)}></input>
            <button type="submit">Search</button>
            </form>
        </div>
    )
};

export default SearchBar

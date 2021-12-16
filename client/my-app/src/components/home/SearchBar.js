import React from 'react';
import {useState} from "react";
import './SearchBar.css'
import { useNavigate } from 'react-router-dom';

function SearchBar({setCurrentConstituency}) {

    const [searchTerm, setSearchTerm] = useState();
    const navigate = useNavigate();

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
                setCurrentConstituency(data)
                
            }).then(()=>navigate('/constituency/current'))
            .catch(err => {
                console.log(err)
                alert("Please enter a valid postcode");
            })
        }
    

    return (
        <div className='search-bar'>
            <form onSubmit={(event) => handleSubmit(event, searchTerm)}>
            <input className='constituency-search' type="text" placeholder="Find your constituency by postcode" 
            onChange={(event) => setSearchTerm(event.target.value)}></input>
            <button className='search-button' type="submit">Search</button>
            </form>
        </div>
    )
};

export default SearchBar

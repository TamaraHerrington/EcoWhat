import { DomEvent } from 'leaflet'
import React from 'react'
import {useState} from "react";
import { Link } from 'react-router-dom'
import react from 'react';

function CommentForm({user, getComments, token, currentConstituency}) {

    const [title, setTitle] = useState();
    const [comment, setComment] = useState();
    const [category, setCategory] = useState("Recylcing");

    //TODO: Add some logic for content checking here (swearing, banned words, etc)
    const handleCommentSubmit = () => {
        const today = new Date();
        const dateTime = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate() + " " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
        console.log(user.id)
        console.log(title)
        console.log(comment)
        console.log(currentConstituency.constituency_id)
        console.log(dateTime)

        if(comment && title && token){
            fetch(
                "http://localhost:8080/api/comments/add",
                {
                    method: 'POST',
                    headers: {
                        "content-type": "application/json"
                    },
                    body: {
                        "userId" : user.id,
                        "comment_title" : title,
                        "comment": comment,
                        "comment_category": category,
                        "constituencId": currentConstituency.constituency_id,
                        "post_date": dateTime
                    }   
                }
            )
            .then(response => {
                if(!response.ok){
                    return response.json().then(err => {throw new Error(err.message)})
                }
                return response.json();
            })
            .then(() => getComments())
            .catch(err => {
                alert("Comment not posted. Please try again");
            })
        }
        else if(comment && title && !token){
            alert("Not signed in. Please sign in to make comments")
        }
        else{
            alert("Please enter a valid comment")
        }
    }

    return (
        <form className="comment-form" onSubmit={handleCommentSubmit}>
                    <h1 className="comment-header">Add Comment</h1>

                    <label forHtml="title">Title</label>
                    <input type="text" id="comment-title" onChange={(event) => setTitle(event.target.value)}/>

                    <label forHtml="category">Choose a relevant category</label>
                    <select id="categories" name="categories" onChange={(event) => setCategory(event.target.value)}>
                        <option value="Recyling">Recyling</option>
                        <option value="Pollution">Pollution</option>
                        <option value="Policy">Policy</option>
                        <option value="Energy">Energy</option>
                        <option value="Farming">Farming</option>
                    </select>

                    <label forhtml="comment_body">Comment</label>
                    <input type="text" id="comment-text" onChange={(event) => setComment(event.target.value)}/>

                    <input type="submit" value="Leave comment"/>
                    
                </form>
        
    )
}

export default CommentForm

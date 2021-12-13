import React from 'react'
import Comment from "./Comment"

function CommentsList({comments}) {

    const commentComponents = comments.map((comment) =>{
        return(
            <Comment key={comment.id} comment={comment}/>
        )
    } )

    return (
       commentComponents
    )
}

export default CommentsList


import React from 'react'
import Comment from "./Comment"

function CommentsList({comments, upvoteComment, downvoteComment}) {

    const commentComponents = comments.map((comment) =>{
        return(
            <Comment key={comment.id} comment={comment} upvoteComment={upvoteComment} downvoteComment={downvoteComment}/>
        )
    } )

    return (
       commentComponents
    )
}

export default CommentsList


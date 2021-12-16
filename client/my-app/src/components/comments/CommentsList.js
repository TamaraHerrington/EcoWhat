import React from 'react'
import Comment from "./Comment"

function CommentsList({token, comments, upvoteComment, downvoteComment, linkToConstituency}) {

    const commentComponents = comments.map(comment => {
        return(
            <Comment key={comment.id} token={token} comment={comment} upvoteComment={upvoteComment} downvoteComment={downvoteComment} linkToConstituency={linkToConstituency}/>
        )
    } )

    const compareNumbers = (a, b) => {
        return b.key - a.key;
    }

    commentComponents.sort(compareNumbers)

    return (
       commentComponents
    )
}

export default CommentsList


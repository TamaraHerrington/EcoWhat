import React from 'react'

function Comment({token, comment, upvoteComment, downvoteComment}) {

    let userId = parseInt(token.userId)

    return (
        <div>
            <h3>{comment.comment_title}</h3>
            <h4>{comment.comment_category}</h4>
            <h5>{comment.post_date}</h5>
            <p>{comment.comment}</p>
            <h5>{comment.upvotes}</h5>
            <button type="button" onClick={() => upvoteComment(userId, comment.id)}>Upvote</button>
            <h5>{comment.downvotes}</h5>
            <button type="button" onClick={() => downvoteComment(userId, comment.id)}> Downvote</button>
        </div>
    )
}

export default Comment

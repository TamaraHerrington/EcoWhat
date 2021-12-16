import React from 'react'

function Comment({token, comment, upvoteComment, downvoteComment}) {

    return (
        <div>
            <h3>{comment.comment_title}</h3>
            <h3>{comment.comment_category}</h3>
            <h3>{comment.post_date}</h3>
            <p>{comment.comment}</p>
            {upvoteComment && downvoteComment ?
                <>
                    <h4>{comment.upvotes}</h4>
                    <button type="button" onClick={() => upvoteComment(token.userId, comment.id)}>Upvote</button>
                    <h4>{comment.downvotes}</h4>
                    <button type="button" onClick={() => downvoteComment(token.userId, comment.id)}>Downvote</button>
                </>
                :
                <></>
            }
        </div>
    )
}

export default Comment

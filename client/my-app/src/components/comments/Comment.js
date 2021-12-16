import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons'
import { faThumbsDown } from '@fortawesome/free-solid-svg-icons'

function Comment({ token, comment, upvoteComment, downvoteComment, linkToConstituency }) {


    const categorise = (comment) => {

        let category = ""

        switch(comment.comment_category){
            case "Pollution":
                category = "comment-category-pollution";
                break;
            case "Policy":
                category = "comment-category-policy";
                break;
            case "Energy":
                category = "comment-category-energy";
                break;
            case "Farming":
                category = "comment-category-farming"
                break;
            default:
                category = "comment-category-recycling";
        }

        return category;    
    }


    return (
        <div className='comment'>
            <div className='comment-title'>
                {linkToConstituency ?
                    <h2 onClick={() => linkToConstituency(comment.constituencyId)}>{comment.comment_title}</h2>
                :
                    <h2>{comment.comment_title}</h2>
                }
                <h2 className={categorise(comment)}>{comment.comment_category}</h2>
            </div>
            <h3 className='comment-date'>{comment.post_date}</h3>
            <p className="comment-body">{comment.comment}</p>
            <div className='comment-ratings'>
                
                {upvoteComment ?
                    <>
                    <h4>{comment.upvotes}</h4>
                    <button className="comment-votebutton" type="button" onClick={() => upvoteComment(token.userId, comment.id)}><FontAwesomeIcon icon={faThumbsUp} /></button>
                    </> 
                    :
                    <h4>Upvotes: {comment.upvotes}</h4>
                }
                
                {downvoteComment ?
                    <>
                    <h4>{comment.downvotes}</h4>
                    <button className="comment-votebutton" type="button" onClick={() => downvoteComment(token.userId, comment.id)}><FontAwesomeIcon icon={faThumbsDown} /></button>
                    </> 
                    :
                    <h4>Downvotes: {comment.downvotes}</h4>
                }
            </div>
        </div>
    )
}

export default Comment

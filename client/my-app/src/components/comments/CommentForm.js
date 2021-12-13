import React from 'react'

function CommentForm() {
    return (
        <form className="comment-form" onSubmit={handleSubmit}>
                    <h1 className="comment-header">Add Comment</h1>

                    <label forHtml="title">Title</label>
                    <input type="text" id="user-email" onChange={handleTitleChange}/>

                    <label forHtml="category">Choose a relevant category</label>
                    <select id="categories" name="categories">
                        <option value="Recyling">Recyling</option>
                        <option value="Pollution">Pollution</option>
                        <option value="Policy">Policy</option>
                        <option value="Energy">Energy</option>
                        <option value="Farming">Farming</option>
                    </select>

                    <input className="login-btn" type="submit" value="Login"/>
                    
                    <div className="login-register">
                        <p>Need an account?</p>
                        <Link to="/registration">Sign Up</Link>
                    </div>
                    
                </form>
        
    )
}

export default CommentForm

import React from "react";

const About=()=>{
    return(
        <>
        <section class="youhelp">
            <header className="youhelp__header">
                <h1 className="youhelp__heading">Meet the Team:  </h1>
                <div class="scroll-container">
                    <span class="scroll-text">
                        Helena <br></br>
                        Vinh <br></br>
                        Chaam <br></br>
                        Jonathan<br></br>
                        Tamara
                    </span>
                </div>
            </header>
        </section>
        <h1>About EcoWhat</h1>
        <img class="image" src={"https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2940&q=80"}/>
       </>
    )
}

export default About;
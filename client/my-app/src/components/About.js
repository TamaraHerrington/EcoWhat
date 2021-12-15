import React from "react";

const About = () => {

    return(
        <>
            <section className="youhelp">
                <header className="youhelp__header">
                    <h1 className="youhelp__heading">For A Better</h1>
                    <div className="scroll-container">
                        <span className="scroll-text">
                            Planet <br></br>
                            Future <br></br>
                            You
                        </span>
                    </div>
                </header>
            </section>
            <h1>About EcoWhat</h1>
            <img className="image" src={"https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2940&q=80"} alt="Group of people working on laptops"/>
            <p>Welcome to EcoWhat? We're a team of eco-concious software engineers looking to make a positive change for the environment.
            Together we developed EcoWhat? - a website that allows you to get involved in eco-activism. Here you can search for your MP and use our auto-generated templates to ask them to care more for the environemnt. </p>
       </> 
    )
}

export default About;
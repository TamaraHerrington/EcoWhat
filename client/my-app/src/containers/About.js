import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { faInstagram } from '@fortawesome/free-brands-svg-icons'
import { faFacebook } from '@fortawesome/free-brands-svg-icons'
import "../App.css"

const About = () => {

    return(
        <>
            <section className="about">
                <header className="about__header">
                    <h1 className="about__heading">Meet the Team: </h1>
                    <div className="scroll-container--about">
                        <span className="scroll-text--about">
                            Chaam <br></br>
                            Vinh <br></br>
                            Helena <br></br>
                            Jonathan<br></br>
                            Tamara
                        </span>
                    </div>
                </header>
            </section>

            <section className="about-body">
                <div className="about-body--section-1">
            <img className="about-body__img" src={"https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2940&q=80"} alt="Group of people working on laptops"/>
                </div>
                <div className="about-body--section-2">
                    <h2>About EcoWhat</h2>
                    <p>Welcome to EcoWhat? We're a team of eco-concious software engineers looking to make a positive change for the environment.
                    Together we developed EcoWhat? - a website that allows you to get involved in eco-activism. Here you can search for your MP and use our auto-generated templates to ask them to care more for the environment. </p>
                    <div className="about-social">
                        <a className="about-icon" href="#">
                        <FontAwesomeIcon icon={ faFacebook } />
                        </a>
                        <a className="about-icon" href="#">
                        <FontAwesomeIcon icon={ faTwitter } />
                        </a>
                        <a className="about-icon" href="#">
                        <FontAwesomeIcon icon={ faInstagram } />
                        </a>
                    </div>
                </div>
            </section>
       </> 
    )
}

export default About;
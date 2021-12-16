import React from "react";
import Card from "../components/Card";
//import "./YouHelp.css";

const YouHelp = () => {

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
            <h2 className="youhelp__comment">How you can help the environment...</h2>

            <Card card />
        </>   
    )
}

export default YouHelp;
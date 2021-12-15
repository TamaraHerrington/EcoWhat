import React from "react";
import Card from "./Card";
//import "./YouHelp.css";

const YouHelp=()=>{

    return(
    <>
        <section class="youhelp">
            <header className="youhelp__header">
                <h1 className="youhelp__heading">For A Better</h1>
                <div class="scroll-container">
                    <span class="scroll-text">
                        Planet <br></br>
                        Future <br></br>
                        You
                    </span>
                </div>
            </header>
        </section>
            <p className="youhelp__comment">How you can help the environment...</p>

        <Card card />
        </>
       
    )
}

export default YouHelp;
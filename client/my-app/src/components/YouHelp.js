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
                        Home
                    </span>
                </div>
            </header>
        </section>

        <Card card />
        </>
       
    )
}

export default YouHelp;
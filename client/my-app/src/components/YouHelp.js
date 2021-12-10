import React from "react";
import Card from "./Card";
import "./YouHelp.css";

const YouHelp=()=>{

    return(
    <>
        <section class="youhelp">
        <p> For A Better   </p>
            <div class="scroll-container">
                <span class="scroll-text">
                    Planet <br></br>
                    Future <br></br>
                    Home
                </span>
            </div>
        </section>

        <Card card />
        </>
       
    )
}

export default YouHelp;
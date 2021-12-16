import { useState, useEffect } from "react";
import './EnvironmentalData.css'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faEnvelope} from '@fortawesome/free-solid-svg-icons'
import { faTemperatureHigh } from "@fortawesome/free-solid-svg-icons";
import { faTemperatureLow } from "@fortawesome/free-solid-svg-icons";
import { faSeedling } from "@fortawesome/free-solid-svg-icons";
import { faAirFreshener } from "@fortawesome/free-solid-svg-icons";
import { faAtom } from "@fortawesome/free-solid-svg-icons";
import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";

const EnvironmentalData = ({envData}) => {
    
    return(envData==undefined?
        <div key={envData} className="env-data">           
            <p>Loading...</p>
        </div>
            :
        <div key={envData} className="env-data">
            <h2>Environmental Data</h2>
            <p><FontAwesomeIcon icon={faMapMarkerAlt} /> County: {envData.countyName}</p>
            <p><FontAwesomeIcon icon={faAtom} /> Nitrogen Dioxide: {envData.nitrogenDioxide} ug/m3</p>
            <p><FontAwesomeIcon icon={faAirFreshener} /> Particulate Matter: {envData.particulateMatter} ug/m3</p>
            <p><FontAwesomeIcon icon={faSeedling} /> Herbicides: {envData.herbicides} kg</p>
            <p><FontAwesomeIcon icon={faTemperatureHigh} /> DBPs Summer: {envData.dbpsSummer}</p>
            <p><FontAwesomeIcon icon={faTemperatureLow} /> DBPs Winter: {envData.dbpsWinter}</p>
        </div>

    )
}

export default EnvironmentalData;
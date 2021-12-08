import countries from "../data/countries.json";
import {MapContainer} from "react-leaflet";

const MyMap = () => {

    return (
        <div>
            <h1>hello world</h1>
            <MapContainer style={"{height: 80vh}"}></MapContainer>
        </div>
    )

}

export default MyMap;
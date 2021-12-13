import constituencies from '../data/constituencies.json';
import {MapContainer, GeoJSON} from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import './Home.css';
import SearchBar from './SearchBar';

const Home = ({ token, currentConstituency, setCurrentConstituency }) => {
    const mapStyle = {
        fillColor: "navy",
        fillOpacity: 0.6,
        color: "black",
        weight: 0.5,
        
    }

    const onEachConstituency = (constituency, layer) => {
        const constituencyName = constituency.properties.PCON13NM;
        console.log(constituencyName);
        layer.bindPopup(constituencyName);

        layer.on({
            click: (event) => {
                
            },
            mouseover: (event) => {
                event.target.setStyle({fillColor: "turquoise"});
                layer.bindPopup(constituencyName).openPopup();
            },
            mouseout: (event) => {
                event.target.setStyle({fillColor: "navy"});
                layer.bindPopup(constituencyName).closePopup(constituencyName);
            }
        })


    }

    return (
        <>
            {/* <h1>Constituencies Map</h1> */}
            <SearchBar setCurrentConstituency={setCurrentConstituency}/>
            <h1>Find your constituency. Understand your MPs stance on climate change. Have your say.</h1>
      <MapContainer style={{height: "85vh"}} zoom={6.5} center={[53,0]}>
        <GeoJSON className="geo-json" style={mapStyle} data={constituencies.features} onEachFeature={onEachConstituency}/>
      </MapContainer>
        </>
    )
}

export default Home
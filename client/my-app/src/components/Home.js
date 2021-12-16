import constituencies from '../data/constituencies.json';
import { MapContainer, GeoJSON } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import './Home.css';
import SearchBar from './SearchBar';
import { useNavigate } from 'react-router-dom';
import {useState, useLayoutEffect} from 'react';

const Home = ({ token, currentConstituency, setCurrentConstituency }) => {
    const [windowSize, setWindowSize] = useState([window.innerWidth, window.innerHeight]);
    const [mapZoom, setZoom] = useState(7);
    const [mapCenter, setMapCenter] = useState([53,0]);

    useLayoutEffect(() => {
        function updateSize() {
          setWindowSize([window.innerWidth, window.innerHeight]);
        }
        window.addEventListener('resize', updateSize);
        updateSize();
        if (windowSize[0]<600){
            setZoom(6);
            setMapCenter([52, -2]);
        }
        // else if (windowSize[0]<900){
        //     setZoom(6);
        // }
        return () => window.removeEventListener('resize', updateSize);
      }, []);
    console.log(windowSize);
    

    const navigate = useNavigate();

    const mapStyle = {
        fillColor: "navy",
        fillOpacity: 0.6,
        color: "black",
        weight: 0.5,    
    }

    const onEachConstituency = (constituency, layer) => {
        const constituencyName = constituency.properties.PCON13NM;
        // console.log(constituencyName);
        layer.bindPopup(constituencyName);

        layer.on({
            click: (event) => {
                fetch("https://members-api.parliament.uk/api/Location/Constituency/Search?searchText=" + 
                event.target.feature.properties.PCON13NM)
                .then(response => response.json())
                .then(data => data.items[0].value.id)
                .then(data => setCurrentConstituency({constituency_id: data,
                    constituency_name: event.target.feature.properties.PCON13NM}))
                    .then(()=>navigate('/constituency/current'))
                
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
            <section className="home">
                <header className="home__header">
                    <h1 className="home__heading">Eco</h1>
                    <div className="scroll-container--home">
                        <span className="scroll-text--home">
                            Worrier? <br></br>
                            Warrior? <br></br>
                            What?
                        </span>
                    </div>
                </header>
            </section>

            <h2 className='header'>Find your constituency. Understand your MPs stance on climate change. Have your say.</h2>
            <SearchBar setCurrentConstituency={setCurrentConstituency}/>

            <MapContainer key={mapZoom} className="map-container" style={{height: "80vh", width: "95vw"}} 
            zoom={mapZoom} scrollWheelZoom={false} center={mapCenter}>
                <GeoJSON key={mapZoom} className="geo-json" style={mapStyle} data={constituencies.features} onEachFeature={onEachConstituency}/>
            </MapContainer>
        </>
    )
}

export default Home
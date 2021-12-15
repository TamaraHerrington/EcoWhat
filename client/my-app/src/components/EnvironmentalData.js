import './EnvironmentalData.css'

const EnvironmentalData = ({envData}) => {
    
    return (envData === undefined ?
        <div key={envData} className="env-data">           
            <p>Loading...</p>
        </div>
            :
        <div key={envData} className="env-data">
            <h2>Environmental Data:</h2>
            <p>County: {envData.countyName}</p>
            <p>Nitrogen Dioxide: {envData.nitrogenDioxide} ug/m3</p>
            <p>Particulate Matter: {envData.particulateMatter} ug/m3</p>
            <p>Herbicides: {envData.herbicides} kg</p>
            <p>DBPS Summer: {envData.dbpsSummer}</p>
            <p>DBPS Winter: {envData.dbpsWinter}</p>
        </div>
    )
}

export default EnvironmentalData;
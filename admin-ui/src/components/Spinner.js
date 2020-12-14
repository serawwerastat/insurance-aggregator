import React from "react";


function Spinner() {
    return (
        <div className="card" style={{margin: '10px'}}>
            <div className="spinner-border" role="status" style={{margin: '0 auto'}}>
                <span className="sr-only">Loading...</span>
            </div>
        </div>
    )
}

export default Spinner;
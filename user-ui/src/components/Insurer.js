import React from "react";

function Insurer({insurer}) {
    return (
        <div key={insurer.name} className="card" style={{margin: '10px'}}>
            <div className="row no-gutters">
                <div className="col-md-8 text-white bg-dark">
                    <div className="card-body">
                        <h5 className="card-title text-center">{insurer.name}</h5>
                    </div>
                </div>
                <div className="col-md-4 bg-light">
                    <div className="card-body">
                        <h5 className="card-text text-center">{insurer.premium}</h5>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Insurer;
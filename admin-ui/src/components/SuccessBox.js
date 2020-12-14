import React from "react";

function SuccessBox(props) {
    const {msg} = props;
    return (
        <div className="alert alert-success text-center" role="alert" style={{margin: '10px'}}>
            {msg}
        </div>
    )
}

export default SuccessBox;
import React from "react";

function AlertBox(props) {
    const {msg} = props;
    return (
        <div className="alert alert-danger text-center" role="alert" style={{margin: '10px'}}>
            {msg}
        </div>
    )
}

export default AlertBox;
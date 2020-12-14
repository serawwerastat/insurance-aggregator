import React, {Component} from "react";
import InsurerList from "./InsurerList";

class InsurerBrowser extends Component {


    render() {
        return (
            <div className="card" style={{width: '50%', margin: '0 auto', marginTop: "10px"}}>
                <div className="card-header text-center">
                    <h2 >Insurers</h2>
                    <div className="row no-gutters">
                        <h6 className="card-header-tabs col-md-8"  style={{margin: '0 auto'}}>Insurer name</h6>
                        <h6 className="card-header-tabs col-md-4"  style={{margin: '0 auto'}}>Premium &#8595;</h6>
                    </div>
                </div>
                <InsurerList userData={this.props.userData}/>
            </div>
        )
    }

}


export default InsurerBrowser;
import React, {Component} from "react";
import Insurer from "./Insurer";
import AlertBox from "./AlertBox";
import Spinner from "./Spinner";


class InsurerList extends Component {

    state = {
        loading: true,
        insurers: [],
    }

    async componentDidMount() {
        const url = "http://localhost:8085/premium";
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(this.props.userData),
        };
        const response = await fetch(url, requestOptions);
        const insurers = await response.json();
        this.setState({insurers: insurers, loading: false})
    }

    render() {
        if (this.state.loading) {
            return (<Spinner/>);
        }
        const {insurers} = this.state;
        const insurerLists = insurers.map(insurer => <Insurer key={insurer.name} insurer={insurer}/>);
        const noInsurersAlert = insurers.length === 0 &&
            <AlertBox msg="No insurers available at the moment. Please try again later"/>
        return (
            <div className="card" style={{marginTop: "10px"}}>
                {insurerLists}
                {noInsurersAlert}
            </div>
        )
    }

}

export default InsurerList;
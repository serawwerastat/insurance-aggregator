import React, {Component} from "react";
import Insurer from "./Insurer";
import Spinner from "./Spinner";
import AlertBox from "./AlertBox";
import deleteInsurer from "../deleteInsurer";

class InsurerList extends Component {

    state = {
        loading: true,
        insurers: [],
    }

    async componentDidMount() {
        const url = "http://localhost:8085//insurer";
        const response = await fetch(url);
        const insurers = await response.json();
        this.setState({insurers: insurers, loading: false})
    }

    componentWillUpdate(nextProps, nextState) {
        if (Object.keys(nextProps.newInsurer).length !== 0 && nextProps.newInsurer !== this.props.newInsurer) {
            nextState.insurers = [...nextState.insurers].concat([nextProps.newInsurer]);
        }
    }

    render() {
        if (this.state.loading) {
            return <Spinner/>
        }
        if (this.state.insurers.length === 0) {
            return <AlertBox msg="Failed to load insurers!"/>
        }
        const {onDelete} = this;

        const insurersList = this.state.insurers.map(insurer => <Insurer key={insurer.id} insurer={insurer}
                                                                         onDelete={onDelete}/>);
        return (
            <div>
                {insurersList}
            </div>
        )
    }

    onDelete = insurerToDelete => {
        deleteInsurer(insurerToDelete).then(() => {
            const insurersWithoutDeletedOne = this.state.insurers.filter(insurer2 => insurerToDelete.id !== insurer2.id);
            this.setState({
                insurers: insurersWithoutDeletedOne
            })
        })
    }

}

export default InsurerList;
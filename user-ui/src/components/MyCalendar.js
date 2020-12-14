import React, {Component} from 'react'
import moment from 'moment'
import Calendar from 'ciqu-react-calendar'

class MyCalendar extends Component {

    state = {
        value: moment('1990-01-01')
    }

    render() {
        return <div>
            <Calendar
                onChange={this.onChange}
                value={this.state.value}
                allowClear={true}
                disabled={false}
                placeholder={'please input date'}
                format={'DD.MM.YYYY'}
            />
        </div>
    }

    onChange = (value, inputValue) => {
        this.props.handleCalendar(value.format('DD.MM.YYYY'));
        this.setState({value})
    }
}

export default MyCalendar;

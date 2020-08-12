import React from 'react';

class DropDown extends React.Component {

    render(){
        return (
            <select className="form-control" value={this.props.value} name={this.props.name} onChange={this.props.onChange}>{
                this.props.values.map((obj) => {
                    return <option key={obj.name} value={obj.name}>{obj.name}</option>
                })
            }</select>
        );
    }
}

export default DropDown;
import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import {AppContext} from "../../AppContext"
import DropDown from "../Dropdown";

class AddContractComponent extends Component {

    static contextType = AppContext

    constructor(props){
        super(props);
        this.state ={
            errors: [],
            id: '',
            regNumber: '',
            insuranceType: 'Страхование дома',
            insuranceAmount: '',
            insurancePayment: ''
        }
        this.saveContract = this.saveContract.bind(this);
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    // simple form validation
    handleValidation() {
        let errors = {};
        let formIsValid = true;
        const emptyFieldMsg = "Поле не должно быть пустым";

        if (!this.state.regNumber) {
            formIsValid = false;
            errors['regNumber'] = emptyFieldMsg;
        }

        if (!this.state.insuranceAmount) {
            formIsValid = false;
            errors['insuranceAmount'] = emptyFieldMsg;
        }

        if (!this.state.insurancePayment) {
            formIsValid = false;
            errors['insurancePayment'] = emptyFieldMsg;
        }

        this.setState({errors: errors});
        return formIsValid;
    }

    saveContract = (e) => {
        e.preventDefault();

        if (this.handleValidation()) {
            let contract = {
                id: this.state.id
                , regNumber: this.state.regNumber
                , insuranceType: {
                    name: this.state.insuranceType
                }
                , insuranceAmount : this.state.insuranceAmount
                , insurancePayment: this.state.insurancePayment
            };
            ApiService.addContract(contract)
                .then(res => {
                    this.setState({message: 'Contract added successfully.'});
                    this.props.history.push('/');
                });
        } else {
            console.log("form not valid");
        }
    }

    render() {
        const types = this.context.types

        return (
            <div className="row my-3">
                <div className="col">
                    <h3 className="text-center m-2">Добавление</h3>
                    <form>
                        <div className="form-group">
                            <label>Номер:</label>
                            <input placeholder="Номер" name="regNumber" className="form-control" value={this.state.regNumber} onChange={this.onChange}/>
                            <span style={{color: "red"}}>{this.state.errors["regNumber"]}</span>
                        </div>

                        <div className="form-group">
                            <label>Тип:</label>
                            <DropDown name="insuranceType" value={this.state.insuranceType} values={types} onChange={this.onChange}/>
                        </div>

                        <div className="form-group">
                            <label>Страховая сумма:</label>
                            <input placeholder="Страховая сумма" type="number" name="insuranceAmount" className="form-control" value={this.state.insuranceAmount} onChange={this.onChange}/>
                            <span style={{color: "red"}}>{this.state.errors["insuranceAmount"]}</span>
                        </div>

                        <div className="form-group">
                            <label>Платеж:</label>
                            <input placeholder="Платеж" type="number" name="insurancePayment" className="form-control" value={this.state.insurancePayment} onChange={this.onChange}/>
                            <span style={{color: "red"}}>{this.state.errors["insurancePayment"]}</span>
                        </div>

                        <button className="btn btn-success" onClick={this.saveContract}>
                            <i className="fa fa-check" aria-hidden="true"></i>&nbsp; Сохранить
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

export default AddContractComponent;
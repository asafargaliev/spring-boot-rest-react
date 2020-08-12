import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import DropDown from "../Dropdown";
import {AppContext} from "../../AppContext"

class EditContractComponent extends Component {

    static contextType = AppContext

    constructor(props){
        super(props);
        this.state ={
            errors: [],
            id: '',
            regNumber: '',
            insuranceType: '',
            insuranceAmount: '',
            insurancePayment: ''
        }
        this.saveContract = this.saveContract.bind(this);
        this.loadContract = this.loadContract.bind(this);
        this.deleteContract = this.deleteContract.bind(this);
    }

    componentDidMount() {
        this.loadContract();
    }

    loadContract() {
        ApiService.fetchContractById(window.localStorage.getItem("contractId"))
            .then((res) => {
                let contract = res.data;
                this.setState({
                    id: contract.id,
                    regNumber: contract.regNumber,
                    insuranceType: contract.insuranceType.name,
                    insuranceAmount: contract.insuranceAmount,
                    insurancePayment: contract.insurancePayment
                })
            });
    }

    deleteContract = (e) => {
        e.preventDefault();

        const conf = window.confirm('Вы действительно хотите удалить запись?');
        if (conf) {
            ApiService.deleteContract(this.state.id)
                .then(res => {
                    window.location.href = '/'
                })
        }
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

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
            let contract = {id: this.state.id
                , regNumber: this.state.regNumber
                , insuranceType: {
                    name: this.state.insuranceType
                }
                , insuranceAmount : this.state.insuranceAmount
                , insurancePayment: this.state.insurancePayment
            };
            ApiService.editContract(contract)
                .then(res => {
                    this.setState({message : 'Contract edited successfully.'});
                    this.props.history.push('/');
                });
        } else {
            console.log("form not valid");
        }
    }

    render() {
        const types = this.context.types

        return (
            <div>
                <div className="row my-3">
                    <div className="col">
                        <h3 className="text-center m-2">Редактирование</h3>
                        <form>

                            <div className="form-group">
                                <label>ИД:</label>
                                <input type="text" placeholder="Id" name="id" className="form-control" readOnly="{true}" defaultValue={this.state.id}/>
                            </div>

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

                        </form>
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <button className="btn btn-success" onClick={this.saveContract}>
                            <i className="fa fa-check" aria-hidden="true"></i>&nbsp; Сохранить
                        </button>
                    </div>
                    <div className="col text-right"><button className="btn btn-danger" onClick={this.deleteContract}>
                        <i className="fa fa-trash" aria-hidden="true"></i>&nbsp; Удалить
                    </button></div>
                </div>
            </div>

        );
    }
}

export default EditContractComponent;
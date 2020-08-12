import axios from 'axios';
import AuthService from "./AuthService";

const API_BASE_URL = 'http://localhost:8080/api';
const API_BASE_URL_CONTRACTS = API_BASE_URL + '/contracts';
const API_BASE_URL_TYPES = API_BASE_URL + '/types';

class ApiService {

    fetchContracts(page) {
        return axios.get(API_BASE_URL_CONTRACTS + '?page=' + page, this.getAuthHeader());
    }

    fetchContractById(contractId) {
        return axios.get(API_BASE_URL_CONTRACTS + '/' + contractId, this.getAuthHeader());
    }

    deleteContract(contractId) {
        return axios.delete(API_BASE_URL_CONTRACTS + '/' + contractId, this.getAuthHeader());
    }

    addContract(contract) {
        return axios.post(API_BASE_URL_CONTRACTS, contract, this.getAuthHeader());
    }

    editContract(contract) {
        return axios.put(API_BASE_URL_CONTRACTS + '/' + contract.id, contract, this.getAuthHeader());
    }

    fetchTypes() {
        return axios.get(API_BASE_URL_TYPES);
    }

    getAuthHeader() {
        return {
            headers: {
                'Authorization' : 'Bearer_' + AuthService.getToken()
            }
        };
    }

}

export default new ApiService();
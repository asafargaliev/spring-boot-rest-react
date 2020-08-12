import decode from 'jwt-decode';
import axios from 'axios';

const LOGIN_URL = 'http://localhost:8080/auth/login';

class AuthService {

    isAuthenticated() {
        let token = this.getToken();
        return (token != null && !this.isTokenExpired(token))
    }

    isTokenExpired(token) {
        try {
            const decoded = decode(token);
            if (decoded.exp < Date.now() / 1000) { // Checking if token is expired. N
                return true;
            }
            else
                return false;
        }
        catch (err) {
            return false;
        }
    }

    login(username, password, cb) {
        let loginRequest = {
            username: username,
            password: password
        }

        axios.post(LOGIN_URL, loginRequest).then(res => {
            this.setToken(res.data.token);
            cb();
        })
            .catch(
            function (error) {
                console.log('Show error notification!');
                window.alert('Неправильный логин или пароль');
            }
        );
    }

    setToken(idToken) {
        // Saves user token to localStorage
        localStorage.setItem('id_token', idToken)
    }

    getToken() {
        // Retrieves the user token from localStorage
        return localStorage.getItem('id_token')
    }

    logout(cb) {
        // Clear user token and profile data from localStorage
        localStorage.removeItem('id_token');
        cb();
    }

    getProfile() {
        // Using jwt-decode npm package to decode the token
        if (this.isAuthenticated()) {
            return decode(this.getToken());
        }
    }

}

export default new AuthService();
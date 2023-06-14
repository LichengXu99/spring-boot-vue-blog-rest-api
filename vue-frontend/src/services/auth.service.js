import BASE_URL from "@/utils/baseUrl"
import axios from "axios"

const AUTH_API_URL = '/api/v1/auth'

class AuthService {
    login(user) {
        return axios
            .post(BASE_URL + AUTH_API_URL + '/authenticate', {
                email: user.email,
                password: user.password
            })
            .then(response => {
                if (response.data) {
                    localStorage.setItem('token', response.data.token);
                }
                return response.data.token;
            });
    }

    logout() {
        localStorage.removeItem('token');
    }

    register(user) {
        return axios.post(BASE_URL + AUTH_API_URL + '/register', {
            username: user.username,
            email: user.email,
            password: user.password
        });
    }
}

export default new AuthService();
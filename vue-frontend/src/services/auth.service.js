import BASE_URL from "@/utils/baseUrl"
import axios from "axios"

const AUTH_API_URL = '/api/v1/auth'

class AuthService {
    login(user) {
        return axios
            .post(BASE_URL + AUTH_API_URL + '/authenticate', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
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
import axios from 'axios';
import authHeader from '@/services/auth-header';
import BASE_URL from '@/utils/baseUrl';

const AUTH_API_URL = '/api/v1/auth'

class UserService {
  getPublicContent() {
    return axios.get(BASE_URL + AUTH_API_URL + '/all');
  }

  getUserBoard() {
    return axios.get(BASE_URL + AUTH_API_URL + '/user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(BASE_URL + AUTH_API_URL + '/mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(BASE_URL + AUTH_API_URL + '/admin', { headers: authHeader() });
  }
}

export default new UserService();
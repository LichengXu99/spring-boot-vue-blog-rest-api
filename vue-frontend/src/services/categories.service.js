import axios from "axios";
import BASE_URL from "@/utils/baseUrl";
import authHeader from "@/services/auth-header";

const CATEGORIES_API_URL = '/api/v1/categories'

class CategoriesService {

    getAllCategories() {
        return axios
            .get(BASE_URL + CATEGORIES_API_URL)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    getOneCategoryById(id) {
        return axios
            .post(BASE_URL + CATEGORIES_API_URL + '/' + id)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    createNewCategory(newCategory) {
        return axios
            .post(BASE_URL + CATEGORIES_API_URL, { newCategory }, { headers: authHeader() });
    }
}

export default new CategoriesService();
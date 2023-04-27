import axios from "axios";
import BASE_URL from "@/utils/baseUrl";
import authHeader from "@/services/auth-header";

const TAGS_API_URL = '/api/v1/tags'

class TagsService {

    getAllTags() {
        return axios
            .get(BASE_URL + TAGS_API_URL)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    getOneTagById(id) {
        return axios
            .post(BASE_URL + TAGS_API_URL + '/' + id)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    createNewTag(newTag) {
        return axios
            .post(BASE_URL + TAGS_API_URL, { newTag }, { headers: authHeader() });
    }
}

export default new TagsService();
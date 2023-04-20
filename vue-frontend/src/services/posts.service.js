import axios from "axios";
import BASE_URL from "@/utils/baseUrl";
import authHeader from "@/services/auth-header";

const POSTS_API_URL = '/api/v1/posts'

class PostsService {

    getAllPosts(pageNo, sortBy) {
        return axios
            .get(BASE_URL + POSTS_API_URL + '?pageNo' + pageNo + '&sortBy' + sortBy)
            .then(response => {
                console.log(response.data)
                return response.data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    getOnePostDetails(id) {
        return axios
            .post(BASE_URL + POSTS_API_URL + '/' + id)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    createNewPost(newPost) {
        return axios
            .post(BASE_URL + POSTS_API_URL, { newPost }, { headers: authHeader() });
    }

    editOnePost(id, editedPost) {
        return axios
            .put(BASE_URL + POSTS_API_URL + '/' + id, { editedPost }, { headers: authHeader() });
    }

}

export default new PostsService();
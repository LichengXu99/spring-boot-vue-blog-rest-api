import PostsService from "@/services/posts.service";

export const post = {
    state: {
        loaded: false,
        posts: [],
    },
    mutations: {
        getPostsSuccess(state, posts) {
            state.loaded = true;
            state.posts = posts;
        },
        getPostsFailure(state) {
            state.loaded = false;
            state.posts = [];
        },
    },
    actions: {
        getPosts({ commit }, { pageNo, sortBy }) {
            return PostsService.getAllPosts(pageNo, sortBy).then(
                response => {
                    commit('getPostsSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getPostsFailure');
                    return Promise.reject(error);
                }
            );
        },
    },
    getters: {
        getArticles(state) {
            return state.posts;
        },
    }
};
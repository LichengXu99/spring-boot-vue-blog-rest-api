import PostsService from "@/services/posts.service";

export const post = {
    state: {
        loaded: false,
        id: '',
        post: [],
        posts: [],
        pageNo: '',
        sortBy: '',
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
        getPostSuccess(state, post) {
            state.loaded = true;
            state.post = post;
        },
        getPostFailure(state) {
            state.loaded = false;
            state.post = [];
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
        getPost({ commit }, { id }) {
            return PostsService.getOnePostDetails(id).then(
                response => {
                    commit('getPostSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getPostFailure');
                    return Promise.reject(error)
                }
            )
        }
    },
};
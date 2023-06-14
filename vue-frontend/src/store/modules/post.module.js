import PostsService from "@/services/posts.service";

export const post = {
    state: {
        loaded: false,
        id: '',
        post: [],
        posts: [],
        pageNo: '',
        sortBy: '',
        categoryId: '',
        tagId: '',
        keyword: '',
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
        setKeywordSuccess(state, keyword) {
            state.keyword = keyword;
        },
        selectTagIdSuccess(state, tagId) {
            state.tagId = tagId;
        },
        selectCategoryIdSuccess(state, categoryId) {
            state.categoryId = categoryId;
        },
        updatePostSuccess(state) {
            state.loaded = true;
        },
        updatePostFailure(state) {
            state.loaded = false;
        }
    },
    actions: {
        updatePost({ commit }, editedPost) {
            console.log(editedPost)
            return PostsService.editOnePost(editedPost).then(
                response => {
                    commit('updatePostSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('updatePostFailure')
                    return Promise.reject(error);
                }
            )
        },
        setKeyword({ commit }, keyword) {
            commit('setKeywordSuccess', keyword);
        },
        setTag({ commit }, tagId) {
            commit('selectTagIdSuccess', tagId)
        },
        setCategory({ commit }, categoryId) {
            commit('selectCategoryIdSuccess', categoryId)
        },
        getPosts({ commit }, { pageNo, sortBy, categoryId, tagId , keyword}) {
            return PostsService.getAllPosts(pageNo, sortBy, categoryId, tagId, keyword).then(
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
        },
    },
    getters: {
        getKeyword: state => state.keyword,
        getTagId: state => state.tagId,
        getCategoryId: state => state.categoryId,
    },
};
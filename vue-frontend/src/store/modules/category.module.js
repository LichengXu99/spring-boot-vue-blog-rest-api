import CategoriesService from "@/services/categories.service";

export const category = {
    state: {
        loaded: false,
        categories: [],
        category: [],
    },
    mutations: {
        getCategoriesSuccess(state, categories) {
            state.loaded = true;
            state.categories = categories;
        },
        getCategoriesFailure(state) {
            state.loaded = false;
            state.categories = [];
        },
        getTagSuccess(state, category) {
            state.loaded = true;
            state.category = category;
        },
        getTagFailure(state) {
            state.loaded = false;
            state.category = [];
        },
    },
    actions: {
        getCategories({ commit }) {
            return CategoriesService.getAllCategories().then(
                response => {
                    commit('getCategoriesSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getCategoriesFailure');
                    return Promise.reject(error);
                }
            );
        },
        getCategory({ commit }, { id }) {
            return CategoriesService.getOneCategoryById(id).then(
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
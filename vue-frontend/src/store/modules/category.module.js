import CategoriesService from "@/services/categories.service";

export const category = {
    state: {
        loaded: false,
        categories: [],
        category: [],
        id: '',
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
        getCategorySuccess(state, category) {
            state.loaded = true;
            state.category = category;
        },
        getCategoryFailure(state) {
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
                    commit('getCategorySuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getCategoryFailure');
                    return Promise.reject(error)
                }
            )
        }
    },
};
import TagsService from "@/services/tags.service";

export const tag = {
    state: {
        loaded: false,
        tags: [],
        tag: [],
        id: '',
    },
    mutations: {
        getTagsSuccess(state, tags) {
            state.loaded = true;
            state.tags = tags;
        },
        getTagsFailure(state) {
            state.loaded = false;
            state.tags = [];
        },
        getTagSuccess(state, tag) {
            state.loaded = true;
            state.tag = tag;
        },
        getTagFailure(state) {
            state.loaded = false;
            state.tag = [];
        },
    },
    actions: {
        getTags({ commit }) {
            return TagsService.getAllTags().then(
                response => {
                    commit('getTagsSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getTagsFailure');
                    return Promise.reject(error);
                }
            );
        },
        getTag({ commit }, { id }) {
            return TagsService.getOneTagById(id).then(
                response => {
                    commit('getTagSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getTagFailure');
                    return Promise.reject(error)
                }
            )
        }
    },
};
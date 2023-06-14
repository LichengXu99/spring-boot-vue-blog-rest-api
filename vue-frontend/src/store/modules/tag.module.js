import TagsService from "@/services/tags.service";

export const tag = {
    state: {
        loaded: false,
        tags: [],
        tag: [],
        tagByKeyword: [],
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
        getTagByKeywordSuccess(state, tags) {
            state.loaded = true;
            state.tagByKeyword = tags;
        },
        getTagByKeywordFailure(state) {
            state.loaded = false;
            state.tagByKeyword = [];
        },
        createTagSuccess(state, newTag) {
            state.loaded = true;
        },
        createTagFailure(state) {
            state.loaded = false;
        },
    },
    actions: {
        getTags({ commit }) {
            return TagsService.getAllTags('').then(
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
                    return Promise.reject(error);
                }
            )
        },
        getTagByKeyword({ commit }, { keyword }) {
            return TagsService.getAllTags(keyword).then(
                response => {
                    commit('getTagByKeywordSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('getTagByKeywordFailure');
                    return Promise.reject(error);
                }
            )
        },
        saveNewTag({ commit }, { tagName }) {
            return TagsService.createNewTag(tagName).then(
                response => {
                    commit('createTagSuccess', response.data);
                    return Promise.resolve(response);
                },
                error => {
                    commit('createTagFailure')
                    return Promise.reject(error);
                }
            )
        }
    },
};
<template>


    <v-md-preview :text="markdown"></v-md-preview>
</template>

<script>
export default {
    name: "PostContent",
    data() {
        return {
            id: '',
            markdown: '',
            title: '',
        }
    },
    mounted() {
        this.id = this.$route.params.id;
        this.fetchContent();
    },

    methods: {
        fetchContent() {
            this.$store.dispatch('getPost', {id: this.id})
                .then(response => {
                    this.markdown = response.content;
                    this.title = response.title;
                })
                .catch(error => {
                    console.log(error)
                });
        }
    },
};
</script>

<style scoped>

</style>
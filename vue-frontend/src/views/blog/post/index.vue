<template>
    <v-md-preview :text="markdown"></v-md-preview>
</template>

<script>
export default {
    name: "Post",
    data() {
        return {
            markdown: '',
        }
    },
};
</script>

<style scoped>

</style>
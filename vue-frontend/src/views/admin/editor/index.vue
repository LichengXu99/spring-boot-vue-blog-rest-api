<template>
    <v-md-editor v-model="content" height="400px"/>
</template>

<script>
export default {
    name: "MarkdownEditor",
    data() {
        return {
            text: 'content',
        };
    },
};
</script>
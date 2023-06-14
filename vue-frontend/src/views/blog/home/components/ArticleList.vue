<template>

    <div class="search-conditions" v-if="tagId || categoryId || keyword">
        <span v-if="tagId || categoryId || keyword">當前搜尋條件：</span>
        <span v-if="tagId">標籤: {{ selectedTagName }}</span>
        <span v-if="categoryId">分類: {{ selectedCategoryName }}</span>
        <span v-if="keyword">關鍵字: {{ keyword }}</span>
        <span class="float-end"
              id="cleanUp"
              @click="cleanUp"
              v-if="tagId || categoryId || keyword">清空所有條件</span>
    </div>

    <div class="card single"
         v-for="post in posts"
         :key="post.id">
        <div class="category">
            {{ post.categoryName }}
        </div>
        <div class="body">
            <div class="img-post">
                <img class="d-block img-fluid" src="https://www.bootdey.com/image/800x280/FFB6C1/000000"
                     alt="封面">
            </div>
            <h3>{{ post.title }}</h3>
            <p>{{ post.description }}</p>
        </div>

        <div class="footer">
            <div class="actions">
                <router-link v-if="loggedIn" class="btn btn-outline-secondary" :to="'/editor/' + post.id">編輯文章</router-link>
                <router-link v-else class="btn btn-outline-secondary" :to="'/post/' + post.id">繼續閱讀</router-link>
            </div>
            <ul class="tags" v-for="(tag, index) in post.tags" :key="index">
                <li>{{ tag.name }}</li>
            </ul>
        </div>

    </div>

  <!-- Pagination -->
    <ul class="pagination pagination-primary">

        <li class="page-item">
            <button class="page-link"
                    :disabled="pageNum === 1"
                    @click="previousPage">
                Previous
            </button>
        </li>

        <li class="page-item"
            v-for="page in totalPages"
            :class="{ active: page === pageNum }"
            :key="page">
            <button class="page-link"
                    :disabled="pageNum === page"
                    @click="getPage(page)">
                {{ page }}
            </button>
        </li>

        <li class="page-item">
            <button class="page-link"
                    :disabled="last === true || pageNo === totalPages - 1"
                    @click="nextPage">
                Next
            </button>
        </li>

    </ul>
  <!-- Pagination -->

</template>

<script>
export default {
    name: 'ArticleList',
    data() {
        return {
            posts: [],
            pageNo: 0,
            sortBy: '',
            totalPages: '',
            last: false,
            selectedTagName: '',
            selectedCategoryName: '',
        }
    },
    computed: {
        pageNum() {
            return this.pageNo + 1;
        },
        keyword() {
            return this.$store.getters.getKeyword;
        },
        tagId() {
            return this.$store.getters.getTagId || '';
        },
        categoryId() {
            return this.$store.getters.getCategoryId || '';
        },
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },
    mounted() {
        this.fetchPosts();
    },
    watch: {
        keyword(newVal, oldVal) {
            this.fetchPosts();
        },
        tagId(newVal, oldVal) {
            this.fetchPosts();
            this.getTagName(this.tagId);
        },
        categoryId(newVal, oldVal) {
            this.fetchPosts();
            this.getCategoryName(this.categoryId);
        },
        '$route': {
            immediate: true,
            handler(to, from) {
                this.$store.dispatch('setTag', '');
                this.$store.dispatch('setCategory', '');
                this.$store.dispatch('setKeyword', '');
            }
        },
    },
    methods: {
        fetchPosts() {
            this.$store.dispatch('getPosts', {
                pageNo: this.pageNo,
                sortBy: this.sortBy,
                categoryId: this.categoryId,
                tagId: this.tagId,
                keyword: this.keyword,
            })
                .then(response => {
                        this.posts = response.content;
                        this.totalPages = response.totalPages;
                        this.last = response.last;
                    }, setTimeout(() => {
                        window.scroll({
                            top: 0,
                            left: 0,
                            behavior: 'smooth'
                        });
                    }, 500)
                )
                .catch(error => {
                    console.error(error);
                });
        },
        getPage(page) {
            this.pageNo = page - 1;
            this.fetchPosts();
        },
        previousPage() {
            if (this.pageNo !== 0) {
                this.pageNo--;
                this.fetchPosts();
            }
        },
        nextPage() {
            if (this.pageNum < this.totalPages) {
                this.pageNo++;
                this.fetchPosts();
            }
        },
        getTagName(tagId) {
            this.$store.dispatch('getTag', {id: tagId})
                .then(response => {
                    this.selectedTagName = response.name;
                })
                .catch(error => {
                    console.log(error);
                })
        },
        getCategoryName(categoryId) {
            this.$store.dispatch('getCategory', {id: categoryId})
                .then(response => {
                    this.selectedCategoryName = response.name;
                })
                .catch(error => {
                    console.log(error);
                })
        },
        cleanUp() {
            this.$store.dispatch('setTag', '');
            this.$store.dispatch('setCategory', '');
            this.$store.dispatch('setKeyword', '');
        },
    }
};

</script>

<style scoped>
body {
    background-color: #f4f7f6;
    margin-top: 20px;
}

.search-conditions {
    margin: 10px 0;
    padding: 10px;
    background-color: #f2f2f2;
}

.search-conditions span {
    margin-right: 10px;
    color: #848181;
}

#cleanUp {
    cursor: pointer;
}

#cleanUp:hover {
    color: #4a4a4a;
}

.card {
    background: #fff;
    transition: .5s;
    border: 0;
    margin-bottom: 30px;
    border-radius: .55rem;
    position: relative;
    width: 100%;
    box-shadow: 0 1px 2px 0 rgb(0 0 0 / 10%);
}

.card .body {
    color: #444;
    padding: 20px;
    font-weight: 400;
}

.single {
    -webkit-transition: all .4s ease;
    transition: all .4s ease
}

.single .body {
    padding: 30px
}

.single .img-post {
    position: relative;
    overflow: hidden;
    max-height: 500px;
    margin-bottom: 30px
}

.single .img-post > img {
    -webkit-transform: scale(1);
    -ms-transform: scale(1);
    transform: scale(1);
    opacity: 1;
    -webkit-transition: -webkit-transform .4s ease, opacity .4s ease;
    transition: transform .4s ease, opacity .4s ease;
    max-width: 100%;
    filter: none;
    -webkit-filter: grayscale(0);
}

.single .img-post:hover img {
    -webkit-transform: scale(1.02);
    -ms-transform: scale(1.02);
    transform: scale(1.02);
    opacity: .7;
    -webkit-filter: grayscale(1);
    -webkit-transition: all .8s ease-in-out
}

.single .footer {
    padding: 0 30px 30px 30px;
    flex-wrap: wrap;
    justify-content: space-between;
}

.single .category {
    padding: 5px 20px;
    border-radius: 5px 5px 0 0;
    background-color: rgba(171, 206, 221, 0.9);
    display: inline-block;
    color: rgb(0, 0, 0);
    text-align: right;
    font-weight: bold;
    font-family: "cwTeXFangSong", "Noto Serif TC", "黑體-繁", "微軟正黑體", serif;
}

.single .footer .actions {
    display: inline-block
}

.single .footer .tags {
    cursor: default;
    list-style: none;
    display: inline-block;
    float: right;
    margin: 0;
    line-height: 35px;
    background-color: #ffffff;
    border-radius: 5px;
    text-align: right;
}

.single .footer .tags li {
    border-left: solid 1px rgba(160, 160, 160, 0.3);
    display: inline-block;
    font-weight: 400;
    letter-spacing: 0.25em;
    line-height: 1;
    margin: 0 0 0 2em;
    text-transform: uppercase;
    font-size: 13px;
    border-radius: 5px;
    background-color: #bfbfbf;
    padding: 5px 10px;
    text-align: right;
}

.single .footer .tags li a {
    color: #777
}

.single .footer .tags li:first-child {
    border-left: 0;
    margin-left: auto;
}

.single h3 {
    font-size: 20px;
    text-transform: uppercase
}

.single h3 a {
    color: #242424;
    text-decoration: none
}

.single p {
    font-size: 16px;
    line-height: 26px;
    font-weight: 300;
    margin: 0
}

.single .blockquote p {
    margin-top: 0 !important
}

.single .meta li {
    display: inline-block;
    margin-right: 15px
}

.single .meta li a {
    font-style: italic;
    color: #959595;
    text-decoration: none;
    font-size: 12px
}

.single .meta li a i {
    margin-right: 6px;
    font-size: 12px
}

ul {
    padding-left: 0.5rem;
}

@media (max-width: 640px) {
    .blog-page .left-box .single-comment-box > ul > li {
        padding: 25px 0
    }

    .blog-page .single .footer .tags {
        float: none;
        margin-top: 10px
    }

    .blog-page .single .body,
    .blog-page .single .footer {
        padding: 30px
    }

    ul {
        padding-left: 0.1rem;
    }
}
</style>
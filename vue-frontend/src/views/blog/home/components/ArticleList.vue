<template>
    <div class="card single"
         v-for="post in posts"
         :key="post.id">
        <div class="body">
            <div class="img-post">
                <img class="d-block img-fluid" src="https://www.bootdey.com/image/800x280/FFB6C1/000000"
                     alt="First slide">
            </div>
            <h3><a href="blog-details.html">All photographs are accurate</a></h3>
            <p>{{ post.description }}</p>
        </div>
        <div class="footer">
            <div class="actions">
                <router-link  :to="'/post/' + post.id">繼續閱讀</router-link>
            </div>
            <ul class="category">
                <li>{{ post.category }}</li>
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
                    :disabled="last === 'true'"
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
            last: 'false',
        }
    },
    computed: {
        pageNum() {
            return this.pageNo + 1;
        },
    },
    mounted() {
        this.fetchPosts();
    },
    methods: {
        fetchPosts() {
            this.$store.dispatch('getPosts', {pageNo: this.pageNo, sortBy: this.sortBy})
                .then(response => {
                    this.posts = response.content;
                    this.totalPages = response.totalPages;
                    this.last = response.last;
                })
                .catch(error => {
                    console.error(error);
                });
        },
        getPage(page) {
            this.pageNo = page - 1;
            this.fetchPosts();
            setTimeout(() => {
                window.scrollTo(0, 0);
            }, 500);
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
    }
};

</script>

<style scoped>
body {
    background-color: #f4f7f6;
    margin-top: 20px;
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
    padding: 0 30px 30px 30px
}

.single .footer .actions {
    display: inline-block
}

.single .footer .category {
    cursor: default;
    list-style: none;
    padding: 0;
    display: inline-block;
    float: right;
    margin: 0;
    line-height: 35px
}

.single .footer .category li {
    border-left: solid 1px rgba(160, 160, 160, 0.3);
    display: inline-block;
    font-weight: 400;
    letter-spacing: 0.25em;
    line-height: 1;
    margin: 0 0 0 2em;
    padding: 0 0 0 2em;
    text-transform: uppercase;
    font-size: 13px
}

.single .footer .category li a {
    color: #777
}

.single .footer .category li:first-child {
    border-left: 0;
    margin-left: 0;
    padding-left: 0
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

@media (max-width: 640px) {
    .blog-page .left-box .single-comment-box > ul > li {
        padding: 25px 0
    }

    .blog-page .single .footer .category {
        float: none;
        margin-top: 10px
    }

    .blog-page .single .body,
    .blog-page .single .footer {
        padding: 30px
    }
}
</style>
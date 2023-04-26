<template>
        <div class="card" id="search">
            <div class="body search">
                <div class="input-group m-b-0">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-search"></i></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Search...">
                </div>
            </div>
        </div>

        <div class="card" id="profile">
            <div class="body widget">

            </div>

        </div>

        <div class="card" id="tag">
            <div class="header">
                <span id="tag_title">文章標籤</span>
            </div>
            <div class="body widget">
                <ul class="list-unstyled categories-clouds m-b-0">
                    <li><a href="javascript:void(0);">Java</a></li>
                    <li><a href="javascript:void(0);">Spring</a></li>
                    <li><a href="javascript:void(0);">Vue.js</a></li>
                    <li><a href="javascript:void(0);">Wordpress</a></li>
                    <li><a href="javascript:void(0);">Angular JS</a></li>
                    <li><a href="javascript:void(0);">Enterprise Mobility</a></li>
                    <li><a href="javascript:void(0);">Website Design</a></li>
                    <li><a href="javascript:void(0);">HTML5</a></li>
                    <li><a href="javascript:void(0);">Infographics</a></li>
                    <li><a href="javascript:void(0);">Wordpress Development</a></li>
                </ul>
            </div>
        </div>
</template>

<script>
export default {
    name: "SideBox"
}
</script>

<style scoped>
body{
    background-color: #f4f7f6;
    margin-top:20px;
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

#tag_title {
    font-size: 36px;
    color: #f1f1f1;
}

.card .body {
    color: #444;
    padding: 20px;
    font-weight: 400;
}
.card .header {
    color: #444;
    padding: 20px;
    position: relative;
    box-shadow: none;
}
.card h2 {
    color: #fff
}
.card a {
    text-decoration: none;
}

.single_post .body {
    padding: 30px
}

.single_post .img-post>img {
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

.single_post .img-post:hover img {
    -webkit-transform: scale(1.02);
    -ms-transform: scale(1.02);
    transform: scale(1.02);
    opacity: .7;
    -webkit-filter: grayscale(1);
    -webkit-transition: all .8s ease-in-out
}

.single_post .footer .category li {
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

.single_post .footer .category li a {
    color: #777
}

.single_post .footer .category li:first-child {
    border-left: 0;
    margin-left: 0;
    padding-left: 0
}

.single_post h3 {
    font-size: 20px;
    text-transform: uppercase
}

.single_post h3 a {
    color: #242424;
    text-decoration: none
}

.single_post p {
    font-size: 16px;
    line-height: 26px;
    font-weight: 300;
    margin: 0
}

.single_post .blockquote p {
    margin-top: 0 !important
}

.single_post .meta li {
    display: inline-block;
    margin-right: 15px
}

.single_post .meta li a {
    font-style: italic;
    color: #959595;
    text-decoration: none;
    font-size: 12px
}

.single_post .meta li a i {
    margin-right: 6px;
    font-size: 12px
}

.single_post2 .content .actions_sidebar a {
    display: inline-block;
    width: 100%;
    height: 60px;
    line-height: 60px;
    margin-right: 0;
    text-align: center;
    border-right: 1px solid #e4eaec
}

.right-box .categories-clouds li {
    display: inline-block;
    margin-bottom: 5px
}

.right-box .categories-clouds li a {
    display: block;
    border: 1px solid;
    padding: 6px 10px;
    border-radius: 3px
}

.right-box .instagram-plugin li {
    float: left;
    overflow: hidden;
    border: 1px solid #fff
}

.comment-reply li {
    margin-bottom: 15px
}

.comment-reply li:last-child {
    margin-bottom: auto
}

.comment-reply li h5 {
    font-size: 18px
}

.comment-reply li p {
    margin-bottom: 0;
    font-size: 15px;
    color: #777
}

.comment-reply .list-inline li {
    display: inline-block;
    margin: 0;
    padding-right: 20px
}

.comment-reply .list-inline li a {
    font-size: 13px
}

@media (max-width: 640px) {
    .blog-page .left-box .single-comment-box>ul>li {
        padding: 25px 0
    }

}
</style>
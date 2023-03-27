<!--<template>-->
<!--  <ul-->
<!--      v-loading="loading"-->
<!--      class="note-list"-->
<!--  >-->
<!--    <transition-group name="fade-list">-->
<!--      <li v-for="(item, index) in list" :key="index" class="list-item">-->
<!--        <div class="wrap-img">-->
<!--          <img :src="item.cover">-->
<!--        </div>-->
<!--        <div class="wrapper-meta">-->
<!--          <div class="avatar-wrapper">-->
<!--            <img class="user-avatar" :src="item.user.avatar">-->
<!--          </div>-->
<!--          <span class="right-solt">{{ formatDate(item.publishTime) }}</span>-->
<!--          <span class="active" @click="categoryClick(item.categoryId)">{{ item.categoryName }}</span>-->
<!--        </div>-->

<!--        <div class="content">-->
<!--          <router-link :to="'/article/' + item.id" class="title"><span v-if="item.original !== 1">【转载】</span>-->
<!--            {{ item.title }}-->
<!--          </router-link>-->
<!--          &lt;!&ndash;          <p class="abstract multi-ellipsis&#45;&#45;l3">{{ item.summary }}</p>&ndash;&gt;-->
<!--          <div class="tags-wrapper">-->
<!--            &lt;!&ndash;            <span&ndash;&gt;-->
<!--            &lt;!&ndash;                v-for="(tag, index2) in item.tagList"&ndash;&gt;-->
<!--            &lt;!&ndash;                :key="index2"&ndash;&gt;-->
<!--            &lt;!&ndash;                class="tag active btn"&ndash;&gt;-->
<!--            &lt;!&ndash;                @click="tagClick(tag.id)"&ndash;&gt;-->
<!--            &lt;!&ndash;            >&ndash;&gt;-->
<!--            &lt;!&ndash;              {{ tag.name }}&ndash;&gt;-->
<!--            &lt;!&ndash;            </span>&ndash;&gt;-->
<!--            &lt;!&ndash;          </div>&ndash;&gt;-->
<!--            &lt;!&ndash;          <div class="meta">&ndash;&gt;-->
<!--            &lt;!&ndash;            <span>{{ item.commentCount }}&ensp;评论</span>&ndash;&gt;-->
<!--            &lt;!&ndash;            <span>{{ item.likeCount }}&ensp;点赞</span>&ndash;&gt;-->
<!--            &lt;!&ndash;            <span>{{ item.collectCount }}&ensp;收藏</span>&ndash;&gt;-->
<!--            &lt;!&ndash;            <span>{{ item.viewCount }}&ensp;浏览</span>&ndash;&gt;-->
<!--          </div>-->
<!--        </div>-->
<!--      </li>-->
<!--    </transition-group>-->
<!--    <div v-show="list.length === 0 && !loading" class="list-empty">列表为空</div>-->
<!--  </ul>-->
<!--</template>-->
<template>
  <ul>

<li class="list-item">
  <div class="wrap-img">
    <img src="">
  </div>
  <div class="wrapper-meta">
    <div class="avatar-wrapper">
      <img class="user-avatar" src="https://unsplash.com/photos/WMD64tMfc4k">
    </div>
    <span class="right-solt">publishTime</span>
    <span class="active">categoryName</span>
  </div>

  <div class="content">
<!--    <router-link :to="'/article/' + item.id" class="title"><span v-if="item.original !== 1">【转载】</span>-->
<!--      {{ item.title }}-->
<!--    </router-link>-->
    <!--          <p class="abstract multi-ellipsis&#45;&#45;l3">{{ item.summary }}</p>-->
    <div class="tags-wrapper">
      <!--            <span-->
      <!--                v-for="(tag, index2) in item.tagList"-->
      <!--                :key="index2"-->
      <!--                class="tag active btn"-->
      <!--                @click="tagClick(tag.id)"-->
      <!--            >-->
      <!--              {{ tag.name }}-->
      <!--            </span>-->
      <!--          </div>-->
      <!--          <div class="meta">-->
      <!--            <span>{{ item.commentCount }}&ensp;评论</span>-->
      <!--            <span>{{ item.likeCount }}&ensp;点赞</span>-->
      <!--            <span>{{ item.collectCount }}&ensp;收藏</span>-->
      <!--            <span>{{ item.viewCount }}&ensp;浏览</span>-->
    </div>
  </div>
</li>

<div  class="list-empty">列表为空</div>
</ul>
</template>

<script>
export default {
  name: 'ArticleList'
}
</script>

<style scoped>

</style>
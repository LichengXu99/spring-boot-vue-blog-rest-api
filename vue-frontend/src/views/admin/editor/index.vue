<template>
  <div>
    <div class="row">
      <div class="col-md-12 mb-3">
        <div class="input-group">
          <label class="label-style" for="title">標題：</label>
          <input type="text" id="title" class="select-style" v-model="article.title" required>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12 mb-3">
        <div class="input-group">
          <label class="label-style" for="description">描述：</label>
          <textarea id="description" class="select-style" v-model="article.description" required></textarea>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12 mb-3">
        <div class="input-group">
          <label for="tag" class="label-style">標籤：</label>
          <div class="tag-input">
            <input type="text" v-model="newTag" placeholder="新增標籤" @input="searchTag" @keydown.enter="addTag">
            <button @click="addTag">新增</button>
          </div>

        </div>
        <ul v-if="showTagsSuggest && tagsSuggest.length > 0 && this.newTag !== ''" class="suggested-tags select-style">
          <li v-for="tag in filteredTagsSuggest" :key="tag.id" @click="selectSuggestedTag(tag)">{{ tag.name }}</li>
        </ul>
        <div class="input-group tag-list">
      <span v-for="tag in article.tags" :key="tag.id" class="tag">
        {{ tag.name }}
        <button @click="removeTag(tag.id)">X</button>
      </span>
        </div>
      </div>
    </div>

    <div class="input-group mb-3">
      <label for="category" class="label-style">類別：</label>
      <select id="category" v-model="article.categoryId" class="select-style" required>
        <option v-for="category in categories" :key="category.id" :value="category.id">{{ category.name }}</option>
      </select>
    </div>

    <button type="submit" class="submit-button-style" @click="updatePost">提交</button>

    <v-md-editor v-model="article.content" height="400px"/>
  </div>
</template>

<script>
export default {
  name: "MarkdownEditor",
  data() {
    return {
      article: '',
      newTag: '',
      categories: [],
      tagSet: new Set(),
      tagsSuggest: [],
    };
  },
  computed: {
    showTagsSuggest() {
      return this.tagsSuggest.length > 0 && this.newTag !== '';
    },
    filteredTagsSuggest() {
      return this.tagsSuggest.filter((tag) => {
        const normalizedTag = tag.name.toLowerCase().trim();
        return !this.tags.some((t) => t.name.toLowerCase().trim() === normalizedTag);
      });
    },
  },
  watch: {
    newTag(newValue) {
      if (newValue.trim() !== '') {
        this.searchTag();
      } else {
        this.tagsSuggest = [];
      }
    }
  },
  mounted() {
    this.id = this.$route.params.id;
    this.fetchContent();
    this.fetchCategories();
  },

  methods: {
    fetchContent() {
      this.$store
          .dispatch('getPost', {id: this.id})
          .then((response) => {
            this.article = response;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    fetchCategories() {
      this.$store
          .dispatch('getCategories')
          .then((response) => {
            this.categories = response;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    searchTag() {
      this.$store.dispatch('getTagByKeyword', {keyword: this.newTag})
          .then((response) => {
            this.tagsSuggest = response;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    addTag() {
      if (this.newTag.trim() !== "") {
        const normalizedTag = this.newTag.trim();
        const existingTag = this.article.tags.find(tag => tag.name.toLowerCase() === normalizedTag.toLowerCase());
        if (!existingTag) {
          this.$store.dispatch('saveNewTag', {tagName: this.newTag})
              .then((response) => {
                const newTagId = response.data.id;
                const newTagName = response.data.name;
                const newTag = {id: newTagId, name: newTagName};
                this.article.tags.push(newTag);
              })
              .catch((error) => {
                console.log(error);
              });
        }
      }
      this.newTag = '';
      this.tagsSuggest = [];
    },
    removeTag(tagId) {
      const tagIndex = this.article.tags.findIndex((tag) => tag.id === tagId);
      if (tagIndex !== -1) {
        const removedTag = this.article.tags.splice(tagIndex, 1)[0];
        this.tagSet.delete(removedTag.name);
      }
    },
    selectSuggestedTag(tag) {
      if (tag) {
        const existingTag = this.tags.find((t) => t.name.toLowerCase() === tag.name.toLowerCase());
        if (!existingTag) {
          const newTagId = this.tags.length + 1;
          const newTag = {id: newTagId, name: tag.name};
          this.tags.push(newTag);
        }
        this.selectedSuggestedTag = null;
      }
      this.newTag = "";
      this.tagsSuggest = [];
    },
    updatePost() {
      this.$store.dispatch('updatePost', this.article)
          .then(
              () => {
              this.$router.push('/post/' + this.article.id);
          })
          .catch(
              (error) => {
                console.log(error);
              }
          )
    }
  },
};
</script>

<style>
/* WordPress 風格標籤 */
.tag-input {
  display: flex;
  align-items: center;
  margin-bottom: 8px;
}

.tag-input input[type="text"] {
  padding: 6px;
  border: 1px solid #ddd;
  border-radius: 3px;
}

.tag-input button {
  margin-left: 8px;
  padding: 6px 12px;
  background-color: #0073aa;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.tag-list {
  margin-top: 8px;
}

.tag {
  display: inline-block;
  margin-right: 8px;
  padding: 4px 8px;
  background-color: #f0f0f0;
  color: #333;
  border-radius: 3px;
}

.tag button {
  margin-left: 4px;
  padding: 2px;
  background-color: transparent;
  color: #888;
  border: none;
  cursor: pointer;
}

.label-style {
  font-weight: bold;
  color: #333;
}

.select-style {
  width: 100%;
  padding: 6px 12px;
  border: 1px solid #ddd;
  border-radius: 3px;
  background-color: #fff;
  color: #333;
}

.submit-button-style {
  width: 100%;
  padding: 10px 20px;
  background-color: #0073aa;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.submit-button-style:hover {
  background-color: #005d8c;
}

/* Tag suggestion dropdown menu */
.suggested-tags {
  position: absolute;
  z-index: 1;
  list-style: none;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 3px;
  padding: 0;
  margin: 0;
  width: 100%;
}

.suggested-tags li {
  padding: 4px 8px;
  cursor: pointer;
}

.suggested-tags li:hover {
  background-color: #f0f0f0;
}
</style>

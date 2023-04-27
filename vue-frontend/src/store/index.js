import { createStore } from "vuex";
import {auth} from '@/store/modules/auth.module'
import {post} from "@/store/modules/post.module";
import {tag} from "@/store/modules/tag.module";
import {category} from "@/store/modules/category.module";


const store = createStore({
  modules: {
    auth,
    post,
    tag,
    category,
  },
});

export default store;
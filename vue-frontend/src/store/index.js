import { createStore } from "vuex";
import {auth} from '@/store/modules/auth.module'
import {post} from "@/store/modules/post.module";


const store = createStore({
  modules: {
    auth,
    post
  },
});

export default store;
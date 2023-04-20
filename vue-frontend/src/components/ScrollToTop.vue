<template>
    <div v-on:scroll.passive="onScroll">
        <button v-if="!isVisible"
                v-bind:class="{ 'scroll-button-active': isAtTopOrBottom }"
                id="to-bottom"
        >
            <i class="fa-solid fa-chevron-down"></i>
        </button>

        <button v-else
                @click="toTop"
                v-bind:class="{ 'scroll-button-active': isAtTopOrBottom }"
        >
            <i class="fa-solid fa-chevron-up"></i>
        </button>

    </div>
</template>

<script>
export default {
    name: "ScrollToTop",
    data() {
        return {
            isVisible: false,
        };
    },
    mounted() {
        window.addEventListener('scroll', this.onScroll, { passive: true });
    },
    methods: {
        onScroll() {
            this.isVisible = window.scrollY > 50;
        },
        toTop() {
            window.scrollTo({
                top: 0,
                left: 0,
                behavior: "smooth",
            });
        },
    },
    computed: {
        isAtTopOrBottom() {
            const scrollPosition = window.pageYOffset || document.documentElement.scrollTop;
            const windowHeight = window.innerHeight;
            const bodyHeight = document.body.scrollHeight;

            const isAtTop = scrollPosition < windowHeight / 2;
            const isAtBottom = scrollPosition + windowHeight >= bodyHeight - windowHeight / 2;

            return isAtTop || isAtBottom;
        }
    }
};
</script>

<style scoped>
button {
    position: fixed;
    right: 20px;
    bottom: 20px;
    background-color: #1d55b2;
    color: white;
    border: none;
    border-radius: 50%;
    width: 40px;
    height: 40px;
    font-size: 24px;
    cursor: pointer;
    transition: opacity 0.2s ease-in-out;
}

button:hover {
    opacity: 0.8;
}

button:focus {
    outline: none;
}

button i {
    display: block;
    margin-top: 5px;
}

#to-bottom {
    display: none;
}
</style>

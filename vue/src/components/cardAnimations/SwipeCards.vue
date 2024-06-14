<!-- <template>
        <div class="swipe-container" v-touch:swipeLeft="swipeLeft" v-touch:swipeRight="swipeRight">
        <RestaurantCard v-for="(restaurant, index) in shuffledRestaurants"
                                :key="restaurant.id"
                                :restaurant="restaurant"
                                :class="{ active:index === currentCardIndex }"/>

        </div>
</template>

<script>
import RegisterView from '../views/RegisterView.vue';   
import Vue2TouchEvents from 'vue2-touch-events'

export default {
    components: {
        RestaurantCard
    },
    data() {
        return {
            shuffledRestaurants: this.shuffleArray(this.restaurants),
            currentCardIndex: 0
        };
    },
    methods: {
        shuffleArray(array) {
            for (let i = array.length -1; i > 0; i--) {
                const j = Math.floor(Math.random() * (i + 1));
                [array[i], array[j]] = [array[j], array[i]];
            }
            return array;
        },
        swipeLeft() {
            if (this.currentCardIndex < this.shuffledRestaurants.length -1) {
                this.currentCardIndex ++
            }
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.$el.addEventListener('touchstart', this.handleTouchStart);
            this.$el.addEventListener('touched', this.handleTouchEnd);
        });
    },
    beforeDestory() {
        this.$el.removeEventListener('touchstart', this.handleTouchStart);
        this.$el.removeEventListener('touched', this.handleTouchEnd);
    }
}

</script>

<style scoped>
.swipe-container {
    position: relative;
    width: 100%;
    height: 100%;
}
.card {
    position: absolute;
    width: 100%;
    height: 100%;
    transition: transform 0.5s, opacity 0.5s;
}
.card-active {
    z-index: 1;
}

</style> -->
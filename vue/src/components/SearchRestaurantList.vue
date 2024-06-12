<!-- <template> 
    <div class="search-restaurant-list">
        <RestaurantList :restaurants="restaurants" />
        </div></template>

        
<script>
import { mapState, mapActions } from 'vuex';
import RestaurantList from './RestaurantList.vue';



export default {
    components: {
    RestaurantList
},

props: {
        zipcode: String
    },
    computed : {
        ...mapState(['restaurants']), 

        },
    methods: {
        ...mapActions(['fetchRestaurants']), 
},
    mounted() {
        this.fetchRestaurants({ zipcode: this.zipcode, limit: 10 });
    }

};
</script>

<style scoped>
.cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}
</style> -->
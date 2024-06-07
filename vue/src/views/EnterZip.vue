<!-- <template>

    this is now in the home page
    <div class="calculate-zip">
        <h2> Enter your ZIP Code </h2>
        <form @submit.prevent="setZipCode">

            <input type="text" v-model="zipCode"
        placeholder="Enter Zip Code" />
        <button type="submit">Submit</button>
        </form>
        <div v-if="error" class="error">{{ error }}</div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            zipCode:'',
            //error: ''
        };
    },
    methods: {
        setZipCode() {
            if (this.zipCode) {
                this.$store.commit('SET_ZIP_CODE', this.zipCode);
                this.$router.push({ name: 'RestaurantList' });
            } //else {
                //this.error = 'Please enter a valid ZIP Code.';
            //}
        }
    }
};
</script>
 -->

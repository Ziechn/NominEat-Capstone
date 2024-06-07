<template>
    <div class="restaurant-details">
            <h2 class="restaurant-name">{{ restaurant.name }}</h2>

            <p class="restaurant-category">{{ restaurant.category }}</p>
     z       <p class="restaurant-categories">{{ restaurant.categories }}</p>
            <p class="restaurant-price">{{ restaurant.price }}</p>
            <p class="restaurant-address1">{{ restaurant.address1 }}</p>
            <p class="restaurant-city">{{ restaurant.city }}</p>
            <p class="restaurant-state">{{ restaurant.state }}</p>
            <p class="restaurant-zipcode">{{ restaurant.zipcode }}</p>
    </div>
</template>

<script>
    export default {
        props: {
            restaurant: {
            type: Object,
            required: true,
        },
    },
    };
</script>
<style scoped>
.restaurant-details {
    padding: 20px;
    background-color: var(--bg1100);
    color: var(--text-100);
}
.restaurant-name {
    font-size: 24px;
    font-weight: bold;
    color: var(--text-100);
}


</style>
<!-- MVP: hours of operation - store and card(back) need, open and closed data -store needs card has, call to order store and card(back) need
Nice to Haves: Num of stars, map, takeout.delivery option shown  -->
<template>
    <div class="restaurant-card" @click="flipCard">
        <div class="card-inner" :class="{ flipped: isFlipped, enlarged: isHovered }">

            <div class="card-front" aria-label="restaurant-card-front-of-card">
                <img :src="restaurant.imageUrl" :alt="restaurant.name" class="restaurant-image" />
                <div class="restaurant-info">
                    <h2 class="restaurant-name" >{{ restaurant.name }} </h2>
                    <p class="restaurant-category">
                        <span v-for="(category, index) in restaurant.categories" v-bind:key="index">
                            {{ category }}<span v-if="index < restaurant.categories.length - 1">, </span>
                        </span>
                    </p>
                    <p class="restaurant-price" >{{ restaurant.price }}</p>
                    <p class="restaurant-rating" >{{ restaurant.rating }}</p>
                </div>
            </div>
            <div class="card-back" aria-label="restaurant-back">
                <div class="restaurant-info">
                    <h2 class="restaurant-name">{{ restaurant.name }}</h2>
                    <p class="restaurant-category">{{ restaurant.categories }}</p>
                    <p class="restaurant-price" >{{ restaurant.price }}</p>
                    <p class="restaurant-address" >{{ restaurant.address1 }}</p>
                    <p class="restaurant-status" >{{ restaurant.isOpenNow ? 'Open now' : 'Closed' }}</p>
                    <a :href="restaurant.menuUrl" target="_blank" class="menu-link"> View Menu</a>
                </div>
            </div>
        </div>
        <!-- <div v-if="isEnlarged" class="overlay" @click="enlargeCard"> -->
    </div>
    <!-- </div> -->
</template>


<script>
export default {
    props: {
        restaurant: Object
    },

    data() {
        return {
            isFlipped: false,
            isHovered: false,
            isEnlarged: false
        };
    },
    methods: {
        flipCard() {
            this.isFlipped = !this.isFlipped;
        },
        // enlargeCard() {
        //     this.isEnlarged = !this.isEnlarged;
        //     if (this.isEnlarged) {
        //         document.body.classList.add('blur-background');
        //     } else {
        //         document.body.classList.remove('blur-background');
        //     }
        //   }
    }
}
</script>

<style scoped>
/* .restaurant-card-wrapper {
        position: relative;
        perspective: 1000px;
        transition: transform 0.3s ease-in-out;
    } */
.restaurant-card {
    perspective: 1000px;
    width: 250px;
    height: 300px;
    margin: 20px;
    cursor: pointer;
    transition: transform 0.3s;
}

.card-inner {
    width: 100%;
    height: 100%;
    transition: transform 0.6s, transform 0.3s;
    transform-style: preserve-3d;
    position: relative;
    color: var(--primary-100);
}

.card-inner.flipped {
    transform: rotateY(180deg);
}

.card-inner.enlarged {
    position: fixed;
    top: 50%;
    left: 50%;
    transition: translate(-50%, -50%) scale(1.5);
    z-index: 1000;
    background-color: white;
    box-shadow: 0 4px 8px rgb(0, 0, 0, 0.3);
}

/*.card-inner.enlarged {
        transform: scale(1.1);
    }*/

.restaurant-card:hover {
    transform: scale(1.05);
}

/* .restaurant-card.flipped {
        transform: rotateY(180deg);
    } */
.card-front,
.card-back {
    backface-visibility: hidden;
    position: absolute;
    width: 100%;
    height: 100%;
    border: 1px solid #ccc;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0, 1);
}

.card-front {
    background-color: #fff;
}

.card-back {
    background-color: white;
    transform: rotateY(180deg);
    padding: 15px;
}

.restaurant-image {
    width: 100%;
    height: 150px;
    object-fit: cover;
}

.restaurant-info {
    padding: 15px;
    text-align: center;
}

.restaurant-name {
    font-size: 1.2em;
    margin: 5px 0;
    color: var(--text-100);
}

.restaurant-category {
    font-size: .09em;
    color: var(--text-200);
}

.restaurant-rating {
    font-size: .09em;
    color: var(--text-200);
}


.menu-link {
    margin-top: 20px;
    text-decoration: none;
    color: var(--text-100);
    font-weight: bold;
}

.open {
    color: var(--accent-100);
}

.closed {
    color: var(--primary-300);
}

.restaurant-card-wrapper.enlarged .restaurant-card {
    width: 500px;
    height: 700px;
    z-index: 10001;
    transform: scale(1.5);
}

.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    z-index: 1000;
}

.blur-background {
    filter: blur(5px);
    pointer-events: none;
}

a {
    color: var(--primary-100);
    text-decoration: none;
    font-weight: bold;
}

a:hover {
    text-decoration: underline;
}
</style>
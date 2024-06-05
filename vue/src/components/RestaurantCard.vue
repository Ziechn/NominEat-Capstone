
<!-- Goal is to split the card into two divs - one for each side -Lines -->
<template> 
<div class="restaurant-card" @mouseover="isHovered = true" @mouseleave="isHovered = false" @click="flipCard" @dblclick="enlargeCard">
    <div class="card-inner" :class="{ flipped: isFlipped, enlarged: isEnlarged }">

        <div class="card-front">
            <img :src="restaurant.imageUrl" :alt="restaurant.name" class="restaurant-image" /> 
            <div class="restaurant-info">
            <h2 class="restaurant-name">{{ restaurant.name }}</h2>
            <p v-if="restaurant.categories && restaurant.categories.length"  class="restaurant-category">
                {{ restaurant.categories[0].title }}
             </p>
             <p class="restaurant-price">{{  restaurant.price }}</p>
            <p class="restaurant-rating">{{ restaurant.rating }}</p>    
        </div>
    </div>
        <div class="card-back">
            <div class="details">
                <h2>{{  restaurant.name }}</h2>
                <p>{{ restaurant.price }}</p>
              <p>{{ restaurant.location.address1 }}</p> 
          
          <!-- <p :class="{ open: restaurant.isOpenNow, closed: !restaurant.isOpenNow }"> 

            {{  restaurant.isOpenNow ? 'Open now' : 'Closed' }}
        </p>  -->
        <a :href="restaurant.menuUrl" target="_blank" class="menu-link"> View Menu</a>
 </div>
</div>
</div>
<div v-if="isEnlarged" class="overlay" @click="enlargeCard">
</div> 
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
    data() { 
        return {
            isFlipped: false,
            isEnlarged: false,
        };

},
methods: {
    flipCard() {
        this.isFlipped = !this.isFlipped;
    },
    enlargeCard() {
        this.isEnlarged = !this.isEnlarged;
        if (this.isEnlarged) {
            document.body.classList.add('blur-background');
        } else {
            document.body.classList.remove('blur-background');
        }
    },
},

};
</script>

<style scoped>

    /* .restaurant-card-wrapper {
        position: relative;
        perspective: 1000px;
        transition: transform 0.3s ease-in-out;
    } */
    .restaurant-card {
        perspective: 1000px;
        border: 1px solid #ccc;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        transition: transform 0.2s;
        width: 250px;
        height: 300px;
        margin: 20px;
        cursor: pointer;
    }

    .card-inner {
        width: 100%;
        height: 100%;
        transition: transform 0., transform 0.3s;
        transform-style: preserve-3d;
        position: relative;
    }

    .card-inner.flipped {
        transform: rotateY(180deg);
    }

    /* .card-inner.enlarged {
        position: fixed;
        top: 50%;
        left: 50%;
        transition: translate(-50%, -50%) scale(1.5);
        z-index: 1000;
        background-color: white;
        box-shadow: 0 4px 8px rgb(0,0,0,0.3);
    } */

    .restaurant-card:hover {
        transform: scale(1.05);
    }

    .restaurant-card.flipped {
        transform: rotateY(180deg);
    }
    .card-front,
    .card-back {
        backface-visibility: hidden;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        border: 1px solid #ccc;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0,0,0,0,1);
    }

    .card-front {
        background-color: #fff;
        display: flex;
        flex-direction: column;
    }
    .card-back {
        background-color: white;
        transform: rotateY(180deg);
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        padding: 215x;
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

  
    .details h2 {
        margin-bottom: 10px;
        color: var(--text-100);
    }

    .details p {
        margin: 5px 0;
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
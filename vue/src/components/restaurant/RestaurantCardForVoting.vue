<template>
    <div class="restaurant-card" @mouseover="isHovered = true" @mouseleave="isHovered = false" 
    @click="flipCard">

        <div class="card-inner" :class="{ flipped: isFlipped, enlarged: isHovered, blurred: isVoted }">
            <div class="card-front" aria-label="restaurant-card-front-of-card">
                <img :src="restaurant.imageUrl" :alt="restaurant.name" class="restaurant-image" />
                <div class="vote-buttons">
                 <VoteButtons :eventId="eventId" :restaurantId="restaurant.id" class="vote-buttons" @vote="applyBlur" />
                </div>
                <div class="restaurant-info">
                    <h2 class="restaurant-name-front" >{{ restaurant.name }}</h2>
                    <p class="restaurant-category"> 
                        <span v-for="(category, index) in restaurant.categories" v-bind:key="index">
                            {{ category }}<span v-if="index < restaurant.categories.length - 1">, </span>
                        </span>
                    </p> 
                
            </div>
        </div>

    <!-- <div class="restaurant-card">
         <h3>{{  restaurant.name }}</h3>
        <p>{{  restaurant.type }}</p>
        <p>{{  restaurant.address1 }}</p> -->
        <div class="card-back" aria-label="restaurant-back">
                <div class="restaurant-info" id="back-info">
                    <h2 class="restaurant-name">{{ restaurant.name }}</h2>
                    <p class="restaurant-category">
                        <span v-for="(category, index) in restaurant.categories" v-bind:key="index">
                            {{ category }}<span v-if="index < restaurant.categories.length - 1">, </span>
                        </span>
                    </p>
                    <p class="restaurant-address" >{{ restaurant.address1 }}</p>
                    <p class="restaurant-status" >{{ restaurant.isOpenNow ? 'Open now' : 'Closed' }}</p>
                    <p class="restaurant-price" >{{ restaurant.price }}</p>
                    
                    <div class="restaurant-hours">
                    <div  v-for="(hours, index) in restaurant.hours" v-bind:key="index" class="hours-row" >
                        <span>{{ getDayByDayNum(hours.day) }}</span>
                        <span>{{ formatHours(hours.start, hours.end) }}</span>
                    </div>  
                    </div>
                    <div class="menu">
                    <a :href="restaurant.menuUrl" target="_blank" class="menu-link"> View Menu</a></div>
                    <!-- <button class="call-button" v-if="restaurant.phoneNumber !== null" @click.stop="showNumber">Call to order</button>
                    <div v-if="isVisible"> {{ restaurant.phoneNumber }} </div>
       -->
                </div>
            </div>
       </div>



    </div>
</template>

<script>
import VoteButtons from '@/components/restaurant/VoteButtons.vue';

export default {
    components: { VoteButtons },
    props: 
        ['restaurant', 'eventId', ],
        // 'voted' could pass ina  voted Boolean

        data() {
        return {
            isFlipped: false,
            isHovered: false,
            isVisible: false,
            isVoted: false
        };
    },
    methods: {
        flipCard() {
            this.isFlipped = !this.isFlipped;
        },
        showNumber() {
            this.isVisible = !this.isVisible;
        },
        selectRestaurant() {
            this.$emit('selectRestaurant', this.restaurant);
        },
        formatTime(time) {
        let hour = parseInt(time.substring(0, 2), 10);
        const minute = time.substring(2, 4);
        const ampm = hour >= 12 ? 'PM' : 'AM';
        hour = hour % 12 || 12; 
        return `${hour}:${minute} ${ampm}`;
        },
        formatHours(start, end) {
            return `${this.formatTime(start)} - ${this.formatTime(end)}`
        },
        getDayByDayNum(dayNum){
            const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday','Sunday'];
            return days[dayNum];
        },
        applyBlur() {
            this.isVoted = true;
        }


}
};

</script>

<style scoped>
.vote-buttons {
    display: flex;
    justify-content: space-between;
    padding-right: 6vw; 
    padding-left: 1vw;
    width: 120px;
    margin: 0 auto;
}

.restaurant-card {
    perspective: 1000px; 
    position: relative;
    width: 300px;
    height: 400px;
    margin: 20px;
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
    display: grid;
    align-items: center;
    transition: opacity 0.3s;
}
.restaurant-card:hover {
    transform: scale(1.05);
}
/* 
.restaurant-card.voted{
    opacity: 0.5;
} */
.card-inner {
    width: 100%;
    height: 100%;
    transition: transform 0.6s;
    transform-style: preserve-3d;
    position: relative;
} 

.card-inner.flipped {
    transform: rotateY(180deg);
}

.card-inner.blurred {
    filter: blur(5px);
    
}

.card-front,
.card-back {
    backface-visibility: hidden;
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    background-color: var(--bg-100);
    overflow: hidden;
    display: flex;
    flex-direction: column;
} 

.card-front{
    justify-content: space-between;
}

.card-back {
    align-items: center;
    transform: rotateY(180deg);
     padding: 15px;
    display: grid;
    grid-template-rows: auto 1fr auto;
    gap: 10px; 
}

#back-info {
    text-align: center;
}

.restaurant-image {
    width: 100%;
    height: auto;
    aspect-ratio: 16/9;
    object-fit: cover;
    border-radius: 10px 10px 0 0;
}


.restaurant-name {
    font-size: 1.2em;
    margin: 5px 0;
    color: var(--primary-100);
    text-align: center;
    margin-top: 0;
    padding-top: 0;

}

.restaurant-name-front {
    font-size: 1.2em;
    margin: 5px 0;
    color: var(--text-100);
}
.restaurant-info {
    padding: 10px;
    text-align: center;
}


.restaurant-category,
.restaurant-rating,
.restaurant-status,
.restaurant-address {
    font-size: .9em;
    color: var(--text-200);
}

.restaurant-price {
    font-size: .9em;
    color: var(--accent-100);
}
.hours-row {
    text-align: center;
    display: flex;
    justify-content: space-between;
    width: 100%;
    font-size: 0.9em;
    height: 100%;
}
.menu {
    padding-top: 30px;
}
/* .restaurant-hours {
    font-size: 0.8em;
    margin:0px;
    color: var(--text-200);
    border-top: 1px solid var(--bg-200);
    border-bottom: 1px solid var(--bg-200);
    padding: 10px 0;
    width: 100%;
    text-align: left;
} */
.menu-link {
    margin-top: 20px;
    font-size: 20px;
    text-decoration: none;
    color: var(--text-200);
}
.menu-link:hover {
    color: var(--accent-100);
}
/* .call-button {
    font-size: 20px;
    margin: 10px;
    background-color: var(--bg-100);
    border: none;
    cursor: pointer;
    color: var(--text-200);
    padding-top: 20px;
    border-radius: 5px;
} */
.select-button {
    margin: 10px;
    background-color: var(--bg-100);
    border: none;
    cursor: pointer;    
    color: var(--accent-200);
    padding-top: 0px;
    border-radius: 5px;
    font-weight: bold;
    font-size: large;
}
.select-button:hover {
    color: var(--accent-100);
    font-size: x-large;
}

.call-button:hover {
    color: var(--accent-100);
    border-color: var(--accent-100);
 }
 .vote-buttons {
    display: flex;
    justify-content: space-between;
    width: 100%;
    margin-top: 10px;
    padding: none;
 }
 

 
/* .open {
    color: var(--accent-100);
}

.closed {
    color: var(--primary-300);
} */

/* .restaurant-card-wrapper.enlarged .restaurant-card {
    width: 500px;
    height: 700px;
    z-index: 10001;
    transform: scale(1.5);
} */

/* .overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    z-index: 1000;
} */

</style>
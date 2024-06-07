<template>
    <div class="flip-card">
        <div class="card-inner"  :class="{ flipped: isFlipped }">

            <div class="card-front">
                <slot name="front"></slot>
            </div>

            <div class="card-back">
                <slot name="back"></slot>
            </div>


        </div>
    </div>
</template>

<script>
export default {
    props: {
        restaurant: Object
    },

    data() {
        return {
            isFlipped: false
        };
    },
    methods:  {
        flipCard() {
            this.isFlipped = !this.isFlipped;
        }
    }
};
</script>

<style scoped>
    .flip-card {
        perspective: 1000px; 
    width: 300px;
    height: 400px;
    }

    .card-inner {
    width: 100%;
    height: 100%;
    transition: transform 0.6s 0.3s;
    transform-style: preserve-3d;
    position: relative; 
    }

    .card-inner.flipped {
    transform: rotateY(180deg);
    }

    
.card-front,
.card-back {
    backface-visibility: hidden;
    position: absolute;
    width: 100%;
    height: 100%;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: var(--bg-100);
    overflow: hidden;
    box-shadow: 0 4px 8px rgba(0, 0, 0, .3);
    display: flex;
    align-items: center;
    justify-content: center;
}
    
.card-back {
    transform: rotateY(180deg);
}


    </style>
<!-- <template>
    <div class="event-creation-confirmation">
        <div class="confirmation-content">
            <h2>Event Created Successfully!</h2>
            <p>Your event has been created. You can share this link with others to invite them:</p>
            <p><router-link :to="{ name: 'Voting', query: { eventLink } }">{{ eventLink }}</router-link></p>
            <button @click="$emit('close')">Close</button>
            <router-link to="/" class="home-link">Return to Home</router-link>
      
        </div>
    </div>
</template>

<script>
export default {
    props: ['eventLink'],
};
</script>

<style scoped>
.event-creation-confirmation {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.confirmation-content {
    background: white;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
}

.confirmation-content p {
    margin: 10px 0;
}

.confirmation-content .home-link {
    display: block;
    text-decoration: none;
    color:grey;

}

.confirmation-content .home-link:hover {
    display: block;
    text-decoration: none;
    color:red;

}
</style> -->
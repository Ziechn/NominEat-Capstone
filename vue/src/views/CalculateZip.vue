<template>
    <div class="calculate-zip">
        <h2> Calculating your location... </h2>
        <div v-if="loading" class="loading">Loading...please wait...</div>
        <div v-if="error" class="error">{{ error }}</div>
    </div>
</template>

<script>
// import axios from 'axios';

// export default {
//     data() {
//         return {
//             loading: true,
//             error: ''
//         };

//     }
//}
</script>
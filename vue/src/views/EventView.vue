MAIN VIEW FOR EVENT DETAILS AND RESTAURANT LIST
    <template>
    <div>
        <!-- <HeaderComp /> -->
        <EventInformation :eventId="eventId" />
        <EventRestaurantList :eventId="eventId" />
    </div>
</template>

<script>
//import HeaderComp from '@/components/HeaderComp.vue';
import EventInformation from '@/components/event/EventInformation.vue'
import EventRestaurantList from '@/components/event/EventRestaurantList.vue'


export default {
    components: {
        //HeaderComp,
        EventInformation,
        EventRestaurantList
    },
    props: {
        eventId: String
    }
};
</script>
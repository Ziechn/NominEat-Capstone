import { createRouter as createRouter, createWebHistory } from 'vue-router';
import { useStore } from 'vuex';

// Import components
import Home from '@/views/Home.vue';
import LoginAndZip from '@/views/LoginAndZip.vue';
import RestaurantSearch from '@/views/RestaurantSearch.vue';
import EventView from '@/views/EventView.vue';
import RegisterView from '@/views/RegisterView.vue';
import ProfileView from '@/views/ProfileView.vue';
//import EventCreationPopup from '@/components/EventCreationPopup.vue';
import CreateEvent from '@/components/event/CreateEvent.vue';
import Vote from '@/views/Vote.vue';
import LogoutView from '@/views/LogoutView.vue';





/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginAndZip,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/restaurants/search',
    name: 'RestaurantSearch',
    component: RestaurantSearch,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/event/:id', 
    name: 'EventView',
    component: EventView,
    props: true
  },
  {
    path: '/vote/:id', 
    name: 'Vote',
    component: Vote,
    props: true
    },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/create-event',
    name: 'CreateEvent',
    component: CreateEvent
  },
  {
    path: '/profile',
    name: 'profile',
    component: ProfileView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  }
  
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;

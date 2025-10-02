import { createRouter, createWebHistory } from 'vue-router'
import JobsView from '@/views/JobsView.vue'
import JobsHistoryView from '@/views/JobsHistoryView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/jobs', component: JobsView },
    { path: '/history', component: JobHistoryView }
  ],
})

export default router

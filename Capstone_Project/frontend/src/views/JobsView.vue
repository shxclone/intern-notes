<script setup>
import { ref, onMounted } from 'vue'
import { getJobs, triggerJob } from '@/services/jobService'
import JobForm from '@/components/JobForm.vue'

const jobs = ref([])
const loading = ref(true)
const showForm = ref(false)
const selectedJob = ref(null)

onMounted(async () => {
  jobs.value = await getJobs()
  loading.value = false
})

const runNow = async (id) => {
  await triggerJob(id)
  alert("Job triggered successfully")
}

const editJob = (job) => {
  selectedJob.value = job
  showForm.value = true
}

const reloadJobs = async () => {
  jobs.value = await getJobs()
  showForm.value = false
  selectedJob.value = null
}
</script>

<template>
  <div>
    <h2>Jobs</h2>
    <div v-if="loading">Loading...</div>
    <table v-else border="1" cellpadding="5">
      <thead>
        <tr>
          <th>Code</th>
          <th>Name</th>
          <th>Status</th>
          <th>Last Trigger</th>
          <th>Next Trigger</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="job in jobs" :key="job.id">
          <td>{{ job.jobCode }}</td>
          <td>{{ job.jobName }}</td>
          <td>{{ job.status }}</td>
          <td>{{ job.lastTriggeredDate }}</td>
          <td>{{ job.nextTriggerDate }}</td>
          <td>
            <button @click="runNow(job.id)">Run Now</button>
            <button @click="editJob(job)">Edit</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Modal form -->
    <JobForm
      v-if="selectedJob"
      :job="selectedJob"
      :show="showForm"
      @saved="reloadJobs"
      @close="showForm=false"
    />
  </div>
</template>

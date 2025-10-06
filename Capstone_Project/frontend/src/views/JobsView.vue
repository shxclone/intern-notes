<script setup>
import { ref, onMounted, nextTick } from 'vue'
import { getJobs, getJob, triggerJob } from '@/services/jobService'
import JobForm from '@/components/JobForm.vue'

const jobs = ref([])
const loading = ref(true)

const selectedJob = ref({})
const showForm = ref(false)

const formatDate = (dateStr) => {
  if (!dateStr) return '-'
  return new Date(dateStr).toLocaleString()
}

const handleRunNow = async (jobId) => {
  try {
    await triggerJob(jobId)
    alert(`Job ${jobId} triggered successfully!`)

    setTimeout(async () => {
      jobs.value = await getJobs()
    }, 1500)
  } catch (err) {
    console.error(err)
    alert(`Failed to run job ${jobId}`)
  }
}

const handleEdit = async (jobId) => {
  try {
    const job = await getJob(jobId)      
    selectedJob.value = job || {}       
    await nextTick()  
    showForm.value = true
  } catch (err) {
    console.error(err)
    alert(`Failed to load job ${jobId}`)
  }
}

const handleSaved = async () => {
  // refresh jobs after saving
  jobs.value = await getJobs()
  showForm.value = false
}

onMounted(async () => {
  try {
    jobs.value = await getJobs()
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div>
    <h2>Jobs</h2>
    <div v-if="loading">Loading...</div>
    <div v-else class="table-wrapper">
      <table class="styled-table">
        <thead>
          <tr>
            <th>Job Code</th>
            <th>Job Name</th>
            <th>Status</th>
            <th>Active</th>
            <th>Job Frequency</th>
            <th>Last Trigger</th>
            <th>Next Trigger</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="job in jobs" :key="job.id">
            <td>{{ job.jobCode }}</td>
            <td>{{ job.jobName }}</td>
            <td><span :class="['status', job.status]">{{ job.status }}</span></td>
            <td>{{ job.active === 'Y' ? 'Yes' : 'No' }}</td>
            <td>{{ job.schedulePeriod }}</td>
            <td>{{ formatDate(job.lastTriggeredDate) }}</td>
            <td>{{ formatDate(job.nextTriggerDate) }}</td>
            <td>
              <button class="btn-primary" @click="handleRunNow(job.id)">Run Now</button>
              <button class="btn-secondary" @click="handleEdit(job.id)">Edit</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Reuse JobForm.vue -->
    <JobForm
      v-if="showForm"
      :job="selectedJob"
      :show="showForm"
      @saved="handleSaved"
      @close="showForm = false"
    />
  </div>
</template>

<style scoped>
.table-wrapper {
  max-height: 60vh;       /* constrain table height */
  overflow-y: auto;       /* enable vertical scroll */
  margin-top: 1rem;
  border-radius: 8px;
  border: 1px solid #333;
}

.styled-table {
  width: 100%;
  border-collapse: collapse;
  background: #1e1e1e;
}
.styled-table th {
  background: #2c2c2c;
  color: #fff;
  text-align: left;
  padding: 12px;
}
.styled-table td {
  padding: 12px;
  border-top: 1px solid #333;
  color: #ddd;
}
.styled-table tr:hover {
  background: #2a2a2a;
}
.status.S {
  color: #4ade80;
  font-weight: bold;
}
.status.E {
  color: #f87171;
  font-weight: bold;
}
.status.P {
  color: #facc15;
  font-weight: bold;
}
.btn-primary {
  background: #4ade80;
  color: #000;
  padding: 6px 10px;
  border-radius: 6px;
  margin-right: 5px;
  cursor: pointer;
  border: none;
}
.btn-primary:hover {
  background: #22c55e;
}
.btn-secondary {
  background: #3b82f6;
  color: #fff;
  padding: 6px 10px;
  border-radius: 6px;
  cursor: pointer;
  border: none;
}
.btn-secondary:hover {
  background: #2563eb;
}
</style>

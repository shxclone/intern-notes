<script setup>
import { ref, onMounted } from 'vue'
import { getAllHistory } from '@/services/historyService'

const history = ref([])
const loading = ref(true)

const formatDate = (dateStr) => {
  if (!dateStr) return '-'
  return new Date(dateStr).toLocaleString()
}

onMounted(async () => {
  try {
    history.value = await getAllHistory()
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div>
    <h2>Job History</h2>
    <div v-if="loading">Loading...</div>
    <div v-else class="table-wrapper">
      <table class="styled-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Job Code</th>
            <th>Job Type</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Status</th>
            <th>Message</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="h in history" :key="h.id">
            <td>{{ h.id }}</td>
            <td>{{ h.jobCode }}</td>
            <td>{{ h.jobType }}</td>
            <td>{{ formatDate(h.startTime) }}</td>
            <td>{{ formatDate(h.endTime) }}</td>
            <td><span :class="['status', h.status]">{{ h.status }}</span></td>
            <td>{{ h.message }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
.table-wrapper {
  width: 100%;           
  max-height: 60vh;      
  overflow-y: auto;      
  overflow-x: auto;      /* allow scroll if table too wide */
  margin-top: 1rem;
  border-radius: 8px;
  border: 1px solid #333;
}

.styled-table {
  width: 100%;          
  border-collapse: collapse;
  background: #1e1e1e;
  /* removed table-layout: fixed so columns size naturally */
}

.styled-table th {
  background: #2c2c2c;
  color: #fff;
  text-align: left;
  padding: 12px;
  white-space: nowrap;  /* prevent header wrapping */
}

.styled-table td {
  padding: 12px;
  border-top: 1px solid #333;
  color: #ddd;
  white-space: nowrap;  /* prevent weird wrapping */
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
</style>

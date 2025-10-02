<script setup>
import { ref, watch, defineEmits, defineProps } from 'vue'
import { updateJob } from '@/services/jobService'

const props = defineProps({
  job: { type: Object, required: true },
  show: { type: Boolean, default: false } // toggle modal visibility
})
const emit = defineEmits(['saved', 'close'])

const schedulePeriod = ref('')
const hour = ref('00')
const minute = ref('00')
const dayOfWeek = ref('')
const dayOfMonth = ref(null)
const startDate = ref(null)
const endDate = ref(null)

watch(() => props.job, (job) => {
  if (!job) return
  schedulePeriod.value = job.schedulePeriod || 'DAILY'

  if (job.runTime) {
    const [h, m] = job.runTime.split(':')
    hour.value = h.padStart(2, '0')
    minute.value = m.padStart(2, '0')
  }

  dayOfWeek.value = job.dayOfWeek || ''
  dayOfMonth.value = job.dayOfMonth || null
  startDate.value = job.startDate || null
  endDate.value = job.endDate || null
}, { immediate: true })

const save = async () => {
  const payload = {
    schedulePeriod: schedulePeriod.value,
    runTime: `${hour.value}:${minute.value}`,
    dayOfWeek: dayOfWeek.value,
    dayOfMonth: dayOfMonth.value,
    startDate: startDate.value,
    endDate: endDate.value
  }
  await updateJob(props.job.id, payload)
  emit('saved')
}
</script>

<template>
  <div v-if="show" class="modal-overlay">
    <div class="modal">
      <h3>Edit Job: {{ props.job?.jobCode }}</h3>
      <form @submit.prevent="save">

        <label>Schedule Period:</label>
        <select v-model="schedulePeriod">
          <option value="DAILY">Daily</option>
          <option value="WEEKLY">Weekly</option>
          <option value="MONTHLY">Monthly</option>
        </select>
        <br />

        <label>Run Time:</label>
        <select v-model="hour">
          <option v-for="h in 24" :key="h" :value="String(h-1).padStart(2,'0')">
            {{ String(h-1).padStart(2,'0') }}
          </option>
        </select>
        :
        <select v-model="minute">
          <option v-for="m in 60" :key="m" :value="String(m-1).padStart(2,'0')">
            {{ String(m-1).padStart(2,'0') }}
          </option>
        </select>
        <br />

        <div v-if="schedulePeriod === 'WEEKLY'">
          <label>Day of Week:</label>
          <select v-model="dayOfWeek">
            <option disabled value="">-- Select --</option>
            <option>MON</option>
            <option>TUE</option>
            <option>WED</option>
            <option>THU</option>
            <option>FRI</option>
            <option>SAT</option>
            <option>SUN</option>
          </select>
          <br />
        </div>

        <div v-if="schedulePeriod === 'MONTHLY'">
          <label>Day of Month:</label>
          <input type="number" v-model="dayOfMonth" min="1" max="31" />
          <small>Note: If this day doesn’t exist in a month, the job won’t run that month.</small>
          <br />
        </div>

        <label>Start Date:</label>
        <input type="date" v-model="startDate" />
        <br />

        <label>End Date:</label>
        <input type="date" v-model="endDate" />
        <br />

        <div class="modal-actions">
          <button type="submit">Save</button>
          <button type="button" @click="$emit('close')">Cancel</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background: white;
  padding: 20px;
  border-radius: 8px;
  min-width: 400px;
}

.modal-actions {
  margin-top: 15px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>

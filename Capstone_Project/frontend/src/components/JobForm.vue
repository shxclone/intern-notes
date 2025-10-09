<script setup>
import { ref, watch, defineEmits, defineProps } from 'vue'
import { updateJob } from '@/services/jobService'

const props = defineProps({
  job: { type: Object, required: true },
  show: { type: Boolean, default: false }
})
const emit = defineEmits(['saved', 'close'])

const schedulePeriod = ref('DAILY')
const hour = ref('00')
const minute = ref('00')
const dayOfWeek = ref('')
const dayOfMonth = ref(null)
const startDate = ref(null)
const endDate = ref(null)

// helper: parse cron expression
const parseCron = (cron) => {
  if (!cron) return { minute: '00', hour: '00', schedulePeriod: 'DAILY', dayOfWeek: '', dayOfMonth: null }

  const parts = cron.trim().split(' ') // ["0","30","9","?","*","MON"]

  const minute = parts[1]?.padStart(2, '0') || '00'
  const hour = parts[2]?.padStart(2, '0') || '00'

  if (parts[3] === '*' && parts[5] === '?') {
    // DAILY
    return { minute, hour, schedulePeriod: 'DAILY', dayOfWeek: '', dayOfMonth: null }
  } else if (parts[3] === '?' && parts[5] && parts[5] !== '?') {
    // WEEKLY
    return { minute, hour, schedulePeriod: 'WEEKLY', dayOfWeek: parts[5].toUpperCase(), dayOfMonth: null }
  } else if (parts[3] && parts[3] !== '?' && parts[5] === '?') {
    // MONTHLY
    return { minute, hour, schedulePeriod: 'MONTHLY', dayOfWeek: '', dayOfMonth: parseInt(parts[3]) }
  }

  return { minute, hour, schedulePeriod: 'DAILY', dayOfWeek: '', dayOfMonth: null }
}

// populate form fields when job changes
watch(() => props.job, (job) => {
  if (!job) return

  const { minute: m, hour: h, schedulePeriod: sp, dayOfWeek: dow, dayOfMonth: dom } = parseCron(job.cronExpression)

  schedulePeriod.value = job.schedulePeriod || sp
  hour.value = h
  minute.value = m
  dayOfWeek.value = dow
  dayOfMonth.value = dom
  startDate.value = job.startDate ? job.startDate.split('T')[0] : ''
  endDate.value = job.endDate ? job.endDate.split('T')[0] : ''
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

        <div class="form-group">
          <label>Schedule Period:</label>
          <select v-model="schedulePeriod">
            <option value="DAILY">Daily</option>
            <option value="WEEKLY">Weekly</option>
            <option value="MONTHLY">Monthly</option>
          </select>
        </div>

        <div class="form-group">
          <label>Run Time:</label>
          <div class="run-time-group">
            <select v-model="hour">
              <option v-for="h in 24" :key="h" :value="String(h-1).padStart(2,'0')">
                {{ String(h-1).padStart(2,'0') }}
              </option>
            </select>
            <select v-model="minute">
              <option v-for="m in 60" :key="m" :value="String(m-1).padStart(2,'0')">
                {{ String(m-1).padStart(2,'0') }}
              </option>
            </select>
          </div>
        </div>

        <div class="form-group" v-if="schedulePeriod === 'WEEKLY'">
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
        </div>

        <div class="form-group" v-if="schedulePeriod === 'MONTHLY'">
          <label>Day of Month:</label>
          <input type="number" v-model="dayOfMonth" min="1" max="31" />
          <small class="hint">If this day doesn’t exist in a month, the job won’t run.</small>
        </div>

        <div class="form-group">
          <label>Start Date:</label>
          <input type="date" v-model="startDate" />
        </div>

        <div class="form-group">
          <label>End Date:</label>
          <input type="date" v-model="endDate" />
        </div>

        <div class="modal-actions">
          <button type="button" @click="$emit('close')">Cancel</button>
          <button type="submit">Save</button>
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
  background: rgba(0,0,0,0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background: #1e1e1e;
  padding: 2rem;
  border-radius: 12px;
  min-width: 420px;
  color: #fff;
  box-shadow: 0 8px 24px rgba(0,0,0,0.4);
}

.modal h3 {
  margin-bottom: 1.5rem;
  font-size: 1.25rem;
  font-weight: 600;
  border-bottom: 1px solid #333;
  padding-bottom: 0.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 1.2rem;
}

.form-group label {
  font-size: 0.95rem;
  font-weight: 500;
  margin-bottom: 0.4rem;
  color: #ccc;
}

.form-group input,
.form-group select {
  padding: 0.6rem 0.75rem;
  border: 1px solid #444;
  border-radius: 6px;
  background: #2a2a2a;
  color: #fff;
  font-size: 0.95rem;
  appearance: none;
}

.form-group input[type="date"],
.form-group input[type="number"] {
  color-scheme: dark;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #4ade80;
  box-shadow: 0 0 0 2px rgba(74, 222, 128, 0.25);
}

.run-time-group {
  display: flex;
  gap: 0.5rem;
}

.hint {
  color: #aaa;
  font-size: 0.8rem;
  margin-top: 0.25rem;
  display: block;
}

.modal-actions {
  margin-top: 1.5rem;
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
}

button {
  padding: 0.5rem 1rem;
  border-radius: 6px;
  cursor: pointer;
  border: none;
  font-size: 0.95rem;
  transition: background 0.2s ease;
}

button[type="submit"] {
  background: #4ade80;
  color: #000;
}

button[type="submit"]:hover {
  background: #22c55e;
}

button[type="button"] {
  background: #3b82f6;
  color: #fff;
}

button[type="button"]:hover {
  background: #2563eb;
}
</style>

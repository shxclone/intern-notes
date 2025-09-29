<script setup>
import { ref, onMounted } from 'vue'

const message = ref("Loading...")

onMounted(async () => {
  const res = await fetch("/api/hello")
  message.value = await res.text()
})
</script>

<template>
  <div class="container mt-5">
    <h1>{{ message }}</h1>
  </div>
</template>

<script setup>
</script>

<template>
  <div id="app">
    <!-- Header -->
    <header class="header">
      <h1 class="title">Batch Job Lifecycle Monitor</h1>
      <nav class="nav">
        <router-link to="/jobs">Jobs</router-link>
        <router-link to="/history">History</router-link>
      </nav>
    </header>

    <!-- Page Content -->
    <main class="main">
      <router-view />
    </main>
  </div>
</template>

<style scoped>
#app {
  min-height: 100vh;
  width: 90vw;
  display: flex;
  flex-direction: column;
}

.header {
  display: flex;
  justify-content: space-between; /* title left, nav right */
  align-items: center;
  padding: 1rem 2rem;
  border-bottom: 1px solid #ccc;

  width: 100%;    /* force full width */
}

.title {
  margin: 0;
}

.nav a {
  margin-left: 1.5rem;
  text-decoration: none;
}

.main {
  flex: 1;
  width: 100%;   /* ensure content fills width too */
}
</style>

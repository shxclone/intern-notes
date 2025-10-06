import api from './api'

export const getJobs = () =>
  api.get('/jobs').then(res => res.data)

export const getJob = (id) =>
  api.get(`/jobs/${id}`).then(res => res.data)

export const updateJob = (id, payload) =>
  api.put(`/jobs/${id}`, payload).then(res => res.data)

export const triggerJob = (id) =>
  api.post(`/jobs/${id}/trigger`).then(res => res.data)
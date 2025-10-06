import api from './api'

// Get ALL job history entries
export const getAllHistory = () =>
  api.get('/history').then(res => res.data)

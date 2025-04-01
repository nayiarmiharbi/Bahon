import React from 'react'
import CarNotAvailable from '../components/Cars/CarNotAvailable'
import { Check } from '@mui/icons-material'

const AdminDashboard = () => {
  return (
    <div>AdminDashboard
        <CarNotAvailable/>
        <button>See All Cars</button>
        <button>See All Users</button>
        <button>Check insurance Application</button>
        <button>Check New Car Application</button>
    </div>
  )
}

export default AdminDashboard
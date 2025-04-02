import React from 'react'
import CarNotAvailable from '../components/Cars/CarNotAvailable'

const AdminDashboard = () => {
  return (
    <div>AdminDashboard
        <CarNotAvailable/>
        <button>See All Cars</button>
        <button>See All Users</button>
        <button>Check insurance Application</button>
        <button>Check New Car Application</button>
        <button>Verify Payments</button>
    </div>
  )
}

export default AdminDashboard
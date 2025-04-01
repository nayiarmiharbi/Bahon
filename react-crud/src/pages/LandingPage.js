import React from 'react'
import v from '../assets/v.svg'
import r from '../assets/r.svg'
import logo from '../assets/tire-svgrepo-com.svg'
import m from '../assets/m.svg'
import '../App.css'

const LandingPage = () => {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          <img src={v} className="App-logo" alt="V" />
          <img src={r} className="App-logo" alt="R" />
          <img src={logo} className="App-logo tire-logo" alt="Tire" />
          <img src={logo} className="App-logo tire-logo" alt="Tire" />
          <img src={m} className="App-logo" alt="M" />
        </p>
        <p>
          Your Trusted Car Rental Service
        </p>
        <a
          className="App-link"
          href="/cars"
        >
          See Cars
        </a>
      </header>
    </div>
  )
}

export default LandingPage;
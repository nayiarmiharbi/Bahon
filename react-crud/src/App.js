import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import UsersPage from "./pages/UsersPage";
import UserForm from "./components/Users/UserForm";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Navbar from "./components/Navbar";
import UserDetailsForm from "./components/Users/UserDetailsForm";
import { ToastContainer } from "react-toastify";
import CarsPage from "./pages/CarsPage";
import CarForm from "./components/Cars/CarForm";
import CarList from "./components/Cars/CarList";
import LandingPage from "./pages/LandingPage";

function App() {
    return (
        <Router>
            < ToastContainer />
            <Navbar />
            <Routes>
                <Route path="/" element={<LandingPage />} />
                
                <Route path="/dashboard" element={<Dashboard/>} />
                
                <Route path="/login" element={<Login />} />
                <Route path="/users" element={<UsersPage />} />
                <Route path="/users/add" element={<UserForm />} />
                <Route path="/users/edit/:id" element={<UserForm />} />
                <Route path="/userdetails/:id" element={<UserDetailsForm />} />

                <Route path="/cars" element={<CarsPage />} />
                <Route path="/cars/edit/:regNo" element={<CarForm />} />
                <Route path="/cars/ownerlist" element={<CarList />} />
            </Routes>
        </Router>
    );
}

export default App;

import axios from "axios";

const API_URL = "http://localhost:8081";

export const login = async (username, password) => {
  try {
    const response = await axios.post(`${API_URL}/api/auth/login`, { username, password });
    return response.data;
  } catch (error) {
    throw error.response?.data || "Login failed";
  }
}
export const getUsers = async () => axios.get(`${API_URL}/users`);
export const getUserById = async (id) => axios.get(`${API_URL}/users/${id}`);
export const createUser = async (user) => axios.post(`${API_URL}/users`, user);
export const updateUser = async (id, user) => axios.put(`${API_URL}/users/${id}`, user);
export const deleteUser = async (id) => axios.delete(`${API_URL}/users/${id}`);

export const getUserDetails = async (id) => axios.get(`${API_URL}/userdetails/${id}`);
export const createUserDetails = async (userDetails) => axios.post(`${API_URL}/userdetails`, userDetails);
export const updateUserDetails = async (id, userDetails) => axios.put(`${API_URL}/userdetails/${id}`, userDetails);
export const deleteUserDetails = async (id) => axios.delete(`${API_URL}/userdetails/${id}`);

export const getAllCars = async () => axios.get(`${API_URL}/cars`);
export const createCar = async (id, car) => axios.post(`${API_URL}/cars/${id}`, car);
export const updateCar = async (regNo, car) => axios.put(`${API_URL}/cars/regno/${regNo}`, car);
export const deleteCar = async (regNo) => axios.delete(`${API_URL}/cars/regno/${regNo}`);
export const getCarsByOwner = async (id) => axios.get(`${API_URL}/cars/${id}`);
export const getCarByRegNo = async (regNo) => axios.get(`${API_URL}/cars/regno/${regNo}`);
export const getCarsByModel = async (model) => axios.get(`${API_URL}/cars/model/${model}`);
export const getCarsByStatus = async (status) => axios.get(`${API_URL}/cars/${status}`);
export const getCarsByFuel = async (fuel) => axios.get(`${API_URL}/cars/fueltype/${fuel}`);
export const getCarsByCapacity = async (capacity) => axios.get(`${API_URL}/cars/capacity/${capacity}`);
export const getCarsByRate = async (rate) => axios.get(`${API_URL}/cars/rate/${rate}`);

export const getAllBookings = async () => axios.get(`${API_URL}/bookings`);
export const createBooking = async (booking) => axios.post(`${API_URL}/bookings`, booking);
export const updateBooking = async (id, booking) => axios.put(`${API_URL}/bookings/${id}`, booking);
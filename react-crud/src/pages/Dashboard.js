import React, { useEffect } from "react";
import { useAtom } from "jotai";
import { userAtom } from "../atoms/userAtom";
import { getUserById } from "../services/api";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const [user] = useAtom(userAtom);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCars();
  }, []);


  const fetchCars = async () => {
    const response = await getUserById(user.uid);
    const userDetails = response.data;
    if (userDetails.userType === 1) {
      navigate("/customer");
    } else if (userDetails.userType === 2) {
      navigate("/owner");
    } else {
      navigate("/admin");
    }
  };


};

export default Dashboard;

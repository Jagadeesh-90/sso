import { useAuth0 } from "@auth0/auth0-react";
import { Button } from "@mui/material";

const Dashboard = () => {
  const {logout}=useAuth0()
  const handleLogout = () => {
    logout();  
    localStorage.removeItem("token");
  };
  return<>
   <h1>Dashboard</h1>
   <Button onClick={handleLogout}>Logout</Button>
  </>

};

export default Dashboard;

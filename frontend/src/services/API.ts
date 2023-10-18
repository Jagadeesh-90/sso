import axios from "axios";
export default axios.create({
  baseURL: "http://localhost:9005",
  headers: {
    "content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
});

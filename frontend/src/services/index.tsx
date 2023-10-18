import API from "./API";

export const getToken = async (data: any) => {
  const response = await API.post("/users/login", data);
  return response.data;
};

API.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const getUserById = async (id: number) => {
  const response = await API.get(`/users/dashboard?userId=${id}`);
  return response.data;
};

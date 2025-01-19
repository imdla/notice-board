import axios from "axios";
import store from "../store/store";

const api = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL,
});

// interceptor

export default api;

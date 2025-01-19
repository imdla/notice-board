import api from "./axios";

const ENDPOINT = "/auth";
const authApi = {
  // 회원가입
  signup: async (formData) => {
    const response = await api.post(`${ENDPOINT}/signup`, formData);
    return response;
  },

  // 로그인
  login: async (formData) => {
    const response = await api.post(`${ENDPOINT}/login`, formData);
    return response;
  },
};

export default authApi;

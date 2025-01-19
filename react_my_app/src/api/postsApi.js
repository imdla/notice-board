import api from "./axios";
const ENDPOINT = "/posts";
const postApi = {
  // 리스트 GET
  getPosts: async (params = {}) => {
    const response = await api.get(ENDPOINT, { params });
    return response;
  },

  // 개별 GET, 댓글과 태그들과 함께
  getPostById: async (postId) => {
    const response = await api.get(`${ENDPOINT}/${postId}/detail`);
    return response;
  },

  // POST
  createPost: async (formData) => {
    const response = await api.post(ENDPOINT, formData);
    return response;
  },
};

export default postApi;

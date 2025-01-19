import api from "./axios";
const ENDPOINT = "/posts";
const postApi = {
  // 리스트 GET
  getPosts: async (params = {}) => {
    const response = await api.get(ENDPOINT, { params });
    return response;
  },

  // GET, 개별 게시글 조회
  getPostById: async (postId) => {
    const response = await api.get(`${ENDPOINT}/${postId}`);
    return response;
  },

  // GET, 태그별 게시글 조회
  getPostById: async (tagName) => {
    const response = await api.get(`${ENDPOINT}/tags?tag=${tagName}`);
    return response;
  },

  // POST
  createPost: async (formData) => {
    const response = await api.post(ENDPOINT, formData);
    return response;
  },
};

export default postApi;

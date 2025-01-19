import api from './axios';
const ENDPOINT = '/posts';
const postApi = {
  // POST, 게시글 작성
  createPost: async (formData) => {
    const response = await api.post(ENDPOINT, formData);
    return response;
  },

  // GET, 개별 게시글 조회
  getPostById: async (postId) => {
    const response = await api.get(`${ENDPOINT}/${postId}`);
    return response;
  },

  // GET, 게시글 리스트 조회
  getPosts: async (params = {}) => {
    const response = await api.get(ENDPOINT, { params });
    return response;
  },

  // GET, 태그별 게시글 조회
  getPostsByTag: async (tagName) => {
    const response = await api.get(`${ENDPOINT}/tags?tag=${tagName}`);
    return response;
  },

  // PUT, 게시글 수정
  updatePost: async (postId, formData) => {
    const response = await api.put(`${ENDPOINT}/${postId}`, formData);
    return response;
  },

  // DELETE, 게시글 삭제
  deletePost: async (postId) => {
    const response = await api.delete(`${ENDPOINT}/${postId}`);
    return response;
  },

  // GET, 개별 게시글 댓글 조회
  getComments: async (postId) => {
    const response = await api.get(`${ENDPOINT}/${postId}/comments`);
    return response;
  },
};

export default postApi;

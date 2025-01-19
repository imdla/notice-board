import api from './axios';
const ENDPOINT = `/posts`;
const postApi = {
  // POST, 댓글 작성
  addComment: async (postId, formData) => {
    const response = await api.post(ENDPOINT, `/${postId}/comments`, formData);
    return response;
  },

  // PUT, 댓글 수정
  addComment: async (postId, commentId, formData) => {
    const response = await api.post(
      ENDPOINT,
      `/${postId}/comments/${commentId}`,
      formData
    );
    return response;
  },
};

export default postApi;

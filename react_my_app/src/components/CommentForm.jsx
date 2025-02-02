import React, { useState } from 'react';
import postApi from '../api/postsApi';

export default function CommentForm({ postId }) {
  const [formData, setFormData] = useState({ content: '' });

  function handleSubmit(e) {
    e.preventDefault();

    async function createComment() {
      try {
        const response = await postApi.createComment(postId, formData);
        const data = response.data;
        console.log(data);
        setFormData({ content: '' });
      } catch (err) {
        console.log(err);
      }
    }
    createComment();
  }
  function handleFormInput(e) {
    const inputValue = e.target.value;
    const key = e.target.name;
    setFormData({
      ...formData,
      [key]: inputValue,
    });
  }
  return (
    <>
      <div>댓글 작성</div>
      <form onSubmit={handleSubmit}>
        <textarea
          id="content"
          name="content"
          required
          value={formData.content}
          onChange={handleFormInput}
        ></textarea>
        <button>제출</button>
      </form>
    </>
  );
}

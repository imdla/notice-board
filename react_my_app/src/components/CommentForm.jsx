import React, { useEffect, useState } from 'react';
import postApi from '../api/postsApi';

export default function CommentForm({ postId }) {
  const [comments, setComments] = useState({});
  const [formData, setFormData] = useState({ content: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    async function fetchPost() {
      try {
        const response = await postApi.getComments(postId);
        setComments(response.data.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }
    fetchPost();
  }, [formData]);

  if (loading) return <div>로딩중...</div>;

  if (error.status == 404) {
    return <h3>{error}</h3>;
  }

  function handleSubmit(e) {
    e.preventDefault();

    async function createComment() {
      try {
        const response = await postApi.createComment(postId, formData);
        const data = response.data;
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
      {comments?.length ? (
        <ol>
          {comments?.map((comment) => {
            const { id, content, author, createdAt } = comment;
            return (
              <li key={`comment-${id}`}>
                <p>
                  {content} <span>{author}</span>{' '}
                  <span>{createdAt.slice(0, 10)}</span>
                </p>
              </li>
            );
          })}
        </ol>
      ) : (
        <div>댓글이 없습니다.</div>
      )}

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

import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import postApi from '../api/postsApi';
import CommentItem from './CommentItem';

export default function CommentForm({ postId }) {
  const [comments, setComments] = useState([]);
  const [formData, setFormData] = useState({ content: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState({ message: '', status: null });
  const { user } = useSelector((state) => state.auth);

  async function fetchPost() {
    try {
      const response = await postApi.getComments(postId);
      setComments(response.data.data);
    } catch (err) {
      setError({
        message: err.message,
        status: err.response?.status || 500,
      });
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    fetchPost();
  }, [formData]);

  if (loading) {
    return <div>로딩 중입니다.</div>;
  }

  if (error.status == '404') {
    return <div>{error.message}</div>;
  }

  function handleSubmit(e) {
    e.preventDefault();

    async function createComment() {
      try {
        const response = await postApi.createComment(postId, formData);
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
      <>
        <ol>
          {comments.length > 0 ? (
            comments.map((comment) => {
              return (
                <li key={`comment-${comment.id}`}>
                  <CommentItem
                    fetchPost={fetchPost}
                    user={user}
                    comment={comment}
                  ></CommentItem>
                </li>
              );
            })
          ) : (
            <div>댓글이 없습니다.</div>
          )}
        </ol>
      </>

      <div>댓글 작성</div>

      <>
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
    </>
  );
}

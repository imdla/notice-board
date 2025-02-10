import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import postApi from '../api/postsApi';
import CommentList from './CommentList';

export default function CommentForm({ postId }) {
  const [comments, setComments] = useState('');
  const [commentsList, setCommentsList] = useState('');
  const [formData, setFormData] = useState({ content: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const { user } = useSelector((state) => state.auth);

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

  useEffect(() => {
    comments?.length
      ? setCommentsList(
          <CommentList user={user} comments={comments}></CommentList>
        )
      : setCommentsList(<div>댓글이 없습니다.</div>);
  }, [comments]);

  if (loading) return <div>로딩중...</div>;

  if (error.status == 404) {
    return <h3>{error}</h3>;
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
      {commentsList}

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

import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import postApi from '../api/postsApi';

export default function CommentItem({ fetchPost, user, comment }) {
  const [isUpdated, setIsUpdated] = useState(false);
  const [formData, setFormData] = useState({ content: '' });
  const { postId } = useParams();

  const { id, content, author, createdAt } = comment;

  function handleClick(e) {
    if (isUpdated) {
      setIsUpdated(false);
      handleSubmit(e);
      fetchPost();
    } else {
      setIsUpdated(true);
    }
  }

  function handleSubmit(e) {
    e.preventDefault();

    async function updateComment() {
      try {
        const response = await postApi.updateComment(postId, id, formData);
        setFormData({ content: '' });
      } catch (err) {
        console.log(err);
      }
    }
    updateComment();
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
    <div>
      <p>
        {content} <span> {author}</span> <span> {createdAt.slice(0, 10)}</span>
      </p>

      {isUpdated ? (
        <form onSubmit={handleSubmit}>
          <textarea
            id="content"
            name="content"
            required
            value={formData.content}
            onChange={handleFormInput}
          ></textarea>
        </form>
      ) : (
        ''
      )}

      {user.name == author ? (
        <>
          <button onClick={handleClick}>
            {isUpdated ? '수정 완료' : '수정'}
          </button>
        </>
      ) : (
        ''
      )}
    </div>
  );
}

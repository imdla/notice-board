import React, { useState } from 'react';

export default function CommentItem({ user, comment }) {
  const { content, author, createdAt } = comment;

  const [isUpdated, setIsUpdated] = useState(false);

  function handleClick(e) {
    console.log(e.target);

    if (isUpdated) {
      setIsUpdated(false);
    } else {
      setIsUpdated(true);
    }
  }

  return (
    <>
      <p>
        {content}
        <span>{author}</span>
        <span>{createdAt.slice(0, 10)}</span>
        {user.name == author ? (
          <>
            <button onClick={handleClick}>
              {isUpdated ? '수정완료' : '수정'}
            </button>
          </>
        ) : (
          '없음'
        )}
      </p>
    </>
  );
}

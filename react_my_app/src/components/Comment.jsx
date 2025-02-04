import React from 'react';

export default function Comment({ user, comment }) {
  const { id, content, author, createdAt } = comment;

  return (
    <li key={`comment-${id}`}>
      <p>
        {content} <span>{author}</span> <span>{createdAt.slice(0, 10)}</span>
        {user.name == author ? (
          <>
            <button onClick={handleClick}>수정</button>
          </>
        ) : (
          ''
        )}
      </p>
    </li>
  );
}

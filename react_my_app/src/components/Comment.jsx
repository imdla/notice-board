import React from 'react';

export default function Comment({ user, comments }) {
  const commentsList = comments.map((comment) => {
    const { id, content, author, createdAt } = comment;

    <li key={`comment-${id}`}>
      <p>
        {content} <span>{author}</span> <span>{createdAt.slice(0, 10)}</span>
        {user.name == author ? (
          <>
            <button>수정</button>
          </>
        ) : (
          ''
        )}
      </p>
    </li>;
  });

  return { commentsList };
}

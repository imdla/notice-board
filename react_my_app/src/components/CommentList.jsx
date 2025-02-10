import React from 'react';

export default function CommentList({ user, comments }) {
  const commentList = comments.map((comment) => {
    const { id, content, author, createdAt } = comment;

    return (
      <li key={`comment-${id}`}>
        <p>
          {content}
          <span>{author}</span>
          <span>{createdAt.slice(0, 10)}</span>
          {user.name == author ? (
            <>
              <button>수정</button>
            </>
          ) : (
            '없음'
          )}
        </p>
      </li>
    );
  });

  return <ol>{commentList}</ol>;
}

import React, { useState } from 'react';
import CommentItem from './CommentItem';

export default function CommentList({ user, comments }) {


  const commentList = comments.map((comment) => {
    return (
      <li key={`comment-${comment.id}`}>
        <CommentItem user={user} comment={comment}></CommentItem>
      </li>
    );
  });

  return <ol>{commentList}</ol>;
}

import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import postApi from '../api/postsApi';
import CommentForm from '../components/CommentForm';

export default function PostDetail() {
  const [post, setPost] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const { postId } = useParams();

  useEffect(() => {
    async function fetchPost() {
      // 게시글 조회 로직
      try {
        const response = await postApi.getPostById(postId);
        setPost(response.data.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }
    fetchPost();
  }, []);

  if (loading) return <div>로딩중...</div>;

  if (error.status == 404) {
    return <h3>존재하지 않는 게시글입니다.</h3>;
  }

  return (
    <div>
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
      <p>작성일 : {post?.createdAt.slice(0, 10)}</p>
      <hr />
      <div>
        tags :
        {post?.tags?.map((tag) => {
          return <span key={tag}>#{tag} </span>;
        })}
      </div>
      <hr />

      <CommentForm postId={postId}></CommentForm>
    </div>
  );
}

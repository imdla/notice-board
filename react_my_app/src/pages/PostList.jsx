import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import postApi from '../api/postsApi';

export default function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    async function fetchPosts() {
      // 게시글 조회 로직
      try {
        const params = {
          page: 0,
          size: 100,
        };
        const response = await postApi.getPosts(params);
        const data = response.data.data.posts;

        setPosts(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    fetchPosts();
  }, []);

  if (loading) {
    return <div>로딩중</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Post List</h2>
      <ul>
        {posts.length > 0 ? (
          posts.map((post) => {
            const { id, title } = post;
            return (
              <li key={id}>
                <Link to={`/posts/${id}`}>
                  <h3>
                    {id}번째 게시글 : {title}
                  </h3>
                </Link>
              </li>
            );
          })
        ) : (
          <div>게시글이 없습니다.</div>
        )}
      </ul>
    </div>
  );
}

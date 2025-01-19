import React, { useEffect, useState } from "react";

import post2Api from "../api/posts2Api";

export default function MyPage() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchPosts() {
      try {
        const response = await post2Api.getMyPosts();
        const data = response.data;

        setPosts(data.data.posts);
      } catch (err) {
        setError(err.message);
        console.error(err.responase);
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
      <h2>내가 쓴 게시글</h2>
      <ul>
        {posts.map((post) => {
          const { id, title, content } = post;
          return (
            <li key={id}>
              <h3>
                {id} :<div>제목 :{title} </div>
                <div>{content}</div>
              </h3>
            </li>
          );
        })}
      </ul>
    </div>
  );
}

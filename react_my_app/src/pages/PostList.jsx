import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import postApi from "../api/postsApi";

export default function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchPosts() {
      // 게시글 조회 로직
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
    </div>
  );
}

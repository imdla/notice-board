import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import postApi from "../api/postsApi";

export default function PostDetail() {
  const [post, setPost] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const { postId } = useParams();

  useEffect(() => {
    async function fetchPost() {
      // 게시글 조회 로직
    }
    fetchPost();
  }, []);

  if (loading) return <div>로딩중...</div>;

  return <div>Post Detail</div>;
}

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Navigate, useNavigate } from "react-router-dom";
import post2Api from "../api/posts2Api";

export default function PostCreate() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { isLoggedIn } = useSelector((state) => state.auth);
  const [formData, setFormData] = useState({ title: "", content: "" });
  const [error, setError] = useState("");

  useEffect(() => {
    if (!isLoggedIn) {
      navigate("/");
    }
  }, [isLoggedIn]);

  function handleFormInput(e) {
    const inputValue = e.target.value;
    const key = e.target.name;
    setFormData({
      ...formData,
      [key]: inputValue,
    });
  }

  function handleSubmit(e) {
    e.preventDefault();
    setError("");

    async function createPost() {
      try {
        const response = await post2Api.createPost(formData);
        const data = response.data;
        const id = data.data.id;
        navigate(`/my-page`);
      } catch (err) {
        console.log(err);
      }
    }
    createPost();
  }

  return (
    <>
      <h3>게시글 작성</h3>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">제목 : </label>
        <input
          id="title"
          name="title"
          required
          type="text"
          value={formData.title}
          onChange={handleFormInput}
        />
        <label>
          내용 :
          <textarea
            id="content"
            name="content"
            required
            value={formData.content}
            onChange={handleFormInput}
          ></textarea>
        </label>

        <button>제출</button>
      </form>
    </>
  );
}

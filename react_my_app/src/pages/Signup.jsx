import React, { useState } from "react";
import authApi from "../api/authApi";
import { useNavigate } from "react-router-dom";

export default function SignUp() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");

  const handleFormInput = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setIsLoading(true);

    try {
      await authApi.signup(formData);
      alert("회원가입 성공");
      navigate("/");
    } catch (err) {
      setError(err.message);
      console.error(err.response);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div>
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">사용자 이름 : </label>
        <input
          id="username"
          name="username"
          required
          placeholder="사용자 이름"
          value={formData.username}
          onChange={handleFormInput}
        />

        <div>
          <label htmlFor="email">이메일</label>
          <input
            id="email"
            name="email"
            type="email"
            required
            placeholder="이메일"
            value={formData.email}
            onChange={handleFormInput}
          />
        </div>

        <div>
          <label htmlFor="password">비밀번호</label>
          <input
            id="password"
            name="password"
            type="password"
            required
            placeholder="비밀번호"
            value={formData.password}
            onChange={handleFormInput}
          />
        </div>

        {error && <div>{error}</div>}

        <button type="submit">{isLoading ? "처리중..." : "회원가입"}</button>
      </form>
    </div>
  );
}

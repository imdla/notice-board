import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { login, logout } from "../store/slices/authSlice";

export default function Header() {
  const { isLoggedIn } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  return (
    <header>
      <div>
        Hello user!
        {isLoggedIn ? (
          <>
            <button
              onClick={() => {
                dispatch(logout());
              }}
            >
              로그아웃
            </button>
            <Link to="/my-page">
              <button>마이페이지</button>
            </Link>
          </>
        ) : (
          <>
            <Link to="/signup">
              <button>회원가입</button>
            </Link>
            <Link to="/login">
              <button>로그인</button>
            </Link>
          </>
        )}
      </div>
      <ul>
        <li>
          <Link to="/">Home으로</Link>
        </li>
        <li>
          <Link to="/posts">게시글로(User없는)</Link>
        </li>
        {isLoggedIn && (
          <>
            <li>
              <Link to="/posts/create">게시글 생성(User 없는)</Link>
            </li>
            <li>
              <Link to="/posts2/create">게시글 생성(User 있는)</Link>
            </li>
          </>
        )}
      </ul>
    </header>
  );
}

import React, { useEffect } from "react";
import { Outlet } from "react-router-dom";
import Header from "./components/Header";
import { useDispatch } from "react-redux";
import { logout } from "./store/slices/authSlice";
import authApi from "./api/authApi";

export default function RootLayout() {
  const dispatch = useDispatch();

  useEffect(() => {
    const verifyToken = async () => {
      try {
        await authApi.verify();
      } catch (err) {
        dispatch(logout());
      }
    };
    verifyToken();
  }, []);

  return (
    <>
      <Header></Header>
      <Outlet></Outlet>
      <footer></footer>
    </>
  );
}

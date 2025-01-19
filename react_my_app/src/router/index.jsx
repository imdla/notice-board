import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Signup from "../pages/Signup";
import LogIn from "../pages/LogIn";
import MyPage from "../pages/MyPage";
import RootLayout from "../RootLayout";
import PostList from "../pages/PostList";
import PostDetail from "../pages/PostDetail";
import PostCreate from "../pages/PostCreate";
import Post2Create from "../pages/Post2Create";
import NotFound from "../pages/NotFound";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    // errorElement: <NotFound />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "/signup",
        element: <Signup />,
      },
      {
        path: "/login",
        element: <LogIn />,
      },
      {
        path: "/my-page",
        element: <MyPage />,
      },
      {
        path: "/posts",
        element: <PostList />,
      },
      {
        path: "/posts/create",
        element: <PostCreate />,
      },
      {
        path: "/posts2/create",
        element: <Post2Create />,
      },
      {
        path: "/posts/:postId",
        element: <PostDetail />,
      },
    ],
  },
  {
    path: "/not-found",
    element: <NotFound></NotFound>,
  },
]);

export default router;

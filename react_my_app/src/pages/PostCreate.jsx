import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Navigate, useNavigate } from 'react-router-dom';
import postApi from '../api/postsApi';

export default function PostCreate() {
  const navigate = useNavigate();

  const { isLoggedIn } = useSelector((state) => state.auth);
  const [formData, setFormData] = useState({
    title: '',
    content: '',
    tags: [],
  });
  const [error, setError] = useState('');

  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/');
    }
  }, [isLoggedIn]);

  function handleFormInput(e) {
    const key = e.target.name;
    let inputValue = e.target.value;
    if (key == 'tags') {
      inputValue = inputValue.split(' ');
    }

    setFormData({
      ...formData,
      [key]: inputValue,
    });
  }

  function handleSubmit(e) {
    e.preventDefault();
    setError('');

    async function createPost() {
      try {
        // 게시글 생성 로직
        // const frm = new FormData();
        // if (formData.tags.length > 0) {
        //   formData.tags = formData.tags.split(' ');
        // }
        // const datas = {
        //   ...formData,
        //   tags: formData.tags || [],
        // };
        // frm.append(
        //   'data',
        //   new Blob([JSON.stringify(datas)], {
        //     type: 'application/json',
        //   })
        // );

        // const response = await postApi.createPost(frm);
        const response = await postApi.createPost(formData);
        const data = response.data;
        const id = data.data.id;
        navigate(`/posts/${id}`);
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

        <label>
          태그 :
          <textarea
            id="tags"
            name="tags"
            required
            value={formData.tags}
            onChange={handleFormInput}
          ></textarea>
        </label>

        <label>
          <input type="file" id="image" name="image" />
        </label>

        <button>제출</button>
      </form>
    </>
  );
}

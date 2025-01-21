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
  const [image, setImage] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/');
    }
  }, [isLoggedIn]);

  function handleFormInput(e) {
    const key = e.target.name;
    const inputValue = e.target.value;

    setFormData({
      ...formData,
      [key]: key === 'tags' ? inputValue.split(' ') : inputValue,
    });
  }

  // function handleFileInput(e) {
  //   setImage(e.target.files[0]);
  // }

  function handleSubmit(e) {
    e.preventDefault();
    setError('');

    async function createPost() {
      try {
        const formDataObj = new FormData();
        formDataObj.append(
          'data',
          new Blob([JSON.stringify(formData)], { type: 'application/json' })
        );
        if (image) {
          formDataObj.append('image', image);
        }

        const response = await postApi.createPost(formDataObj);
        const { id } = response.data.data;
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
          태그(공백으로 구분) :
          <textarea
            id="tags"
            name="tags"
            required
            value={formData.tags}
            onChange={handleFormInput}
          ></textarea>
        </label>

        {/* <label>
          이미지 :
          <input
            type="file"
            id="image"
            name="image"
            accept="image/png, image/jpg"
            onChange={handleFileInput}
          />
        </label> */}

        <button type="submit">제출</button>
      </form>
    </>
  );
}

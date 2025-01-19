import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  token: localStorage.getItem('token'),
  isLoggedIn: !!localStorage.getItem('token'),
  user: {
    name: localStorage.getItem('userName'),
  },
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    login: (state, action) => {
      state.token = action.payload.token;
      state.isLoggedIn = true;
      state.user.name = action.payload.username;
      localStorage.setItem('token', action.payload.token);
      localStorage.setItem('userName', action.payload.username);
    },
    logout: (state, action) => {
      state.token = null;
      state.isLoggedIn = false;
      state.user.name = null;
      localStorage.removeItem('token');
      localStorage.removeItem('userName');
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;

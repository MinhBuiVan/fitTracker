import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import BASE_API from "../../mock/api";
import axios from "axios";
import { toast } from "react-toastify";
import dayjs from "dayjs";

const API = `${BASE_API}/api/auth`;

const initialState = {
  isLoading: false,
  user: {},
  error: null,
  isLogin: false,
};

export const signUp = createAsyncThunk(
  "auth/signUp",
  async ({ userData, navigate }, { rejectWithValue }) => {
    try {
      const res = await axios.post(`${API}/signup`, userData);
      toast.success("Sign Up Successfully!");
      navigate("/auth/login");
      return res.data;
    } catch (error) {
      return rejectWithValue(error.response.data.message);
    }
  }
);

export const login = createAsyncThunk(
  "auth/login",
  async ({ loginData }, { rejectWithValue }) => {
    try {
      const res = await axios.post(`${API}/signin`, loginData);
      toast.success("Log In Successfully!");
      localStorage.setItem("token", res.data.token);
      const user = {};
      user.id = res.data.id;
      user.username = res.data.username;
      user.firstName = res.data.firstName;
      user.lastName = res.data.lastName;
      user.email = res.data.email;
      user.birthday = dayjs(res.data.birthday).format("YYYY-MM-DD");
      user.gender = res.data.gender;
      user.country = res.data.country;
      user.city = res.data.city;
      user.weight = res.data.weight;
      user.height = res.data.height;
      user.image = res.data.image;
      user.role = res.data.roles[0];
      localStorage.setItem("user", JSON.stringify(user));
      return res.data;
    } catch (error) {
      return rejectWithValue(error.response.data.message);
    }
  }
);

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logout: (state) => {
      state.user = {};
      state.isLogin = false;
    },
    loadUserData: (state) => {
      state.user = JSON.parse(localStorage.getItem("user"));
    },
    setIsLogin: (state) => {
      state.isLogin = localStorage.getItem("token") !== null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(signUp.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(signUp.fulfilled, (state) => {
        state.isLoading = false;
      })
      .addCase(signUp.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      })
      .addCase(login.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(login.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isLogin = true;

        //get user data
        state.user.id = action.payload.id;
        state.user.username = action.payload.username;
        state.user.firstName = action.payload.firstName;
        state.user.lastName = action.payload.lastName;
        state.user.email = action.payload.email;
        state.user.birthday = dayjs(action.payload.birthday).format(
          "YYYY-MM-DD"
        );
        state.user.gender = action.payload.gender;
        state.user.country = action.payload.country;
        state.user.city = action.payload.city;
        state.user.weight = action.payload.weight;
        state.user.height = action.payload.height;
        state.user.image = action.payload.image;
        state.user.role = action.payload.roles[0];
      })
      .addCase(login.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      });
  },
});

export default authSlice.reducer;
export const { logout, loadUserData, setIsLogin } = authSlice.actions;

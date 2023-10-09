import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import BASE_API from "../../mock/api";
import axios from "axios";
import { toast } from "react-toastify";

const initialState = {
  isLoading: false,
  workouts: [],
  workoutDetails: {},
  error: null,
};

export const getWorkouts = createAsyncThunk(
  "user/getWorkouts",
  async (_, { rejectWithValue }) => {
    try {
      const token = localStorage.getItem("token");
      const res = await axios.get(`${BASE_API}/api/workouts`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return res.data;
    } catch (error) {
      return rejectWithValue(error.response.data.message);
    }
  }
);

export const getWorkout = createAsyncThunk(
  "user/getWorkout",
  async ({ id }, { rejectWithValue }) => {
    try {
      const token = localStorage.getItem("token");
      const res = await axios.get(`${BASE_API}/api/workouts/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      return res.data;
    } catch (error) {
      return rejectWithValue(error.response.data.message);
    }
  }
);

export const createWorkout = createAsyncThunk(
  "user/createWorkout",
  async ({ workoutData, navigate }, { rejectWithValue }) => {
    try {
      const token = localStorage.getItem("token");
      const res = await axios.post(`${BASE_API}/api/workouts`, workoutData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("New workout created successfully!");
      navigate("/dashboard");
      return res.data;
    } catch (error) {
      return rejectWithValue(error.response.data.message);
    }
  }
);

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getWorkouts.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(getWorkouts.fulfilled, (state, action) => {
        state.isLoading = false;
        state.workouts = action.payload;
      })
      .addCase(getWorkouts.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      })
      .addCase(createWorkout.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(createWorkout.fulfilled, (state, action) => {
        state.isLoading = false;
        state.workouts.add(action.payload);
      })
      .addCase(createWorkout.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      })
      .addCase(getWorkout.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(getWorkout.fulfilled, (state, action) => {
        state.isLoading = false;
        state.workoutDetails = action.payload;
      })
      .addCase(getWorkout.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      });
  },
});

export default userSlice.reducer;

import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./features/authSlice";
import adminReducer from "./features/adminSlice";
import userReducer from "./features/userSlice";

const store = configureStore({
  reducer: {
    auth: authReducer,
    admin: adminReducer,
    user: userReducer,
  },
});

export default store;

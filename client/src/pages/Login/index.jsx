import {
  Box,
  FormControl,
  FormHelperText,
  IconButton,
  InputAdornment,
  InputLabel,
  OutlinedInput,
  TextField,
  Typography,
} from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import StyledButton from "../../components/StyledButton";
import { useFormik } from "formik";
import * as yup from "yup";
import { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useDispatch, useSelector } from "react-redux";
import { login } from "../../store/features/authSlice";

const validationSchema = yup.object({
  username: yup.string("Enter your username").required("Username is required"),
  password: yup
    .string("Enter your password")
    .matches(
      "^[a-zA-Z0-9]{6,}$",
      "Password must has length of 6 and has at least 1 number"
    )
    .required("Password is required"),
});

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema,
    onSubmit: (val) => {
      dispatch(login({ loginData: val }));
    },
  });

  const { user, isLogin } = useSelector((state) => state.auth);

  const handleClickShowPassword = () => setShowPassword((prev) => !prev);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  useEffect(() => {
    if (isLogin) {
      if (user.role === "ROLE_ADMIN") navigate("/admin");
      else navigate("/dashboard");
    }
  }, [isLogin, navigate, user, dispatch]);

  return (
    <Box
      sx={{
        maxWidth: "500px",
        width: "95%",
        marginInline: "auto",
        textAlign: "center",
      }}
      mt={5}
      mb={20}
    >
      <Typography variant="h4" fontWeight={700} mt={5}>
        Log In
      </Typography>
      <Typography mb={3} fontSize={16}>
        Don&apos;t have an account?{" "}
        <Link to="/auth/signup" style={{ color: "#000", fontWeight: 500 }}>
          Sign Up
        </Link>
      </Typography>
      <form onSubmit={formik.handleSubmit}>
        <TextField
          fullWidth
          id="username"
          label="Username"
          value={formik.values.username}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.username && Boolean(formik.errors.username)}
          helperText={formik.touched.username && formik.errors.username}
          sx={{ marginBottom: "20px" }}
        />
        <FormControl
          fullWidth
          variant="outlined"
          sx={{ marginBottom: "20px" }}
          error={formik.touched.password && Boolean(formik.errors.password)}
        >
          <InputLabel htmlFor="password">Password</InputLabel>
          <OutlinedInput
            id="password"
            type={showPassword ? "text" : "password"}
            value={formik.values.password}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                  edge="end"
                >
                  {showPassword ? (
                    <FontAwesomeIcon
                      icon="fa-regular fa-eye-slash"
                      size="2xs"
                    />
                  ) : (
                    <FontAwesomeIcon icon="fa-regular fa-eye" size="2xs" />
                  )}
                </IconButton>
              </InputAdornment>
            }
            label="Password"
          />
          <FormHelperText>
            {formik.touched.password && formik.errors.password}
          </FormHelperText>
        </FormControl>
        <Typography
          sx={{ "& > a": { color: "#000", fontWeight: 500, fontSize: 14 } }}
        >
          <Link to="/auth/forgot_password">Forgot Password?</Link>
        </Typography>
        <Box width="100%" textAlign={"center"} mt={3}>
          <StyledButton type="submit" mode="dark" sx={{ width: "300px" }}>
            LOG IN
          </StyledButton>
        </Box>
      </form>
    </Box>
  );
};

export default Login;

import * as yup from "yup";
import { useFormik } from "formik";
import {
  Box,
  FormControl,
  FormHelperText,
  IconButton,
  InputAdornment,
  InputLabel,
  MenuItem,
  OutlinedInput,
  TextField,
  Typography,
} from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import dayjs from "dayjs";
import country_list from "../../mock/country";
import StyledButton from "../../components/StyledButton";
import { useDispatch, useSelector } from "react-redux";
import { signUp } from "../../store/features/authSlice";
import { toast } from "react-toastify";

const validationSchema = yup.object({
  username: yup
    .string("Enter your username")
    .min(6, "Username must contains at least 6 characters")
    .max(20, "Username max length is 20")
    .required("Username is required"),
  firstName: yup
    .string("Enter your first name")
    .required("First name is required"),
  lastName: yup
    .string("Enter your last name")
    .required("Last name is required"),
  email: yup
    .string("Enter your email")
    .email("Enter a valid email")
    .required("Email is required"),
  password: yup
    .string("Enter your password")
    .matches(
      "^[a-zA-Z0-9]{6,}$",
      "Password must has length of 6 and has at least 1 number"
    )
    .required("Password is required"),
  birthday: yup.date().required("Birthdate is required").nonNullable(),
  gender: yup
    .string()
    .oneOf(["male", "female"])
    .required("Geneder is required"),
  country: yup.string().oneOf(country_list).required("Country is required"),
  city: yup.string().required("City is required"),
});

const SignUp = () => {
  const [showPassword, setShowPassword] = useState(false);
  const dispatch = useDispatch();

  const handleClickShowPassword = () => setShowPassword((prev) => !prev);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const { error, isLogin } = useSelector((state) => state.auth);
  const navigate = useNavigate();

  useEffect(() => {
    if (isLogin) navigate("/dashboard");
  }, [navigate, isLogin]);

  useEffect(() => {
    if (error !== null) {
      toast.error(error);
    }
  }, [error]);

  const formik = useFormik({
    initialValues: {
      username: "",
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      birthday: dayjs(Date.now()),
      gender: "",
      country: "",
      city: "",
    },
    validationSchema,
    onSubmit: (val) => {
      const userData = {
        ...val,
        birthday: val.birthday.format("YYYY-MM-DD"),
      };
      try {
        dispatch(signUp({ userData, navigate }));
      } catch (error) {
        toast.error(error);
      }
    },
  });
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
        Welcome to FitTracker
      </Typography>
      <Typography mb={3} fontSize={16}>
        Already a member?{" "}
        <Link to="/auth/login" style={{ color: "#000", fontWeight: 500 }}>
          Log In
        </Link>
      </Typography>
      <form onSubmit={formik.handleSubmit} style={{ textAlign: "left" }}>
        <TextField
          required
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
        <TextField
          required
          fullWidth
          id="firstName"
          label="First Name"
          value={formik.values.firstName}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.firstName && Boolean(formik.errors.firstName)}
          helperText={formik.touched.firstName && formik.errors.firstName}
          sx={{ marginBottom: "20px" }}
        />
        <TextField
          required
          fullWidth
          id="lastName"
          label="Last Name"
          value={formik.values.lastName}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.lastName && Boolean(formik.errors.lastName)}
          helperText={formik.touched.lastName && formik.errors.lastName}
          sx={{ marginBottom: "20px" }}
        />
        <TextField
          fullWidth
          required
          label="Email"
          id="email"
          type="email"
          value={formik.values.email}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.email && Boolean(formik.errors.email)}
          helperText={formik.touched.email && formik.errors.email}
          sx={{ marginBottom: "20px" }}
        />
        <FormControl
          required
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
        <DatePicker
          disableFuture
          label="Birthdate"
          format="YYYY/MM/DD"
          value={formik.values.birthday}
          onChange={(date) => {
            formik.setFieldValue("birthday", date);
          }}
          sx={{ width: "100%", marginBottom: "20px" }}
          slotProps={{
            textField: {
              required: true,
              variant: "outlined",
              error: formik.touched.birthday && Boolean(formik.errors.birthday),
              helperText: formik.touched.birthday && formik.errors.birthday,
            },
          }}
        />
        <TextField
          required
          fullWidth
          select
          id="gender"
          label="Gender"
          value={formik.values.gender}
          onChange={formik.handleChange("gender")}
          error={formik.touched.gender && Boolean(formik.errors.gender)}
          helperText={formik.touched.gender && formik.errors.gender}
          sx={{ marginBottom: "20px" }}
        >
          <MenuItem selected value={"male"}>
            Male
          </MenuItem>
          <MenuItem value={"female"}>Female</MenuItem>
        </TextField>
        <TextField
          required
          fullWidth
          select
          id="country"
          label="Country/Region"
          value={formik.values.country}
          onChange={formik.handleChange("country")}
          error={formik.touched.country && Boolean(formik.errors.country)}
          helperText={formik.touched.country && formik.errors.country}
          sx={{ marginBottom: "20px" }}
        >
          {country_list.map((country, idx) => (
            <MenuItem key={idx} value={country}>
              {country}
            </MenuItem>
          ))}
        </TextField>
        <TextField
          required
          fullWidth
          id="city"
          label="City"
          value={formik.values.city}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.city && Boolean(formik.errors.city)}
          helperText={formik.touched.city && formik.errors.city}
          sx={{ marginBottom: "50px" }}
        />
        <Box width="100%" textAlign={"center"}>
          <StyledButton type="submit" mode="dark" sx={{ width: "300px" }}>
            SIGN UP
          </StyledButton>
        </Box>
      </form>
    </Box>
  );
};

export default SignUp;

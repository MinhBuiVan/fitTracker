import { Box, TextField, Typography } from "@mui/material";
import { useFormik } from "formik";
import { Link } from "react-router-dom";
import * as yup from "yup";
import StyledButton from "../../components/StyledButton";

const validationSchema = yup.object({
  email: yup
    .string("Enter your email")
    .email("Enter a valid email")
    .required("Email is required"),
});

const ForgotPassword = () => {
  const formik = useFormik({
    initialValues: {
      email: "",
    },
    validationSchema,
    onSubmit: (val) => {
      console.log(val);
    },
  });

  return (
    <Box
      sx={{
        width: "100%",
        maxWidth: "500px",
        marginInline: "auto",
        textAlign: "center",
      }}
      mt={5}
    >
      <Typography variant="h4" mb={5} fontWeight={700}>
        Reset Password
      </Typography>
      <Typography fontSize={14} mb={5}>
        Forgot your password? No problem. Enter in your email address and
        we&apos;ll send you a link to reset it.
      </Typography>
      <form onSubmit={formik.handleSubmit}>
        <TextField
          fullWidth
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
        <Box width="100%" textAlign={"center"} my={2}>
          <StyledButton type="submit" mode="dark" sx={{ width: "300px" }}>
            RESET PASSWORD
          </StyledButton>
        </Box>
      </form>
      <Typography
        fontSize={14}
        fontWeight={600}
        sx={{ "& a": { color: "#000" } }}
      >
        <Link to="/auth/login">Back to Log In</Link>
      </Typography>
    </Box>
  );
};

export default ForgotPassword;

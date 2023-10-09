/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import { Box, Typography } from "@mui/material";

const Header = ({ title, subtitle }) => {
  return (
    <Box mb="30px">
      <Typography varaint="h2" fontWeight="bold" mb={2} fontSize={26}>
        {title}
      </Typography>
      <Typography fontWeight={500} color="#d2042d">
        {subtitle}
      </Typography>
    </Box>
  );
};

export default Header;

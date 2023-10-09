import { Box } from "@mui/material";
import Navbar from "../../Navbar";
import { Outlet } from "react-router-dom";
import Footer from "../../Footer.jsx";

const MainLayout = () => {
  return (
    <>
      <Navbar />
      <Box
        sx={{
          minHeight: "100vh",
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
        }}
      >
        <Box
          sx={{
            width: "100%",
            maxWidth: "1440px",
            mx: "auto",
            flex: "1 1 auto",
          }}
        >
          <Outlet />
        </Box>
        <Footer />
      </Box>
    </>
  );
};

export default MainLayout;

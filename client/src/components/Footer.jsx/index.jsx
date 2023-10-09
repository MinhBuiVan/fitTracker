import { Box, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { grey } from "@mui/material/colors";

const Footer = () => {
  return (
    <Box sx={{ width: "100%" }}>
      <Box
        sx={{
          padding: "50px",
          paddingInline: { sm: 0, md: "200px" },
          display: "flex",
          justifyContent: "space-evenly",
          flexDirection: { xs: "column", sm: "row" },
          backgroundColor: grey[200],
        }}
      >
        <Box sx={{ textAlign: { xs: "center", sm: "left" } }}>
          <img src="/brand.svg" alt="fit tracker brand" width="180px" />
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            "& > p": { fontSize: "14px", cursor: "pointer" },
            "& > p:hover": { fontWeight: 600 },
          }}
        >
          <Typography variant="h6" fontSize={14} mb={2}>
            Help
          </Typography>
          <Typography>Log In/Register</Typography>
          <Typography>Support</Typography>
          <Typography>Feedback</Typography>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            "& > p": { fontSize: "14px", cursor: "pointer" },
            "& > p:hover": { fontWeight: 600 },
          }}
        >
          <Typography variant="h6" fontSize={14} mb={2}>
            About
          </Typography>
          <Typography>Contact Us</Typography>
          <Typography>Join Our Team</Typography>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            "& > p": { fontSize: "14px", cursor: "pointer" },
            "& > p:hover": { fontWeight: 600 },
          }}
        >
          <Typography variant="h6" fontSize={14} mb={2}>
            Connect
          </Typography>
          <Typography>
            <FontAwesomeIcon icon="fa-brands fa-instagram" /> Instagram
          </Typography>
          <Typography>
            <FontAwesomeIcon icon="fa-brands fa-facebook-f" /> Facebook
          </Typography>
          <Typography>
            <FontAwesomeIcon icon="fa-brands fa-x-twitter" /> Twitter
          </Typography>
          <Typography>
            <FontAwesomeIcon icon="fa-brands fa-youtube" /> Youtube
          </Typography>
        </Box>
      </Box>
      <Box
        sx={{
          padding: "20px",
          paddingInline: { sm: 0, md: "200px" },
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          flexDirection: { xs: "column", sm: "row" },
          backgroundColor: "#000",
          color: "#fff",
          "& > p": { fontSize: "14px", letterSpacing: 2, marginLeft: 1 },
        }}
      >
        <img src="/logo-white.png" alt="fit tracker brand" width="40px" />
        <Typography>@2023 FitTracker D7 KTITS</Typography>

        <Typography>
          Copyright belongs to
          <a
            style={{ marginLeft: 2, color: "#fff" }}
            href="https://www.mapmyfitness.com/"
          >
            mapmyfitness.com
          </a>
        </Typography>
      </Box>
    </Box>
  );
};

export default Footer;

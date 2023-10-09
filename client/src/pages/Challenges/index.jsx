import {
  Box,
  Typography,
  Button,
  CardActionArea,
  CardActions,
} from "@mui/material";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import StyledButton from "../../components/StyledButton";
const Challenges = () => {
  return (
    <Box>
      <Box
        sx={{
          backgroundColor: "#333333",
          color: "white",
          width: "100%",
          height: 310,
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          // padding: 10
        }}
      >
        <Box
          sx={{
            // padding: "80px 56px",
            alignItems: "center",
            width: "90%",
          }}
        >
          <Typography
            sx={{
              fontSize: { xs: "60px", md: "80px" },
              fontWeight: 700,
              color: { md: "#fff" },
              marginBottom: "1%",
            }}
            mb={4}
            component="h1"
          >
            CHALLENGE YOURSELF
          </Typography>
          <Typography
            component="span"
            sx={{
              color: { md: "#fff" },
            }}
            width={630}
            display="inline-block"
            mb={4}
          >
            Participate in challenges to set goals, motivate yourself, and cheer
            on your friends. Prizes are given for certain challenges, so don't
            wait—join a challenge today!
          </Typography>
        </Box>
      </Box>
      <Box
        sx={{
          margin: "30px 30px",
          fontFamily:
            "Neue Plak UA, Helvetica Neue, Helvetica, Arial, sans-serif",
        }}
      >
        <Typography
          sx={{
            fontSize: { xs: "10px", md: "30px" },
            fontWeight: 400,
            color: "dark",
            marginTop: "50px",
            marginLeft: "25px",
          }}
          mb={4}
          // component="h5"
        >
          Suggested
        </Typography>
        <Box
          sx={{
            marginLeft: "2%",
          }}
        >
          <Card
            sx={{
              maxWidth: "400px",
              "&:hover": {
                boxShadow: "0 0 10px rgba(0, 0, 0, 0.5)", // Tạo hiệu ứng shadow khi hover
                border: "1px solid black", // Thêm viền đen khi hover
              },
            }}
          >
            <CardActionArea>
              <CardMedia
                component="img"
                height="140"
                image="/img-12.jpg"
                alt="green iguana"
              />
              <CardContent>
                <Typography
                  gutterBottom
                  component="div"
                  sx={{ fontSize: 30, fontWeight: "bold" }}
                >
                  You VS The Year 2023
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Ends in 132 days
                </Typography>
                <Typography gutterBottom sx={{ fontSize: 20 }} component="div">
                  Run or walk 1,000 km in 2023
                </Typography>
              </CardContent>
            </CardActionArea>
            <CardActions>
              <Box width="100%" textAlign={"center"} mt={3}>
                <StyledButton
                  type="submit"
                  mode="dark"
                  sx={{ width: "85%", marginBottom: "5px" }}
                >
                  JOIN CHALLENGE
                </StyledButton>
              </Box>
            </CardActions>
          </Card>
          <Typography
            sx={{
              fontSize: { xs: "10px", md: "30px" },
              fontWeight: 400,
              color: "dark",
              margin: "50px 0",
            }}
            mb={4}
            // component="h5"
          >
            Recent Milestones
          </Typography>
          <img
            src="/images/huyhieu.png"
            style={{
              objectFit: "fill",
              width: "4%",
              marginBottom: "100px",
            }}
            alt="image-banner"
          />
        </Box>
      </Box>
    </Box>
  );
};

export default Challenges;

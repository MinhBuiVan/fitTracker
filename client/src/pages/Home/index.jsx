import { Box, Typography } from "@mui/material";
import StyledButton from "../../components/StyledButton";
import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { useEffect } from "react";
import { useSelector } from "react-redux";

const HomePage = () => {
  const { isLogin } = useSelector((state) => state.auth);

  const navigate = useNavigate();

  useEffect(() => {
    if (isLogin) {
      navigate("/dashboard");
    }
  }, [isLogin, navigate]);
  return (
    <Box sx={{ width: "95%", maxWidth: "1440px", marginInline: "auto" }}>
      {/* HEADER  */}
      <Box position="relative">
        <img
          src="/bg.webp"
          style={{
            objectFit: "fill",
            width: "100%",
          }}
          alt="image-banner"
        />
        <Box
          sx={{
            position: { md: "absolute" },
            bottom: 200,
            right: 50,
            display: "flex",
            flexDirection: "column",
            alignItems: { xs: "center", md: "end" },
          }}
        >
          <Typography
            sx={{
              fontSize: { xs: "60px", md: "80px" },
              fontWeight: 700,
              color: { md: "#fff" },
            }}
            mb={4}
            component="h1"
          >
            REACH YOUR BEST
          </Typography>
          <Typography
            component="span"
            sx={{
              color: { md: "#fff" },
            }}
            width={400}
            display="inline-block"
            mb={4}
          >
            Whether you’re training for a marathon or your biggest season yet,
            we’re here to help you make serious progress.
          </Typography>
          <Link to="/auth/signup">
            <StyledButton
              sx={{
                width: "320px",
                backgroundColor: { xs: "#000", md: "#fff" },
                color: { xs: "#fff", md: "#000" },
              }}
              mode="light"
            >
              SIGN UP
            </StyledButton>
          </Link>
          <Typography
            variant="p"
            sx={{
              fontSize: 16,
              marginRight: 10,
              color: "#fff",
              "& a": { color: "#fff", fontWeight: 500 },
            }}
            mt={2}
          >
            Already a member? <Link to="/auth/login">Log In</Link>
          </Typography>
        </Box>
      </Box>
      {/* SECTION INTRO  */}
      <Box width="1100px" maxWidth="100%" sx={{ marginInline: "auto" }} mt={10}>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            flexDirection: { xs: "column-reverse", md: "row" },
            alignItems: "center",
          }}
          width="100%"
        >
          <Box
            sx={{
              width: "100%",
              maxWidth: "400px",
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-around",
              alignItems: { xs: "center", md: "start" },
              textAlign: { xs: "center", md: "left" },
            }}
          >
            <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                "& > span": {
                  fontSize: "50px",
                  fontWeight: 700,
                  color: "#000",
                  lineHeight: 1,
                },
                width: "100%",
              }}
              mb={4}
            >
              <Typography component="span">SET GOALS.</Typography>
              <Typography component="span">LOG WORKOUTS.</Typography>
              <Typography component="span">STAY ON TRACK.</Typography>
            </Box>
            <Typography component="p" fontSize={16} mb={4}>
              Easily track your Workouts, set Training Plans, and discover new
              Workout Routines to crush your goals.
            </Typography>
            <Link to="/auth/login">
              <StyledButton
                sx={{ width: "320px", margin: "0px !important" }}
                mode="dark"
              >
                GET STARTED
              </StyledButton>
            </Link>
          </Box>
          <Box
            sx={{
              textAlign: "right",
              "& img": {
                maxHeight: "400px",
                objectFit: "cover",
              },
            }}
          >
            <img src="/img-11.png" alt="image" />
          </Box>
        </Box>
        <Box mt={10}>
          <Box
            sx={{
              backgroundColor: "#000",
              padding: "25px",
              borderRadius: "5px",
              display: "flex",
              alignItems: { xs: "start", md: "center" },
              flexDirection: { xs: "column", md: "row" },
            }}
          >
            <Box
              sx={{
                width: "100%",
                maxWidth: { sx: "100%", md: "600px" },
                height: "300px",
                "& > img": {
                  width: "100%",
                  height: "100%",
                  objectFit: "cover",
                },
              }}
            >
              <img src="/img-12.jpg" width="100%" alt="challenge-image" />
            </Box>
            <Box sx={{ padding: { xs: 0, md: 5 } }}>
              <Typography
                component="h5"
                color="#fff"
                fontWeight={700}
                fontSize={35}
                mb={3}
              >
                YOU VS THE YEAR 2023
              </Typography>
              <Typography component="p" color="#fff" fontSize={16}>
                Run or walk 1,000km in 2023.
              </Typography>
            </Box>
          </Box>
        </Box>
        <Box
          sx={{
            width: "5px",
            height: "5px",
            borderRadius: 9999,
            backgroundColor: "#000",
            marginInline: "auto",
          }}
          my={5}
        ></Box>
        <Box textAlign="center">
          <Typography fontSize={45} color="#000" fontWeight={700} mb={3}>
            PUSH YOUR LIMITS
          </Typography>
          <Typography
            variant="p"
            display="block"
            color="#000"
            fontSize={16}
            mb={3}
          >
            Hit milestones and PR’s by taking on a new challenge every month.
          </Typography>
          <StyledButton sx={{ width: "280px" }} mode="dark">
            JOIN A CHALLENGE
          </StyledButton>
        </Box>
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            flexDirection: { xs: "column-reverse", md: "row" },
          }}
          mt={10}
        >
          <Box
            sx={{
              width: "100%",
              maxWidth: { md: "400px" },
              color: "#000",
              textAlign: { xs: "center", md: "left" },
            }}
          >
            <Typography
              fontSize={55}
              color="#000"
              fontWeight={700}
              lineHeight={1}
              mb={5}
              mt={2}
            >
              BUILD TO MAKE YOU BETTER
            </Typography>
            <Box
              sx={{
                width: "300px",
                height: "100%",
                "& > h6,p": {
                  marginBottom: "15px",
                },
                "& > h6": {
                  fontWeight: 600,
                },
                marginInline: { xs: "auto", md: "0" },
              }}
            >
              <Typography variant="h6">Smarter Training</Typography>
              <Typography fontSize={16}>
                Turn your phone or smartwatch into your coach—track your
                workouts and get tons of data and tips to help you run better.
              </Typography>
              <Typography variant="h6">Custom Workouts</Typography>
              <Typography fontSize={16}>
                5K? Marathon? No matter where you’re at, get personalized
                Training Plans built just for you and your goals.
              </Typography>
              <Typography variant="h6">Strong Community</Typography>
              <Typography fontSize={16}>
                Create your own custom challenges to push yourself and your
                friends. For extra motivation, reach out and find support from
                the entire UA MapMyRun™ community.
              </Typography>
            </Box>
          </Box>
          <Box>
            <img src="/img-13.png" width="100%" alt="image" />
          </Box>
        </Box>
      </Box>
      {/* SECTION TRAFFIC INFO  */}
      <Box
        sx={{
          backgroundColor: "#000",
          borderRadius: "5px",
          padding: "100px 0",
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            flexDirection: { xs: "column", md: "row" },
            marginInline: { xs: 0, md: "200px" },
          }}
        >
          <Box sx={{ textAlign: "center", color: "#fff" }} py={2} width="350px">
            <FontAwesomeIcon icon="fa-solid fa-road" size="2xl" color="red" />
            <Typography mt={2} fontSize={16} fontWeight={600}>
              MILES LOGGED
            </Typography>
            <Typography fontSize={80} fontWeight={700}>
              5.2
            </Typography>
            <Typography fontWeight={700} fontSize={22}>
              Billion
            </Typography>
          </Box>
          <Box sx={{ textAlign: "center", color: "#fff" }} py={2} width="350px">
            <FontAwesomeIcon
              icon="fa-solid fa-user-group"
              size="2xl"
              color="red"
            />
            <Typography mt={2} fontSize={16} fontWeight={600}>
              USERS ACTIVE
            </Typography>
            <Typography fontSize={80} fontWeight={700}>
              14
            </Typography>
            <Typography fontWeight={700} fontSize={22}>
              Million
            </Typography>
          </Box>
          <Box sx={{ textAlign: "center", color: "#fff" }} py={2} width="350px">
            <FontAwesomeIcon
              icon="fa-solid fa-person-running"
              size="2xl"
              color="red"
            />
            <Typography mt={2} fontSize={16} fontWeight={600}>
              WORKOUTS LOGGED
            </Typography>
            <Typography fontSize={80} fontWeight={700}>
              1.2
            </Typography>
            <Typography fontWeight={700} fontSize={22}>
              Billion
            </Typography>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default HomePage;

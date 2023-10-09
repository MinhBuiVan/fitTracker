import { Box, Typography } from "@mui/material";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { getWorkout } from "../../store/features/userSlice";
import Loading from "../../components/Loading";
import dayjs from "dayjs";

const WorkoutDetails = () => {
  const { workoutId } = useParams();
  const dispatch = useDispatch();
  const { isLoading, workoutDetails } = useSelector((state) => state.user);
  const { isLogin } = useSelector((state) => state.auth);
  const navigate = useNavigate();

  useEffect(() => {
    dispatch(getWorkout({ id: workoutId }));
  }, [dispatch, workoutId]);

  useEffect(() => {
    if (!isLogin) {
      navigate("/auth/login");
    }
  }, [navigate, isLogin]);

  if (isLoading) {
    return <Loading />;
  }

  const { activity, user, name, caloriesBurned, timeStart, timeEnd, dateTime } =
    workoutDetails;
  return (
    <Box
      display="flex"
      border="1px solid #d9d9d9"
      width="95%"
      maxWidth={1000}
      mx={"auto"}
      mt={5}
      p={3}
    >
      <Box
        display="flex"
        gap={"30px"}
        flexDirection={"column"}
        width="65%"
        borderRight={"1px solid #d9d9d9"}
      >
        <Typography fontSize={28} fontWeight={700}>
          {name}
        </Typography>

        <Box display="flex" alignItems={"center"} gap={"10px"}>
          <Box width="20px" height="20px">
            <img
              src={`/activities/${activity?.image}`}
              style={{
                display: "block",
                width: "100%",
                height: "100%",
                objectFit: "cover",
              }}
            />
          </Box>
          <Typography fontSize={18} color="#a9a9a9">
            {activity?.name}
          </Typography>
        </Box>
        <Box display="flex" gap={"60px"} alignItems={"center"}>
          <Box>
            <Typography fontWeight={500} fontSize={20}>
              Time start:{" "}
            </Typography>
            <Typography fontSize={24} fontWeight={700}>
              {dayjs(timeStart).format("YYYY-MM-DD HH:mm:ss")}
            </Typography>
          </Box>
          <Box>
            <Typography fontWeight={500} fontSize={20}>
              Time end:{" "}
            </Typography>
            <Typography fontSize={24} fontWeight={700}>
              {dayjs(timeEnd).format("YYYY-MM-DD HH:mm:ss")}
            </Typography>
          </Box>
          <Box>
            <Typography fontWeight={500} fontSize={20}>
              Calories burned:
            </Typography>
            <Typography fontSize={24} fontWeight={700}>
              {caloriesBurned} kal
            </Typography>
          </Box>
        </Box>
      </Box>
      <Box width="35%" px={5}>
        <Box display="flex" gap={"20px"} alignItems={"center"}>
          <Box sx={{ width: "50px", height: "50px", borderRadius: "50%" }}>
            <img
              src={`/${user?.image}`}
              style={{
                objectFit: "cover",
                width: "100%",
                height: "100%",
                borderRadius: "50%",
              }}
            />
          </Box>
          <Typography fontWeight={700}>
            {user?.lastName + " " + user?.firstName}
          </Typography>
        </Box>
        <Typography>
          Created at: {dayjs(dateTime).format("YYYY-MM-DD HH:mm:ss")}
        </Typography>
      </Box>
    </Box>
  );
};

export default WorkoutDetails;

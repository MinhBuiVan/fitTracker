import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Box, Button, IconButton, Typography } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import dayjs from "dayjs";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import Loading from "../../components/Loading";
import { getWorkouts } from "../../store/features/userSlice";

const Dashboard = () => {
  const [monthly, setMonthly] = useState(dayjs().format("MMM YYYY"));

  const prevWeek = () => {
    const parsedDate = dayjs(monthly, "MMM YYYY");
    const prevMonth = parsedDate.subtract(1, "month");
    setMonthly(prevMonth.format("MMM YYYY"));
  };

  const dispatch = useDispatch();
  const { workouts, isLoading, error } = useSelector((state) => state.user);
  const { isLogin } = useSelector((state) => state.auth);
  const navigate = useNavigate();

  const nextWeek = () => {
    const parsedDate = dayjs(monthly, "MMM YYYY");
    const nextMonth = parsedDate.add(1, "month");
    setMonthly(nextMonth.format("MMM YYYY"));
  };

  const columns = [
    {
      field: "workoutId",
      headerName: "ID",
    },
    {
      field: "activity",
      headerName: "Activity",
      flex: 1,
      renderCell: ({ row: { activity } }) => {
        const { image, name } = activity;
        return (
          <Box display="flex" alignItems={"center"}>
            <Box sx={{ width: "20px", height: "20px" }}>
              <img
                src={`/activities/${image}`}
                style={{ display: "block", width: "100%", height: "100%" }}
              />
            </Box>
            <Typography ml={1}>{name}</Typography>
          </Box>
        );
      },
    },
    {
      field: "distance",
      headerName: "Distance",
      renderCell: ({ row: { distance } }) => {
        return (
          <Typography>{distance === 1 ? null : `${distance} km`}</Typography>
        );
      },
      flex: 1,
    },
    {
      field: "timeStart",
      headerName: "Time start",
      flex: 1,
      renderCell: ({ row: { timeStart } }) => {
        return <span>{dayjs(timeStart).format("YYYY-MM-DD HH:mm:ss")}</span>;
      },
    },
    {
      field: "timeEnd",
      headerName: "Time end",
      flex: 1,
      renderCell: ({ row: { timeEnd } }) => {
        return <span>{dayjs(timeEnd).format("YYYY-MM-DD HH:mm:ss")}</span>;
      },
    },
    {
      field: "pace",
      headerName: "Pace",
      flex: 1,
    },
    {
      field: "caloriesBurned",
      headerName: "Calories Burned",
      flex: 1,
    },
    {
      field: "details",
      headerName: "#",
      renderCell: ({ row: { workoutId } }) => {
        return <Link to={`/workouts/${workoutId}`}>Details</Link>;
      },
    },
  ];

  useEffect(() => {
    dispatch(getWorkouts());
  }, [dispatch]);

  useEffect(() => {
    if (error !== null) {
      toast.error(error);
    }
  }, [error]);

  useEffect(() => {
    if (!isLogin) {
      navigate("/auth/login");
    }
  }, [isLogin, navigate]);

  if (isLoading) {
    return <Loading />;
  }

  return (
    <Box p={10}>
      <Typography fontSize={40} fontWeight={700}>
        Dashboard
      </Typography>
      <Box
        mt={5}
        p={3}
        sx={{
          border: "1px solid #a9a9a9",
        }}
      >
        <Box
          display="flex"
          alignItems={"center"}
          justifyContent={"space-between"}
        >
          <Box display={"flex"} alignItems={"center"}>
            <IconButton onClick={prevWeek} color="#000">
              <FontAwesomeIcon icon="fa-solid fa-chevron-left" size="xs" />
            </IconButton>
            <Typography fontWeight={600} fontSize={22} mx={3}>
              {monthly}
            </Typography>
            <IconButton
              onClick={nextWeek}
              disabled={dayjs(monthly).month() === dayjs().month()}
              color="#000"
            >
              <FontAwesomeIcon icon="fa-solid fa-chevron-right" size="xs" />
            </IconButton>
          </Box>
          <Button
            sx={{
              backgroundColor: "#dedede",
              color: "#000",
              textTransform: "capitalize",
              "&:hover": {
                backgroundColor: "#a9a9a9",
              },
            }}
          >
            <Typography
              fontSize={14}
              fontWeight={500}
              sx={{ "& a": { textDecoration: "none", color: "#000" } }}
            >
              <FontAwesomeIcon
                icon="fa-solid fa-plus"
                style={{ marginRight: "10px" }}
              />{" "}
              <Link to="/add-workout">Add Workout</Link>
            </Typography>
          </Button>
        </Box>
        <Box mt={4} height="75vh">
          <DataGrid
            rows={workouts}
            columns={columns}
            sx={{ textAlign: "center" }}
            getRowId={(row) => row.workoutId}
          />
        </Box>
      </Box>
    </Box>
  );
};

export default Dashboard;

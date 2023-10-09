import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Box, Grid, Typography } from "@mui/material";
import { BarChart, LineChart, PieChart } from "@mui/x-charts";

const xLabels = ["Mon", "Tue", "Web", "Thu", "Fri", "Sat", " Sun"];
const cLabels = [
  "4x4 Workouts",
  "You vs The Year 2023",
  "Swimming 100km",
  "Biking and Be King",
];

const usersTrackDataThisWeek = [728, 875, 1009, 644, 853, 923, 1230];
const usersTrackDataLastWeek = [824, 1203, 905, 867, 812, 1024, 1324];

const newUsersSignedDataThisWeek = [24, 33, 48, 102, 43, 64, 53];
const newUsersSignedDataLastWeek = [14, 21, 19, 38, 65, 70, 121];

const usersSigned = [418, 503, 323, 754];
const userPassed = [187, 329, 216, 375];

const AdminDashboard = () => {
  return (
    <Box p={3}>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          gap: "40px",
          "& > div": { boxShadow: "0 0 5px rgba(0, 0, 0, 0.2)" },
        }}
      >
        <Box
          display="flex"
          flexDirection={"column"}
          gap="10px"
          flex={1}
          backgroundColor="#fff"
          p={2}
          borderRadius={2}
          alignItems={"center"}
        >
          <Typography fontSize={20} fontWeight={700}>
            Users
          </Typography>
          <Box display="flex" alignItems={"center"} color="#d2042d">
            <Typography fontWeight={700} fontSize={50} mr={2}>
              3,352
            </Typography>
            <FontAwesomeIcon icon="fa-solid fa-user-group" size="xl" />
          </Box>
          <Typography fontWeight={500}>On Track</Typography>
        </Box>
        <Box
          display="flex"
          flexDirection={"column"}
          gap="10px"
          flex={1}
          backgroundColor="#fff"
          p={2}
          borderRadius={2}
          alignItems={"center"}
        >
          <Typography fontSize={20} fontWeight={700}>
            New Users
          </Typography>
          <Box display="flex" alignItems={"center"} color="#276221">
            <Typography fontWeight={700} fontSize={50} mr={2}>
              367
            </Typography>
            <FontAwesomeIcon icon="fa-solid fa-user" size="xl" />
          </Box>
          <Typography fontWeight={500}>Signed this Week</Typography>
        </Box>
        <Box
          display="flex"
          flexDirection={"column"}
          gap="10px"
          flex={1}
          backgroundColor="#fff"
          p={2}
          borderRadius={2}
          alignItems={"center"}
        >
          <Typography fontSize={20} fontWeight={700}>
            Workouts
          </Typography>
          <Box display="flex" alignItems={"center"} color="#187bcd">
            <Typography fontWeight={700} fontSize={50} mr={2}>
              167,341
            </Typography>
            <FontAwesomeIcon icon="fa-solid fa-dumbbell" size="xl" />
          </Box>
          <Typography fontWeight={500}>Scheduled</Typography>
        </Box>
        <Box
          display="flex"
          flexDirection={"column"}
          gap="10px"
          flex={1}
          backgroundColor="#fff"
          p={2}
          borderRadius={2}
          alignItems={"center"}
        >
          <Typography fontSize={20} fontWeight={700}>
            Challenges
          </Typography>
          <Box display="flex" alignItems={"center"} color="#9300ff">
            <Typography fontWeight={700} fontSize={50} mr={2}>
              17
            </Typography>
            <FontAwesomeIcon icon="fa-solid fa-person-running" size="xl" />
          </Box>
          <Typography fontWeight={500}>Organized</Typography>
        </Box>
      </Box>
      <Grid container spacing={2} mt={5} backgroundColor="#fff">
        <Grid item xs={12} md={6}>
          <Box>
            <LineChart
              width={500}
              height={400}
              series={[
                { data: usersTrackDataThisWeek, label: "This Week" },
                { data: usersTrackDataLastWeek, label: "Last Week" },
              ]}
              xAxis={[{ scaleType: "point", data: xLabels }]}
            />
            <Typography
              fontWeight={700}
              fontSize={22}
              color="#02B2AF"
              textAlign={"center"}
              mt={5}
            >
              Users Logged Tracking
            </Typography>
          </Box>
          <Box>
            <LineChart
              width={500}
              height={400}
              series={[
                { data: newUsersSignedDataThisWeek, label: "This Week" },
                { data: newUsersSignedDataLastWeek, label: "Last Week" },
              ]}
              xAxis={[{ scaleType: "point", data: xLabels }]}
            />
            <Typography
              fontWeight={700}
              fontSize={22}
              color="#2e96ff"
              textAlign={"center"}
              mt={5}
            >
              New Users Signed
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={12} md={6} borderRadius={5}>
          <Box>
            <PieChart
              width={600}
              height={400}
              series={[
                {
                  data: [
                    { id: 0, value: 32528, label: "Running" },
                    { id: 1, value: 22452, label: "Walking" },
                    { id: 2, value: 6498, label: "Hiking" },
                    { id: 3, value: 9320, label: "Swimming" },
                    { id: 4, value: 5256, label: "Weightlifting" },
                    { id: 5, value: 19720, label: "Biking" },
                    { id: 6, value: 11254, label: "Yoga" },
                    { id: 7, value: 18553, label: "Pushups" },
                    { id: 8, value: 14626, label: "Crunch" },
                    { id: 9, value: 7488, label: "Dumbbel Lift" },
                    { id: 10, value: 3123, label: "Bicyle Crunch" },
                    { id: 11, value: 13523, label: "Aerobic" },
                  ],
                },
              ]}
            />
            <Typography
              fontWeight={700}
              fontSize={22}
              color="#b800d8"
              textAlign={"center"}
              mt={5}
            >
              Workout Scheduled
            </Typography>
          </Box>
          <Box>
            <BarChart
              width={500}
              height={400}
              series={[
                { data: usersSigned, label: "Signed" },
                { data: userPassed, label: "Passed" },
              ]}
              xAxis={[{ data: cLabels, scaleType: "band" }]}
            />
            <Typography
              fontWeight={700}
              fontSize={22}
              color="#276221"
              textAlign={"center"}
              mt={5}
            >
              Recent Challenge&apos;s Results
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default AdminDashboard;

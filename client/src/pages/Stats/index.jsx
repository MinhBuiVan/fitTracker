import { Box, Typography } from "@mui/material";
import { BarChart } from "@mui/x-charts";
import React from "react";

const dayLabels = ["Mon", "Tue", "Web", "Thu", "Fri", "Sat", " Sun"];
const durationDataThisWeek = [5.2, 4.2, 0, 7.1, 3.3, 5.7, 4.8];
const durationDataLastWeek = [4.6, 4.3, 2, 4.6, 5.5, 6.1, 4.9];

const distanceDataThisWeek = [10, 12.8, 8, 12, 14.5, 9, 6, 6.9];
const distanceDataLastWeek = [10.4, 15.8, 4.5, 12.1, 12.5, 9.6, 6.6, 7.9];

const caloriesBurnedThisWeek = [1120, 1673, 120, 584, 1103, 657, 495];
const caloriesBurnedLastWeek = [927, 1245, 420, 1184, 1253, 757, 695];

const Stats = () => {
  return (
    <Box
      mt={5}
      p={4}
      border="1px solid #dedede"
      width="95%"
      maxWidth="1100px"
      mx={"auto"}
    >
      <Typography fontSize={30} fontWeight={700}>
        User&apos;s personal stats
      </Typography>
      <Box display="flex" gap={"40px"} alignItems={"center"} mt={10}>
        <Box>
          <Typography fontWeight={500} fontSize={20}>
            Total Duration (hours)
          </Typography>
          <Typography fontWeight={700} fontSize={26} color="#d40d2d">
            110.20 hours
          </Typography>
        </Box>
        <Box>
          <Typography fontWeight={500} fontSize={20}>
            Total Distance (km)
          </Typography>
          <Typography fontWeight={700} fontSize={26} color="#d40d2d">
            342.69
          </Typography>
        </Box>
        <Box>
          <Typography fontWeight={500} fontSize={20}>
            Total Calories Burned (kal)
          </Typography>
          <Typography fontWeight={700} fontSize={26} color="#d40d2d">
            10,234.52
          </Typography>
        </Box>
      </Box>
      <Box width="820px">
        <BarChart
          width={800}
          height={500}
          series={[
            { data: durationDataThisWeek, label: "This Week" },
            { data: durationDataLastWeek, label: "Last Week" },
          ]}
          xAxis={[
            {
              data: dayLabels,
              scaleType: "band",
            },
          ]}
        />
        <Typography
          textAlign={"center"}
          fontWeight={700}
          fontSize={22}
          color="#d2042d"
        >
          Workout Total Duration (hours)
        </Typography>
      </Box>
      <Box width="820px">
        <BarChart
          width={800}
          height={500}
          series={[
            { data: distanceDataThisWeek, label: "This Week" },
            { data: distanceDataLastWeek, label: "Last Week" },
          ]}
          xAxis={[
            {
              data: dayLabels,
              scaleType: "band",
            },
          ]}
        />
        <Typography
          textAlign={"center"}
          fontWeight={700}
          fontSize={22}
          color="#d2042d"
        >
          Workout Total Distance (km)
        </Typography>
      </Box>
      <Box width="820px">
        <BarChart
          width={800}
          height={500}
          series={[
            { data: caloriesBurnedThisWeek, label: "This Week" },
            { data: caloriesBurnedLastWeek, label: "Last Week" },
          ]}
          xAxis={[
            {
              data: dayLabels,
              scaleType: "band",
            },
          ]}
        />
        <Typography
          textAlign={"center"}
          fontWeight={700}
          fontSize={22}
          color="#d2042d"
        >
          Workout Total Calories Burned (kal)
        </Typography>
      </Box>
    </Box>
  );
};

export default Stats;

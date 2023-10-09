import { Box } from "@mui/material";
import Header from "../../../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import { useDispatch, useSelector } from "react-redux";
import Loading from "../../../../components/Loading";
import { useEffect } from "react";
import { getAchievements } from "../../../../store/features/adminSlice";

const Achievements = () => {
  const { isLoading, achievements } = useSelector((state) => state.admin);
  const columns = [
    { field: "achievementId", headerName: "ID", flex: 1 },
    { field: "activity_name", headerName: "Activity", flex: 1 },
    { field: "description", headerName: "Description", flex: 1 },
    {
      field: "target",
      headerName: "Target",
      renderCell: (params) => {
        const { value, row } = params;
        const type = row.type;
        return (
          <span>
            {type === "distance"
              ? `${value} km`
              : type === "duration"
              ? `${value} hours`
              : `${value} workouts`}
          </span>
        );
      },
      flex: 1,
    },
    {
      field: "image",
      headerName: "Award",
      flex: 1,
      renderCell: ({ row: { image } }) => {
        return (
          <Box
            sx={{
              width: "50px",
              height: "50px",
            }}
          >
            <img
              width={"100%"}
              height={"100%"}
              style={{ objectFit: "cover" }}
              src={`/achievements/${image}`}
              alt="image"
            />
          </Box>
        );
      },
    },
  ];

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getAchievements());
  }, [dispatch]);

  if (isLoading) {
    return <Loading />;
  }
  return (
    <Box m={1}>
      <Header
        title="Achievements Data"
        subtitle="Manage all FitTracker's achievements information"
      />
      <Box mt={4} height="75vh" backgroundColor="#fff">
        <DataGrid
          rows={achievements}
          columns={columns}
          getRowId={(row) => row.achievementId}
        />
      </Box>
    </Box>
  );
};

export default Achievements;

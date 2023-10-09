import { Box } from "@mui/material";
import Header from "../../../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getActivities } from "../../../../store/features/adminSlice";
import Loading from "../../../../components/Loading";

const Activities = () => {
  const dispatch = useDispatch();
  const { activities, isLoading } = useSelector((state) => state.admin);
  const columns = [
    { field: "activityId", headerName: "ID", flex: 1 },
    {
      field: "image",
      headerName: "Icon",
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
              src={`/activities/${image}`}
              alt="image"
            />
          </Box>
        );
      },
    },
    { field: "name", headerName: "Activity Name", flex: 1 },
    { field: "met", headerName: "MET", flex: 1 },
  ];

  useEffect(() => {
    dispatch(getActivities());
  }, [dispatch]);

  if (isLoading) {
    return <Loading />;
  }

  return (
    <Box m={1}>
      <Header
        title="Activities Data"
        subtitle="Manage all FitTracker's activities information"
      />
      <Box mt={4} height="75vh" backgroundColor="#fff">
        <DataGrid
          rows={activities}
          columns={columns}
          getRowId={(row) => row.activityId}
        />
      </Box>
    </Box>
  );
};

export default Activities;

import { Box, Modal, Typography } from "@mui/material";
import Header from "../../../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUsers } from "../../../../store/features/adminSlice";
import Loading from "../../../../components/Loading";

const Users = () => {
  const [open, setOpen] = useState(false);
  const [imagePath, setImagePath] = useState("");

  const handleClose = () => {
    setOpen(false);
  };

  const { users, isLoading } = useSelector((state) => state.admin);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getUsers());
  }, [dispatch]);

  const columns = [
    { field: "userId", headerName: "ID" },
    {
      field: "firstName",
      headerName: "First Name",
      flex: 1,
    },
    {
      field: "lastName",
      headerName: "Last Name",
      flex: 1,
    },
    {
      field: "email",
      headerName: "Email",
      flex: 1,
    },
    {
      field: "birthday",
      headerName: "Birthdate",
      minWidth: 200,
    },
    {
      field: "gender",
      headerName: "Gender",
      renderCell: ({ row: { gender } }) => {
        return (
          <Box>
            {gender === "male" ? (
              <FontAwesomeIcon color="#01A6EA" icon="fa-solid fa-mars" />
            ) : (
              <FontAwesomeIcon color="#FFB1CB" icon="fa-solid fa-venus" />
            )}
          </Box>
        );
      },
    },
    {
      field: "country",
      headerName: "Country",
    },
    {
      field: "city",
      headerName: "City",
    },
    {
      field: "image",
      headerName: "Image",
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
              src={`/${image}`}
              alt="image"
              onClick={() => {
                setOpen(true);
                setImagePath(image);
              }}
            />
          </Box>
        );
      },
    },
    {
      field: "role",
      headerName: "Role",
      renderCell: ({ row: { roles } }) => {
        return (
          <Box
            width="100%"
            m="0 auto"
            p="5px"
            display="flex"
            alignItems="center"
            backgroundColor={
              roles[0].name === "ROLE_ADMIN" ? "#B22222" : "#228B22"
            }
            borderRadius="4px"
          >
            {roles[0].name === "ROLE_ADMIN" && (
              <FontAwesomeIcon icon="fa-solid fa-shield-halved" color="#fff" />
            )}
            {roles[0].name === "ROLE_USER" && (
              <FontAwesomeIcon icon="fa-solid fa-user" color="#fff" />
            )}
            <Typography ml={1} color="#fff">
              {roles[0].name === "ROLE_ADMIN" ? "Admin" : "User"}
            </Typography>
          </Box>
        );
      },
    },
  ];

  if (isLoading) {
    return <Loading />;
  }

  return (
    <Box m={1}>
      <Header
        title="Users Data"
        subtitle="Manage all FitTracker's users information"
      />
      <Box mt={4} height="75vh" backgroundColor="#fff">
        <DataGrid
          rows={users}
          columns={columns}
          getRowId={(row) => row.userId}
        />
      </Box>

      {/* MODAL  */}
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Box maxWidth="600px" maxHeight="600px" display="flex">
          <img
            width="100%"
            src={`/${imagePath}`}
            style={{ objectFit: "cover" }}
          />
        </Box>
      </Modal>
    </Box>
  );
};

export default Users;

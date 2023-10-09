/* eslint-disable react/prop-types */
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Box, IconButton, Typography } from "@mui/material";
import { useState } from "react";
import { Sidebar, Menu, MenuItem } from "react-pro-sidebar";
import { useSelector } from "react-redux";
import { Link, useLocation } from "react-router-dom";

const Item = ({ title, icon, to, selected, setSelected }) => {
  return (
    <MenuItem
      active={selected === to}
      onClick={() => setSelected(to)}
      component={<Link to={to} />}
      icon={icon}
    >
      <Typography>{title}</Typography>
    </MenuItem>
  );
};

const MySidebar = () => {
  const location = useLocation();

  const [isCollapsed, setIsCollapsed] = useState(false);
  const [selected, setSelected] = useState(location.pathname);

  const { user } = useSelector((state) => state.auth);

  return (
    <Sidebar collapsed={isCollapsed}>
      <Box
        height="100%"
        display="flex"
        flexDirection="column"
        sx={{
          "& .ps-active": {
            color: "#d2042d !important",
          },
        }}
      >
        <Box
          mb={"24px"}
          mt={"16px"}
          display={"flex"}
          justifyContent={"center"}
          alignItems="center"
        >
          {!isCollapsed && (
            <Typography pl={1} flex={1}>
              ADMINIS
            </Typography>
          )}
          <IconButton onClick={() => setIsCollapsed(!isCollapsed)}>
            <FontAwesomeIcon icon="fa-solid fa-bars" size="xs" />
          </IconButton>
        </Box>
        <Box>
          {!isCollapsed && (
            <Box mb="25px">
              <Box display="flex" justifyContent="center" alignItems="center">
                <img
                  alt="profile-user"
                  width="100px"
                  height="100px"
                  src={user.image}
                  style={{
                    cursor: "pointer",
                    borderRadius: "50%",
                    objectFit: "cover",
                  }}
                />
              </Box>
              <Box textAlign="center">
                <Typography
                  variant="h5"
                  fontWeight="bold"
                  sx={{ m: "10px 0 0 0" }}
                  color="#d2042d"
                >
                  {user.lastName + " " + user.firstName}
                </Typography>
              </Box>
            </Box>
          )}
        </Box>
        <Box
          sx={{
            flex: 1,
            marginBottom: "32px",
            "& p": { fontSize: 16 },
            "& a": { color: "#d2d42d" },
          }}
        >
          <Box sx={{ p: "0 20px", mb: "8px" }}>
            <Typography fontWeight={600}>General</Typography>
          </Box>
          <Menu>
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Dashboard"
              to="/admin"
              icon={<FontAwesomeIcon icon="fa-solid fa-house" size="xs" />}
            />
          </Menu>
          <Box sx={{ p: "0 20px", mb: "8px" }}>
            <Typography fontWeight={600}>Data</Typography>
          </Box>
          <Menu>
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Users"
              to="/admin/users"
              icon={<FontAwesomeIcon icon="fa-solid fa-user-group" size="xs" />}
            />
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Activities"
              to="/admin/activities"
              icon={<FontAwesomeIcon icon="fa-solid fa-dumbbell" size="xs" />}
            />
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Community Posts"
              to="/admin/community-posts"
              icon={<FontAwesomeIcon icon="fa-solid fa-pencil" size="xs" />}
            />
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Challenges"
              to="/admin/challenges"
              icon={
                <FontAwesomeIcon icon="fa-solid fa-person-running" size="xs" />
              }
            />

            <Item
              selected={selected}
              setSelected={setSelected}
              title="Achivements"
              to="/admin/achivements"
              icon={<FontAwesomeIcon icon="fa-solid fa-award" size="xs" />}
            />
          </Menu>
          <Box sx={{ p: "0 20px", mb: "8px" }}>
            <Typography fontWeight={600}>Form</Typography>
          </Box>
          <Menu>
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Activity Form"
              to="/admin/activity-form"
              icon={<FontAwesomeIcon icon="fa-solid fa-list-check" size="xs" />}
            />
            <Item
              selected={selected}
              setSelected={setSelected}
              title="Achivement Form"
              to="/admin/achivement-form"
              icon={<FontAwesomeIcon icon="fa-solid fa-trophy" size="xs" />}
            />
          </Menu>
        </Box>
      </Box>
    </Sidebar>
  );
};

export default MySidebar;

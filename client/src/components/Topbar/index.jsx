import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  Box,
  IconButton,
  InputBase,
  Menu,
  MenuItem,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { logout } from "../../store/features/authSlice";

const Topbar = () => {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (e) => {
    setAnchorEl(e.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const dispatch = useDispatch();
  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <Box
      display="flex"
      justifyContent="space-between"
      p={2}
      backgroundColor="#fff"
    >
      <Box display="flex" borderRadius="3px" backgroundColor="#f9f9f9">
        <InputBase sx={{ ml: 2, flex: 1 }} placeholder="Search" />
        <IconButton type="button" p={1}>
          <FontAwesomeIcon icon="fa-solid fa-magnifying-glass" size="2xs" />
        </IconButton>
      </Box>

      <Box display="flex" position="relative">
        <IconButton
          onClick={handleClick}
          aria-controls={open ? "basic-menu" : undefined}
          aria-haspopup="true"
          aria-expanded={open ? "true" : undefined}
        >
          <FontAwesomeIcon icon="fa-solid fa-gear" size="2xs" />
        </IconButton>
        <Menu
          open={open}
          onClose={handleClose}
          anchorEl={anchorEl}
          MenuListProps={{
            "aria-labelledby": "basic-button",
          }}
        >
          <MenuItem onClick={handleLogout}>
            <Typography fontSize={16} color="#d2042d" fontWeight={500}>
              Log Out
            </Typography>
          </MenuItem>
        </Menu>
      </Box>
    </Box>
  );
};

export default Topbar;

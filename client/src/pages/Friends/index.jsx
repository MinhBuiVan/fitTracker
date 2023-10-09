// const Friends = () => {
//   return (
//     <Box>

//     </Box>
//   );
// };
import * as React from "react";
import { Typography, Box, Tab, Button } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import FormControl from "@mui/material/FormControl";
import OutlinedInput from "@mui/material/OutlinedInput";

export default function Friends() {
  const [value, setValue] = React.useState("one");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Box sx={{ width: "100%", margin: "30px 0 900px 300px" }}>
      <TabContext value={value}>
        <TabList
          value={value}
          onChange={handleChange}
          aria-label="wrapped label tabs example"
          // indicatorColor="secondary"
          textColor="inherit"
        >
          <Tab
            value="one"
            label="MY FRIENDS"
            // wrapped
          />
          <Tab value="two" label="FIND FRIENDS" />
        </TabList>
        <TabPanel value="one">
          <Typography
            variant="body1"
            gutterBottom
            sx={{
              marginTop: "40px",
              marginBottom: "10px",
              fontSize: 22,
              fontWeight: 500,
            }}
          >
            Friends (0 of 0)
          </Typography>
          <Typography variant="body1" gutterBottom>
            You have not added any friends yet. Click the Find Friends tab above
            to get started.
          </Typography>
        </TabPanel>
        <TabPanel value="two">
          <Typography
            variant="body1"
            gutterBottom
            sx={{
              marginTop: "40px",
              marginBottom: "10px",
              fontSize: 22,
              fontWeight: 500,
            }}
          >
            Find FitTracker Friends by username:
          </Typography>
          <FormControl sx={{ width: "60ch" }}>
            <OutlinedInput />
          </FormControl>
          <Button
            variant="contained"
            size="large"
            sx={{ transform: "translate(10px,5px)" }}
          >
            Search
          </Button>
        </TabPanel>
      </TabContext>
    </Box>
  );
}
// export default Friends;

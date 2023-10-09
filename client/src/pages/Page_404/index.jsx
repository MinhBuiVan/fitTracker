import { Box, Typography } from "@mui/material";

const Page_404 = () => {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: { xs: "column-reverse", md: "row-reverse" },
        justifyContent: "center",
        alignItems: "center",
        height: "60vh",
      }}
    >
      <Typography
        variant="h2"
        fontWeight={800}
        ml={2}
        fontSize={"6vh"}
        textAlign={"center"}
      >
        404 Page Not Found!
      </Typography>
      <img src="/chika.svg" width="300px" alt="chika chika dance" />
    </Box>
  );
};

export default Page_404;

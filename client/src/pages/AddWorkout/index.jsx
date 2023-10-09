import { Box, TextField, MenuItem, Button } from "@mui/material";
import { DatePicker, TimePicker } from "@mui/x-date-pickers";
import dayjs from "dayjs";
import { useFormik } from "formik";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import * as yup from "yup";
import { getActivities } from "../../store/features/adminSlice";
import { createWorkout } from "../../store/features/userSlice";

const AddWorkout = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { activities } = useSelector((state) => state.admin);
  const { user, isLogin } = useSelector((state) => state.auth);

  const [chosenActivity, setChosenActivity] = useState(null);
  const [duration, setDuration] = useState(null);
  const [pace, setPace] = useState(null);
  const [caloriesBurned, setCaloriesBurned] = useState(null);

  useEffect(() => {
    dispatch(getActivities());
  }, [dispatch]);

  const validationSchema = yup.object({
    name: yup.string("Enter name of workout"),
    hourStart: yup.string().required("Time is required"),
    hourEnd: yup.string().required("Time is required"),
    dateStart: yup.date().required("Date is required"),
    dateEnd: yup.string().required("Date is required"),
    activity: yup
      .string()
      .oneOf(activities.map((activity) => activity.name))
      .required("Select your workout&apos;s activity"),
    distance: yup.number().nullable(),
    note: yup.string(),
  });

  const formik = useFormik({
    initialValues: {
      name: "",
      hourStart: dayjs(),
      hourEnd: dayjs(),
      dateStart: dayjs(),
      dateEnd: dayjs(),
      activity: "",
      distance: null,
      note: "",
    },
    validationSchema,
    onSubmit: (val) => {
      const workoutData = {
        name: val.name,
        timeStart:
          dayjs(val.dateStart).format("YYYY-MM-DD") +
          "T" +
          dayjs(val.hourStart).format("HH:mm:ss"),
        timeEnd:
          dayjs(val.dateEnd).format("YYYY-MM-DD") +
          "T" +
          dayjs(val.hourEnd).format("HH:mm:ss"),
        activityId: chosenActivity.activityId,
        dateTime: dayjs(Date.now()).format("YYYY-MM-DDTHH:ss:mm"),
        userId: user.id,
        distance: val.distance === null ? 1 : val.distance,
        note: val.note,
      };

      console.log(workoutData);

      dispatch(createWorkout({ workoutData, navigate }));
    },
  });

  const calculateDuration = () => {
    if (
      formik.values.hourEnd.length !== 0 &&
      formik.values.hourStart.length !== 0 &&
      formik.values.dateEnd.length !== 0 &&
      formik.values.dateStart.length !== 0
    ) {
      const timeEnd = dayjs(
        formik.values.dateEnd + formik.values.hourEnd
      ).format("YYYY-MM-DD HH:mm:ss");
      const timeStart = dayjs(
        formik.values.dateStart + formik.values.hourStart
      ).format("YYYY-MM-DD HH:mm:ss");

      const seconds = dayjs(timeEnd).diff(dayjs(timeStart), "seconds");

      const hours = Math.floor(seconds / 3600);
      const minutes = Math.floor((seconds % 3600) / 60);
      const remainingSeconds = seconds % 60;

      setDuration(`${hours}:${minutes}:${remainingSeconds}`);
    }
  };

  const calculatePace = () => {
    if (formik.values.distance !== null && duration !== null) {
      const timeString = duration;
      const [hours, minutes, seconds] = timeString.split(":");

      const totalSeconds =
        parseInt(hours) * 3600 + parseInt(minutes) * 60 + parseInt(seconds);
      const res = (formik.values.distance * 1000) / totalSeconds;

      setPace(res.toFixed(2));
    }
  };

  const calculateCaloriesBurned = () => {
    let res;
    if (chosenActivity.type === "no-gym") {
      res = chosenActivity.met * pace * 100;
    } else {
      const timeString = duration;
      const arr = timeString.split(":");

      res = chosenActivity.met * parseInt(arr[0]) * 10;
    }
    setCaloriesBurned(res.toFixed(2));
  };

  useEffect(() => {
    if (!isLogin) {
      navigate("/auth/login");
    }
  });

  useEffect(() => {
    setChosenActivity(
      activities.find((item) => item.name === formik.values.activity)
    );
  }, [formik.values.activity, activities]);

  return (
    <Box
      sx={{
        maxWidth: "700px",
        width: "95%",
        marginInline: "auto",
        border: "1px solid #a9a9a9",
      }}
      mt={5}
      mb={20}
      p={4}
    >
      <form onSubmit={formik.handleSubmit}>
        <TextField
          size="small"
          fullWidth
          id="name"
          label="Workout name"
          value={formik.values.name}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.name && Boolean(formik.errors.name)}
          helperText={formik.touched.name && formik.errors.name}
          sx={{ marginBottom: "20px" }}
        />
        <Box display="flex" sx={{ marginBottom: "20px" }}>
          <TimePicker
            views={["hours", "minutes", "seconds"]}
            sx={{ flex: 1, mr: 1 }}
            label="Time start"
            value={formik.values.hourStart}
            onChange={(time) => {
              formik.setFieldValue("hourStart", time);
            }}
            slotProps={{
              textField: {
                variant: "outlined",
                size: "small",
                required: true,
                error:
                  formik.touched.hourStart && Boolean(formik.errors.hourStart),
                helperText: formik.touched.hourStart && formik.errors.hourStart,
              },
            }}
          />
          <DatePicker
            disablePast
            label="Date start"
            format="YYYY/MM/DD"
            value={formik.values.dateStart}
            onChange={(date) => {
              formik.setFieldValue("dateStart", date);
            }}
            sx={{ flex: 1, ml: 1 }}
            slotProps={{
              textField: {
                variant: "outlined",
                size: "small",
                required: true,
                error:
                  formik.touched.dateStart && Boolean(formik.errors.dateStart),
                helperText: formik.touched.dateStart && formik.errors.dateStart,
              },
            }}
          />
        </Box>
        <TextField
          size="small"
          fullWidth
          required
          select
          id="activity"
          label="Activity"
          value={formik.values.activity}
          onChange={formik.handleChange("activity")}
          error={formik.touched.activity && Boolean(formik.errors.activity)}
          helperText={formik.touched.activity && formik.errors.activity}
          sx={{ marginBottom: "20px" }}
        >
          {activities.map((activity) => (
            <MenuItem key={activity.id} value={activity.name}>
              {activity.name}
            </MenuItem>
          ))}
        </TextField>
        {chosenActivity !== undefined && (
          <>
            <Box display="flex" sx={{ marginBottom: "20px" }}>
              <TimePicker
                views={["hours", "minutes", "seconds"]}
                sx={{ flex: 1, mr: 1 }}
                label="Time end"
                value={formik.values.hourEnd}
                onChange={(time) => {
                  formik.setFieldValue("hourEnd", time);
                }}
                slotProps={{
                  textField: {
                    variant: "outlined",
                    size: "small",
                    required: true,
                    error:
                      formik.touched.hourEnd && Boolean(formik.errors.hourEnd),
                    helperText: formik.touched.hourEnd && formik.errors.hourEnd,
                  },
                }}
              />
              <DatePicker
                disablePast
                label="Date start"
                format="YYYY/MM/DD"
                value={formik.values.dateEnd}
                onChange={(date) => {
                  formik.setFieldValue("dateEnd", date);
                }}
                sx={{ flex: 1, ml: 1 }}
                slotProps={{
                  textField: {
                    variant: "outlined",
                    size: "small",
                    required: true,
                    error:
                      formik.touched.dateEnd && Boolean(formik.errors.dateEnd),
                    helperText: formik.touched.dateEnd && formik.errors.dateEnd,
                  },
                }}
              />
            </Box>
            <Box display="flex" gap="20px" mb={"20px"}>
              <TextField
                label="Duration"
                value={duration}
                readOnly
                size="small"
                InputProps={{ readOnly: true }}
                InputLabelProps={{ shrink: true }}
              />
              <Button
                variant="outlined"
                sx={{ fontWeight: 700, flexShrink: 0 }}
                onClick={calculateDuration}
              >
                Calculate
              </Button>
            </Box>
            {chosenActivity?.type === "no-gym" && (
              <>
                <TextField
                  size="small"
                  id="distance"
                  label="Distance (km)"
                  value={formik.values.distance}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  error={
                    formik.touched.distance && Boolean(formik.errors.distance)
                  }
                  helperText={formik.touched.distance && formik.errors.distance}
                  sx={{ marginBottom: "20px" }}
                  type="number"
                />

                <Box display="flex" gap="20px" mb={"20px"}>
                  <TextField
                    label="Pace m/s"
                    value={pace}
                    placeholder="m/s"
                    InputProps={{ readOnly: true }}
                    InputLabelProps={{ shrink: true }}
                    size="small"
                  />
                  <Button
                    variant="outlined"
                    sx={{ fontWeight: 700, flexShrink: 0 }}
                    onClick={calculatePace}
                  >
                    Calculate
                  </Button>
                </Box>
              </>
            )}
            <Box display="flex" gap="20px" mb={"20px"}>
              <TextField
                label="Calories Burned (kal)"
                value={caloriesBurned}
                placeholder="m/s"
                InputProps={{ readOnly: true }}
                InputLabelProps={{ shrink: true }}
                size="small"
              />
              <Button
                variant="outlined"
                sx={{ fontWeight: 700, flexShrink: 0 }}
                onClick={calculateCaloriesBurned}
              >
                Calculate
              </Button>
            </Box>
            <textarea
              id="note"
              style={{
                width: "100%",
                padding: "10px",
                fontSize: 16,
                marginBottom: "30px",
              }}
              rows={5}
              placeholder="Leave a note"
              value={formik.values.note}
              onChange={formik.handleChange}
            ></textarea>
            <Button type="submit" variant="contained">
              Save
            </Button>
          </>
        )}
      </form>
    </Box>
  );
};

export default AddWorkout;

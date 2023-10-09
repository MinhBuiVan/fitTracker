package com.example.fittracker.controller;

import com.example.fittracker.documents.CommonDocs;
import com.example.fittracker.model.*;
import com.example.fittracker.model.api.response.ChallengeResponse;
import com.example.fittracker.model.api.response.UserResponse;
import com.example.fittracker.model.api.response.WorkoutOverViewResponse;
import com.example.fittracker.model.base.Pagination;
import com.example.fittracker.model.base.ResponseBody;
import com.example.fittracker.model.dto.ActivityDto;
import com.example.fittracker.model.dto.WorkoutDetailDto;
import com.example.fittracker.model.dto.WorkoutOverViewDto;
import com.example.fittracker.payload.challenge.ChallengeStats;
import com.example.fittracker.payload.request.CreatePostRequest;
import com.example.fittracker.payload.workout.CreateGoalRequest;
import com.example.fittracker.payload.workout.CreateWorkoutRequest;
import com.example.fittracker.service.*;
import com.example.fittracker.util.JsonPropertyUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private GoalService goalService;
    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private UserChallengeService userChallengeService;
    @Autowired
    private PostService postService;

    // user

        @GetMapping("/users")
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        @PostMapping("/users")
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        @GetMapping("/users/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        @PutMapping("/users/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        @DeleteMapping("/users/{id}")
        public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        // advanced user
        @GetMapping("/users/search/{query}")
        public ResponseEntity<List<User>> searchFriends(@PathVariable String query) {
            List<User> friends = userService.searchFriends(query);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        }
        @PostMapping("/users/{userId}/sendFriendRequest/{friendId}")
        public ResponseEntity<String> sendFriendRequest(@PathVariable Long userId, @PathVariable Long friendId) {
            boolean success = userService.sendFriendRequest(userId, friendId);

            if (success) {
                return new ResponseEntity<>("Friend request sent successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to send friend request", HttpStatus.BAD_REQUEST);
            }
        }

    // goal

        @GetMapping("/goals")
        public ResponseEntity<List<Goal>> getAllGoals() {
            List<Goal> goals = goalService.getAllGoals();
            return new ResponseEntity<>(goals, HttpStatus.OK);
        }
        @PostMapping("/goals")
        public ResponseEntity<Goal> createGoal(@RequestBody CreateGoalRequest request) {
            Goal goal = goalService.createGoal(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(goal);
        }
        @GetMapping("/goals/{id}")
        public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
            Goal goal = goalService.getGoalById(id);
            return new ResponseEntity<>(goal, HttpStatus.OK);
        }
        @DeleteMapping("/goals/{id}")
        public ResponseEntity<HttpStatus> deleteGoal(@PathVariable Long id) {
            goalService.deleteGoal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // advanced goal
        @GetMapping("/goals/users/{userId}")
        public ResponseEntity<List<Goal>> getGoalsByUserId(@PathVariable("userId") Long userId) {
            List<Goal> goals = goalService.getGoalsByUserId(userId);
            return ResponseEntity.ok(goals);
        }

    // workout
        @RequestMapping(method = RequestMethod.GET, path = "/workouts")
        @ResponseStatus(HttpStatus.OK)
        @ApiOperation(value = "Show workouts overview information")
        @ApiResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = UserResponse.class),
                        examples = {@ExampleObject(name = "success", summary = "success", value = "")}))
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class), examples = {
                @ExampleObject(name = "Internal Server Error", summary = "Internal Server Error", value = CommonDocs.INTERNAL_SERVER_ERROR)
        }))
        public ResponseBody<WorkoutOverViewDto> getListWorkout(
                @RequestParam(name = JsonPropertyUtil.ID) Long id,
                @RequestParam(name = JsonPropertyUtil.ACTIVITY_NAME, required = false) @ApiParam(value = "filter by activityName", example = "") String activityName,
                @RequestParam(name = JsonPropertyUtil.MONTHLY_TIME, required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth monthly) {

            WorkoutOverViewDto workoutData = workoutService.getListWorkouts(id, activityName, monthly);
            return ResponseBody.ok(workoutData);
        }
        @PostMapping("/workouts")
        public ResponseEntity<Workout> createWorkout(@RequestBody CreateWorkoutRequest request) {
            Workout workout = workoutService.createWorkout(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(workout);
        }
        @PutMapping("/workouts/{id}")
        public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout workoutDetails) {
            Workout updatedWorkout = workoutService.updateWorkout(id, workoutDetails);
            return new ResponseEntity<>(updatedWorkout, HttpStatus.OK);
        }
        @DeleteMapping("/workouts/{id}")
        public ResponseEntity<HttpStatus> deleteWorkout(@PathVariable Long id) {
            workoutService.deleteWorkout(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // workout-advanced
        @RequestMapping(method = RequestMethod.GET, path = "/workouts/detail")
        @ResponseStatus(HttpStatus.OK)
        @ApiOperation(value = "Show workouts detail overview information")
        @ApiResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = UserResponse.class),
                        examples = {@ExampleObject(name = "success", summary = "success", value = "")}))
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class), examples = {
                @ExampleObject(name = "Internal Server Error", summary = "Internal Server Error", value = CommonDocs.INTERNAL_SERVER_ERROR)
        }))
        public ResponseBody<WorkoutDetailDto> getWorkoutDetail(
                @RequestParam(name = JsonPropertyUtil.ID) Long id
        ) {

            WorkoutDetailDto workoutDetailData = workoutService.getWorkoutDetail(id);
            return ResponseBody.ok(workoutDetailData);
        }
        @GetMapping("/workouts/users/{userId}/{date}")
        public ResponseEntity<List<Workout>> getWorkoutsByUserIdAndDate(
                @PathVariable("userId") Long userId,
                @PathVariable("date") LocalDate date) {
            List<Workout> workouts = workoutService.getWorkoutsByUserIdAndDate(userId, date);
            return ResponseEntity.ok(workouts);
        }
        @GetMapping("/workouts/{year}/{month}")
        public ResponseEntity<List<Workout>> getAllWorkoutsByMonthAndYear(@PathVariable("year") int year, @PathVariable("month") int month) {
            try {
                List<Workout> workouts = workoutService.getAllWorkoutsByMonthAndYear(year, month);
                return ResponseEntity.ok(workouts);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @GetMapping("/workouts/count/{user_id}/{date}")
        public ResponseEntity<Long> countWorkoutsByUserIdAndDate(
                @PathVariable("user_id") Long userId,
                @PathVariable("date") LocalDate date
        ) {
            long count = workoutService.countWorkoutsByUser_UserIdAndDate(userId, date);
            return ResponseEntity.ok(count);
        }
        @GetMapping("/workouts/sum-calories/{user_id}/{date}")
        public ResponseEntity<BigDecimal> totalCaloriesBurnedByUserIdAndDateTime(
                @PathVariable("user_id") Long userId,
                @PathVariable("date") LocalDate date
        ) {
            BigDecimal sum = workoutService.sumCaloriesBurnedByUser_UserIdAndDate(userId, date);
            return ResponseEntity.ok(sum);
        }
        @GetMapping("/workouts/sum-distance/{user_id}/{date}")
        public ResponseEntity<BigDecimal> totalDistanceByUserIdAndDateTime(
                @PathVariable("user_id") Long userId,
                @PathVariable("date") LocalDate date
        ) {
            BigDecimal sum = workoutService.sumDistanceByUser_UserIdAndDate(userId, date);
            return ResponseEntity.ok(sum);
        }
        @GetMapping("/workouts/total-time/{user_id}/{date}")
        public ResponseEntity<String> calculateTotalTimeByUserIdAndDate(
                @PathVariable("user_id") Long userId,
                @PathVariable("date") LocalDate date
        ) {
            String totalTime = workoutService.calculateTotalTimeByUser_UserIdAndDate(userId, date);
            return ResponseEntity.ok(totalTime);
        }

        @GetMapping("/workouts/count/{user_id}")
        public ResponseEntity<Long> countWorkoutsByUserId(@PathVariable("user_id") Long userId
        ) {
            long count = workoutService.countWorkoutsByUser_UserId(userId);
            return ResponseEntity.ok(count);
        }
        @GetMapping("/workouts/sum-calories/{user_id}")
        public ResponseEntity<BigDecimal> totalCaloriesBurnedByUserId(@PathVariable("user_id") Long userId
        ) {
            BigDecimal sum = workoutService.sumCaloriesBurnedByUser_UserId(userId);
            return ResponseEntity.ok(sum);
        }
        @GetMapping("/workouts/sum-distance/{user_id}")
        public ResponseEntity<BigDecimal> totalDistanceByUserId(@PathVariable("user_id") Long userId
        ) {
            BigDecimal sum = workoutService.sumDistanceByUser_UserId(userId);
            return ResponseEntity.ok(sum);
        }
        @GetMapping("/workouts/total-time/{user_id}")
        public ResponseEntity<String> calculateTotalTimeByUserId(@PathVariable("user_id") Long userId
        ) {
            String totalTime = workoutService.calculateTotalTimeByUserId(userId);
            return ResponseEntity.ok(totalTime);
        }
    // challenge
        @PostMapping("/challenge/join/{user_id}/{challenge_id}")
        public ResponseEntity<String> joinChallenge(
                @PathVariable("user_id") Long userId,
                @PathVariable("challenge_id") Long challengeId) {
            userChallengeService.joinChallenge(userId, challengeId);
            return ResponseEntity.ok("User joined the challenge successfully.");
        }

        // advanced
        @GetMapping("/challenge/{userId}/{challengeId}")
        public ChallengeStats getChallengeStats(@PathVariable Long userId, @PathVariable Long challengeId) {
            return userChallengeService.getChallengeStats(userId, challengeId);
        }
    // report

        @PostMapping("/reports")
        public ResponseEntity<Report> createReport(@RequestBody Report report) {
            Report savedReport = reportService.saveReport(report);
            return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
        }

    // post

        @PostMapping(value = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<?> createPost(    @RequestParam("content") String content,
                                                @RequestParam("image") MultipartFile image,
                                                @RequestParam("create") LocalDateTime create,
                                                @RequestParam("userId") Long userId) {
            String imageURl = imageService.create(image);
            return ResponseEntity.ok(postService.createPost(content, imageURl, create, userId));
        }
        @Autowired
        ImageService imageService;
        @PostMapping("/test")
        public String uploadImage(@RequestParam("file") MultipartFile file){
              String url=  imageService.create(file);
              return url;
        }

}

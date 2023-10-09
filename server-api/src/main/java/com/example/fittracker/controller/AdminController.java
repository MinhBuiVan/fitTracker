package com.example.fittracker.controller;

import com.example.fittracker.documents.CommonDocs;
import com.example.fittracker.model.Achievement;
import com.example.fittracker.model.Activity;
import com.example.fittracker.model.Challenge;
import com.example.fittracker.model.Workout;
import com.example.fittracker.model.api.response.ActivityResponse;
import com.example.fittracker.model.api.response.ChallengeResponse;
import com.example.fittracker.model.api.response.UserResponse;
import com.example.fittracker.model.base.Pagination;
import com.example.fittracker.model.base.ResponseBody;
import com.example.fittracker.payload.workout.CreateWorkoutRequest;
import com.example.fittracker.service.*;
import com.example.fittracker.util.JsonPropertyUtil;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private ImageService imageService;

    // activity

    @PostMapping(value = "/activities", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createActivity(@RequestParam("MET") BigDecimal MET,
                                            @RequestParam("image") MultipartFile image,
                                            @RequestParam("name") String name,
                                            @RequestParam("type") String type) {
        String imageURl = imageService.create(image);
        return ResponseEntity.ok(activityService.createActivity(MET, imageURl, name, type));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/activities")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Show activities overview information")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserResponse.class),
                    examples = {@ExampleObject(name = "success", summary = "success", value = "")}))
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class), examples = {
            @ExampleObject(name = "Internal Server Error", summary = "Internal Server Error", value = CommonDocs.INTERNAL_SERVER_ERROR)
    }))
    public ResponseBody<Pagination<List<ActivityResponse>>> getListActivity(
            @RequestParam(name = JsonPropertyUtil.SEARCH, required = false, defaultValue = "") @ApiParam(value = "search by name, id, met or type", example = "") String search,
            @RequestParam(name = JsonPropertyUtil.SORT, required = false, defaultValue = "") @ApiParam(value = "sort by name, desc, position, state, created, update", example = "") String sort,
            @RequestParam(name = JsonPropertyUtil.DIR, required = false, defaultValue = "") @ApiParam(value = "dir is asc or desc", example = "dir asc or desc") String dir,
            @RequestParam(name = JsonPropertyUtil.PAGE, required = false, defaultValue = "1") @ApiParam(value = "default is 1", example = "") Integer page,
            @RequestParam(name = JsonPropertyUtil.PAGE_SIZE, required = false, defaultValue = "10") @ApiParam(value = "default is 10", example = "") Integer pageSize) {

        Pagination<List<ActivityResponse>> activityData = activityService.getListActivities(search, sort, dir, page, pageSize);
        return ResponseBody.ok(activityData);
    }
    @PutMapping("/activities/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id,
                                                   @RequestParam(value = "image", required = false) MultipartFile image,
                                                   @RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "MET", required = false) BigDecimal MET,
                                                   @RequestParam(value = "type", required = false) String type) {
        Activity updatedActivity = activityService.updateActivity(id, image, name, MET, type);
        return ResponseEntity.ok(updatedActivity);
    }
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<HttpStatus> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // challenge
    @RequestMapping(method = RequestMethod.GET, path = "/challenges")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Show challenges overview information")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserResponse.class),
                    examples = {@ExampleObject(name = "success", summary = "success", value = "")}))
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class), examples = {
            @ExampleObject(name = "Internal Server Error", summary = "Internal Server Error", value = CommonDocs.INTERNAL_SERVER_ERROR)
    }))
    public ResponseBody<Pagination<List<ChallengeResponse>>> getListChallenge(
            @RequestParam(name = JsonPropertyUtil.SEARCH, required = false, defaultValue = "") @ApiParam(value = "search by name, id, met or type", example = "") String search,
            @RequestParam(name = JsonPropertyUtil.SORT, required = false, defaultValue = "") @ApiParam(value = "sort by name, desc, position, state, created, update", example = "") String sort,
            @RequestParam(name = JsonPropertyUtil.DIR, required = false, defaultValue = "") @ApiParam(value = "dir is asc or desc", example = "dir asc or desc") String dir,
            @RequestParam(name = JsonPropertyUtil.PAGE, required = false, defaultValue = "1") @ApiParam(value = "default is 1", example = "") Integer page,
            @RequestParam(name = JsonPropertyUtil.PAGE_SIZE, required = false, defaultValue = "10") @ApiParam(value = "default is 10", example = "") Integer pageSize) {

        Pagination<List<ChallengeResponse>> challengeData = challengeService.getListChallenges(search, sort, dir, page, pageSize);
        return ResponseBody.ok(challengeData);
    }
    @PostMapping("/challenges")
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        Challenge savedChallenge = challengeService.saveChallenge(challenge);
        return new ResponseEntity<>(savedChallenge, HttpStatus.CREATED);
    }
    @PutMapping("/challenges/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody Challenge challengeDetails) {
        Challenge updatedChallenge = challengeService.updateChallenge(id, challengeDetails);
        return new ResponseEntity<>(updatedChallenge, HttpStatus.OK);
    }
    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<HttpStatus> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // achievement

    @GetMapping("/achievements")
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        List<Achievement> achievements = achievementService.getAllAchievements();
        return new ResponseEntity<>(achievements, HttpStatus.OK);
    }
    @PostMapping("/achievements")
    public ResponseEntity<Achievement> createAchievement(@RequestBody Achievement achievement) {
        Achievement savedAchievement = achievementService.saveAchievement(achievement);
        return new ResponseEntity<>(savedAchievement, HttpStatus.CREATED);
    }
    @GetMapping("/achievements/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable Long id) {
        Achievement achievement = achievementService.getAchievementById(id);
        return new ResponseEntity<>(achievement, HttpStatus.OK);
    }
    @PutMapping("/achievements/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable Long id, @RequestBody Achievement achievementDetails) {
        Achievement updatedAchievement = achievementService.updateAchievement(id, achievementDetails);
        return new ResponseEntity<>(updatedAchievement, HttpStatus.OK);
    }
    @DeleteMapping("/achievements/{id}")
    public ResponseEntity<HttpStatus> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

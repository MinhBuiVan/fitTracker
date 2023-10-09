package com.example.fittracker.documents;

public class CommonDocs {
    public static final String UNAUTHORIZED = "{\n" + "    \"code\": 401,\n" + "    \"message\": \"Unauthorized\"\n" + "}";

    public static final String MISSING_AUTHEN_HEADER = "{\n" + "    \"code\": 401,\n" + "    \"message\": \"Unauthorized\",\n" + "    \"errors\": [\n" + "        {\n" + "            \"server_code\": 4010,\n" + "            \"message\": \"Missing X-Authorization Header\"\n" + "        }\n" + "    ]\n" + "}";
    public static final String INVALID_TOKEN = "{\n" + "    \"code\": 401,\n" + "    \"message\": \"Unauthorized\",\n" + "    \"errors\": [\n" + "        {\n" + "            \"server_code\": 4010,\n" + "            \"message\": \"Invalid Token\"\n" + "        }\n" + "    ]\n" + "}";

    public static final String E403_OPERATION_NOT_PERMITTED = "{\n" + "    \"code\": 403,\n" + "    \"message\": \"Forbidden\",\n" + "    \"errors\": [\n" + "        {\n" + "            \"server_code\": 4031,\n" + "            \"message\": \"Operation not permitted!\"\n" + "        }\n" + "    ]\n" + "}";
    public static final String INTERNAL_SERVER_ERROR = "{\n" + "  \"code\": 500,\n" + "  \"message\": \"Internal Server Error\"\n" + "}";
}

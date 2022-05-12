package models;

public class GenerateTokenResponse {
    /*
        {
            "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFsZXgiLCJwYXNzd29yZCI6ImFzZHNhZCNmcmV3X0RGUzIiLCJpYXQiOjE2NDk5NjA1OTJ9.hFjd0tWZRdgNyDoMEIoSxN8lBbC5AfI9wJJLMg3xvn0",
            "expires": "2022-04-21T18:23:12.273Z",
            "status": "Success",
            "result": "User authorized successfully."
        }
         */
    private String token;
    private String expires;
    private String status;
    private String result;

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}
package Framework.Data;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Userdata {
    @JsonProperty("email")
    public String getEmail() {
        return this.email; }
    String email;
    @JsonProperty("password")
    public String getPassword() {
        return this.password; }
    String password;
}

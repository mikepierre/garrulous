package xyz.garrulous.garrulous.model;

/**
 * Created by michaelpierre on 11/14/15.
 */
public class AboutUsers {
    public AboutUsers(int id, int userId, String age, String aboutMe, String location,
                      String university) {
        this.id = id;
        this.userId = userId;
        this.age = age;
        this.aboutMe = aboutMe;
        this.location = location;
        this.university = university;
    }

    int id;
    int userId;
    String age;
    String aboutMe;
    String location;
    String university;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}

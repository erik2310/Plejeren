package sovsen.plejeren.view.presenter;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {


    public static String getEmail;
    public static String Email;
    public String uid;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String uid, String email) {
        this.uid = uid;
        this.Email = email;
    }

    public String getUid() {
        return uid;
    }

    public static String getEmail() {
        return Email;
    }
}
package sovsen.plejeren.view.presenter;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Notes {
    private String Notes;

    public Notes() {
    }

    public Notes(String notes) {
        this.Notes = notes;
    }


    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getNotes(String notes) {
        return Notes;
    }


}

package sovsen.plejeren.activities.ClientComponents;

public class Client {

    // Definerer String datatyper
    private String mName;
    private String mAddress;
    private String mTime;

    // Constructor som tager imod Name og Address som arguments
    public Client(String mName, String mAddress, String mTime) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mTime = mTime;
    }

    // Getters og setters til objekterne

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}

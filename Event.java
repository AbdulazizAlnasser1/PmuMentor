public class Event {
    private int id;
    private String title;
    private String type;
    private String dateTime;
    private String locationOrLink;
    private String createdByUsername;

    public Event(int id, String title, String type, String dateTime, String locationOrLink, String createdByUsername) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.dateTime = dateTime;
        this.locationOrLink = locationOrLink;
        this.createdByUsername = createdByUsername;
    }

    public int getId() {
        return id;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    @Override
    public String toString() {
        return "Event #" + id +
                " | Title: " + title +
                " | Type: " + type +
                " | Date/Time: " + dateTime +
                " | Location/Link: " + locationOrLink +
                " | Created By: " + createdByUsername;
    }
}

package pl.group.Entity;

public class Task {

    private String name;
    private Long startTime;
    private Long stopTime;
    private boolean active;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.startTime = System.currentTimeMillis();
        this.stopTime = null;
        this.active = true;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

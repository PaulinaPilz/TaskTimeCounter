package pl.group.Entity;

public class Task {

    private String name;
    private Long startTime;
    private Long stopTime;

    public Task() {}

    public Task(String name, Long startTime, Long stopTime) {
        this.name = name;
        this.startTime = startTime;
        this.stopTime = stopTime;
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
}

package objects;

public class Map {
    public final String name;
    public final int minPlayers;
    public final int maxPlayers;
    public final int meetingDuration;
    public final int emergencyCooldown;
    public final int emergencyLimit;
    public final int killCooldown;

    public Map(String name, int minPlayers, int maxPlayers, int meetingDuration, int emergencyCooldown, int emergencyLimit, int killCooldown) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.meetingDuration = meetingDuration;
        this.emergencyCooldown = emergencyCooldown;
        this.emergencyLimit = emergencyLimit;
        this.killCooldown = killCooldown;
    }
}

//impostors: 2
//    minPlayers: 4
//    maxPlayers: 10
//    meetingDuration: 60
//    emergencyCooldown: 30
//    emergencyLimit: 1
//    crewSpeed: 2
//    impostorSpeed: 3
//    killCooldown: 25

package app.model.steam;

public class TrialGame extends Game implements Playable{
    private int trialDuration;

    public TrialGame(String gameId, String title, double price, int trialDuration,  String imagePath, String desc) {
        super(gameId, title, price, imagePath, desc);
        this.trialDuration = trialDuration;
    }

    public int getTrialDuration() {
        return trialDuration;
    }

    public String download(){
        return "Downloading Trial " + getTitle() + "...";
    }

    public String play(){
        return "Playing " + getTitle();
    }

    public String exit(){
        return "Exiting Game Trial";
    }    
}

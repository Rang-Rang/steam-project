package app.steam;

public class TrialGame extends Game{
    private int trialDuration;

    public TrialGame(String gameId, String title, double price, int trialDuration) {
        super(gameId, title, price);
        this.trialDuration = trialDuration;
    }

    public void download(){

    }

    public void play(){

    }

    public void exit(){

    }    
}

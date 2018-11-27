package com.example.nacho.fortniteapi.Values;

public class PlayerStatsDetails {
    private FinalStats trnRating;
    private FinalStats kd;
    private FinalStats matches;
    private FinalStats score;

    public FinalStats getTRNRating() {
        return trnRating;
    }

    public void setTRNRating(FinalStats TRNRating) {
        this.trnRating = TRNRating;
    }

    public FinalStats getKDA() {
        return kd;
    }

    public void setKDA(FinalStats KDA) {
        this.kd = KDA;
    }

    public FinalStats getMatches() {
        return matches;
    }

    public void setMatches(FinalStats matches) {
        this.matches = matches;
    }

    public FinalStats getScore() {
        return score;
    }

    public void setScore(FinalStats score) {
        this.score = score;
    }

}

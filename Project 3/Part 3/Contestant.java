public class Contestant {
    public final String name;
    private int score;

    public Contestant(String name) {
	this.name = name;
	this.score = 0;
    }

    public Contestant(String name, int score) {
	this.name = name;
	this.score = score;
    }

    public int getScore() {
	return this.score;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public void addToScore(int score) {
	this.score += score;
    }

    public String toString() {
	return this.name + ": " + this.score;
    }
}

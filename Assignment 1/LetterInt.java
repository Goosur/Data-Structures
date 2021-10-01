public class LetterInt implements Letter {
    
    private int letter;

    public LetterInt(int integer) throws IllegalArgumentException {
        // Check if input character is actually a letter
        if (!((integer >= 65 && integer <= 90) || (integer >= 97 && integer <= 122))) {
            throw new IllegalArgumentException((char) integer + "(" + integer + ")" + " is not a letter.");
        }

        this.letter = integer;
    }

    @Override
    public char getChar() {
        return (char) this.letter;
    }

    @Override
    public int getInt() {
        return this.letter;
    }
}
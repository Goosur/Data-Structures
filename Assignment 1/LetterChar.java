public class LetterChar implements Letter {
    
    private char letter;

    public LetterChar(char character) throws IllegalArgumentException {
        // Check if input character is actually a letter
        if (!(((int) character >= 65 && (int) character <= 90) || ((int) character >= 97 && (int) character <= 122))) {
            throw new IllegalArgumentException(character + "(" + (int) character + ")" + " is not a letter.");
        }

        this.letter = character;
    }

    @Override
    public char getChar() {
        return this.letter;
    }

    @Override
    public int getInt() {
        return (int) this.letter;
    }
}

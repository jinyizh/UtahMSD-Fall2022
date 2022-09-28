public class Fraction {
    private long numerator;
    private long denominator;
    private long GCD() {
        long gcd = this.numerator;
        long remainder = this.denominator;
        while (remainder != 0) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return gcd;
    }
    private void reduce() {
        this.numerator /= this.GCD();
        this.denominator /= this.GCD();
    }
    public Fraction() {
        this.numerator = 0;
        this.denominator = 1;
    }
    public Fraction(long n, long d) {
        this.numerator = n;
        this.denominator = d;
    }
    public Fraction plus(Fraction rhs) {
        long newDenominator = this.denominator * rhs.denominator;
        long newNumerator = this.numerator * rhs.denominator + rhs.numerator * this.denominator;
        Fraction unReduced = new Fraction(newNumerator, newDenominator);
        unReduced.reduce();
        return unReduced;
    }
    public Fraction minus(Fraction rhs) {
        long newDenominator = this.denominator * rhs.denominator;
        long newNumerator = this.numerator * rhs.denominator - rhs.numerator * this.denominator;
        Fraction unReduced = new Fraction(newNumerator, newDenominator);
        unReduced.reduce();
        return unReduced;
    }
    public Fraction times(Fraction rhs) {
        Fraction unReduced = new Fraction(this.numerator * rhs.numerator, this.denominator * rhs.denominator);
        unReduced.reduce();
        return unReduced;
    }
    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }
    public Fraction dividedBy(Fraction rhs) {
        return this.times(rhs.reciprocal());
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.numerator * this.denominator < 0) {
            sb.append("-");
        }
        return sb.toString();
    }
    public double toDouble() {

    }
}

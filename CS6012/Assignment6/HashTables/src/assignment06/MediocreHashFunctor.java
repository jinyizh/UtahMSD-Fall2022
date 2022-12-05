package assignment06;

public class MediocreHashFunctor implements HashFunctor {
  @Override
  public int hash(String item) {
    return item.length() + item.charAt(0) + item.charAt(item.length() - 1); // length, first and last chars
  }
}

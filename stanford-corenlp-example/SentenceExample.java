import edu.stanford.nlp.simple.Sentence;
import java.util.*;

public class SentenceExample {
  public static void main(String []args) {
    String s = "I am a good boy.";
    String s1 = "He really likes his job.";

    String[] arr = {"I", "am", "a", "good", "boy", ".", ".", "."};

    String[] arr1 = {"I", " ", "am", " ", "a", " ", "good", " ", "boy"};

    List<String> tokenizedSentence = Arrays.asList(arr);
    Sentence sentence = new Sentence(tokenizedSentence);
    List<String> lemmas = sentence.lemmas();
    List<String> postags = sentence.posTags();

    System.out.println(Arrays.toString(lemmas.toArray()));
    System.out.println(Arrays.toString(postags.toArray()));

    tokenizedSentence = Arrays.asList(arr1);
    sentence = new Sentence(tokenizedSentence);
    lemmas = sentence.lemmas();
    postags = sentence.posTags();
    System.out.println(Arrays.toString(lemmas.toArray()));
    System.out.println(Arrays.toString(postags.toArray()));

    lemmas = getLemmasList(s);

    System.out.println(Arrays.toString(lemmas.toArray()));
    //System.out.println(Arrays.toString(postags.toArray()));

    lemmas = getLemmasList(s1);
    //postags = getPostagsList(s1);

    System.out.println(Arrays.toString(lemmas.toArray()));
    //System.out.println(Arrays.toString(postags.toArray()));
  }

  public static List<String> getLemmasList(String text) {
    Sentence sentence = new Sentence(text);
    return sentence.lemmas();
  }

  public static List<String> getPostagsList(String text) {
    Sentence sentence = new Sentence(text);
    return sentence.posTags();
  }
}



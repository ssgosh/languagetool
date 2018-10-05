import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.*;

public class MaxentTaggerExample {

  public static String concatStringsWSep(Iterable<String> strings, String separator) {
    StringBuilder sb = new StringBuilder();
    String sep = "";
    for(String s: strings) {
      sb.append(sep).append(s);
      sep = separator;
    }
    return sb.toString();                           
  }

  public static void main(String []args) {
    //MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
    //MaxentTagger tagger = new MaxentTagger("lib/tagger/taggers/english-left3words-distsim.tagger");
    String [] words = {"He", "has", "a", "dog"};
    List<String> sentence = Arrays.asList(words);
    String str_sentence = concatStringsWSep(sentence, " ");
    System.out.println(str_sentence);
    
    List<String> tags = tagString(str_sentence);
    for (String tag: tags) {
        System.out.println(tag);
    }

    tags = tagString("I am a good boy");
    for (String tag: tags) {
        System.out.println(tag);
    }

  }

  public static List<String> tagString(String sentence) {
    MaxentTagger tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");

    String tags = tagger.tagString(sentence);
    //System.out.println(tags);
    String[] eachTag = tags.split("\\s+");

    List<String> postags = new ArrayList<>();
    for (String str : eachTag) {
        int i = str.lastIndexOf("_");
        String postag = str.substring(i + 1);
        //System.out.print(postag + " ");
        postags.add(postag);
    }

    return postags;
  }
}


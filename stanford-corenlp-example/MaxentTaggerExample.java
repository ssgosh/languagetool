import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class MaxentTaggerExample {
  public static void main(String []args) {
    //MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
    //MaxentTagger tagger = new MaxentTagger("lib/tagger/taggers/english-left3words-distsim.tagger");
    MaxentTagger tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
    String tags = tagger.tagString("I am a good boy.");
    //System.out.println(tags);
    String[] eachTag = tags.split("\\s+");

    for (String str : eachTag) {
        int i = str.lastIndexOf("_");
        String postag = str.substring(i + 1);
        System.out.println(postag);
    }
  }
}


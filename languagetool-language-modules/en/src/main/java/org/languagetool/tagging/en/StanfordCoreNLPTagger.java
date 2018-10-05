package org.languagetool.tagging.en;

import java.util.Locale;

import org.languagetool.tagging.BaseTagger;

import org.apache.commons.lang3.StringUtils;

/**
 * English Part-of-speech tagger.
 * The POS tagset is described in
 * <a href="https://github.com/languagetool-org/languagetool/blob/master/languagetool-language-modules/en/src/main/resources/org/languagetool/resource/en/tagset.txt">tagset.txt</a>
 * 
 * @author Marcin Milkowski
 */
public class StanfordCoreNLPTagger extends EnglishTagger{
  MaxentTagger tagger;

  @Override
  public List<AnalyzedTokenReadings> tag(List<String> sentenceTokens)
      throws IOException {

    String str_sentence = concatStringsWSep(sentenceTokens, " ");
    List<AnalyzedTokenReadings> tokenReadings = new ArrayList<>();
    int pos = 0;
    for (String word : sentenceTokens) {
      List<AnalyzedToken> l = getAnalyzedTokens(word);
      tokenReadings.add(new AnalyzedTokenReadings(l, pos));
      pos += word.length();
    }
    return tokenReadings;
  }

  public StanfordCoreNLPTagger() {
    super();
    this.tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
  }

  private String concatStringsWSep(Iterable<String> strings, String separator) {
    StringBuilder sb = new StringBuilder();
    String sep = "";
    for(String s: strings) {
      sb.append(sep).append(s);
      sep = separator;
    }
    return sb.toString();                           
  }

  private List<String> tagString(String sentence) {
    String tags = this.tagger.tagString(sentence);
    //System.out.println(tags);
    String[] eachTag = tags.split("\\s+");

    List<String> postags = new ArrayList<>();
    for (String str : eachTag) {
        int i = str.lastIndexOf("_");
        String postag = str.substring(i + 1);
        postags.add(postag);
        //System.out.print(postag + " ");
    }

    return postags;
  }
}

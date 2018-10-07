package org.languagetool.tagging.en;

import edu.stanford.nlp.simple.Sentence;

import java.util.Locale;

import org.languagetool.tagging.BaseTagger;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

import org.languagetool.tagging.*;

import org.languagetool.*;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;

/**
 * English Part-of-speech tagger.
 * The POS tagset is described in
 * <a href="https://github.com/languagetool-org/languagetool/blob/master/languagetool-language-modules/en/src/main/resources/org/languagetool/resource/en/tagset.txt">tagset.txt</a>
 * 
 * @author Marcin Milkowski
 */
public class StanfordCoreNLPTagger extends EnglishTagger{
  //MaxentTagger tagger;

  @Override
  public List<AnalyzedTokenReadings> tag(List<String> sentenceTokens)
      throws IOException {

    // sentenceTokens contains spaces; just concatenate them as-is
    String str_sentence = concatStringsWSep(sentenceTokens, " ");

    // CoreNLP simple.Sentence does tagging and lemmatization. No need to
    // delve in MaxentTagger or CoreNLP pipeline.
    Sentence coreNLPSentence = new Sentence(str_sentence);
    List<String> lemmas = coreNLPSentence.lemmas();
    List<String> postags = coreNLPSentence.posTags();
    List<AnalyzedTokenReadings> tokenReadings = new ArrayList<>();

    System.out.println(str_sentence);
    for (String word : sentenceTokens) {
        System.out.println(word);
    }

    for (String tag : postags) {
        System.out.println(tag);
    }

    System.out.println("Length of postags: " + postags.size() + ", Number of tokens: " + sentenceTokens.size());

    // Insert lemma and POS tags. For whitespace both are null
    int pos = 0;
    for (int i = 0, j = 0; i < sentenceTokens.size(); ++i) {
      String word = sentenceTokens.get(i);
      List<AnalyzedToken> l = null;
      if (" ".equals(word)) {
        l = getAnalyzedTokensForIthWord(sentenceTokens, postags, lemmas, i, -1);
      } else {
        System.out.println("Sending j for word " + word + ", j = " + j);
        l = getAnalyzedTokensForIthWord(sentenceTokens, postags, lemmas, i, j);
        ++j;
      }
      tokenReadings.add(new AnalyzedTokenReadings(l, pos));
      pos += word.length();
    }
    return tokenReadings;
  }

  public StanfordCoreNLPTagger() {
    super();
    //this.tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
    System.out.println("Using StanfordCoreNLPTagger");
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

/*  private List<String> tagString(String sentence) {
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
*/
  private List<AnalyzedToken> getAnalyzedTokensForIthWord(List<String>sentenceTokens, List<String> postags, List<String> lemmas, int i, int j) {
    List<AnalyzedToken> ret = new ArrayList<>();
    String tag, lemma;
    if (j == -1) {
        tag = null;
    } else {
        tag = postags.get(j);
    }

    if (lemmas == null || j == -1) {
        lemma = null;
    } else {
        lemma = lemmas.get(j);
    }

    ret.add(new AnalyzedToken(sentenceTokens.get(i), tag, lemma));
    return ret;
  }

}

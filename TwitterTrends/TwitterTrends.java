package TwitterTrends;

import java.util.*;
import java.io.*;

// This class, TwitterTrends, analyzes tweets retrieved by a TweetBot instance to determine
// the most frequent word and to flag potential misinformation related to COVID-19.
public class TwitterTrends {
   private TweetBot trends;
   
   // Behavior:
   //   - Initializes TwitterTrends with given TweetBot.
   // Parameters:
   //   - bot: an instance of TweetBot used to analyze and extract trends from tweets.
   public TwitterTrends(TweetBot bot) {
      trends = bot;
   }
   
   // Behavior:
   //   - Analyzes tweets to find the most frequently occurring word
   // Returns:
   //   - String: the most frequent word in the collected tweets
   public String getMostFrequentWord() {
      Map<String, Integer> tweetCounter = new HashMap<>();
      
      for(int i=0; i<trends.numTweets(); i++){
         String tweet = trends.nextTweet().toLowerCase();
         Scanner sc = new Scanner(tweet);
         while(sc.hasNext()){
            String temporary = sc.next();
            if(tweetCounter.containsKey(temporary)){
               int count = tweetCounter.get(temporary);
               tweetCounter.put(temporary, count + 1);
            } else{
               tweetCounter.put(temporary, 1);
            }
         }
      }
      
      Set<String> tweetHolder = tweetCounter.keySet();
      Iterator<String> itr = tweetHolder.iterator();
      String mostFrequentWord = itr.next();
      int maxFrequency = tweetCounter.get(mostFrequentWord);
      
      while(itr.hasNext()){
         String next = itr.next();
         int temp = tweetCounter.get(next);
         if (temp > maxFrequency){
            maxFrequency = temp;
            mostFrequentWord = next;
         }
      }
      return mostFrequentWord;
   }
   
   // Behavior:
   //   - Flags tweets containing COVID or pandemic references for potential misinformation
   public void flagPotentialMisinformation() {
      List<String> flaggedTweets = new ArrayList<>();
      int originalIndex = trends.numTweets();
      
      for (int i = 0; i < originalIndex; i++) {
         String tweet = trends.nextTweet();
         Scanner sc = new Scanner(tweet.toLowerCase());
         boolean hasCOVIDReference = false;
         
         while (sc.hasNext()) {
            String word = sc.next();
            if (word.equals("covid") || word.equals("pandemic")) {
               hasCOVIDReference = true;
               break;
            }
         }
         
         if (hasCOVIDReference) {
            flaggedTweets.add(tweet);
            
            System.out.println("Disclaimer: This tweet references COVID or the pandemic.");
            System.out.println("For accurate information, visit the CDC website: https://www.cdc.gov/");
            System.out.println("Tweet: " + tweet);
            System.out.println("--------------------------------------------");
            System.out.println();
         }
      }
      
      for (int i = 0; i < originalIndex; i++) {
         trends.nextTweet();
      }
   }
}
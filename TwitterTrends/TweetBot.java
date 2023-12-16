package TwitterTrends;

import java.util.*;
import java.io.*;

// This class, TweetBot, manages a list of tweets, allowing addition,
// removal, retrieval, and management of tweets.
public class TweetBot {
   private List<String> tweetList;
   private int currentIndex = 0;
   
   // Behavior:
   //   - Constructs a TweetBot, initializing it with a list of tweets.
   // Parameters:
   //   - tweets: a List<String> containing tweets to be managed by the TweetBot.
   // Exceptions:
   //   - If the provided 'tweets' list is empty, an IllegalArgumentException is thrown.
   public TweetBot(List<String> tweets) {
      if (tweets.size() < 1) {
         throw new IllegalArgumentException();
      }
      tweetList = new ArrayList<>(tweets);
   }
   
   // Behavior:
   //   - Returns the total number of tweets in the tweetList.
   // Returns:
   //   - int: the number of tweets.
   public int numTweets() {
      return tweetList.size();
   }
   
   // Behavior:
   //   - Adds a tweet to the tweetList.
   // Parameters:
   //   - tweet: String tweet to be added.
   public void addTweet(String tweet) {
      tweetList.add(tweet);
   }
   
   // Behavior:
   //   - Retrieves the next tweet in the list and updates the currentIndex.
   //     and starts back at beginning of list if function called at end of list
   // Returns:
   //   - String: the next tweet.
   public String nextTweet() {
      if (currentIndex == tweetList.size()){
         currentIndex = 0;
      }
      
      String result = tweetList.get(currentIndex);
      currentIndex++;
      
      
      return result;
   }
   
   // Behavior:
   //   - Removes a specific tweet from the tweetList.
   // Parameters:
   //   - tweet: String tweet to be removed.
   public void removeTweet(String tweet) {
      int indexToRemove = tweetList.indexOf(tweet);
      if (indexToRemove >= 0) {
         tweetList.remove(indexToRemove);
         if (currentIndex > indexToRemove) {
            currentIndex = Math.max(0, currentIndex - 1);
         } else if (currentIndex >= tweetList.size()) {
            currentIndex = 0;
         }
      }
   }
   
   // Behavior:
   //   - Resets the currentIndex to the beginning of the tweetList.
   public void reset() {
      currentIndex = 0;
   }
}

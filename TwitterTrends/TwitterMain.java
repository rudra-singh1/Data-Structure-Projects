package TwitterTrends;

import java.util.*;
import java.io.*;

// This class analyzes tweets from a tweet file, identifies trends, and 
// flags COVID-related misinformation.
public class TwitterMain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("tweets.txt")); // Make Scanner over tweet file
        List<String> tweets = new ArrayList<>();
        while (input.hasNextLine()) { // Add each tweet in file to List
            tweets.add(input.nextLine());
        }

        TweetBot bot = new TweetBot(tweets); // Create TweetBot object with list of tweets
        TwitterTrends trends = new TwitterTrends(bot); // Create TwitterTrends object
   
        System.out.println("Most Frequent Word in tweets.txt: " + trends.getMostFrequentWord());
        System.out.println();
        trends.flagPotentialMisinformation();
    }
}

import java.util.ArrayList;

public class Twitter {
	
	//ADD YOUR CODE BELOW HERE

	private MyHashTable<String, Tweet> latestTweets;

	private MyHashTable<String, ArrayList<Tweet>> dailyTweets;

	private MyHashTable<String, String> stopWords;

	private ArrayList<String> messages;



		
	//ADD CODE ABOVE HERE 
	
	// O(n+m) where n is the number of tweets, and m the number of stopWords
	public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {
		//ADD YOUR CODE BELOW HERE

		// initialize & populate hash tables & array of this Twitter

		this.latestTweets = new MyHashTable<String, Tweet>(1);
		this.dailyTweets = new MyHashTable<String, ArrayList<Tweet>>(1);
		this.stopWords = new MyHashTable<String, String>(1);
		this.messages = new ArrayList<String>(tweets.size());
		for (int i=0; i<tweets.size(); i++) {
			this.addTweet(tweets.get(i));
			this.messages.add(tweets.get(i).getMessage());
		}
		for (int i=0; i<stopWords.size(); i++) {
			String currentLowerCase  = stopWords.get(i).toLowerCase();
			this.stopWords.put(currentLowerCase, currentLowerCase);
		}

		//ADD CODE ABOVE HERE 
	}
	
	
    /**
     * Add Tweet t to this Twitter
     * O(1)
     */
	public void addTweet(Tweet t) {
		//ADD CODE BELOW HERE

		// add Tweet to latest tweets table

		String key1 = t.getAuthor();

		// only add tweet to latest tweets table if it is the latest by an author

		if (this.latestTweets.get(key1) != null) {
			String originalTime = this.latestTweets.get(key1).getDateAndTime();
			if (originalTime.compareTo(t.getDateAndTime())<=0) {
				this.latestTweets.put(key1, t);
			}
		}
		else { this.latestTweets.put(key1, t); }

		String includesTime = t.getDateAndTime();
		String key2 = "";
		for (int i=0; i<10; i++) {
			key2 = key2 + includesTime.charAt(i);
		}

		// add tweet to daily tweets table

		if (this.dailyTweets.get(key2) == null) {
			this.dailyTweets.put(key2, new ArrayList<Tweet>(1));
		}
		this.dailyTweets.get(key2).add(t);

		//ADD CODE ABOVE HERE 
	}
	

    /**
     * Search this Twitter for the latest Tweet of a given author.
     * If there are no tweets from the given author, then the 
     * method returns null. 
     * O(1)  
     */
    public Tweet latestTweetByAuthor(String author) {
        //ADD CODE BELOW HERE

		return latestTweets.get(author);

        //ADD CODE ABOVE HERE 
    }


    /**
     * Search this Twitter for Tweets by `date' and return an 
     * ArrayList of all such Tweets. If there are no tweets on 
     * the given date, then the method returns null.
     * O(1)
     */
    public ArrayList<Tweet> tweetsByDate(String date) {
        //ADD CODE BELOW HERE

		return dailyTweets.get(date);
    	
        //ADD CODE ABOVE HERE
    }
    
	/**
	 * Returns an ArrayList of words (that are not stop words!) that
	 * appear in the tweets. The words should be ordered from most 
	 * frequent to least frequent by counting in how many tweet messages
	 * the words appear. Note that if a word appears more than once
	 * in the same tweet, it should be counted only once. 
	 */
    public ArrayList<String> trendingTopics() {
        //ADD CODE BELOW HERE

		MyHashTable<String, Integer> topWords = new MyHashTable<String, Integer>(1);

		boolean b = true;

		// populate the table with pairs whose keys are the unique words, and values are how many times they appear

		for (int i=0; i<this.messages.size(); i++) {
			ArrayList<String> tweetsWordsArr = getWords(this.messages.get(i));
			MyHashTable<String, String> tweetsWordsHash = new MyHashTable<String, String>(3);
			for (int j=0; j<tweetsWordsArr.size(); j++) {
				String currentWord = tweetsWordsArr.get(j).toLowerCase();
				tweetsWordsHash.put(currentWord, currentWord);
				}
			tweetsWordsArr = tweetsWordsHash.values();
			for (int j=0; j<tweetsWordsArr.size(); j++) {
				String currentWord = tweetsWordsArr.get(j);
				if (topWords.get(currentWord) == null && this.stopWords.get(currentWord) == null) {
					topWords.put(currentWord, 1);
				}
				else if (this.stopWords.get(currentWord) == null) {
					topWords.put(currentWord, topWords.get(currentWord)+1);
				}
			}
		}


		System.out.println(topWords.get("the"));
		ArrayList<String> topWordsKeys = MyHashTable.fastSort(topWords);
    	
    	return topWordsKeys;
    	
        //ADD CODE ABOVE HERE    	
    }
    
    
    
    /**
     * An helper method you can use to obtain an ArrayList of words from a 
     * String, separating them based on apostrophes and space characters. 
     * All character that are not letters from the English alphabet are ignored. 
     */
    private static ArrayList<String> getWords(String msg) {
    	msg = msg.replace('\'', ' ');
    	String[] words = msg.split(" ");
    	ArrayList<String> wordsList = new ArrayList<String>(words.length);
    	for (int i=0; i<words.length; i++) {
    		String w = "";
    		for (int j=0; j< words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
    				w += c;
    			
    		}
    		wordsList.add(w);
    	}
    	return wordsList;
    }
}

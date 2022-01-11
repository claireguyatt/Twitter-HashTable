import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyTester {

    public static void main(String[] args) {

        ArrayList<String> stopWords = new ArrayList<String>();
        Twitter t = new Twitter(initTweetList(), stopWords);

        System.out.println(t.trendingTopics().toString());






        MyHashTable<Integer, Integer> test = new MyHashTable<Integer, Integer>(5);


        test.put(1, 1);
        //HashPair<Integer, Integer> one = new HashPair<>(1,1);
        //System.out.println(test.hashFunction(one.getKey()));
        test.put(2, 2);
        //HashPair<Integer, Integer> two = new HashPair<>(2,2);
        //System.out.println(test.hashFunction(two.getKey()));
        test.put(3, 3);
        //HashPair<Integer, Integer> three = new HashPair<>(3,3);
        //System.out.println(test.hashFunction(three.getKey()));
        test.put(4, 4);
        //HashPair<Integer, Integer> four = new HashPair<>(4,4);
        //System.out.println(test.hashFunction(four.getKey()));
        test.put(5, 5);
        //HashPair<Integer, Integer> five = new HashPair<>(5,5);
        //System.out.println(test.hashFunction(five.getKey()));
        test.put(6,6);
        test.put(7,7);
        test.put(8,8);
        test.put(9,9);
        test.put(10,10);
        test.put(11,11);
        test.put(12,12);
        test.put(13,13);
        test.put(14,14);
        test.put(15,15);
        test.put(16,16);

        ArrayList<Tweet> twits = new ArrayList<Tweet>();
        twits.add(new Tweet("USER_989b85bb","2010-03-04 15:34:46","@USER_6921e61d I can be made into one twitter superstar."));
        twits.add(new Tweet("USER_989b85bb","2010-03-04 15:34:47","I can be MADE into a need."));

        String time1 = "2010-03-04 15:34:46";
        String time2 = "2010-03-04 15:34:47";

        //System.out.println(time1.compareTo(time2));

        ArrayList<String> stops = new ArrayList<String>();

        Twitter twitter = new Twitter(twits, stops);


        //System.out.println(twitter.latestTweetByAuthor("USER_989b85bb"));





    }

    private static ArrayList<Tweet> initTweetList() {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:46","@USER_6921e61d I can be made into one twitter superstar."));
        tweets.add(new Tweet("USER_a75657c2","2010-03-03 00:02:54","@USER_13e8a102 They reached a compromise just on time"));
        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:47","I can be MADE into a need."));
        tweets.add(new Tweet("USER_a75657c2","2010-03-07 21:45:48","So SunChips made a bag that is 100% biodegradeable. It is about damn time somebody did."));
        tweets.add(new Tweet("USER_ee551c6c","2010-03-07 15:40:27","drthema: Do something today that feeds your spirit and empowers you to start the week from a higher place."));
        tweets.add(new Tweet("USER_6c78461b","2010-03-03 05:13:34","@USER_a3d59856 yes, i watched that foolery done disturbed my spirit. @USER_b1d28f26"));
        tweets.add(new Tweet("USER_92b2293c","2010-03-04 14:00:11","@USER_5aac9e88: Let no one push u around today! Be at Peace! If u dont have restful spirit, u'll definitely have a stressful spirit"));
        tweets.add(new Tweet("USER_75c62ed9","2010-03-07 03:35:38","@USER_cb237f7f Congrats on everything I am there in spirit my brother."));
        tweets.add(new Tweet("USER_7f72a368","2010-03-07 07:18:22","Actions speak louder than words but feelings and spirits speak louder than anything #FACT"));
        tweets.add(new Tweet("USER_b6cc1831","2010-03-07 04:04:37","@USER_be777094 urban spirit cafe. On Long st"));
        tweets.add(new Tweet("USER_65006b55","2010-03-05 00:58:28","RT @USER_86e8d97f: @USER_65006b55's spirit just took a turn for the worst. Lol please."));
        tweets.add(new Tweet("USER_60b9991b","2010-03-04 22:33:23","Who on my time ever flew on spirit airlines let me kno if there decent"));
        tweets.add(new Tweet("USER_36607a99","2010-03-03 02:06:01","@USER_561fe280: Nourish your spirit with your own achievement."));
        tweets.add(new Tweet("USER_9506fb5f","2010-03-04 01:16:34","Great spirits have often encountered violent opposition from weak minds"));
        tweets.add(new Tweet("USER_d3ca457f","2010-03-03 04:53:06","RT @USER_6d6bfb4d: The things that make a woman beautiful are her character, intellect, and spirituality."));
        tweets.add(new Tweet("USER_14f78255","2010-03-03 17:07:45","@USER_9afbc367 Oh in spirit. That's all that matters lol"));
        tweets.add(new Tweet("USER_3dfae4fe","2010-03-05 00:44:33","time for a spiritual cleansing of my facebook friend list"));
        tweets.add(new Tweet("USER_bd852fb7","2010-03-03 14:19:51","RT @USER_24bd1961:God's spirit is like a Radio station, broadcasting all the time. You just have to learn how to tune in and receive his signal"));
        tweets.add(new Tweet("USER_136c16da","2010-03-07 19:56:54","RT @USER_11d35e61: @USER_136c16da finally a kindred spirit. *daps* lol thanks"));
        tweets.add(new Tweet("USER_47063e51","2010-03-04 12:47:54","cathartic - noun - a purification or purgation that brings about spiritual renewal or release from tension"));
        tweets.add(new Tweet("USER_1e4eb302","2010-03-03 20:13:18","Anything worth having you have to contribute yourself heart, mind, soul and spirit to. It is so rewarding. Have u contributed lately?"));
        tweets.add(new Tweet("USER_5d246e83","2010-03-04 14:57:01","@USER_8e090edb That's always good to hear. Starting off to a good morning, always puts your spirit in a great place."));
        tweets.add(new Tweet("USER_b7117680","2010-03-03 06:55:17","I got a hustlas spirit, period!"));
        tweets.add(new Tweet("USER_25ecff25","2010-03-05 17:33:20","RT @USER_3a117437: The woman at the rental car spot tried 2 give us a Toyota! No ma'am lk the old spiritual says \"aint got time 2 die!\""));
        tweets.add(new Tweet("USER_f91d8165","2010-03-03 22:33:24","#RandomThought why do people grab guns or knives when they think theres a ghost? DUMBASS! You can't shoot a spirit, grab some holy water! duh"));
        tweets.add(new Tweet("USER_86c542b8","2010-03-04 02:52:06","@USER_8cd1512d haha, maybe your right. I use to watch gymnastics all the time. I love the olympics. That's why I have so much spirit lol"));

        return tweets;
    }
}

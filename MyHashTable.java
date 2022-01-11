import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    // ******
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS

        this.numBuckets = initialCapacity;

        this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(this.numBuckets);

        for (int i=0; i<this.numBuckets; i++) {
            this.buckets.add(i, new LinkedList<HashPair<K,V>>());
        }

        this.numEntries = 0;
        
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public boolean isEmpty() {
        return this.numEntries == 0;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE

        // hash input key to find right bucket
        int index = hashFunction(key);
        LinkedList<HashPair<K, V>> rightBucket = this.buckets.get(index);

        // case if the key already exists in the table
        for (HashPair<K, V> hp : rightBucket) {
            if (hp.getKey().equals(key)) {
                V temp = hp.getValue();
                hp.setValue(value);
                return temp;
            }
        }
        // check if adding a new entry will exceed the load, rehash if it will
        if ((double)(numEntries+1)/(double)numBuckets>0.75) {
            this.rehash();
            rightBucket = this.buckets.get(hashFunction(key));
            // make sure this adds the new one still
        }
        // add the new pair to the map
        rightBucket.add(new HashPair<K,V>(key, value));
        this.numEntries++;
    	return null;
        
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE

        // hash input key to find right bucket
        int index = hashFunction(key);
        LinkedList<HashPair<K, V>> rightBucket = this.buckets.get(index);

        // if the key exists in the table
        for (HashPair<K, V> hp : rightBucket) {
            if (hp.getKey().equals(key)) {
                return hp.getValue();
            }
        }
        // if the key is not found in the table
    	return null;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE

        // hash input key to find right bucket
        int index = hashFunction(key);
        LinkedList<HashPair<K, V>> rightBucket = this.buckets.get(index);

        // if the key exists in the table
        for (HashPair<K, V> hp : rightBucket) {
            if (hp.getKey().equals(key)) {
                V temp = hp.getValue();
                rightBucket.remove(hp);
                this.numEntries--;
                return temp;
            }
        }
        // if the key is not found in the table
    	return null;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /** 
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
        //ADD YOUR CODE BELOW HERE

        this.numBuckets = this.numBuckets*2;
        ArrayList<LinkedList<HashPair<K, V>>> temp = new ArrayList<LinkedList<HashPair<K, V>>>(this.numBuckets);

        for (int i=0; i<this.numBuckets; i++) {
            temp.add(i, new LinkedList<HashPair<K,V>>());
        }

        // move the entries in the map according to the new size
        for (int i=0; i<this.numBuckets/2; i++) {
            for (HashPair<K, V> hp : this.buckets.get(i)) {
                int hash = hashFunction(hp.getKey());
                temp.get(hash).add(hp);
            }
        }
        this.buckets = temp;

        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE

        ArrayList<K> keys = new ArrayList<K>(this.numEntries);

        for (int i=0; i<this.numBuckets; i++) {
            for (HashPair<K, V> hp : this.buckets.get(i)) {
                keys.add(hp.getKey());
            }
        }
    	return keys;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE

        // initialize new hash table to access unique values of entries
        ArrayList<LinkedList<HashPair<K, V>>> temp = new ArrayList<LinkedList<HashPair<K, V>>>(this.numBuckets);

        for (int i=0; i<this.numBuckets; i++) {
            temp.add(i, new LinkedList<HashPair<K,V>>());
        }

        int counter = 0;

        for (int i=0; i<this.numBuckets; i++) {
            for (HashPair<K, V> hp : this.buckets.get(i)) {
                boolean b = true;
                // make the entry of each pair defined by the value of the pair
                int rightTempBucket = valueHashFunction(hp.getValue());
                // if there is already an entry with the same value, don't re-add it
                for (HashPair<K, V> hp1 : temp.get(rightTempBucket)) {
                    if (hp.getValue().equals(hp1.getValue())) {
                        b = false;
                        break;
                    }
                }
                if (b==true) {
                    counter++;
                    temp.get(rightTempBucket).add(hp);
                }
            }
        }
        // initialize & populate new ArrayList with unique values
        ArrayList<V> values = new ArrayList<V>(counter);
        for (int i=0; i<this.buckets.size(); i++) {
            for (HashPair<K,V> hp : temp.get(i)) {
                values.add(hp.getValue());
            }
        }
    	return values;
    	
        //ADD CODE ABOVE HERE
    }
    private int valueHashFunction(V value) {
        int hashValue = Math.abs(value.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort (MyHashTable<K, V> results) {
        ArrayList<K> sortedResults = new ArrayList<>();
        for (HashPair<K, V> entry : results) {
			V element = entry.getValue();
			K toAdd = entry.getKey();
			int i = sortedResults.size() - 1;
			V toCompare = null;
        	while (i >= 0) {
        		toCompare = results.get(sortedResults.get(i));
        		if (element.compareTo(toCompare) <= 0 )
        			break;
        		i--;
        	}
        	sortedResults.add(i+1, toAdd);
        }
        return sortedResults;
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to.
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K, V> results) {
        //ADD CODE BELOW HERE

        // initialize hashpair arraylist and populate with pairs from input hashtable

        ArrayList<HashPair<K,V>> arrHP = new ArrayList<HashPair<K,V>>(results.numEntries);
        for (int i=0; i<results.numBuckets; i++) {
            for (HashPair<K,V> hp : results.buckets.get(i)) {
                arrHP.add(hp);
            }
        }
        // sort the pairs then return a list of the sorted values
        ArrayList<HashPair<K,V>> sortedPairs = mergeSort(arrHP);

        ArrayList<K> sortedKeys = new ArrayList<K>(sortedPairs.size());
        for (int i=0; i<sortedPairs.size(); i++) {
            sortedKeys.add(sortedPairs.get(i).getKey());
        }
    	return sortedKeys;
		
        //ADD CODE ABOVE HERE
    }

    private static <K, V extends Comparable<V>> ArrayList<HashPair<K,V>> mergeSort(ArrayList<HashPair<K,V>> sortMe) {

        if (sortMe.size() == 1) {
            return sortMe;
        } else {
            int mid = sortMe.size() / 2;
            ArrayList<HashPair<K,V>> list1 = new ArrayList<HashPair<K,V>>(mid);
            for (int i = 0; i < mid; i++) {
                list1.add(sortMe.get(i));
            }
            ArrayList<HashPair<K,V>> list2 = new ArrayList<HashPair<K,V>>(mid + 1);
            for (int i = mid; i < sortMe.size(); i++) {
                list2.add(sortMe.get(i));
            }
            list1 = mergeSort(list1);
            list2 = mergeSort(list2);
            return merge(list1, list2);

        }
    }

    private static <K, V extends Comparable<V>> ArrayList<HashPair<K,V>> merge(ArrayList<HashPair<K,V>> list1, ArrayList<HashPair<K,V>> list2) {

        ArrayList<HashPair<K,V>> finalProduct = new ArrayList<HashPair<K,V>>(list1.size() + list2.size());
        while (!list1.isEmpty() && !list2.isEmpty()) {
            V val1 = list1.get(0).getValue();
            V val2 = list2.get(0).getValue();
            if (val1.compareTo(val2)>=0) {
                finalProduct.add(list1.remove(0));
            } else {
                finalProduct.add(list2.remove(0));
            }
        }
        while (!list1.isEmpty()) {
            finalProduct.add(list1.remove(0));
        }
        while (!list2.isEmpty()) {
            finalProduct.add(list2.remove(0));
        }
        return finalProduct;
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        //ADD YOUR CODE BELOW HERE

        HashPair<K,V> current;
        ArrayList<HashPair<K,V>> list;
        int tracker = 0;
    	
        //ADD YOUR CODE ABOVE HERE
    	
    	/**
    	 * Expected average runtime is O(m) where m is the number of buckets
    	 */
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE

            this.list = new ArrayList<HashPair<K,V>>(numEntries+1);

            for (int i=0; i<buckets.size(); i++) {
                for (HashPair<K,V> hp : buckets.get(i)) {
                    this.list.add(hp);
                }
            }
            this.current = this.list.get(0);
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE

            if (this.current != null) {
                return true;
            }
            else return false;

            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE

            // check if there are more elements, throw exception if not

            if (this.tracker==numEntries-1) {
                HashPair<K,V> temp = this.current;
                this.current = null;
                return temp;
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            HashPair<K,V> temp = this.current;
            this.tracker++;
            this.current = this.list.get(this.tracker);
            return temp;

            //ADD YOUR CODE ABOVE HERE
        }
    }
}

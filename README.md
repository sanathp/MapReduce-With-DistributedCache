MapReduce-With-DistributedCache
===============================

Running a MapReduce Job Using a Distributed Cache . 


This Hadoop MapReduce Job counts the frequency of a list of words in the text files present in the input directory. 
A .txt file which contains the list of words whose frequency needs to be calculated is given as input.
This file is added to distributed cache so that it will be accessible to the mapper class. The mapper class parses the 
.txt file and creates a hash map with by adding all the words in the file to Hashmap , and then 
it parses the txt files in input-directory if the word is present in the hash map it creates a key-value pair 
with that word, key is the word and value is 1.So the key value pair contains only words which are 
present in word-pattern.txt file .The reducer function aggregates the key value pairs with same key and 
sums the value of all the keys . As the value is 1 the sum will give the frequency of the words present in 
word-pattern.txt.

How to Execute :

Place three files in one folder and create a jar .

Run the below hadop command :

hadoop jar WordFrequencyOfAList.jar WordFrequencyOfAList input-directory output-directory word-pattern-file-location

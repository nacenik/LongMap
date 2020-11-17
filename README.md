# long-map

Completed the development of classes that implement the LongMap interface.
They are a hash table (like a HashMap) with different storage strategies:
* LongHashMap - records with the same hashes are stored in the singly linked list for every bucket.
* LongOpenAddressingMap - all records are stored in the bucket array itself.

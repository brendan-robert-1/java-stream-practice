# Streams notes and tips

Streams don't change underlying data structure just provide a result.

Streams consist of a source followed by zero or more intermediate part1.operations piped together
and a terminal method to process the objects obtained from the source

### Operations on streams

#### Intermediate Operations
These all return an instance of a stream which allows the daisy chaining
 * Map
 * Filter
 * Sorted
 * Distinct

#### Terminal part1.operations
* **Collect**
  * simplest terminal operation that just gathers the result of the stream. Heavily uses the `Collectors` static methods to collect
* **Reduce**
  * 
* **forEach**
  * 


helpful links:
* https://www.geeksforgeeks.org/intermediate-methods-of-stream-in-java/
* https://www.geeksforgeeks.org/stream-in-java/?ref=lbp
<h2><a href="https://www.geeksforgeeks.org/search-almost-sorted-array/">Search in nearly Sorted Array</a></h2><h3>Medium</h3><hr><div><p>Given an array which is sorted, but after sorting some elements are moved to either of the adjacent positions, i.e., arr[i] may be present at arr[i+1] or arr[i-1]. Write an efficient function to search an element in this array. Basically the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
For example consider the array {2, 3, 10, 4, 40}, 4 is moved to next position and 10 is moved to previous position.</code>.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [10, 3, 40, 20, 50, 80, 70], target = 40
<strong>Output:</strong> 2
<strong>Explanation:</strong> Output is index of 40 in given array
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [10, 3, 40, 20, 50, 80, 70], target = 90
<strong>Output:</strong> -1
<strong>Explanation:</strong> -1 is returned to indicate element is not present
</pre>

Explanation:
https://www.youtube.com/watch?v=W3-KgsCVH1U


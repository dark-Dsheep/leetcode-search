### 1.两数之和
🤣🤣🤣[题目链接](https://leetcode.cn/problems/two-sum/)

**解题思路**
```text
正在编写中🤣
```
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 时间复杂度O(n), 空间复杂度O(n)
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int v = target - nums[i];
            if (map.containsKey(v)) {
                return new int[]{map.get(v), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
```
```golang
func twoSum(nums []int, target int) []int {
	mp := make(map[int]int)
	for i, val := range nums {
		v := target - val
		if j, f := mp[v]; f {
			return []int{i, j}
		}
		mp[val] = i
	}
	return []int{-1, -1}
}
```
package leetCode.subjectRang1To50;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject1 {

    private static final Logger logger = LoggerFactory.getLogger(subject1.class);

    //执行用时    内存消耗
    // 2074 ms	39 MB
    public static int[] twoSum2(int[] nums, int target) {
        List<Integer> arr = new ArrayList<Integer>();

        Stream.iterate(0, i -> i + 1).limit(nums.length).forEach(i -> {
            Stream.iterate(0, y -> y + 1).limit(nums.length).forEach(y -> {
                logger.info("{}", i);
                logger.info("{}", y);
                if (i != y && i < y) {
                    if (nums[i] + nums[y] == target) {
                        arr.add(i);
                        arr.add(y);
                    }
                }
            });

        });
        return arr.stream().mapToInt(Integer::intValue).toArray();

    }

    //执行用时    内存消耗
    //280 ms	39 MB
    public static int[] twoSum(int[] nums, int target) {
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (i != i1 && i < i1) {
                    if (nums[i] + nums[i1] == target) {
                        arr.add(i);
                        arr.add(i1);
                    }
                }
            }
        }
        return arr.stream().mapToInt(Integer::intValue).toArray();
    }

    //执行用时    内存消耗
    // 6 ms	38.8 MB
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    //执行用时    内存消耗
    // 216 ms	38.4 MB
    //个人最好
    public int[] twoSum4(int[] nums, int target) {
        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (i != i1 && i < i1) {
                    if (nums[i] + nums[i1] == target) {
                        arr[0] = i;
                        arr[1] = i1;
                        break;

                    }
                }
            }
        }
        return arr;
    }

    public static class Solution {
/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
    }

}

package novice.Sort;


/**
 * 题目描述：
 * 给定一个数组arr，和一个数num，请把小于等于num的数放在数 组的左边，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * 问题二(荷兰国旗问题)
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的 左边，等于num的数放在数组的中间，大于num的数放在数组的 右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */

/**
 * 解题思路：
 * 小于区域推着等于区域往右走，大于区域从right-1往左边添加，逼近等于区域
 * 首先分成三部分，如上图，中间是等于部分的。最开始，中间部分也就是最原始的数组。
 * less, cur, more 分别可以视为三部分的指针。
 * 所以比较a[l]和p的值，当小于时，则交换a[cur]和a[less+1]，同时小于部分的范围向右扩大1，而等于部分则向右移动1，cur也要+1；
 * 同理，当大于时，则交换a[cur]和a[more-1]，同时大于部分的范围向左扩大1，而等于部分大小就缩小1，但cur值不变；
 * 当等于时，三者范围都不变，只需要将要当前指针向右移动1即可
 */
public class QuickSort_NetherLandsFlag {
    public static int[] partition(int[] arr, int l, int r, int p) {
        int less = l - 1; // 小于num部分的索引，开始时是-1
        int more = r + 1; // 大于num部分的索引，开始时是arr.length
        int cur = l;
        while (cur < more) {// 当等于num的部分的标签小于大于部分标签more时候
            if (arr[cur] < p) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > p) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[] { less + 1, more - 1 };//这其实就是等于区域的左边界和右边界
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = generateArray();

        printArray(test);
        int[] res = partition(test, 0, test.length - 1, 1);
        printArray(test);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}

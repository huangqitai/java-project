package com.qitai.niuke;

/**
 * 现有一个 3x3 规格的 Android 智能手机锁屏程序和两个正整数 m 和 n ，请计算出使用最少m 个键和最多 n个键可以解锁该屏幕的所有有效模式总数。
 * 其中有效模式是指：
 * 1、每个模式必须连接至少m个键和最多n个键；
 * 2、所有的键都必须是不同的；
 * 3、如果在模式中连接两个连续键的行通过任何其他键，则其他键必须在模式中选择，不允许跳过非选择键（如图）；
 * 4、顺序相关，单键有效（这里可能跟部分手机不同）。
 * <p>
 * 输入：m,n
 * 代表允许解锁的最少m个键和最多n个键
 * 输出：
 * 满足m和n个键数的所有有效模式的总数
 */
public class LockScreen {
    public boolean select[] = new boolean[10];
    //写出所有无效的路线
    public String numberStr[] = {"13", "19", "17", "31", "91", "71", "39", "37", "93", "73", "79", "97", "46", "64", "82", "28"};
    //与无效路线集合下标对应，值为穿过的点
    public String pont[] = {"2", "5", "4", "2", "5", "4", "6", "5", "6", "5", "8", "8", "5", "5", "5", "5"};
    public int solution(int m, int n) {
        if (n < 0 || n - m < 0) {
            return 0;
        }
        int tot = 0;
        if (n == 9 && m < 9 || n > 9 && m < 9) {
            tot = allselect(m, n) - (4 * cal(m, 8, 1, 1, "") + 4 * cal(m, 8, 2, 1, "") + cal(m, 8, 5, 1, "")) - (4 * cal(8, 8, 1, 1, "") + 4 * cal(8, 8, 2, 1, "") + cal(8, 8, 5, 1, ""));
        } else {
            //  1 3 7 9 类似情况合并   2 4 6 8 类似情况合并   5又是一种情况
            if (n == 9 && m == 9) {
                n = 8;
                m = 8;
            }
            tot = allselect(m, n) - (4 * cal(m, n, 1, 1, "") + 4 * cal(m, n, 2, 1, "") + cal(m, n, 5, 1, ""));
        }

        return tot;
    }

    public int allselect(int m, int n) {
        int total = 0;
        int initM = m;
        int initN = n;
        int maxSelect = 9;
        if (n < m || n == 0) {
            return 0;
        }
        if (n > 9) {
            initN = 9;
        }
        while (initM > 0) {
            if (total == 0) {
                total = maxSelect;
            } else {
                total = maxSelect * total;
            }
            maxSelect = maxSelect - 1;
            initM--;
        }
        if ((m + 1) <= initN) {
            total = total + allselect(m + 1, initN);
        }
        return total;
    }

    public int cal(int m, int n, int start, int end, String str) {
        String path = str + start;
        int count = 0;
        if (end > n) {
            return 0;
        }
        if (end >= m && path.length() > 1 && end < n) {
            int j = 0;
            boolean isFound = false;
            while (j < numberStr.length && !isFound) {
                if (path.indexOf(numberStr[j]) >= 0) {
                    if (path.indexOf(pont[j]) == -1 || path.indexOf(pont[j]) > path.indexOf(numberStr[j])) {
                        isFound = true;
                        count++;
                    }
                }
                j++;
            }
        }
        select[start] = true;
        for (int i = 1; i < 10; i++) {
            if (!select[i] && end < n) {
                count = count + cal(m, n, i, end + 1, path);
            }
            if (!select[i] && end == n && n > 1) {
                int j = 0;
                boolean isFound = false;
                while (j < numberStr.length && isFound == false) {
                    if (path.indexOf(numberStr[j]) >= 0) {
                        if (path.indexOf(pont[j]) == -1 || path.indexOf(pont[j]) > path.indexOf(numberStr[j])) {
                            count++;
                            select[start] = false;
                            return count;
                        }
                    }
                    j++;
                }
            }
        }
        select[start] = false;
        return count;
    }
}

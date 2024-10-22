package algorithms.lc;

public class Medium {
     // 1041. Robot Bounded in Circle (Medium) [T = O(n), S = O(1)]
    public static boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int facing = 0; // N E S W
        int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // N E S W
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'L') {
                facing = (facing + 3) % 4; // Modular arithmetic: x % n in range (0, n-1)
            } else if (instructions.charAt(i) == 'R') {
                facing = (facing + 1) % 4;
            } else {
                x += directions[facing][0];
                y += directions[facing][1];
            }
        }
        if ((x == 0 && y == 0) || facing != 0)
            return true; // Bounded circle
        return false;
    }

    // 91. Decode Ways (Medium) [T = O(n), S = O(n) (due to DP array size)]
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        // Dynamic programming: dynamically build up to the result
        int[] array = new int[s.length() + 1]; // 0 to s.length inclusive
        // Example: 226 => 1 1 2 3
        array[0] = 1; // DP convention
        array[1] = 1; // 1 way to decode a single number
        for (int i = 2; i <= s.length(); i++) {
            int singleDigit = Integer.parseInt(s.substring(i - 1, i));
            int doubleDigit = Integer.parseInt(s.substring(i - 2, i));
            if (singleDigit >= 1 && singleDigit <= 9) {
                array[i] += array[i - 1];
            }
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                array[i] += array[i - 2];
            }
        }
        return array[array.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGGLLGG"));
        System.out.println(numDecodings("226"));
    }
}

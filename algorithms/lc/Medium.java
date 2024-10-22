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

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGGLLGG"));
    }
}

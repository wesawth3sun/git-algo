package test;

import java.util.HashSet;

public class Poketmon {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int answer = 0;
        HashSet<Integer> poketmons = new HashSet<>();
        for (int num : nums) {
            poketmons.add(num);
        }
        int pick = nums.length / 2;

        if (poketmons.size() < pick) {
            for (int i = 0; i < poketmons.size(); i++) {
                answer++;
            }
        } else {
            for (int i = 0; i < pick; i++) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

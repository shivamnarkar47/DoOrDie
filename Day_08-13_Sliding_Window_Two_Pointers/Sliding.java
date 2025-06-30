import java.util.*;
import java.lang.*;

public class Sliding {

	public static String minWindow(String s, String t) {
		if (s.length() == 0 || t.length() == 0)
			return "";

		Map<Character, Integer> tMap = new HashMap<>();

		for (char c : t.toCharArray()) {
			tMap.put(c, tMap.getOrDefault(c, 0) + 1);
		}

		int required = tMap.size();
		int left = 0, right = 0, formed = 0;
		Map<Character, Integer> windowCounts = new HashMap<>();

		int[] ans = { -1, 0, 0 };

		while (right < s.length()) {
			char c = s.charAt(right);
			windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
			if (tMap.containsKey(c) && windowCounts.get(c).intValue() == tMap.get(c).intValue()) {
				formed++;
			}

			while (left <= right && formed == required) {
				if (ans[0] == -1 || right - left + 1 < ans[0]) {
					ans[0] = right - left + 1;
					ans[1] = left;
					ans[2] = right;
				}

				char l = s.charAt(left);
				windowCounts.put(l, windowCounts.get(l)-1);

				if (tMap.containsKey(l) && windowCounts.get(l).intValue() < tMap.get(l).intValue()) {
					formed--;
				}

				left++;
			}

			right++;
		}

		return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);

	}

	public static int lengthOfLongestSubstring(String s) {
		Set<Character> seen = new HashSet<>();
		int left = 0, maxLen = 0;
		for (int right = 0; right < s.length(); right++) {
			while (seen.contains(s.charAt(right))) {
				seen.remove(s.charAt(left));
				left++;
			}

			seen.add(s.charAt(right));

			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}

	public static void main(String args[]) {
//		String s = "abcabcbb";
//		System.out.println("Longest substring length: " + lengthOfLongestSubstring(s));

		String s = "ADOBECODEBANC";
		String t = "ABC";
		System.out.println("Minimum Window: " + minWindow(s, t));
	}
}

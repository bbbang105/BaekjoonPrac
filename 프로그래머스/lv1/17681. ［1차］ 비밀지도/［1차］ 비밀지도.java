class Solution {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < arr1.length; i++) {
            int n1 = arr1[i]; int n2 = arr2[i];
            int binary_num = (int) Math.pow(2,n-1);
            int index = 0;
            String s = "";
            while (n1 != 0 || n2 != 0 || index < n) {
                boolean flag1 = false; boolean flag2 = false;
                // 각각 벽인지 확인
                if (n1 >= binary_num) {
                    n1 -= binary_num;
                    flag1 = true;
                }
                if (n2 >= binary_num) {
                    n2 -= binary_num;
                    flag2 = true;
                }
                // 둘 중 하나라도 벽이라면, 문자열 추가
                if (flag1 || flag2) {
                    s += "#";
                } else {
                    s += " ";
                }
                // 인덱스, 이진수 갱신
                index += 1;
                binary_num /= 2;
            }
            answer[i] = s; // 암호 추가
        }
        return answer;
    }
}
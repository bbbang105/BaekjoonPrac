import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        String[][] record = new String[files.length][3]; // HEAD, NUMBER, INDEX 순
        
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            StringBuilder sb = new StringBuilder();
            boolean headComplete = false;
            for (int j = 0; j < file.length(); j++) {
                char c = file.charAt(j);
                if (!headComplete) {
                    if (!Character.isDigit(c)) sb.append(c);
                    else {
                        headComplete = true;
                        record[i][0] = sb.toString(); // HEAD
                        sb.setLength(0);
                        sb.append(c);
                    }
                } else {
                    // NUMBER가 5자리를 넘어가거나, TAIL로 넘어가는 경우
                    if (sb.length() == 5 || !Character.isDigit(c)) {
                        record[i][1] = sb.toString(); // NUMBER
                        break;
                    }
                    sb.append(c);
                }
                // 마지막이 숫자로 끝나는 경우
                if (j == file.length() - 1) {
                        record[i][1] = sb.toString(); // NUMBER
                }
            }
            record[i][2] = String.valueOf(i); // INDEX
        }
        
        Arrays.sort(record, (a, b) -> {
            // HEAD 비교, 대소문자 구분 없이
            int headResult = a[0].toLowerCase().compareTo(b[0].toLowerCase());
            if (headResult != 0) return headResult; // HEAD가 다르면, HEAD 비교 결과 반환
            
            // NUMBER 비교, 정수 값으로 변환하여 비교
            int num1 = Integer.parseInt(a[1]);
            int num2 = Integer.parseInt(b[1]);
            return Integer.compare(num1, num2); // NUMBER가 다르면, NUMBER 비교 결과 반환
        });
        
        String[] answer = new String[files.length];
        for (int k = 0; k < files.length; k++) {
            answer[k] = files[Integer.parseInt(record[k][2])];
        }
        
        return answer;    
    }
}
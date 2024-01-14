import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            if (!map.keySet().contains(genre)) {
                // 처음 만나는 장르인 경우
                map.put(genre, List.of(plays[i], i, -1));
            } else {
                // 이미 저장된 장르인 경우
                List<Integer> genreInfos = map.get(genre);
                int totalPlays = genreInfos.get(0);
                int topOneIndex = genreInfos.get(1);
                int topTwoIndex = genreInfos.get(2);

                if (topTwoIndex == -1) {
                    if (plays[topOneIndex] > plays[i]) {
                        topTwoIndex = i;
                    } else {
                        topTwoIndex = topOneIndex;
                        topOneIndex = i;
                    }
                } else {
                    if (plays[topTwoIndex] < plays[i]) {
                        topTwoIndex = i;
                    }
                    if (plays[topOneIndex] < plays[i]) {
                        topTwoIndex = topOneIndex;
                        topOneIndex = i;
                    }
                }
                // 해당 장르 재생횟수 +
                totalPlays += plays[i];
                // 장르 정보 업데이트
                map.put(genre, List.of(totalPlays, topOneIndex, topTwoIndex));
            }
        }
        // 재생횟수가 많은 장르순으로 내림차순 정렬
        List<String> genreKeys = new ArrayList<>(map.keySet());
        Collections.sort(genreKeys, (o1, o2) -> map.get(o2).get(0).compareTo(map.get(o1).get(0)));

        // 최대 두개만 수록
        for (String genre : genreKeys) {
            List<Integer> genreInfos = map.get(genre);
            answer.add(genreInfos.get(1));
            if (genreInfos.get(2) != -1) answer.add(genreInfos.get(2));
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
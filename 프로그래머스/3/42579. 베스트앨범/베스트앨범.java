import java.util.*;
import java.io.*;

/**
[조건]
1. 노래 수록 기준은 아래와 같다.
    1-1. 장르 중에서, 재생 횟수가 가장 많은 순 -> 장르 정렬
    1-2. 장르 내에서, 재생 횟수가 가장 많은 순 -> 노래 정렬
    1-3. 장르 내에서, 재생 횟수가 동일하다면 고유 번호를 기준으로 오름차순 -> 노래 정렬
2. 장르 별로 노래를 최대 2개까지 모아서 배열에 저장한다. 하나의 배열로 리턴한다.
3. 고유번호는 인덱스와 동일하다.
4. 장르와 플레이 배열의 길이는 1이상 10,000이하이다.
5. 장르 종류는 100개 미만이다.
6. 장르에 속한 곳이 1개라면, 1개만 택한다.
7. 모든 장르의 재생 횟수 합은 서로 다르다.(= 추가적인 정렬 조건이 필요 없음)
*/
class Solution {
    
    private static Map<String, Integer> genreMap;     // 장르별 재생 횟수 합 저장
    private static Map<String, List<Music>> musicMap; // 장르별 음악 정보 저장
    
    public int[] solution(String[] genres, int[] plays) {
        genreMap = new HashMap<>();
        musicMap = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        
        String genre;
        int countOfPlay;
        for (int index = 0; index < genres.length; index++) {
            genre = genres[index];
            countOfPlay = plays[index];
            genreMap.put(genre, genreMap.getOrDefault(genre, 0) + countOfPlay);
            musicMap.putIfAbsent(genre, new ArrayList<>());
            musicMap.get(genre).add(new Music(countOfPlay, index));
        }
        
        List<String> sortedGenres = new ArrayList<>(genreMap.keySet());
        Collections.sort(sortedGenres, (o1, o2) -> genreMap.get(o2).compareTo(genreMap.get(o1)));
        List<Music> musics;
        for (String key : sortedGenres) {
            musics = musicMap.get(key);
            Collections.sort(musics);
            answer.add(musics.get(0).index);
            if (musics.size() > 1) {
                answer.add(musics.get(1).index);
            }
        }
        
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    /**
    * 음악 정보를 담은 클래스.
    */
    static class Music implements Comparable<Music> {
        int countOfPlay;
        int index;
        
        Music(int countOfPlay, int index) {
            this.countOfPlay = countOfPlay;
            this.index = index;
        }
        
        @Override
        public int compareTo(Music m) {
            if (this.countOfPlay == m.countOfPlay) {
                return this.index - m.index;
            }
            return m.countOfPlay - this.countOfPlay;
        } 
    }
}

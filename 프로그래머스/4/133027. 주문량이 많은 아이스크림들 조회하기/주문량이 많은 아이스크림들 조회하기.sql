WITH FIRST_HALF_PLUS_JULY AS 
(
SELECT F.FLAVOR, (F.TOTAL_ORDER + J.TOTAL_ORDER) AS PLUS
FROM FIRST_HALF F
LEFT JOIN JULY J ON F.FLAVOR = J.FLAVOR
)

SELECT FLAVOR
FROM FIRST_HALF_PLUS_JULY
GROUP BY FLAVOR
ORDER BY (SUM(PLUS)) DESC LIMIT 3;
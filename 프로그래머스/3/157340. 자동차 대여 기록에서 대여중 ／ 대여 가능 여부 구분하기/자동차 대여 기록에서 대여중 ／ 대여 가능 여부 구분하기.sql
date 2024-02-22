SELECT CAR_ID, 
IF(SUM(IF('2022-10-16' BETWEEN START_DATE AND END_DATE, '1', '0')) = 0, '대여 가능', '대여중')
AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;
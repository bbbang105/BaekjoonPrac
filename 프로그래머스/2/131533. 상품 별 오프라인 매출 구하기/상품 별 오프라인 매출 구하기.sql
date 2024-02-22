SELECT PRODUCT_CODE, SUM(SALES_AMOUNT) * PRICE AS SALES
FROM OFFLINE_SALE 
LEFT JOIN PRODUCT USING (PRODUCT_ID)
GROUP BY PRODUCT_CODE
ORDER BY SALES DESC, PRODUCT_CODE ASC;
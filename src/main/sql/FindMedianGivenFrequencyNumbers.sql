SELECT AVG(Number) median
FROM (
SELECT n1.Number
FROM Numbers n1, Numbers n2
GROUP BY n1.Number
HAVING ABS(SUM(SIGN(n2.number-n1.number)*n2.Frequency)) <= MAX(n1.Frequency)
    ) t
--Write a SQL query to find the cancellation rate of requests with unbanned users
--(both client and driver must not be banned) each day between "2013-10-01" and "2013-10-03".



select Request_at as Day,
ROUND(SUM(CASE WHEN Status = 'completed' THEN 0 ELSE 1 END) / count(*), 2) as 'Cancellation rate'
FROM Trips t
LEFT JOIN Users u
ON t.Client_Id = u.Users_Id
LEFT JOIN Users us
ON t.Driver_Id = us.Users_Id
WHERE t.Request_at BETWEEN '2013-10-01' AND '2013-10-03' AND u.Banned='NO' and us.banned= 'no'
GROUP BY t.Request_at
DELETE FROM restarea;



INSERT INTO RestArea
			(ra_code, ra_name, ra_routeNo, ra_routeName, ra_destination, ra_xValue, ra_yValue)
		VALUES
			('1', 'a', 'a', 'a', 'a', 'a', 'a');

SELECT * FROM restarea WHERE RA_ROUTENAME ='경부선';

SELECT * FROM GASSTATION;

SELECT * FROM FOODMENU WHERE FM_NAME ='자장면';


SELECT RestArea.*
FROM RestArea
WHERE ra_routeName = '경부선' AND ra_DESTINATION ='서울'
ORDER BY ra_code
;
SELECT *
FROM 
(
SELECT ROWNUM rnum, a.*
FROM
(
SELECT RestArea.*, ra_like.ra_like_cnt
FROM RestArea
LEFT OUTER JOIN 
(SELECT ra_code, COUNT(*) AS ra_like_cnt
FROM ra_like
GROUP BY ra_code) ra_like ON restarea.ra_code=ra_like.ra_code
ORDER BY ra_like.ra_like_cnt DESC, RestArea.ra_code
) a
WHERE ROWNUM< 10+10
)
WHERE rnum >=11
;

SELECT *
FROM RA_LIKE rl ;

SELECT ra_code, COUNT(*) AS ra_like_cnt
FROM ra_like
GROUP BY ra_code;

--경부선 y 서울/부산/양방향
--광주대구선 x 고서/옥포 3
--남해2지선 x 서부산  1
--남해선 x 순천/부산
--동해선 y 부산(하)/포항(상) 삼척(동해)/속초
--무안광주선 x 광주/무안  2
--서울양양선 y 서울/양양 2
--서천공주선 y 서천/공주 4
--서해안선 y 서울/목포/양방향



SELECT *
FROM(
	SELECT
		ROWNUM rnum, a.*
	FROM (
		SELECT
			RestArea.*
		FROM 
			RestArea
				WHERE 
					ra_routeName='경부선'
		ORDER BY 
			RestArea.ra_code
	) a
	WHERE ROWNUM < 1 + 10
)
WHERE rnum >=1
;

SELECT *
FROM(
	SELECT ROWNUM rnum, a.*
	FROM(
		SELECT
			gs.*, ra.ra_routeName, ra.ra_destination
		FROM
			GasStation gs
			LEFT OUTER JOIN
				RestArea ra ON gs.ra_code=ra.ra_code
			LEFT OUTER JOIN (
				SELECT gs_code, COUNT(*) AS gs_like_cnt
				FROM gs_like
				GROUP BY gs_code
				) gs_like ON gs.gs_code=gs_like.gs_code
		WHERE 
			ra.ra_routeName='경부선'
			 AND ra.ra_destination = '부산'
		ORDER BY
			gs.ra_code
	) a
	WHERE ROWNUM <1+10
)
WHERE rnum >=1
;



SELECT *
FROM(
	SELECT ROWNUM rnum, a.*
	FROM(
		SELECT
			fm.*, ra.ra_routeName, ra.ra_destination
		FROM
			FoodMenu fm
			LEFT OUTER JOIN
				RestArea ra ON fm.ra_code=ra.ra_code
			LEFT OUTER JOIN (
				SELECT fm_id, COUNT(*) AS fm_like_cnt
				FROM fm_like
				GROUP BY fm_id
				) fm_like ON fm.fm_id=fm_like.fm_id
--		WHERE 
--			ra.ra_routeName='ALL'
--			 AND ra.ra_destination = 'ALL'
		ORDER BY
			fm.fm_price,
			fm.ra_code
	) a
--	WHERE ROWNUM <1+10
)
--WHERE rnum >=1
;



SELECT RA_DESTINATION 
FROM RESTAREA
WHERE RA_ROUTENAME ='경부선'
GROUP BY RA_DESTINATION ;


SELECT *
FROM(
	SELECT ROWNUM rnum, a.*
	FROM(
		SELECT
			fm.*, ra.ra_routeName, ra.ra_destination
		FROM
			FoodMenu fm
			LEFT OUTER JOIN
				RestArea ra ON fm.ra_code=ra.ra_code
		WHERE 
			ra.ra_routeName='경부선'
		ORDER BY
			fm.ra_code, fm.fm_id
	) a
--	WHERE ROWNUM < 1+10
)
WHERE rnum >= 1
;

SELECT *
FROM RESTAREA
WHERE ra_code='A00001'
;


SELECT *
FROM usermember;

SELECT *
FROM auth;

SELECT *
FROM post;

SELECT RestArea.*, a.ra_like_cnt   
FROM RestArea LEFT OUTER JOIN(     
	SELECT ra_code, COUNT(*) AS ra_like_cnt    
	FROM ra_like     
	GROUP BY ra_code    
) a   ON restarea.ra_code=a.ra_code
WHERE     RestArea.ra_code='A00001'
;


SELECT COUNT(*)
FROM  ra_LIKE
WHERE ra_code='A00001'
GROUP BY ra_code;

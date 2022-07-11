--# 서브쿼리 사용
SET NOCOUNT ON	
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
-- 학습생 배정 중지 로직 시작
BEGIN TRAN
--TEST CASE
DECLARE @EMPLYEEID varchar(20) = 'tclass0536'

--TEST
DECLARE @MIN_ORDERNO INT

-- 1. MIN 13%
SELECT @MIN_ORDERNO=MIN(OrderNo) 
FROM SOURCE_DISTRIBUTION_QUEUE TCSDO   
INNER JOIN SOURCE_DISTRIBUTION_QUEUE_Weight TCSDW ON TCSDO.EmployeeId=TCSDW.EmployeeId       
WHERE  TCSDW.Weight > 0

-- 2. UPDATE 73%
UPDATE NXT
SET
	NXT.LastReceiveFlag1 = ISNULL(NXT.LastReceiveFlag1, PRE.LastReceiveFlag1) 
	, NXT.LastReceiveFlag2 = ISNULL(NXT.LastReceiveFlag2, PRE.LastReceiveFlag2) 
	, NXT.LastReceiveFlag3 = ISNULL(NXT.LastReceiveFlag3, PRE.LastReceiveFlag3) 
	, NXT.LastReceiveFlag6 = ISNULL(NXT.LastReceiveFlag6, PRE.LastReceiveFlag6) 
	, NXT.LastReceiveFlag_etc = ISNULL(NXT.LastReceiveFlag_etc, PRE.LastReceiveFlag_etc)
FROM SOURCE_DISTRIBUTION_QUEUE NXT
INNER JOIN (
	SELECT OrderNo AS PreNo, LEAD(OrderNo, 1, @MIN_ORDERNO) OVER(ORDER BY ORDERNO) AS NextNo
	FROM SOURCE_DISTRIBUTION_QUEUE TCSDO
	INNER JOIN SOURCE_DISTRIBUTION_QUEUE_Weight TCSDW ON TCSDO.EmployeeId=TCSDW.EmployeeId       
	WHERE  TCSDW.Weight > 0
) TMP ON TMP.NextNo = NXT.OrderNo
INNER JOIN SOURCE_DISTRIBUTION_QUEUE PRE ON PRE.OrderNo = TMP.PreNo AND PRE.EmployeeId = @EMPLYEEID 

-- 3. DELETE 14%
DELETE FROM SOURCE_DISTRIBUTION_QUEUE WHERE EmployeeId = @EMPLYEEID

ROLLBACK
SET NOCOUNT OFF	

--------------------------------------------------------------------------------------------
//
--------------------------------------------------------------------------------------------
-- # 순차 조회
SET NOCOUNT ON	
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
-- 학습생 배정 중지 로직 시작
BEGIN TRAN
--SELECT * FROM SOURCE_DISTRIBUTION_QUEUE

--TEST CASE
DECLARE @EMPLYEEID varchar(20) = 'tclass0536'

--TEST
DECLARE @MIN_ORDERNO INT

-- 1. MIN 16%
SELECT @MIN_ORDERNO=MIN(OrderNo) 
FROM SOURCE_DISTRIBUTION_QUEUE TCSDO   
INNER JOIN SOURCE_DISTRIBUTION_QUEUE_Weight TCSDW ON TCSDO.EmployeeId=TCSDW.EmployeeId       
WHERE  TCSDW.Weight > 0

-- 2. SET NxtNo 30%
DECLARE @NxtNo INT

SELECT @NxtNo = WithNxt.NextNo
FROM(
	SELECT TCSDO.EmployeeId, LEAD(TCSDO.OrderNo, 1, @MIN_ORDERNO) OVER(ORDER BY ORDERNO) AS NextNo
	FROM SOURCE_DISTRIBUTION_QUEUE TCSDO
	INNER JOIN SOURCE_DISTRIBUTION_QUEUE_Weight TCSDW ON TCSDO.EmployeeId=TCSDW.EmployeeId       
	WHERE  TCSDW.Weight > 0
) WithNxt
WHERE WithNxt.EmployeeId = @EMPLYEEID
-- 2. UPDATE 37%
UPDATE NXT
SET
	NXT.LastReceiveFlag1 = ISNULL(NXT.LastReceiveFlag1, PRE.LastReceiveFlag1) 
	, NXT.LastReceiveFlag2 = ISNULL(NXT.LastReceiveFlag2, PRE.LastReceiveFlag2) 
	, NXT.LastReceiveFlag3 = ISNULL(NXT.LastReceiveFlag3, PRE.LastReceiveFlag3) 
	, NXT.LastReceiveFlag6 = ISNULL(NXT.LastReceiveFlag6, PRE.LastReceiveFlag6) 
	, NXT.LastReceiveFlag_etc = ISNULL(NXT.LastReceiveFlag_etc, PRE.LastReceiveFlag_etc)
FROM SOURCE_DISTRIBUTION_QUEUE NXT
INNER JOIN SOURCE_DISTRIBUTION_QUEUE PRE ON PRE.EmployeeId = @EMPLYEEID 
WHERE NXT.OrderNo = @NxtNo
-- 3. DELETE 17%
DELETE FROM SOURCE_DISTRIBUTION_QUEUE WHERE EmployeeId = @EMPLYEEID


--SELECT * FROM SOURCE_DISTRIBUTION_QUEUE
ROLLBACK
SET NOCOUNT OFF

-- => 과정의 %는 전체 실행계획에서의 비용 비율을 의미한다.
-- => 서브쿼리를 지양하고, 변수/cte/임시테이블을 써야하는 이유이다!
# API

Controller기준



#### UserController

| controller      | URL         | HTTP   | 설명             | params                                                       | Res                                                          |
| --------------- | ----------- | ------ | ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| api/v1/user     | /signup     | post   | 회원가입         | {'userEmail':email,<br />"userId":String,<br />"userName":String<br />"userPw":String} | {"statusCode":200}                                           |
|                 | /login      | post   | 로그인           | "{"userId":String,<br />"userPw":String}"                    | {"statusCode":200}                                           |
|                 | /logout     | post   | 로그아웃         |                                                              | {"statusCode":200}                                           |
|                 | /           | update | 회원정보 수정    | Token<br />{'userEmail':email,<br />"userId":String,<br />"userName":String<br />"userPw":String} | {"statusCode":200}                                           |
|                 | /           | get    | 회원정보 조회    |                                                              | {"statusCode":200<br />'userEmail':email,<br />"userId":String,<br />"userName":String<br />user_profile:String<br />} |
|                 | /findiid    | get    | 아이디 찾기      | {"userName":String<br />"userId":String}                     | {"statusCode":200}                                           |
|                 | /pw         | get    | 비밀번호인증page | Token                                                        | {"statusCode":200}                                           |
|                 | /checkemail | get    | 이메일 중복검사  | {"userEmail":String}                                         | {"statusCode":200}                                           |
| 후순위          | /findpw     | get    | 비밀번호 초기화  | Token<br />{userId:string<br />userEmail:String<br />userName:String} | {"statusCode":200}                                           |
|                 | /pw         | put    | 비밀번호 변경    | Token<br />{"pastPw":String<br />"newPw":String}             |                                                              |
| user_delete변경 | /           | put    | 회원탈퇴         | Token                                                        | {"statusCode":200}                                           |



#### ResumeController

모두 Token 필요

자기소개서 : c, r, u, d

| controller    | URL       | HTTP | 설명        | params                                                       | Res                                                          |
| ------------- | --------- | ---- | ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| api/v1/resume | /         | get  | 조회        |                                                              | {"statusCode":200<br />[itemNo:<br />[detailQuestion,<br />detailContents],<br />]} |
|               | /         | put  | 수정        | {[itemNo:<br />[detailQuestion,<br />detailContents],<br />]} | "statusCode":200                                             |
|               | /         | post | 생성        | {[itemNo:<br />[detailQuestion,<br />detailContents],<br />]} | "statusCode":200                                             |
|               | /feedback | post | 피드백 생성 | {resumeNo:Long<br />itemNo:Long<br />itemContents: String<br />writeNo:Long<br />stdNo:Long} | {"statusCode":200}                                           |
|               |           | put  | 피드백 수정 | {resumeNo:Long<br />itemNo:Long<br />itemContents: String<br />writeNo:Long<br />stdNo:Long} | {"statusCode":200}                                           |
|               |           | put  | 피드백 삭제 |                                                              | {"statusCode":200}                                           |
|               |           | get  | 피드백 조회 |                                                              | {resumeNo:Long<br />itemNo:Long<br />itemContents: String<br />writeNo:Long<br />stdNo:Long} |





#### StudyController

| controller   | URL         | HTTP | 설명        | params                                                       | Res                                |
| ------------ | ----------- | ---- | ----------- | ------------------------------------------------------------ | ---------------------------------- |
| api/v1/study | /join       | post | 스터디 신청 | {joinType:enum<br />stdNo:Long<br />userNo:Long<br />userName:String<br />userProfile:String} |                                    |
|              | /joinReject | put  | 참석 여부   | {joinNo:Long<br />}                                          | {join:bool<br />userNo:Long<br />} |
|              |             | get  | 참석 조회   | {stdNo:Long}                                                 |                                    |
|              |             |      |             |                                                              |                                    |
|              |             |      |             |                                                              |                                    |
















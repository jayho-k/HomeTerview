# API

Controller기준

test2

#### UserController

| controller      | URL         | HTTP   | 설명             | params                                                       | Res                                                          |
| --------------- | ----------- | ------ | ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| api/v1/user     | /join       | post   | 회원가입         | {'userEmail':email,<br />"user":String,<br />"userName":String<br />"userPw":String} | {"statusCode":200}                                           |
|                 | /login      | post   | 로그인           | "{"userId":String,<br />"userPw":String}"                    | {"statusCode":200}                                           |
|                 | /logout     | post   | 로그아웃         |                                                              | {"statusCode":200}                                           |
|                 | /           | update | 회원정보 수정    | Token<br />{'userEmail':email,<br />"userId":String,<br />"userName":String<br />"userPw":String} | {"statusCode":200}                                           |
|                 | /           | get    | 회원정보 조회    |                                                              | {"statusCode":200<br />'userEmail':email,<br />"userId":String,<br />"userName":String<br />user_profile:String<br />} |
|                 | /findiid    | get    | 아이디 찾기      | {"userName":String<br />"userId":String}                     | {"statusCode":200}                                           |
|                 | /pw         | get    | 비밀번호인증page | Token                                                        | {"statusCode":200}                                           |
|                 | /checkemail | get    | 이메일 중복검사  | {"userEmail":String}                                         | {"statusCode":200}                                           |
| 후순위          | /findpw     | get    | 비밀번호 초기화  | Token<br />{userId:string<br />userEmail:String<br />userName:String} | {"statusCode":200}                                           |
|                 | /pw         | put    | 비밀번호 변경    | Token<br />{"pastPw":String<br />"newPw":String}             | "statusCode":200                                             |
| user_delete변경 | /           | put    | 회원탈퇴         | Token                                                        | {"statusCode":200}                                           |



#### ResumeController

모두 Token 필요

자기소개서 : c, r, u, d

| controller    | URL                   | HTTP | 설명        | params                                                       | Res                                                          |
| ------------- | --------------------- | ---- | ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| api/v1/resume | /                     | get  | 전체 조회   | {user_no}                                                    | {"statusCode":200<br />[itemNo:<br />resume_title,<br />resume_delete]} |
|               | /{resume_no}          | get  | 상세 조회   |                                                              | {"statusCode":200<br />[itemNo:<br />[detailQuestion,<br />detailContents,<br />],<br />]} |
|               | /                     | put  | 수정        | {[itemNo:<br />[detailQuestion,<br />detailContents<br />detailContents],<br />]} | {[itemNo:<br />[detailQuestion,<br />detailContents<br />detailContents],<br />]} |
|               | /                     | post | 생성        | {[itemNo:<br />[detailQuestion,<br />detailContents],<br />]} | "statusCode":200                                             |
|               | /{resume_no}          | put  | 삭제        | {itemNo<br />resume_delete}                                  | {"statusCode":200<br />[itemNo:<br />resume_title,<br />resume_delete]} |
|               | /feedback             | post | 피드백 생성 | {resumeNo:Long<br />itemNo:Long<br />itemContents: String<br />writeNo:Long<br />stdNo:Long} | {"statusCode":200}                                           |
|               | /feedback/{quest_no}/ | put  | 피드백 수정 | {resumeNo:Long<br />itemNo:Long<br />itemContents: String<br />writeNo:Long<br />stdNo:Long} | {"statusCode":200}                                           |
|               | /feedback/{quest_no}  | put  | 피드백 삭제 |                                                              | {"statusCode":200}                                           |
|               | /feedback/{resumeNo}  | get  | 피드백 조회 |                                                              | {"statusCode":200<br />resumeNo:Long<br />itemNo:Long<br />itemContents: String<br />writeNo:Long<br />stdNo:Long} |





#### StudyController

| controller   | URL         | HTTP         | 설명            | params                                                       | Res                                                          |
| ------------ | ----------- | ------------ | --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| api/v1/study | /join       | post         | 스터디 신청     | {joinType:enum<br />stdNo:Long<br />userNo:Long<br />userName:String<br />userProfile:String} | {"statusCode":200}                                           |
|              | /joinReject | put          | 참석 여부       | {joinNo:Long<br />}                                          | {"statusCode":200<br />join:bool<br />userNo:Long<br />}     |
|              | /joinReject | get          | 참석 조회       |                                                              | {user_no:Long<br />}                                         |
|              | /{std_no}   | get          | 스터디 상세조회 | token<br />{user_no}                                         | {"statusCode":200<br />std_no:[Long<br />std_name<br />std_detail<br />std_img<br />std_type<br />com_name<br />start_date<br />end_date<br />std_day<br />std_limit<br />std_notation]} |
|              | /           | get          | 스터디 전체조회 | token                                                        | token<br />{"statusCode":200<br />std_no:[Long<br />std_name<br />std_detail<br />std_img<br />std_type<br />com_name<br />start_date<br />end_date<br />std_day<br />std_limit<br />std_notation]} |
|              |             | /{std_no}put | 스터디 정보수정 | {std_no:Long<br />std_name<br />std_detail<br />std_img<br />std_type<br />com_name<br />start_date<br />end_date<br />std_day<br />std_limit<br />std_notation} | {"statusCode":200<br />std_no:Long<br />std_name<br />std_detail<br />std_img<br />std_type<br />com_name<br />start_date<br />end_date<br />std_day<br />std_limit<br />std_notation} |
|              | /           | post         | 스터디 생성     | {std_no:Long<br />std_name<br />std_detail<br />std_img<br />std_type<br />com_name<br />start_date<br />end_date<br />std_day<br />std_limit<br />std_notation} | "statusCode":200                                             |
|              |             |              |                 |                                                              |                                                              |



#### Recruit

| controller     | URL           | HTTP | 설명            | params                                                       | Res                                                          |
| -------------- | ------------- | ---- | --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| api/v1/recruit | /             | post | 모집글 생성     | token<br />{recruit_no<br />recruit_title<br />std_name<br />std_detail<br />std_category<br />com_name<br />start_date<br />end_data<br />std_day<br />std_limit<br />recriot_statis<br />} | "statusCode":200                                             |
|                | /{recruit_no} | get  | 모집글 상세조회 | token                                                        | {"statusCode":200<br />recruit_no<br />recruit_title<br />std_name<br />std_detail<br />std_category<br />com_name<br />start_date<br />end_data<br />std_day<br />std_limit<br />recruit_status<br />} |
|                | /             | get  | 모집글 전체조회 | token                                                        | {"statusCode":200<br />recruits:[<br />{recruit_no:<br />[std_img,<br />std_name<br />start_date<br />end_date<br />std_day<br />std_limit<br />recruit_status]}]} |
|                | /{recruit_no} | put  | 모집글 수정     | token<br />{recruit_no<br />recruit_title<br />std_name<br />std_detail<br />std_category<br />com_name<br />start_date<br />end_data<br />std_day<br />std_limit<br />recriot_statis<br />} | "statusCode":200                                             |
|                |               |      |                 |                                                              |                                                              |












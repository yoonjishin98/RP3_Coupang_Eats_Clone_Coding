# 쿠팡이츠 클론코딩 프로젝트
- 기간: 2021.08 ~ 2021.08 (2주)
- 설명: 쿠팡이츠 어플을 클론코딩한 프로젝트입니다.
- 범위: 스플래시 화면부터 음식점 상세화면, 주소 설정 화면, 그리고 결제 프로세스까지 구현 

#### ✔️ **개발 플랫폼** : Android Studio <br/>
#### ✔️ **개발 언어** : Kotlin <br/>
#### ✔️ **서버 통신** : Retrofit2, OkHttp3 라이브러리를 통해 Rest API 연동 <br/>
#### ✔️ **디자인 패턴** : MVP <br/>


-----
### 시연영상 <br/>
- 메인화면(로그인 전/비회원용 API 적용: 거리값X) <br/>
<img src="https://user-images.githubusercontent.com/44793355/133710514-e7dfddd5-b855-4f7e-a864-f36c473f3e8a.gif"  width="20%" height="20%"/> <br/>
_치타배달 여부, 쿠폰 여부, 무료배달 여부, 리뷰 별점 등에 따라 뷰의 visibility 조절_

- 메인화면(로그인 후/회원용 API 적용: 거리값0) <br/>
<img src="https://user-images.githubusercontent.com/44793355/133713920-400cfb42-5279-4285-b75d-3916f4bf6b6c.gif"  width="70%" height="70%"/> <br/>
_치타배달 여부, 쿠폰 여부, 무료배달 여부,리뷰 별점 등에 따라 뷰의 visibility 조절_

- 카카오 도로명 주소 API 사용 <br/>
<img src="https://user-images.githubusercontent.com/44793355/133712867-ea143aef-17d4-48c4-9eac-ea17cd5dd945.png"  width="20%" height="20%"/> <br/>

- 음식점 상세 화면: Coordinator Layout - Appbar Layout - Collapsing Toolbar Layout 사용 <br/>
<img src="https://user-images.githubusercontent.com/44793355/133714663-0399928a-3150-4772-ade9-e2ec30de6a73.gif"  width="70%" height="70%"/> <br/>
